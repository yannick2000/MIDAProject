package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DcumentController implements Initializable {

    private Parent fxml;
    @FXML
    private BorderPane root;

    @FXML
    private Button btn_livres;

    @FXML
    private Button btn_Bd;

    @FXML
    private Button btn_journal;

    @FXML
    void ouvrirBD(MouseEvent event) {
        try{
            fxml= FXMLLoader.load(getClass().getResource("/vue/BD.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void ouvrirjournal() {
        try{
            fxml= FXMLLoader.load(getClass().getResource("/vue/Journal.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void ouvrirlivre() {
        try{
            fxml= FXMLLoader.load(getClass().getResource("/vue/Livres.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
