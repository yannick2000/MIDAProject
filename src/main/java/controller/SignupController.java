package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private TextField txt_nomutilisateur;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_motsepasse;

    @FXML
    private Button btninscrire;

    @FXML
    void ajoutercompte() {
        String nomutilisateur = txt_nomutilisateur.getText();
        String motdepasse = txt_motsepasse.getText();
        String email = txt_email.getText();
        //Requete SQL
        String sql = "insert into login (username,password,email) values (?,?,?)";
        if (!nomutilisateur.equals("") && !motdepasse.equals("") && !email.equals("")){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,nomutilisateur);
                st.setString(2,motdepasse);
                st.setString(3,email);
                st.execute();
                txt_nomutilisateur.setText("");
                txt_motsepasse.setText("");
                txt_email.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Votre Compte a bien ete Creer", ButtonType.OK);
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs",ButtonType.OK);
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
    }
}
