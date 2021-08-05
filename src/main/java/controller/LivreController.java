package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Livre;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    private TableView<Livre> table_livre;

    @FXML
    private TableColumn<Livre, Integer> cln_id;

    @FXML
    private TableColumn<Livre, String> cln_nom;

    @FXML
    private TableColumn<Livre, String> cln_auteur;

    @FXML
    private TableColumn<Livre, String> cln_maisonedition;

    @FXML
    private TableColumn<Livre, String> cln_type;

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
    private ComboBox<String> cb_type;

    @FXML
    private ComboBox<String> cb_genre;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField txt_auteur;

    @FXML
    private TextField txt_maisonedition;

    @FXML
    private ImageView icon_importer;

    private  FileInputStream fs;
    @FXML
    void ajouterLivre() {
        String nom = txt_nom.getText();
        String nombrePage = txt_nbrpages.getText();
        String auteur = txt_auteur.getText();
        String maisonedition = txt_maisonedition.getText();
        String date = String.valueOf(datepicker.getValue());
        String typ = cb_type.getValue();
        String sql1 ="select idType from type where nomType ='"+typ+"'";
        int type = 0;
        try{
            st= cnx.prepareStatement(sql1);
            result= st.executeQuery();
            if (result.next()){
                type=result.getInt("idType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String genr = cb_genre.getValue();
        String sql2 ="select idGenre from genre where nomGenre ='"+genr+"'";
        int genre = 0;
        try{
            st= cnx.prepareStatement(sql2);
            result= st.executeQuery();
            if (result.next()){
                genre=result.getInt("idGenre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        File image = new File(lab__url.getText());
        String sql="insert into livre (nomLivre,auteur,maisonedition,type,genre,nombrePages,dateparution,image) values (?,?,?,?,?,?,?,?) ";
        try {
            st=cnx.prepareStatement(sql);
            st.setString(1,nom);
            st.setString(2,auteur);
            st.setString(3,nombrePage);
            st.setInt(4,type);
            st.setInt(5,genre);
            st.setString(6,nombrePage);
            st.setString(7,date);
            fs = new FileInputStream(image);
            st.setBinaryStream(8,fs,image.length());
            st.executeUpdate();
            showLivres();
            lab__url.setText("aucune donnee selectionnee");
            txt_nom.setText("");
            txt_nbrpages.setText("");
            txt_maisonedition.setText("");
            txt_auteur.setText("");
            cb_type.setValue("nomType");
            cb_genre.setValue("nomGenre");
            image_livre.setImage(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Livre ajouter", ButtonType.OK );
            alert.showAndWait();
            datepicker.setValue(null);

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void importerImage() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg"));
        File f =fc.showOpenDialog(null);
        if (f !=null){
            lab__url.setText(f.getAbsolutePath());
            Image image=new Image(f.toURI().toString(),image_livre.getFitWidth(),image_livre.getFitHeight(),true,true );
            image_livre.setImage(image);
        }


    }

    @FXML
    void mofifierLivre() {

        String nom = txt_nom.getText();
        String nombrePage = txt_nbrpages.getText();
        String auteur = txt_auteur.getText();
        String maisonedition = txt_maisonedition.getText();
        String date = String.valueOf(datepicker.getValue());
        String typ = cb_type.getValue();
        String sql1 ="select idType from type where nomType ='"+typ+"'";
        int type = 0;
        try{
            st= cnx.prepareStatement(sql1);
            result= st.executeQuery();
            if (result.next()){
                type=result.getInt("idType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String genr = cb_genre.getValue();
        String sql2 ="select idGenre from genre where nomGenre ='"+genr+"'";
        int genre = 0;
        try{
            st= cnx.prepareStatement(sql2);
            result= st.executeQuery();
            if (result.next()){
                genre=result.getInt("idGenre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        File image = new File(lab__url.getText());
        String sql="update  livre set nomLivre=?,auteur=?,maisonedition=?,type=?,genre=?,nombrePages=?,dateparution=?,image=? where idLivre ='"+txt_recherche.getText()+"'";
        try {
            st=cnx.prepareStatement(sql);
            st.setString(1,nom);
            st.setString(2,auteur);
            st.setString(3,nombrePage);
            st.setInt(4,type);
            st.setInt(5,genre);
            st.setString(6,nombrePage);
            st.setString(7,date);
            fs = new FileInputStream(image);
            st.setBinaryStream(8,fs,image.length());
            st.executeUpdate();
            showLivres();
            lab__url.setText("aucune donnee selectionnee");
            txt_nom.setText("");
            txt_nbrpages.setText("");
            txt_maisonedition.setText("");
            txt_auteur.setText("");
            cb_type.setValue("nomType");
            cb_genre.setValue("nomGenre");
            image_livre.setImage(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Logement Modifier ", ButtonType.OK );
            alert.showAndWait();
            datepicker.setValue(null);

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void rechercheLivre() {

        String sql="select nomGenre from genre";
        List<String> genres=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                genres.add(result.getString("nomGenre"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        cb_genre.setItems(FXCollections.observableArrayList(genres));

        String sql1="select nomType from type";
        List<String> types=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql1);
            result=st.executeQuery();
            while(result.next()){
                types.add(result.getString("nomType"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        cb_type.setItems(FXCollections.observableArrayList(types));

        String sql2="select idLivre, nomLivre,auteur,maisonedition,nomType,nomGenre,nombrePages,dateparution,image from livre,type,genre where type.idType = livre.type and genre.idGenre = livre.genre and idLivre = ?";
        try{
            st=cnx.prepareStatement(sql2);
            st.setString(1,txt_recherche.getText());
            result=st.executeQuery();
            byte byteImage[];
            Blob blob;
            while (result.next()){
                int id = result.getInt("idLivre");
                txt_recherche.setText(String.valueOf(id));
                txt_auteur.setText(result.getString("auteur"));
                txt_maisonedition.setText(result.getString("maisonedition"));
                txt_nom.setText(result.getString("nomLivre"));
                txt_nbrpages.setText(result.getString("nombrePages"));
                cb_type.setValue(result.getString("nomType"));
                cb_genre.setValue(result.getString("nomGenre"));
                java.util.Date date = result.getDate("dateparution");
                datepicker.setValue(((java.sql.Date) date).toLocalDate());
                blob= result.getBlob("image");
                byteImage = blob.getBytes(1,(int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImage),image_livre.getFitHeight(),image_livre.getFitWidth(),true,true);
                image_livre.setImage(img);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void remplirGenre() {
        String sql="select nomGenre from genre";
        List<String> genres=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                genres.add(result.getString("nomGenre"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        cb_genre.setItems(FXCollections.observableArrayList(genres));

    }

    @FXML
    void remplirType() {
        String sql="select nomType from type";
        List<String> types=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                types.add(result.getString("nomType"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        cb_type.setItems(FXCollections.observableArrayList(types));


    }


    @FXML
    void tableLivreevent() {
        String sql="select nomGenre from genre";
        List<String> genres=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while(result.next()){
                genres.add(result.getString("nomGenre"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        cb_genre.setItems(FXCollections.observableArrayList(genres));

        String sql1="select nomType from type";
        List<String> types=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql1);
            result=st.executeQuery();
            while(result.next()){
                types.add(result.getString("nomType"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        cb_type.setItems(FXCollections.observableArrayList(types));



        Livre livre = table_livre.getSelectionModel().getSelectedItem();
        String sql2="select idLivre, nomLivre,auteur,maisonedition,nomType,nomGenre,nombrePages,dateparution,image from livre,type,genre where type.idType = livre.type and genre.idGenre = livre.genre and idLivre = ?";
        try{
            st=cnx.prepareStatement(sql2);
            st.setInt(1,livre.getIdl());
            result=st.executeQuery();
            byte byteImage[];
            Blob blob;
            while (result.next()){
               int id = result.getInt("idLivre");
               txt_recherche.setText(String.valueOf(id));
                txt_auteur.setText(result.getString("auteur"));
                txt_maisonedition.setText(result.getString("maisonedition"));
                txt_nom.setText(result.getString("nomLivre"));
                txt_nbrpages.setText(result.getString("nombrePages"));
                cb_type.setValue(result.getString("nomType"));
                cb_genre.setValue(result.getString("nomGenre"));
                java.util.Date date = result.getDate("dateparution");
                datepicker.setValue(((java.sql.Date) date).toLocalDate());
                blob= result.getBlob("image");
                byteImage = blob.getBytes(1,(int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImage),image_livre.getFitHeight(),image_livre.getFitWidth(),true,true);
                image_livre.setImage(img);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void supprimerLivre() {
        String sql="delete from livre where idLivre ='"+txt_recherche.getText()+"'";
        try{
            st= cnx.prepareStatement(sql);
            st.executeUpdate();
            showLivres();
            lab__url.setText("aucune donnee selectionnee");
            txt_nom.setText("");
            txt_nbrpages.setText("");
            txt_maisonedition.setText("");
            txt_auteur.setText("");
            cb_type.setValue("nomType");
            cb_genre.setValue("nomGenre");
            image_livre.setImage(null);
            Alert alert = new Alert(Alert.AlertType.WARNING,"Logement supprimer ", ButtonType.OK );
            alert.showAndWait();
            datepicker.setValue(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    ObservableList<Livre> listlivre = FXCollections.observableArrayList();
    public void showLivres(){
        table_livre.getItems().clear();
        String sql ="select idLivre, nomLivre,auteur,maisonedition,nomType from livre,type where type.idType = livre.type";
        try {
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                listlivre.add(new Livre(result.getInt(1),result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cln_id.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("idl"));
        cln_nom.setCellValueFactory(new PropertyValueFactory<Livre,String>("noml"));
        cln_auteur.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteurl"));
        cln_maisonedition.setCellValueFactory(new PropertyValueFactory<Livre,String>("maisonl"));
        cln_type.setCellValueFactory(new PropertyValueFactory<Livre,String>("typel"));
        table_livre.setItems(listlivre);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=ConnexionMysql.connectionDB();
        showLivres();
    }
}
