/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego;

/**
 *
 * @author alex
 */
public class HebraServicio extends Thread {
    
    ServidorJuego servidor;
    
    public HebraServicio(ServidorJuego servidor){
        this.servidor = servidor;
    }
    @Override
    public void run(){
        servidor.iniciarServicio();
        System.out.println("Servidor rulando ....");
        do{
            servidor.recibirPeticion();
        }while(servidor.getEstado() == ServidorJuego.estadoConexionExitosa);
    }
}
