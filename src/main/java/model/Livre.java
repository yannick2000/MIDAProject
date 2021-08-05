package model;


public class Livre {
    int idl;
    String noml;
    String auteurl;
    String maisonl;
    String typel;

    public Livre(){
        super();
    }

    public Livre(int idl, String noml, String auteurl, String maisonl, String typel) {
        this.idl = idl;
        this.noml = noml;
        this.auteurl = auteurl;
        this.maisonl = maisonl;
        this.typel = typel;
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

    public String getTypel() {
        return typel;
    }

    public void setTypel(String typel) {
        this.typel = typel;
    }

}