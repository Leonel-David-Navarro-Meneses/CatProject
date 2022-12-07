
package com.mycompany.catproject;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Gatos_Service {
    
    //Renderiza una imagen dentro de un JOptionPane
    public void desplegarImagen(Gatos unGato, ImageIcon img) throws IOException{
        //crear menú d opciones para el JoptionPane
        String menu =  "Opciones: \n"
                + "1. Ver otro gato \n"
                +"2.Regresar \n";
        
        String[] opciones = {"Ver otro gato", "Regresar"};
        
        String idGato  =unGato.getId();
        String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, img, opciones, opciones[0]);
        
        int seleccion = -1;
        for(int i = 0; i<opciones.length; i++){
        if(opcion.equals(opciones[i])){
            seleccion = i;
            
        }
    
    
    switch( seleccion ){
        case 0 -> getGatos();
        default -> {
                break;
    }
    }
    }
    }
    
     // Recupera gatos de The Cat API
    public void getGatos() throws IOException {
        OkHttpClient client = new OkHttpClient();
        
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/images/search")
            .method("GET", null)
            .build();
        Response response = client.newCall(request).execute();
        
        // Crear un objeto con formato JSON
        
        String gatoJson = response.body().string();
        
        // Quitar llave inicial y final
        
        gatoJson = gatoJson.substring( 1, gatoJson.length() );
        gatoJson = gatoJson.substring( 0, gatoJson.length() - 1 );
        
        System.out.println("gatoJson: " + gatoJson);
        
        //Crear un objeto de la clase Gson
        
        Gson gson = new Gson();
        Gatos gato = gson.fromJson( gatoJson, Gatos.class );
        
        Image image = null;
        try {
            URL url = new URL( gato.getUrl() );
            image = ImageIO.read( url );
            
            ImageIcon imgGato = new ImageIcon(image);
            if(imgGato.getIconWidth()>800){
                Image img = imgGato.getImage();
                Image imgModificada = img.getScaledInstance(700, 500, java.awt.Image.SCALE_SMOOTH);
                imgGato = new ImageIcon(imgModificada);
            }
            
            
            desplegarImagen(gato, imgGato);
            
        } catch ( Exception e ) {
            System.out.println("No se pudo crear el objeto Image");
        }
    }
}

