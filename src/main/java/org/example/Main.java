package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Début : Découverte  JDBC");

        // 1. Initialiser le driver
        String nomDriver = "com.mysql.cj.jdbc.Driver";

        // 2. Les URLs
        String urlDB = "jdbc:mysql://host:25060/defaultdb?useSSL=true";

        // 3. Chargement du driver
        try {
            Class.forName(nomDriver);
            System.out.println("Chargement du driver OK : " + nomDriver);
        } catch (ClassNotFoundException e) {
            System.err.println("Chargement du driver impossible");
            System.exit(-3);
        }

        // 4. Élaboration de la connexion
        Connection cx = null;
        try {
            cx = DriverManager.getConnection(urlDB, "user", "password");
            System.out.println("Connexion OK : " + cx);
        } catch (SQLException e) {
            System.err.println("Connexion impossible");
            System.exit(-4);
        }

        // 5. ....

        String requeteSansSelect = "CREATE TABLE users_test2 (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "prenom VARCHAR(100) NOT NULL," +
                "nom VARCHAR(100) NOT NULL" +
                ")";

        requeteSansSelect = "INSERT INTO users_test2 (prenom, nom) values ('enzo', 'legrand')";

        Statement stmt = null;

        try {
            stmt = cx.createStatement();
            System.out.println("Création du statement OK");
        } catch (SQLException e) {
            System.err.println("Création du statement impossible");
            System.exit(-5);
        }

        try {
            stmt.executeUpdate(requeteSansSelect);
            System.out.println("Requête OK");
        } catch (SQLException e) {
            System.err.println("Requête impossible");
            System.exit(-6);
        }
        //
        //


        // Requete avec SELECT

        String requeteAvecSelect = "SELECT * FROM users";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(requeteAvecSelect);

            while (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString(2));
            }

            System.out.println("Avec select résussi");
        } catch (SQLException e) {
            System.out.println("Echec select");
        }

        // 6. Libération des ressources

        try {
            cx.close();
            System.out.println("Fermeture de la connexion OK");
        } catch (SQLException e) {
            System.err.println("Fermeture de la connexion impossible");
            System.exit(-5);
        }


        System.out.println("Fin : Découverte  JDBC");
    }
}