/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego;

import Util.Util;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class HebraServicioUDP extends Thread {
    public final static int MAXSIZEPACKET = 55; //BYTES
    private ServidorJuego servidor;
    private DatagramSocket socket;
    
    HebraServicioUDP(ServidorJuego servidor, DatagramSocket socket){
        this.servidor = servidor;
        this.socket = socket;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                DatagramPacket p = new DatagramPacket(new byte[MAXSIZEPACKET],MAXSIZEPACKET);
                socket.receive(p);
                byte[] bytes = p.getData();
             // System.out.println(Util.openFileToString(bytes));
                servidor.manejarPaqueteUpdate(p);
            } catch (IOException ex) {
                Logger.getLogger(HebraServicioUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    
    }
}
