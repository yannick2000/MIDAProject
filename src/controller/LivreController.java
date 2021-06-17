package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.Livre;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LivreController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private TextField txt_recherche;

    @FXML
    private TextField txt_nbrpages;

    @FXML
    private TextField txt_nom;


    @FXML
    private TableView<?> table_livre;

    @FXML
    private TableColumn<?, ?> cln_id;

    @FXML
    private TableColumn<?, ?> cln_nom;

    @FXML
    private TableColumn<?, ?> cln_auteur;

    @FXML
    private TableColumn<?, ?> cln_maisonedition;

    @FXML
    private TableColumn<?, ?> cln_type;

    @FXML
    private Label lab__url;

    @FXML
    private ImageView image_livre;

    @FXML
    private Button btn_ajouter;

    @FXML
    private Button btn_supprimer;

    @FXML
    private Button btn_modifier;

    @FXML
    private ComboBox<?> cb_type;

    @FXML
    private ComboBox<?> cb_genre;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField txt_auteur;

    @FXML
    private TextField txt_maisonedition;

    @FXML
    private ImageView icon_importer;

    @FXML
    void ajouterLivre() {

    }

    @FXML
    void importerImage() {

    }

    @FXML
    void mofifierLivre() {

    }

    @FXML
    void rechercheLivre() {

    }

    @FXML
    void remplirGenre() {

    }

    @FXML
    void remplirType() {

    }

    @FXML
    void supprimerLivre() {

    }
    ObservableList<Livre> listlivre = FXCollections.observableArrayList();
    public void showLivres(){
        String sql ="select idLivre, nomLivre,nom";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
    }
}
