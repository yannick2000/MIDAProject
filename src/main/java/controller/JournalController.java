package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BD;
import model.Journal;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class JournalController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private TextField txt_titre;

    @FXML
    private TableView<Journal> tablejournaux;

    @FXML
    private TableColumn<Journal, String> cln_titre;

    @FXML
    private TableColumn<Journal, Date> cln_dateparution;

    @FXML
    private TextField txt_recherche;
    @FXML
    private TextField txt_id;


    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private DatePicker dateparution;
    public ObservableList<Journal> data = FXCollections.observableArrayList();

    @FXML
    void ajouterjournal( ) {
        String titre = txt_titre.getText();
        String date = String.valueOf(dateparution.getValue());
        //Requete SQL
        String sql = "insert into journal (titre_journal,datedeparution) values (?,?)";
        if (!titre.equals("") && !date.equals("") ){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,titre);
                st.setString(2,date);
                st.execute();
                txt_titre.setText("");
                dateparution.setValue(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Un Journal a ete  ajouter avec success",ButtonType.OK);
                alert.showAndWait();
                showJournal();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs",ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void modifierjournal() {
        String titre = txt_titre.getText();
        String date = String.valueOf(dateparution.getValue());

        String sql="update journal set titre_journal=?,datedeparution=? where idJournal = '"+txt_id.getText()+"' ";
        if (!titre.equals("") && !date.equals("")  ){
            try {
                st= cnx.prepareStatement(sql);
                st.setString(1,titre);
                st.setString(2,date);
                st.execute();
                txt_titre.setText("");
                dateparution.setValue(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Un Journal a ete  Modifier avec success",ButtonType.OK);
                alert.showAndWait();
                showJournal();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void supprimerjournal() {
        String sql = "delete from journal where idJournal = '"+txt_id.getText() +"' ";
        try {
            st=cnx.prepareStatement(sql);
            st.executeUpdate();
            txt_titre.setText("");
            dateparution.setValue(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Un Journal a bien ete supprimer ",ButtonType.OK);
            alert.showAndWait();
            showJournal();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tableevent() {
        Journal journal= tablejournaux.getSelectionModel().getSelectedItem();
        String sql ="select * from journal where idJournal = ?";
        try {
            st= cnx.prepareStatement(sql);
            st.setInt(1 ,journal.getId());
            result=st.executeQuery();
            if (result.next()){
                txt_id.setText(result.getString("idJournal"));
                txt_titre.setText(result.getString("titre_journal"));
                Date date = result.getDate("datedeparution");
                dateparution.setValue(((java.sql.Date) date).toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public  void showJournal(){
        tablejournaux.getItems().clear();
        String sql="select * from journal";
        try {
            st= cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                data.add(new Journal(result.getInt("idJournal"), result.getString("titre_journal"), result.getDate("datedeparution") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cln_titre.setCellValueFactory(new PropertyValueFactory<Journal,String>("titre"));
        cln_dateparution.setCellValueFactory(new PropertyValueFactory<Journal,Date>("dateparution"));
        tablejournaux.setItems(data);
    }

    @FXML
    void recherchedynamique() {
        ObservableList<Journal> data = FXCollections.observableArrayList();
        tablejournaux.getItems().clear();
        String sql="select * from journal where titre_journal like ? ";
        try {
            st= cnx.prepareStatement(sql);
            st.setString(1,"%"+txt_recherche.getText()+"%");
            result=st.executeQuery();
            while (result.next()){
                data.add(new Journal(result.getInt("idJournal"), result.getString("titre_journal"), result.getDate("datedeparution") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cln_titre.setCellValueFactory(new PropertyValueFactory<Journal,String>("titre"));
        cln_dateparution.setCellValueFactory(new PropertyValueFactory<Journal,Date>("dateparution"));
        tablejournaux.setItems(data);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cnx=ConnexionMysql.connectionDB();
        showJournal();
    }
}
