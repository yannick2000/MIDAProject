package model;

import java.util.Date;

public class Journal {
    int id;
    String titre;
    Date dateparution;

    public  Journal(){
        super();
    }
    public Journal(int id ,String titre,Date dateparution){
        this.id=id;
        this.titre=titre;
        this.dateparution=dateparution;
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

    public Date getDateparution() {
        return dateparution;
    }

    public void setDateparution(Date dateparution) {
        this.dateparution = dateparution;
    }
}
