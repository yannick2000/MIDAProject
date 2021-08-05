package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    void accueil(MouseEvent event) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void adherent(MouseEvent event) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Adherent.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void historique(MouseEvent event) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Historique.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void impression(MouseEvent event) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Impression.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void livres(MouseEvent event) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Livres.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void location(MouseEvent event) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Louer.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            fxml=FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
