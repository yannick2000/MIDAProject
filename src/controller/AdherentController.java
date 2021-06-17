package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Adherent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class AdherentController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private TextField txt_recherche;

    @FXML
    private TextField txt_nCIN;

    @FXML
    private TextField txt_nom;

    @FXML
    private TableView<Adherent> table_Adherent;

    @FXML
    private TableColumn<Adherent, Integer> cln_id;

    @FXML
    private TableColumn<Adherent, String> cln_nom;

    @FXML
    private TableColumn<Adherent, String> cln_prenom;

    @FXML
    private TableColumn<Adherent, String> cln_addresse;

    @FXML
    private TableColumn<Adherent, Date> cln_date;

    @FXML
    private TableColumn<Adherent, String> cln_cin;

    @FXML
    private TableColumn<Adherent, String> cln_sexe;

    @FXML
    private TableColumn<Adherent, String> cln_telephone;

    @FXML
    private TextField txt_addresse;

    @FXML
    private TextField txt_prenom;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField txt_telephone;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private TextField txt_sexe;

    public ObservableList<Adherent> data = FXCollections.observableArrayList();
    @FXML
    void ajouterAdherent( ){
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        String adresse = txt_addresse.getText();
        String sexe = txt_sexe.getText();
        String telephone = txt_telephone.getText();
        String date = String.valueOf(datepicker.getValue());
        String ncin = txt_nCIN.getText();

        String sql = "insert into adherent (nomAdh,prenomAdh,adresseAdh,datenaisAdh,sexeAdh,nCIN,telephoneAdh) values (?,?,?,?,?,?,?)";
        if (!nom.equals("") && !prenom.equals("") && !adresse.equals("") && !ncin.equals("") && !sexe.equals("") && !datepicker.getValue().equals(null)){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,nom);
                st.setString(2,prenom);
                st.setString(3,adresse);
                st.setString(4, date);
                st.setString(5,sexe);
                st.setString(6,ncin);
                st.setString(7,telephone);
                st.execute();
                txt_nom.setText("");
                txt_prenom.setText("");
                txt_addresse.setText("");
                txt_sexe.setText("");
                txt_nCIN.setText("");
                txt_recherche.setText("");
                txt_telephone.setText("");
                datepicker.setValue(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Adherent ajouter avec success",ButtonType.OK);
                alert.showAndWait();
                showAdherent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs",ButtonType.OK);
            alert.showAndWait();
        }



    }

    @FXML
    void modifierAdherent() {
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        String adresse = txt_addresse.getText();
        String sexe = txt_sexe.getText();
        String telephone = txt_telephone.getText();
        String date = String.valueOf(datepicker.getValue());
        String ncin = txt_nCIN.getText();

        String sql="update adherent set nomAdh=?,prenomAdh=?,adresseAdh=?,datenaisAdh=?,sexeAdh=?,nCIN=?,telephoneAdh=? where nomAdh = '"+txt_recherche.getText()+"' ";
        if (!nom.equals("") && !prenom.equals("") && !adresse.equals("") && !ncin.equals("") && !sexe.equals("") && !datepicker.getValue().equals(null)){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,nom);
                st.setString(2,prenom);
                st.setString(3,adresse);
                st.setString(4, date);
                st.setString(5,sexe);
                st.setString(6,ncin);
                st.setString(7,telephone);
                st.execute();
                txt_nom.setText("");
                txt_prenom.setText("");
                txt_addresse.setText("");
                txt_sexe.setText("");
                txt_nCIN.setText("");
                txt_recherche.setText("");
                txt_telephone.setText("");
                datepicker.setValue(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Adherent Modifier avec success",ButtonType.OK);
                alert.showAndWait();
                showAdherent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void searchAdherent() {
        String sql ="select nomAdh,prenomAdh,sexeAdh,adresseAdh,datenaisAdh,nCIN,telephoneAdh from adherent where nomAdh  = '"+txt_recherche.getText() +"'  ";
        int n=0;
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            if (result.next()){
                txt_nCIN.setText(result.getString("nCIN"));
                txt_nom.setText(result.getString("nomAdh"));
                txt_prenom.setText(result.getString("prenomAdh"));
                txt_addresse.setText(result.getString("adresseAdh"));
                txt_sexe.setText(result.getString("sexeAdh"));
                txt_telephone.setText(result.getString("telephoneAdh"));
                Date date = result.getDate("datenaisAdh");
                datepicker.setValue(((java.sql.Date) date).toLocalDate());
                n=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(n==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Aucun Adherent trouve avec les identifiants = "+txt_recherche.getText()+"",ButtonType.OK);
           alert.showAndWait();
       }
    }

    @FXML
    void supprimerAdherent() {
        String sql = "delete from adherent where nomAdh = '"+txt_recherche.getText() +"' ";

        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            txt_nom.setText("");
            txt_prenom.setText("");
            txt_addresse.setText("");
            txt_sexe.setText("");
            txt_nCIN.setText("");
            txt_recherche.setText("");
            txt_telephone.setText("");
            datepicker.setValue(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Un adherent a bien ete supprimer ",ButtonType.OK);
            alert.showAndWait();
            showAdherent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void tableAdherentEvent() {
        Adherent adherent= table_Adherent.getSelectionModel().getSelectedItem();
        String sql ="select * from adherent where idAdh = ?";
        try {
            st= cnx.prepareStatement(sql);
            st.setInt(1,adherent.getId());
            result=st.executeQuery();
            if (result.next()){
                txt_nCIN.setText(result.getString("nCIN"));
                txt_nom.setText(result.getString("nomAdh"));
                txt_prenom.setText(result.getString("prenomAdh"));
                txt_addresse.setText(result.getString("adresseAdh"));
                txt_sexe.setText(result.getString("sexeAdh"));
                txt_telephone.setText(result.getString("telephoneAdh"));
                Date date = result.getDate("datenaisAdh");
                datepicker.setValue(((java.sql.Date) date).toLocalDate());
                txt_recherche.setText(result.getString("nomAdh"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public  void showAdherent(){
        table_Adherent.getItems().clear();
        String sql="select * from adherent";
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                data.add(new Adherent(result.getInt("idAdh"), result.getString("nomAdh"), result.getString("prenomAdh"), result.getString("sexeAdh"), result.getString("adresseAdh"),result.getDate("datenaisAdh"), result.getString("nCIN"), result.getString("telephoneAdh") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cln_id.setCellValueFactory(new PropertyValueFactory<Adherent,Integer>("id"));
        cln_nom.setCellValueFactory(new PropertyValueFactory<Adherent,String>("nom"));
        cln_prenom.setCellValueFactory(new PropertyValueFactory<Adherent,String>("prenom"));
        cln_addresse.setCellValueFactory(new PropertyValueFactory<Adherent,String>("adresse"));
        cln_date.setCellValueFactory(new PropertyValueFactory<Adherent,Date>("datedenaissance"));
        cln_cin.setCellValueFactory(new PropertyValueFactory<Adherent,String>("nci"));
        cln_sexe.setCellValueFactory(new PropertyValueFactory<Adherent,String>("sexe"));
        cln_telephone.setCellValueFactory(new PropertyValueFactory<Adherent,String>("telephone"));
        table_Adherent.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
        showAdherent();
    }
}
