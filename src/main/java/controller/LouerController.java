package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LouerController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private TextField txt_search_livre;

    @FXML
    private TextField txt_ncin;

    @FXML
    private TextField txt_searchAdh;

    @FXML
    private TextField txt_nomlivre;

    @FXML
    private TextField txt_maisonedition;

    @FXML
    private TextField txt_auteur;

    @FXML
    private TextField txt_telephoneadh;

    @FXML
    private TextField txt_nomadh;

    @FXML
    private DatePicker datedebut;

    @FXML
    private ImageView imageview;

    @FXML
    private DatePicker datefin;

    @FXML
    private TextField txt_periode;

    public Boolean isBetween(java.sql.Date my_date, java.sql.Date my_debut, java.sql.Date my_fin){
        return (my_date.equals(my_debut) || my_date.after(my_debut)) && (my_date.equals(my_fin) || my_date.before(my_fin));
    }
    public Boolean isOut(java.sql.Date datedebut, java.sql.Date datefin, java.sql.Date my_debut, java.sql.Date my_fin){
        return (datedebut.before(my_debut) && datefin.after(my_fin));

    }

    @FXML
    void addLocation() {
        String Livre = txt_nomlivre.getText();
        String sql = "select idAdh from adherent where nCIN ='"+txt_ncin.getText()+"' ";
        int adherent = 0;
        try{
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            if (result.next()){
                adherent=result.getInt("idAdh");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String sql1 = "select idLivre from livre where idLivre ='"+txt_search_livre.getText()+"' ";
        int livre = 0;
        try{
            st= cnx.prepareStatement(sql1);
            result=st.executeQuery();
            if (result.next()){
                livre=result.getInt("idLivre");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        java.util.Date datedd =  Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date datedebut = new java.sql.Date(datedd.getTime());
        java.util.Date datedf =  Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date datefin = new java.sql.Date(datedf.getTime());

        String sql2 = "select dateDebut,dateFin from location where adherent ='"+adherent+"'";
        Boolean debut=false;
        boolean fin = false;
        java.sql.Date dated=null;
        java.sql.Date datef=null;
        Date d =null;
        Date f= null;
        try {
            st= cnx.prepareStatement(sql2);
            result=st.executeQuery();
            while (result.next()){
                dated=result.getDate("dateDebut");
                datef=result.getDate("dateFin");
                if (isBetween(datefin,dated,datef)==true){
                    fin=true;
                }
                if (isBetween(datedebut,dated,datef)==true){
                    debut=true;
                }
                if (isOut(datedebut,datefin,dated,datef)==true){
                    fin=true;
                    debut=true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (fin==true || debut==true){
            Alert alert=new Alert(Alert.AlertType.WARNING, "Ce logement est occupe pendant" +dated+"et "+datef+"", ButtonType.OK);
            alert.showAndWait();
        }else {
            String sql0="insert into location(nomLivre,adherent,dateDebut,dateFin) values(?,?,?,?)";
            try {
                st= cnx.prepareStatement(sql0);
                st.setString(1,Livre);
                st.setInt(2,adherent);
                st.setDate(3,datedebut);
                st.setDate(4,datefin);
                st.executeUpdate();
                txt_searchAdh.setText("");
                txt_search_livre.setText("");
                txt_ncin.setText("");
                txt_nomlivre.setText("");
                txt_auteur.setText("");
                txt_nomadh.setText("");
                txt_maisonedition.setText("");
                txt_telephoneadh.setText("");
                this.datedebut.setValue(null);
                this.datefin.setValue(null);
                imageview.setImage(null );
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Ce logement a ete ajouter avec succes ", ButtonType.OK);
                alert.showAndWait();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void periode() {
        java.util.Date dated =  Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date datedebut = new java.sql.Date(dated.getTime());
        java.util.Date datef =  Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date datefin = new java.sql.Date(datef.getTime());
        int days=daysBetween(datedebut,datefin);
        int mois=days/30;
        txt_periode.setText(String.valueOf(mois));
    }
    public  int daysBetween(java.sql.Date d1,java.sql.Date d2){
        return (int) ((d2.getTime()-d1.getTime())/(1000*60*60*24));
    }

    @FXML
    void searchAdherent() {
        String sql = "select nomAdh ,nCIN,telephoneAdh  from adherent where nCIN ='"+txt_searchAdh.getText() +"'";
        int nbr=0;
        try{
            st=cnx.prepareStatement(sql);
            result= st.executeQuery();
            if (result.next()){
                txt_ncin.setText(result.getString("nCIN"));
                txt_nomadh.setText(result.getString("nomAdh"));
                txt_telephoneadh.setText(result.getString("telephoneAdh"));
                txt_searchAdh.setText("");
                nbr=1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (nbr==0){
            Alert alert=new Alert(Alert.AlertType.ERROR, "aucun Locataire trouve avec CIN=" +txt_searchAdh.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void searchLivre() {
        String sql="select nomLivre,auteur,maisonedition,image from livre where idLivre ='"+txt_search_livre.getText()+"'";
        int nb=0;
        try {
            st= cnx.prepareStatement(sql);
            result= st.executeQuery();
            byte ByteImg[];
            Blob blob;
            if (result.next()){
                txt_nomlivre.setText(result.getString("nomLivre"));
                txt_auteur.setText(result.getString("auteur"));
                txt_maisonedition.setText(result.getString("maisonedition"));
                blob= result.getBlob("image");
                ByteImg=blob.getBytes(1,(int)blob.length());
                Image img=new Image(new ByteArrayInputStream(ByteImg),imageview.getFitWidth(),imageview.getFitHeight(),true,true);
                imageview.setImage(img);
                txt_search_livre.setText("");
                nb=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (nb==0){
            Alert alert=new Alert(Alert.AlertType.ERROR, "aucun Livre trouve avec indentifiant=" +txt_search_livre.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cnx=ConnexionMysql.connectionDB();
    }
}
