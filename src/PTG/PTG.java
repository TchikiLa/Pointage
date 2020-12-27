/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PTG;

import projet.Departement;
import projet.Fonction;
import projet.Login;
import projet.fonctionnaire;
import projet.pointage;

/**
 *
 * @author Lakhdar
 */
public class PTG {
    public static void main(String[] args){ 
      Tools.openForm(new fonctionnaire());
       Tools.openForm(new Fonction());
        Tools.openForm(new Departement());
        Tools.openForm(new pointage());
        
    }
}
