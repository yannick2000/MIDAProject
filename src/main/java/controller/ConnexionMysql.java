package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionMysql {

    public Connection cnx = null;

    public static Connection connectionDB() {

            try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3308/bibliotheque", "root", "");
                    System.out.println("Connexion reussie");
                    return cnx;

            } catch (  Exception e) {
                System.out.println("connexion echoue");
                e.printStackTrace();
                return null;
            }


    }
}
