package org.udg.pds.cheapyandroid.entity;

public class User {

    private String id;
    private String nom;
    private String cognoms;
    private String dataNaixement;
    private String correu;
    private String password;
    private String sexe;
    private Double telefon;
    private int nombreVendes;
    private int nombreCompres;
    private int nombreValoracions;
    private double mitjanaValoracions;
    private Ubicacio ubicació;
    private Imatge imatge;

    public User(){

    }

    public User(String id, String nom, String cognoms, String dataNaixement, String correu, int nombreVendes, int nombreCompres, int nombreValoracions, double mitjanaValoracions, Ubicacio ubicació, Imatge imatge) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.correu = correu;
        this.nombreVendes = nombreVendes;
        this.nombreCompres = nombreCompres;
        this.nombreValoracions = nombreValoracions;
        this.mitjanaValoracions = mitjanaValoracions;
        this.ubicació = ubicació;
        this.imatge = imatge;
    }

    public User(String nom, String cognoms, String dataNaixement, String correu, String contrasenya, String sexe, Double telefon) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.correu = correu;
        this.password = contrasenya;
        this.sexe = sexe;
        this.telefon = telefon;

    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getSexe() {
        return sexe;
    }

    public Double getTelefon() {
        return telefon;
    }

    public String getPassword() {
        return password;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public String getCorreu() {
        return correu;
    }

    public int getNombreVendes() {
        return nombreVendes;
    }

    public int getNombreCompres() {
        return nombreCompres;
    }

    public int getValoracions() {
        return nombreValoracions;
    }

    public double getMitjanaValoracions() {
        return mitjanaValoracions;
    }

    public Ubicacio getUbicació() {
        return ubicació;
    }

    public Imatge getImatge() {
        return imatge;
    }
}