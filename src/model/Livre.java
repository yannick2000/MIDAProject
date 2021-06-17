package model;

import java.util.Date;

public class Livre {
    int idl;
    String noml;
    String nombrepages;
    String auteurl;
    String maisonl;
    Date dateparutionl;
    String typel;
    String genrel;

    public Livre(int idl, String noml, String nombrepages, String auteurl, String maisonl, Date dateparutionl, String typel, String genrel) {
        this.idl = idl;
        this.noml = noml;
        this.nombrepages = nombrepages;
        this.auteurl = auteurl;
        this.maisonl = maisonl;
        this.dateparutionl = dateparutionl;
        this.typel = typel;
        this.genrel = genrel;
    }

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public String getNoml() {
        return noml;
    }

    public void setNoml(String noml) {
        this.noml = noml;
    }

    public String getNombrepages() {
        return nombrepages;
    }

    public void setNombrepages(String nombrepages) {
        this.nombrepages = nombrepages;
    }

    public String getAuteurl() {
        return auteurl;
    }

    public void setAuteurl(String auteurl) {
        this.auteurl = auteurl;
    }

    public String getMaisonl() {
        return maisonl;
    }

    public void setMaisonl(String maisonl) {
        this.maisonl = maisonl;
    }

    public Date getDateparutionl() {
        return dateparutionl;
    }

    public void setDateparutionl(Date dateparutionl) {
        this.dateparutionl = dateparutionl;
    }

    public String getTypel() {
        return typel;
    }

    public void setTypel(String typel) {
        this.typel = typel;
    }

    public String getGenrel() {
        return genrel;
    }

    public void setGenrel(String genrel) {
        this.genrel = genrel;
    }
}
