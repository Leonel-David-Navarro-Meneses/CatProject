

package com.mycompany.catproject;

import java.io.IOException;
import javax.swing.JOptionPane;


public class CatProject {

    
        public static void main(String[] args) throws IOException {
         Gatos_Service catService = new Gatos_Service();
        catService.getGatos();
        
        
        int opcionMenu = -1;
        String[] opciones = {
            "1. Ver Gatos",
            "2. Salir"
        };
        
        do {
            String opcion = ( String ) JOptionPane.showInputDialog( null, "Gatitos Java", "Menú Principal", 
                    JOptionPane.INFORMATION_MESSAGE,
                    null, 
                    opciones, 
                    opciones[0] );
        } while ( opcionMenu != 2 );
    }
    }
    

