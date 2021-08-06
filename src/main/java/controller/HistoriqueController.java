package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Adherent;
import model.Location;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class HistoriqueController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private TextField txt_livre;

    @FXML
    private TextField txt_nCIN;

    @FXML
    private TextField txt_maisonedition;

    @FXML
    private TableView<Location> txt_table;

    @FXML
    private TableColumn<Location, String> cln_livre;

    @FXML
    private TableColumn<Location, Integer> cln_adherent;

    @FXML
    private TableColumn<Location, String> cln_datedebut;

    @FXML
    private TableColumn<Location, String> cln_datefin;

    public ObservableList<Location> data = FXCollections.observableArrayList();

    @FXML
    private TextField txt_adherent;

    @FXML
    private Button btn_modifier;

    @FXML
    private Button btn_supprimer;


    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField txt_location;
    @FXML
    void modifierhistorique() {
        String adherent = txt_adherent.getText();
        String livre = txt_livre.getText();
       // String dated = String.valueOf(dateDebut.getValue());
        //String datef = String.valueOf(dateFin.getValue());
        String sql="update location set adherent =?, livre =?,dateDebut=?,dateFin=? where adherent = '"+txt_adherent.getText()+"' ";

        //if (dateFin.getValue() != null && dateDebut.getValue() != null){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,adherent);
                st.setString(2,livre);
               // st.setString(3,dated);
               // st.setString(4,datef);
                txt_livre.setText("");
                txt_adherent.setText("");
                //dateDebut.setValue(null);
                //dateFin.setValue(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Adherent Modifier avec success",ButtonType.OK);
                alert.showAndWait();
                remplirTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    @FXML
    void searchLivre() {

    }

    @FXML
    void supprimerhistorique() {
        String sql = "delete from location where Adherent = '"+txt_adherent.getText() +"' ";
        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            txt_livre.setText("");
            txt_adherent.setText("");
            dateDebut.setValue(null);
            dateFin.setValue(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Une ligne a bien ete supprimer ",ButtonType.OK);
            alert.showAndWait();
            remplirTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tableEvent() {
        Location location = txt_table.getSelectionModel().getSelectedItem();

       String sql ="select adherent,nomLivre,dateDebut,dateFin from location  where idLocation = ?";
        try{
            st= cnx.prepareStatement(sql);
            st.setInt(1,location.getId());
            result=st.executeQuery();
            while (result.next()){
                txt_adherent.setText(result.getString("adherent"));
                txt_livre.setText(result.getString("nomLivre"));
                Date dated = result.getDate("dateDebut");
                dateDebut.setValue(((java.sql.Date) dated).toLocalDate());
                Date datef = result.getDate("dateFin");
                dateFin.setValue(((java.sql.Date) datef).toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public  void remplirTable(){
        txt_table.getItems().clear();
        String sql="select idLocation,adherent,nomLivre,dateDebut,dateFin from location,adherent where location.adherent = adherent.idAdh";
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                data.add(new Location(result.getInt("idLocation"), result.getInt("Adherent"), result.getString("nomLivre"), result.getString("dateDebut"), result.getString("dateFin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cln_datedebut.setCellValueFactory(new PropertyValueFactory<Location,String>("dateDebut"));
       cln_datefin.setCellValueFactory(new PropertyValueFactory<Location,String>("dateFin"));
        cln_livre.setCellValueFactory(new PropertyValueFactory<Location,String>("nomLivre"));
        txt_table.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
        remplirTable();
    }
}
