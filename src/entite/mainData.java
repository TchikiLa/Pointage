/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javax.swing.JTable;

/**
 *
 * @author Lakhdar
 */
public interface mainData {
    public void add();
    public void update();
    public void delete();
    public String getAutoNumber();/*je lui donne tab et le nom de la cle primaire elle me donne le MAX(id)+1 qui n existe pas encore*/
    public void getAllRows(JTable table); /* je luis donne jtable et il rempli la table  */
    public void getOneRow(JTable table); /* je luis donne une ligne et il la rempli auto  */
    public void getCustomRows(String statement, JTable table); /* remplir le tableau avec la phrase de SELECT que je lui avait donner  */
    public String getValueByName(String name); /* je lui donne un nom par exemple il me donne le ID comme dans la table employer  */ 
    public String getNameByvalue(String value); /*  l inverse je lui donne le ID il me donne le nom  */
    

}
