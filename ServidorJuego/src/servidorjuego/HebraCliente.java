package servidorjuego;

import Protocolo.Protocolo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorjuego.BaseDeDatos.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */
public class HebraCliente extends Thread {
    Usuario usuario;
    BufferedReader in;
    PrintWriter out;
    Socket socket;
    HebrasCliente hebras;
    private ServidorJuego server;
    
   public HebraCliente(HebrasCliente hebras,Usuario usuario,Socket socket, BufferedReader in, PrintWriter out,ServidorJuego server) {
       this.usuario = usuario;
       this.in = in;
       this.out = out;
       this.socket = socket;
       this.hebras = hebras;
       this.server = server;
   }

    @Override
   public void run(){
       do{
           
               manejarPeticion();
           
       }while(true);
   }
   
    private void manejarPeticion()  {
        try{
        String peticion = in.readLine();
         
       // System.out.println(peticion);
        String palabras[] = peticion.split(Protocolo.sp);
        
        if(Protocolo.identificarMensaje(peticion) == Protocolo.mensajeChat){
            String mensaje = palabras[1];
            hebras.getServidor().publicarMensajeTCP(usuario.getNombre(),mensaje);
        }
       } catch (IOException ex) {
           //usuario.getNombre()
           try{
           server.borrarUsuarioLista(usuario.getNombre());
           }catch(Exception e){
           }
               Logger.getLogger(HebraCliente.class.getName()).log(Level.SEVERE, null, ex);
           }catch(Exception ex){
                try{
                 server.borrarUsuarioLista(usuario.getNombre());
                }catch(Exception e){
           }
           }
    }
    public PrintWriter getOut(){
        return out;
    }
}
