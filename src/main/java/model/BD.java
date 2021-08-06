package model;

public class BD {
    int id;
    String titre;
    String dessinateur;

    public BD(){
        super();
    }

    public BD(int id,String titre,String dessinateur){
        this.id=id;
        this.titre=titre;
        this.dessinateur=dessinateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDessinateur() {
        return dessinateur;
    }

    public void setDessinateur(String dessinateur) {
        this.dessinateur = dessinateur;
    }
}
