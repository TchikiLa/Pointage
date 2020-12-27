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
public class pointagee implements mainData{
    
    private int idfonctionnaire;
    private String jour;
    private String statut;

    public int getIdfonctionnaire() {
        return idfonctionnaire;
    }

    public void setIdfonctionnaire(int idfonctionnaire) {
        this.idfonctionnaire = idfonctionnaire;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public void add() {
        boolean existe = db.go.checkpointage(idfonctionnaire, jour);
        if(existe){
        Tools.msgBox("Vous avez deja entree ce pointage");
        }else{
        String strInsert = "insert into pointage values('" + idfonctionnaire + "'," + "'" + jour + "'," + "'" + statut + "')"; 
        boolean estAdd = db.go.runNonQuery(strInsert);
        if(estAdd){
            Tools.msgBox("pointage reussit");
        }
    }
    }
    

    @Override
    public void update() {
        Tools.msgBox(" update ne marche pas ");
    }

    @Override
    public void delete() {
        String strDelete = "DELETE FROM pointage" + " WHERE idfonctionnaire=" + idfonctionnaire ;
        boolean estsupprimer = db.go.runNonQuery(strDelete);
        if(estsupprimer){
        Tools.msgBox("Pointage supprimer");
        }
    }

    @Override
    public String getAutoNumber() {
        Tools.msgBox(" getAutoNumber ne marche pas ");
        return "";
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("SELECT fonctionnaires.nom,jour,statut FROM pointage,fonctionnaires WHERE pointage.idfonctionnaire = fonctionnaires.idfonctionnaire ", table);
    }

    @Override
    public void getOneRow(JTable table) {
        Tools.msgBox(" getOneRow ne marche pas ");
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
        Tools.msgBox(" getNameByvalue ne marche pas ");
        return "";
    }
}
