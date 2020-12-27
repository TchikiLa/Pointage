/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import PTG.Tools;
import PTG.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lakhdar
 */
public class go {
     private static String url;
    private static Connection con;
    
    //preparer l URL
    private static void setURL(){
        url = "jdbc:mysql://localhost:3306/ptg"
                + "?useUnicode=true&caracterEncoding=UTF-8";
    }
    //connection a la BDD
    private static void setConnection(){
        
        try {
            setURL();
            con = DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
        }
        
        
    }
    //Verification des uusername et le mot de passe bayna 
    public static boolean CheckUserAndPass(String username, String password){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            String strCheck = "select * from user_pass where " + "nom_utilisateur='" + username + "' and " + "mot_pass='" + password + "'";
            stmt.executeQuery(strCheck);
            while( stmt.getResultSet().next() ){
                con.close();/* si il recoit les donner il ferme la connection et retourne true*/
                return true;
            }
            con.close(); /* sinon il ferme la connection et retourne false */
        }catch(SQLException ex){
            Tools.msgBox((ex.getMessage()));
        }
        return false;
        
    }
    public static boolean checkpointage(int idfonctionnaire, String jour){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            String strCheck = "select * from pointage where idfonctionnaire=" + idfonctionnaire + " AND " +  "jour='" + jour + "'";
            stmt.executeQuery(strCheck);
            while( stmt.getResultSet().next() ){
                con.close();/* si il recoit les donner il ferme la connection et retourne true*/
                return true;
            }
            con.close(); /* sinon il ferme la connection et retourne false */
        }catch(SQLException ex){
            Tools.msgBox((ex.getMessage()));
        }
        return false;
        
    }
    //methode qi resoit un ordre et l applique sur la base de donnee si l ordre est applique elle return true sinon elle retourne false
    //son role est de faire l insertion suppression et MAJ et non la selection
    public static boolean runNonQuery(String sqlStatement){
        try{
            setConnection();  
            Statement stmt = con.createStatement();
            stmt.execute(sqlStatement);
            con.close();
            return true;
        }catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
            return false;
        }
    }
    //je le donne le nom du tableau et le nom de la colone de la clée primaire elle return le id MAX+1 qi n est pas encore difini (vide)
    // si id max = 5 et on a supprime 2 colone elle nou donne 6 qui n existee pas donc 4 et 5 rest vide 
    //si la base est vide elle return 1
    public static String getAutoNumber(String nomTable, String Nomcolone){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            String strAuto = "select max(" + Nomcolone + ")+1 as autonum" + " from " + nomTable;
            stmt.executeQuery(strAuto);
            String Num = "";
            while(stmt.getResultSet().next()){
                Num = stmt.getResultSet().getString("autonum");
            }
                con.close();
                //si Num == NULL ou vide ("") donc c est le premier num de la table (table vide)
                if(Num == null || "".equals(Num)){
                    //pour reinisialiser l'identifiant
                    //requete(alter table + "nom table" + " auto_increment=0"
                    return "1";
                }else{
                    return Num;
                }    
        }catch(SQLException ex){
            Tools.msgBox(ex.getMessage());    
            return "0";
        }
    }

    
    // methode qui resoit une requete sql et retourne un  tableau rempli de donnee
    public static Table getTableData(String Statement){
        Tools t = new Tools();
        try{
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(Statement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount(); //on a recuperer le nembre de colone pour pouvoire cree le tableau
            
            Table table = t.new Table(c);
            // il faut avoire un tableau repli pour pouvoir instancier les table de type tabl
            while(rs.next()){
                Object row[] = new Object[c];
                //charger le tableau d objet par les resultat de ligne colone par colone (tabl 0 = col 1 , tab 1= col 2 ...
                // le tableau commance par 0
                for(int i = 0 ; i < c; i++){
                    row[i] = rs.getString(i + 1);
                }
                table.addNewRow(row);
            }
            con.close();
            return table;
        }catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
            return t.new Table(0);
        }
    }
    //methode qui resoit le nom d un tableau et nom de colone de tableau et un combobox (donc si je lui donne un tableau employe et une colone nom il va ecrire
    // sur le combo box nom1 nom2 nom3 ...
    public static void fillcombo(String tableName, String columnName, JComboBox combo){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            
            String strSelect = "select " + columnName + " from " + tableName;
            rs = stmt.executeQuery(strSelect);//si on fait rs.getrow il ne donne pas le numero actuele car il est posisionne avant le num actuel 
            rs.last();//elle va posisionne le pointeur (malgre c est pas un pointeur mais bon rak 3aref wachno ;) ) dans le dernier ligne 
            int c = rs.getRow(); // elle nous donne le dernier ligne qui est posicionne sur lui 
            rs.beforeFirst();// elle va le repositionnee avant le debut :) on pris wach lazem (nembre de ligne )
            rs.beforeFirst();
            String values[] = new String[c];//on va la charger avec les donnee pour la metre dans le combo
            int i = 0;
            while(rs.next()){               
                values[i] = rs.getNString(1);
                i++;
            }
            con.close();
            //on faut un nouveau model de combeau et recoit les valeur (charge le setmodel et charge combobox par les valeurs qui sont deja remplis dans Values
            combo.setModel(new DefaultComboBoxModel(values));
        }catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
        }
    }
    // une methode qui prendre en parametre une requete ******ou***** un nom de tableau et prend Jtable et lui rempli 
    public static void fillToJTable(String tabNomourequete, JTable table){
    try{
        setConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        // jai besoin d un filtre pour savoire si tabNomourequete est un tableau ou une requete
        // si il m envoi |SELECT | == select avec espace je le prend comme requete sinon comme tableau 
        String spart = tabNomourequete.substring(0,7).toLowerCase();
        String strSelect;
        if( "select ".equals( spart )){
            strSelect = tabNomourequete;
        }else{
            strSelect = "select * from " + tabNomourequete;
        }
        rs = stmt.executeQuery(strSelect);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int c = rsmd.getColumnCount();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        
        Vector row = new Vector();// ce type vector est apepppppre similaire a la classe TABLE que j ai deja difinit dans la classe tools et ****
                                  // Avec Vecteur on peut remplir le Model en l instanciann comme ca model.addrow(Vector v); rak chayaf kifh l3a9liya 
      //supprimer tout les ligne soit fihe wala mafihche 
        for (int i = model.getRowCount() - 1; i > -1; i--) {
        model.removeRow(i);
    }
        while(rs.next()){
            row = new Vector(c); //chake tour il recoit uun nouveau vecteur donc chaque tour il se reinnisialise  
            for(int i = 1 ; i <= c ; i++){
                row.add(rs.getString(i));
            }
            //apres le remplissage du VECTOR on rempli le model
            model.addRow(row);
        }
        //et SI le nembre de colone 'c' resultat rsmd different de jtable entree en parametre je doit ecrire un mssg d erreur 
        if(table.getColumnCount() != c){
            Tools.msgBox("nombre de colonne de JTable n'egale pas le nembre de colonne du query");
        }
        con.close();
        
    }catch(SQLException ex){
        Tools.msgBox(ex.getMessage());
    }
    }
}
