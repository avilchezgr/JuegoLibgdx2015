/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego;

import Protocolo.Protocolo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import servidorjuego.BaseDeDatos.BaseDeDatos;
import servidorjuego.BaseDeDatos.Usuario;
import Util.Util;
import java.net.DatagramPacket;

/**
 *
 * @author alex
 */
public class ServidorJuego {
   
    public final static int estadoInicial = 0;
    public final static int estadoConexionExitosa = 1;
    public final static int estadoConexionError = 2;
    private int estado;
    private HebraServicioUDP hebraUDP; 
    private int puertoLocalUDP = 9000;
    private String direccionLocalUDP = "localhost"; 
    private ServerSocket serverSocket;
    //List<HebraCliente> listaClientes;
    LinkedHashMap<String,HebrasCliente> listaClientes;
    HebraServicio hebraServ;
    BaseDeDatos datos;
    int puertoTCP;
    private DatagramSocket socketUDP;
    public ServidorJuego(int puertoTCP){
        estado = estadoInicial;
        this.puertoTCP = puertoTCP;
        datos = new BaseDeDatos();
        listaClientes = new LinkedHashMap<String,HebrasCliente>();
        hebraServ = new HebraServicio(this);
        hebraServ.start();
        
    }
    
    public void iniciarSocketUDP() {
        try {
            socketUDP = new DatagramSocket(puertoLocalUDP);
            hebraUDP = new HebraServicioUDP(this,socketUDP);
            hebraUDP.start();
        } catch (SocketException ex) {
            Logger.getLogger(ServidorJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public void iniciarServicio(){
        
        try {
            serverSocket = new ServerSocket(puertoTCP);
            iniciarSocketUDP();
            estado = estadoConexionExitosa;
        } catch (IOException ex) {
            Logger.getLogger(ServidorJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void recibirPeticion(){
        try {
          
            boolean clienteValido = false;
            Socket socketCliente =  serverSocket.accept();
            PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            String peticion=in.readLine();
            if(Protocolo.identificarMensaje(peticion) == Protocolo.mensajeIdentificacionCliente ){
                
                String palabras []=peticion.split(Protocolo.sp);
                
                if(palabras.length>1){
                    
                    String nombre = palabras[1];
                    String clave = palabras[2];
                    int portUDPCliente = Integer.parseInt(palabras[3]);
                    System.out.println("Recibido usuario con nombre "+nombre+" y clave "+clave);
                    if(datos.coincideLoginPassUsuario(nombre, clave)){
                        
                      Usuario usuario =  datos.getUsuarioNombre(nombre);
                        int id = usuario.getId();
                        float posX = usuario.getPosX();
                        float posY = usuario.getPosY();
                        
                        clienteValido = true;
                        System.out.println(Protocolo.mensajeConexionOK(id, posX, posY,usuario.getNFoto(),usuario.getTipoPersonaje()));
                        out.println(Protocolo.mensajeConexionOK(id, posX, posY,usuario.getNFoto(),usuario.getTipoPersonaje()));
                        out.flush();
                       
                       // HebraCliente hebraTCP = new HebraCliente(this,usuario,socketCliente,in,out);
                        //HebraClienteUDP hebraUDP = new HebraClienteUDP(this,usuario,puertoUDPCliente,);
                      //  hebraTCP.start();
                        System.out.println(socketCliente.getInetAddress().getHostAddress());
                        System.out.println("Puerto UDP "+portUDPCliente);
                       // HebrasCliente hebras = new HebrasCliente(this, usuario,socketUDP, puertoUDPCliente, socketCliente.getInetAddress(),socketCliente,in,out);
                        HebrasCliente hebras = new HebrasCliente(this, usuario,socketUDP, portUDPCliente, socketCliente.getInetAddress(),socketCliente,in,out);
                        
                        listaClientes.put(nombre,hebras);
                        //listaClientes.add(hebra);
                        
                        System.out.println("Creando hebra...");
                        System.out.println("Cliente "+nombre+" se ha conectado");
                    }
                }   
            }if(!clienteValido){
                
                out.println(Protocolo.mensajeConexionNoOK(404));
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void publicarMensajeTCP(String nombre,String mensaje) throws Exception{
        ArrayList<HebrasCliente> valueList = new ArrayList<>(listaClientes.values());
        for(int i=0;i<valueList.size();i++){
            publicarMensajeAUsuario(nombre,mensaje,valueList.get(i).getHebraTCP());
        }
    }
    public synchronized void borrarUsuarioLista(String nombre){
        listaClientes.remove(nombre);
        System.out.println(nombre + " Se ha desconectado");
    }
    public synchronized void publicarMensajeAUsuario(String nombre, String mensaje, HebraCliente hebra){
        try{
        hebra.getOut().println(Protocolo.mensajeDifundirChat(nombre,mensaje));
        hebra.getOut().flush();
        }catch(Exception ex){
            System.err.println("error al enviar mensaje al cliente");
        }
    }
    
    public int getEstado(){
        return estado;
    }  
    public static void main(String[] args) {
        // TODO code application logic here
        ServidorJuego server = new ServidorJuego(8888);
        
    }
    public synchronized void manejarPaqueteUpdate(DatagramPacket p){
        byte[] bytes = p.getData();
        //si paquete repetido o anterior al estado que tenemos guardado descartamos
        //if()
        //si no actualizamos estado
        //else
        //actualizar base de datos
        
        
       // int puertoCliente =p.getPort();
        // direccionCliente = p.getAddress().toString();
        
    }
    public synchronized void manejaarPaqueteUDP(byte[] bytes) {
     // String paquete = Util.openFileToString(bytes);
     // String palabras[] = paquete.split(".");
     // String nombre = palabras[1];
      ArrayList<HebrasCliente> lista = new ArrayList<>(listaClientes.values());
      for(int i = 0; i<lista.size(); i++){
         
          enviarPaqueteAUsuario(i,lista,bytes);
      }
      
    }
    public void enviarPaqueteAUsuario(int usuario,ArrayList<HebrasCliente> lista,byte[] bytes){
        
            HebrasCliente user = lista.get(usuario);
        try {
            
            DatagramPacket dato = new DatagramPacket(bytes,bytes.length,user.getInetAddress(),user.getPuertoUDPCliente());
           
            user.enviarPaqueteUDP(dato);
            
        
        }catch(Exception e){
            System.err.println("usuario no encontrado");
            listaClientes.remove(user.getNombre());
        }
    }
}
