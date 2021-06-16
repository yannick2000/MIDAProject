package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdherentController implements Initializable {

    @FXML
    private TextField txt_recherche;

    @FXML
    private TextField txt_nCIN;

    @FXML
    private TextField txt_nom;

    @FXML
    private TableView<?> table_livre;

    @FXML
    private TableColumn<?, ?> cln_id;

    @FXML
    private TableColumn<?, ?> cln_nom;

    @FXML
    private TableColumn<?, ?> cln_prenom;

    @FXML
    private TableColumn<?, ?> cln_addresse;

    @FXML
    private TableColumn<?, ?> cln_date;

    @FXML
    private TableColumn<?, ?> cln_cin;

    @FXML
    private TableColumn<?, ?> cln_sexe;

    @FXML
    private TableColumn<?, ?> cln_telephone;

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

    @FXML
    void ajouterLivre() {

    }

    @FXML
    void modifierLivre() {

    }

    @FXML
    void searchLivre() {

    }

    @FXML
    void supprimerLivre() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
