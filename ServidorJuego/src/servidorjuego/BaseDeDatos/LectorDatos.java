/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego.BaseDeDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class LectorDatos {
     private static String nombreFichero = "src/datos.datos";
     
     public static LinkedHashMap getListaUsuarios(){
       LinkedHashMap <String,Usuario> mapa= new LinkedHashMap();
            FileReader fr = null;
            try {
            
            BufferedReader br;
          //  File archivo = new File ("C:\\Users\\alex\\Desktop\\archivo.txt");
            //System.out.println("Working Directory = " +
              //System.getProperty("user.dir"));
            
            File archivo = new File (nombreFichero);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            Usuario usuario;
            String nombre;
            String clave;
            int id;
            int tipoPersonaje;
            float posX;
            float posY;
            String aux;
            int nFoto;
            do{

               // System.out.println(linea);
                nombre = br.readLine();
                clave = br.readLine();
                aux = br.readLine();
                tipoPersonaje = Integer.parseInt(aux);
                aux = br.readLine();
                id = Integer.parseInt(aux);
                aux = br.readLine();
                posX = Float.parseFloat(aux);
                aux = br.readLine();
                posY = Float.parseFloat(aux);
                nFoto = Integer.parseInt(br.readLine());
                System.out.println("posicion " +posX+" "+posY);
                usuario = new Usuario(nombre,clave,id,posX,posY,nFoto,tipoPersonaje);
                System.out.println("nombre "+ usuario.getNombre());
                System.out.println("clave "+ usuario.getClave());
                System.out.println("id " + usuario.getId());
                System.out.println("nFoto " + nFoto);
                
                System.out.println();
                //System.out.println(pregunta +"----");
                // System.out.su(question.getPregunta()+"----");
                mapa.put(nombre, usuario);
                //System.out.println(question.toString());
                //i=1;
               // if(i!=0){
                    
                //}
                //String limitador=br.readLine();
            }while(br.readLine()!=null);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LectorDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LectorDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                System.out.println("fichero cerrado con exito");
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(LectorDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mapa;
     }
}
