package model;

import java.util.Date;

public class Adherent {
    int id;
    String nom;
    String Prenom;
    String Sexe;
    String Adresse;
    Date datedenaissance;
    String nci;
    String Telephone;

    public Adherent(){
        super();
    }

    public Adherent(int id, String nom, String prenom, String sexe, String adresse, Date datedenaissance, String nci, String telephone) {
        this.id = id;
        this.nom = nom;
        this.Prenom = prenom;
        this.Sexe = sexe;
        this.Adresse = adresse;
        this.datedenaissance = datedenaissance;
        this.nci = nci;
        this.Telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
