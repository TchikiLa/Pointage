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
public class fonction implements mainData{

    private int idfonction;
    private String nom;
    private String salair;
        
   

    public int getIdfonction() {
        return idfonction;
    }

    public void setIdfonction(int idfonction) {
        this.idfonction = idfonction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSalair() {
        return salair;
    }

    public void setSalair(String salair) {
        this.salair = salair;
    }
    
    
    
     @Override
    public void add() {
        String strInsert = "insert into fonction values( NULL ," + "'" + nom + "'," + "'" + salair + "')";
        boolean estAdd = db.go.runNonQuery(strInsert);
        if(estAdd){
            Tools.msgBox("La fonction est Ajouter");
        }
    }
    
    @Override
    public void update() {
       String strUpdate = "update fonction set " + "nom='" + nom + "'," + "salair='" + salair + "' " + " where idfonction=" + idfonction;
        boolean estUpdate = db.go.runNonQuery(strUpdate);
        if(estUpdate){
        Tools.msgBox("La fonction est Modifier");
    }
    }

    @Override
    public void delete() {
        String strDelete = "delete from fonction" + " where idfonction=" + idfonction;
        boolean estsupprimer = db.go.runNonQuery(strDelete);
        if(estsupprimer){
        Tools.msgBox("La fonction est supprimer");
        }
    }

    @Override
    public String getAutoNumber() {
        return db.go.getAutoNumber("fonction", "idfonction");
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("SELECT nom,salair FROM fonction", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from fonction " + " where idfonction=" + idfonction;
                db.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        
        String strSelect = "select idfonction from fonction" + " where nom='" + name + "'";
        String strval = db.go.getTableData(strSelect).Items[0][0].toString();
        return strval;
    }

    @Override
    public String getNameByvalue(String value) {
        String strSelect = "select nom from fonction" + " where idfonction='" + value + "'";
        String strval = db.go.getTableData(strSelect).Items[0][0].toString();
        return strval;
    }
    
}
