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
public class departement implements mainData{
    private int iddepartement;
    private String nom;
    private String faculte;

    public int getIddepartement() {
        return iddepartement;
    }

    public void setIddepartement(int iddepartement) {
        this.iddepartement = iddepartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }
    


    @Override
    public void add() {
        String strInsert = "insert into departement values( NULL ," + "'" + nom + "'," + "'" + faculte + "')";
        boolean estAdd = db.go.runNonQuery(strInsert);
        if(estAdd){
            Tools.msgBox("Le departement est Ajouter");
        }
    }

    @Override
    public void update() {
        String strUpdate = "update departement set " + "nom='" + nom + "'," + "faculte='" + faculte + "' " + " where iddepartement=" + iddepartement;
        boolean estUpdate = db.go.runNonQuery(strUpdate);
        if(estUpdate){
        Tools.msgBox("Le departement est Modifier");
    }
    }

    @Override
    public void delete() {
        String strDelete = "delete from departement" + " where iddepartement=" + iddepartement;
        boolean estsupprimer = db.go.runNonQuery(strDelete);
        if(estsupprimer){
        Tools.msgBox("Le departement est supprimer");
        }
    }

    @Override
    public String getAutoNumber() {
        return db.go.getAutoNumber("departement", "iddepartement");
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("SELECT nom,faculte FROM departement", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from departement " + " where iddepartement=" + iddepartement;
                db.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select iddepartement from departement" + " where nom='" + name + "'";
        String strval = db.go.getTableData(strSelect).Items[0][0].toString();
        return strval;
    }

    @Override
    public String getNameByvalue(String value) {
        String strSelect = "select nom from departement" + " where iddepartement='" + value + "'";
        String strval = db.go.getTableData(strSelect).Items[0][0].toString();
        return strval;
    }
    
}
