/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego;

import Protocolo.Protocolo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorjuego.BaseDeDatos.Usuario;
import Util.Util;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alex
 */
class HebraClienteUDP extends Thread{
    
    private HebrasCliente hebras;
    private Usuario usuario;
    int puertoUDPCliente;
    InetAddress inetAddress;
    DatagramSocket socket;
    List<DatagramPacket> bufferPaquetes;
    HebraClienteUDP() {
      
    }
    
    HebraClienteUDP(HebrasCliente hebras, Usuario usuario,DatagramSocket socket, int puertoUDPCliente, InetAddress inetAddress) {
      this.hebras = hebras;
      this.usuario = usuario;
      this.puertoUDPCliente = puertoUDPCliente;
      this.inetAddress = inetAddress;
      this.socket = socket;
      this.bufferPaquetes = new ArrayList<>();
       
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(HebraClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (bufferPaquetes.size() > 0) {
                   
                    int a = enviar(bufferPaquetes.get(0));
                   // if(a==1){
                        bufferPaquetes.remove(0);
                    //  System.out.println("ok");
                   // }
                    
                }
           

        }
    }

    int enviar(DatagramPacket paquete)  {

        try {
            
            socket.send(paquete);
           // String palabras[] = Util.openFileToString(paquete.getData()).split(Protocolo.sp);
            //if(palabras[0].equals("F0")){
            //System.out.println(Util.openFileToString(paquete.getData()));
            //}
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(HebraClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
            
            return 0;
        }
    }

    public  void addPaquete(DatagramPacket paquete) {
        bufferPaquetes.add(paquete);
    }
}
