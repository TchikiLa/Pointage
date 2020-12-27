/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import PTG.Tools;
import javax.swing.JTable;

/**
 *
 * @author Lakhdar
 */
public class fonctionnaire implements mainData{
    private int idfonctionnaire;
    private String nom;
    private String prenom;
    private String age;
    private int idfonction;
    private int iddepartement;

    public int getIdfonctionnaire() {
        return idfonctionnaire;
    }

    public void setIdfonctionnaire(int idfonctionnaire) {
        this.idfonctionnaire = idfonctionnaire;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getIdfonction() {
        return idfonction;
    }

    public void setIdfonction(int idfonction) {
        this.idfonction = idfonction;
    }

    public int getIddepartement() {
        return iddepartement;
    }

    public void setIddepartement(int iddepartement) {
        this.iddepartement = iddepartement;
    }
    

    @Override
    public void add() {
    String strInsert = "insert into fonctionnaires values( NULL ," + "'" + nom + "'," + "'" + prenom + "'," + "'" + age + "'," + "'" + idfonction + "'," + iddepartement +")"; 
        boolean estAdd = db.go.runNonQuery(strInsert);
        if(estAdd){
            Tools.msgBox("Le fonctionnaire est Ajouter");
        }
    }

    @Override
    public void update() {
        String strUpdate = "update fonctionnaires set " + "nom='" + nom + "'," + "prenom='" + prenom + "'," + "age='" + age + "'," + "idfonction='" + idfonction + "'," + "iddepartement=" + iddepartement + " where idfonctionnaire=" + idfonctionnaire;
        boolean estUpdate = db.go.runNonQuery(strUpdate);
        if(estUpdate){
        Tools.msgBox("Le fonctionnaire est Modifier");
        }
    }

    @Override
    public void delete() {
        String strDelete = "DELETE FROM fonctionnaires" + " WHERE fonctionnaires.idfonctionnaire=" + idfonctionnaire ;
        boolean estsupprimer = db.go.runNonQuery(strDelete);
        if(estsupprimer){
        Tools.msgBox("Le fonctionnaire est supprimer");
        }
    }

    @Override
    public String getAutoNumber() {
        return db.go.getAutoNumber("fonctionnaires", "idfonctionnaire");
    }

    @Override
    public void getAllRows(JTable table) {
         db.go.fillToJTable("SELECT Nom,Prenom,Age,NomFonction,NomDep FROM fonctionnaire_donne", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from fonctionnaire_donne " + " where Numfonctionnair=" + idfonctionnaire;
                db.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select idfonctionnaire from fonctionnaires" + " where nom='" + name + "'";
        String strval = db.go.getTableData(strSelect).Items[0][0].toString();
        return strval;
    }

    @Override
    public String getNameByvalue(String value) {
         String strSelect = "select nom from fonctionnaires" + " where idfonctionnaire='" + value + "'";
        String strval = db.go.getTableData(strSelect).Items[0][0].toString();
        return strval;
    }
    
}
