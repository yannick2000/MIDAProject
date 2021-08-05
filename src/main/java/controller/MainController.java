package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btn_seconnecter;

    @FXML
    private Button btn_sinscrire;

    @FXML
    private VBox vbox;

    private Parent fxml;
    @FXML
    void OpenSignin() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX() * 13.5);
        t.play();
        t.setOnFinished(e ->{
            try {
                fxml = FXMLLoader.load(getClass().getResource("/vue/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            }catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }

    @FXML
    void OpenSignup() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(5);
        t.play();
        t.setOnFinished(e ->{
            try {
                fxml = FXMLLoader.load(getClass().getResource("/vue/SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            }catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX() * 13.5);
        t.play();
        t.setOnFinished(e ->{
            try {
                fxml = FXMLLoader.load(getClass().getResource("/vue/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            }catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }
}
