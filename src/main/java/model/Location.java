package model;

public class Location {
    int id;
    int adherent;
    String nomLivre;
    String dateDebut;
    String dateFin;

    public Location(){
        super();
    }

    public Location(int id, int adherent,String nomLivre,String dateDebut,String dateFin){
        this.id=id;
        this.adherent=adherent;
        this.nomLivre=nomLivre;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdherent() {
        return adherent;
    }

    public void setAdherent(int adherent) {
        this.adherent = adherent;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}
