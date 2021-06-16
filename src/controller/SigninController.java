package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private Button btn_passwordforgotten;

    @FXML
    private Button btn_seconnecter;

    @FXML
    private javafx.scene.layout.VBox VBox;
    private Parent fxml;
    @FXML
    void openHome() {
        String nom = txt_username.getText();
        String pass = txt_password.getText();
        String sql = "select username ,password from login";
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            if (result.next()){

                if (nom.equals(result.getString("username"))&&pass.equals(result.getString("password"))){
                    System.out.println("bien");
                    VBox.getScene().getWindow().hide();
                    Stage home = new Stage();
                    try{
                        fxml = FXMLLoader.load(getClass().getResource("/vue/Acceuil.fxml"));
                        Scene scene = new Scene(fxml);
                        home.setScene(scene);
                        home.show();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Nom d'utilisateur ou mot de passe incorrect", ButtonType.OK);
                    alert.showAndWait();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void sendpassword() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
    }
}
