/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author EsseJacquesDansomon
 */
public class User {

    protected int id;
    protected String nom;
    protected String prenom;
    protected String login;
    protected String password;
    protected String role;
    
    /**
     * COnstructeur par default
     */
    public User() {
    }
    
    
     /**
     * Constructeur de insert d'un Patient
     * @param nom
     * @param prenom
     * @param login
     * @param password
     */
    public User(String nom, String prenom, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
    }

    /**
     * Constructeur de creation|insert d'un SC, RP, Admin
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param role 
     */
    public User(String nom, String prenom, String login, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructeur de Update|Select| d'un SC, RP, Admin, Patient
     * @param id
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param role 
     */
    public User(int id, String nom, String prenom, String login, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }

        
    public Patient userToPatient()
    {
        Patient p =  new Patient(  );
        p.id = this.id;
        p.nom = this.nom;
        p.prenom = this.prenom;
        p.role = this.role;
        return p;
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
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }   
}
