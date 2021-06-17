package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private Label lab_nbr;

    @FXML
    private ImageView imagelog;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_auteur;

    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_maisonedition;

    @FXML
    private ImageView preccedent;

    @FXML
    private ImageView suivant;

    @FXML
    void showPreccedent() {
        String nomLivre = String.valueOf(txt_nom.getText());
        String sql3="select idLivre from livre where nomLivre ='"+nomLivre+"' ";
        int position =0;
        try {
            st= cnx.prepareStatement(sql3);
            result= st.executeQuery();
            if (result.next()){
                position= result.getInt("idLivre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql4= "select nomLivre,maisonedition,nomType,auteur,image from livre,type where type.idType = livre.type and idLivre not in(select livre from location) and idLivre < '"+position+"' ";
        byte byteImg[];
        Blob blob;
        try {
            st= cnx.prepareStatement(sql4);
            result=st.executeQuery();
            if (result.next()){
                txt_auteur.setText(result.getString("auteur"));
                txt_nom.setText(result.getString("nomLivre"));
                txt_maisonedition.setText(result.getString("maisonedition"));
                txt_type.setText(result.getString("nomType"));
                blob= result.getBlob("image");
                byteImg=blob.getBytes(1,(int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImg),imagelog.getFitWidth(),imagelog.getFitHeight(),true,true);
                imagelog.setImage(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showSuivant() {
        String nomLivre = String.valueOf(txt_nom.getText());
        String sql3="select idLivre from livre where nomLivre ='"+nomLivre+"' ";
        int position =0;
        try {
            st= cnx.prepareStatement(sql3);
            result= st.executeQuery();
            if (result.next()){
                position= result.getInt("idLivre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql4= "select nomLivre,maisonedition,nomType,auteur,image from livre,type where  type.idType = livre.type and idLivre not in(select livre from location) and idLivre > '"+position+"' ";
        byte byteImg[];
        Blob blob;
        try {
            st= cnx.prepareStatement(sql4);
            result=st.executeQuery();
            if (result.next()){
                txt_auteur.setText(result.getString("auteur"));
                txt_nom.setText(result.getString("nomLivre"));
                txt_maisonedition.setText(result.getString("maisonedition"));
                txt_type.setText(result.getString("nomType"));
                blob= result.getBlob("image");
                byteImg=blob.getBytes(1,(int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImg),imagelog.getFitWidth(),imagelog.getFitHeight(),true,true);
                imagelog.setImage(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void showLivres(){
        String sql = "select count(*) from livre where idLivre not in(select livre from location )";
         int i=0;
        try {
            st= cnx.prepareStatement(sql);
            result= st.executeQuery();
            if (result.next()){
                i=result.getInt(1);
            }
            lab_nbr.setText(Integer.toString(i));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2= "select nomLivre,maisonedition,nomType,auteur,image from livre,type where  type.idType = livre.type and idLivre not in(select livre from location)";
        byte byteImg[];
        Blob blob;
        try {
            st = cnx.prepareStatement(sql2);
            result = st.executeQuery();
            if (result.next()){
                txt_auteur.setText(result.getString("auteur"));
                txt_nom.setText(result.getString("nomLivre"));
                txt_maisonedition.setText(result.getString("maisonedition"));
                txt_type.setText(result.getString("nomType"));
                blob= result.getBlob("image");
                byteImg=blob.getBytes(1,(int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImg),imagelog.getFitWidth(),imagelog.getFitHeight(),true,true);
                imagelog.setImage(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
        showLivres();
    }
}
