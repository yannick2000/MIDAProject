package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ImpressionController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private TextField txt_auteur;

    @FXML
    private TextField txt_pages;

    @FXML
    private TextField txt_maisonedition;

    @FXML
    private TextField txt_telephone;

    @FXML
    private ImageView image_livre;

    @FXML
    private TextField txt_addresse;

    @FXML
    private ComboBox<Integer> combo_searchLocation;

    @FXML
    private TextField txt_datedebut;

    @FXML
    private TextField txt_datefin;

    @FXML
    private TextField txt_livre;

    @FXML
    private TextField txt_adherent;

    @FXML
    void search_Livre() {
        String sql ="select nomLivre,nomAdh,telephoneAdh,adresseAdh,dateDebut,dateFin from adherent,location where idLocation ='"+combo_searchLocation.getValue()+"'and adherent.idAdh=location.adherent ";
        int log=0;
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            if (result.next()){
                //log= result.getInt("adherent");
                txt_adherent.setText(result.getString("nomAdh"));
                txt_telephone.setText(result.getString("telephoneAdh"));
                txt_addresse.setText(result.getString("adresseAdh"));
                txt_livre.setText(result.getString("nomLivre"));
                Date dated = result.getDate("dateDebut");
                txt_datedebut.setText(String.valueOf(dated));
                Date datef = result.getDate("dateFin");
                txt_datefin.setText(String.valueOf(datef));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        String sql2 ="select auteur,nombrePages,maisonedition,image from livre,location where idLocation ='"+combo_searchLocation.getValue()+"'";
       int loy=0;
        try{
           st=cnx.prepareStatement(sql2);
           result=st.executeQuery();
           byte byteimg[];
           Blob blob;
           if (result.next()){
               txt_auteur.setText(result.getString("auteur"));
               txt_maisonedition.setText(result.getString("maisonedition"));
               txt_pages.setText(result.getString("nombrePages"));
               blob=result.getBlob("image");
               byteimg=blob.getBytes(1,(int) blob.length());
               Image img=new Image(new ByteArrayInputStream(byteimg),image_livre.getFitHeight(),image_livre.getFitWidth(),true,true);
               image_livre.setImage(img);

           }
       }catch (SQLException e){
           e.printStackTrace();
       }

    }
    public void remplirCombo(){
        String sql="select idLocation from location";
        List<Integer> list=new ArrayList<Integer>();
        try {
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                list.add(result.getInt("idLocation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        combo_searchLocation.setItems(FXCollections.observableArrayList(list));
    }
    @FXML
    void imprimer() {
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("location.pdf"));
            doc.open();
            String format ="dd/mm/yy hh:mm";

            SimpleDateFormat formater=new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:\\Users\\Yanoblack\\Documents\\IdeaProjects\\MIDAPROJECT\\target\\classes\\images\\téléchargé.jpg");
            img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            doc.add(img);
            doc.add(new Paragraph("Entre : Adam MAURAULI "
                    + "\nDemeurant a : Paris", FontFactory.getFont(FontFactory.TIMES_ROMAN,14, Font.NORMAL,BaseColor.BLACK)));
            doc.close();
            Desktop.getDesktop().open(new File("location.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cnx=ConnexionMysql.connectionDB();
        remplirCombo();
    }
}
