package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Adherent;
import model.BD;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class BDController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private TableView<BD> tablebd;

    @FXML
    private TableColumn<BD, String> cln_titre;

    @FXML
    private TableColumn<BD, String> cln_dessinateur;

    @FXML
    private TextField txt_titre;

    @FXML
    private TextField txt_dessinateur;

    @FXML
    private Button btn_ajouter;

    @FXML
    private Button btn_modifier;

    @FXML
    private Button btn_supprimer;

    public ObservableList<BD> data = FXCollections.observableArrayList();
    @FXML
    void ajouterbd() {
        String titre = txt_titre.getText();
        String dessinateur = txt_dessinateur.getText();
        //Requete SQL
        String sql = "insert into bd (titre,dessinateur) values (?,?)";
        if (!titre.equals("") && !dessinateur.equals("") ){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,titre);
                st.setString(2,dessinateur);
                st.execute();
                txt_titre.setText("");
                txt_dessinateur.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Une BD a ete  ajouter avec success",ButtonType.OK);
                alert.showAndWait();
              showBD();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs",ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void modifierbd() {
        String titre = txt_titre.getText();
        String dessinateur = txt_dessinateur.getText();

        String sql="update bd set titre=?,dessinateur=? where titre = '"+txt_titre.getText()+"' ";
        if (!titre.equals("") && !dessinateur.equals("")  ){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,titre);
                st.setString(2,dessinateur);
                st.execute();
                txt_titre.setText("");
                txt_dessinateur.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"BD Modifier avec success",ButtonType.OK);
                alert.showAndWait();
                showBD();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();
        }


    }

    @FXML
    void supprimerbd() {
            String sql = "delete from bd where titre = '"+txt_titre.getText() +"' ";
        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            txt_titre.setText("");
            txt_dessinateur.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Une BD a bien ete supprimer ",ButtonType.OK);
            alert.showAndWait();
            showBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tableevent() {
        BD bd= tablebd.getSelectionModel().getSelectedItem();
        String sql ="select * from bd where idBD = ?";
        try {
            st= cnx.prepareStatement(sql);
            st.setInt(1 ,bd.getId());
            result=st.executeQuery();
            if (result.next()){
                txt_titre.setText(result.getString("titre"));
                txt_dessinateur.setText(result.getString("dessinateur"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  void showBD(){
        tablebd.getItems().clear();
        String sql="select * from bd";
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                data.add(new BD(result.getInt("idBD"), result.getString("titre"), result.getString("dessinateur") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cln_titre.setCellValueFactory(new PropertyValueFactory<BD,String>("titre"));
        cln_dessinateur.setCellValueFactory(new PropertyValueFactory<BD,String>("dessinateur"));
        tablebd.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx = ConnexionMysql.connectionDB();
        showBD();
    }
}
