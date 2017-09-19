/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import servidorjuego.BaseDeDatos.Usuario;

/**
 *
 * @author alex
 */
public class HebrasCliente {
    private ServidorJuego servidor;
    private Usuario usuario;
    private String nombre;
    private int puertoUDPCliente;
    InetAddress inetAddress;
    HebraCliente hebraTCP;
    HebraClienteUDP hebraUDP;
    HebrasCliente(ServidorJuego aThis, Usuario usuario,DatagramSocket socketUDP, int puertoUDPCliente, InetAddress inetAddress,Socket socketCliente,BufferedReader in,PrintWriter out) throws SocketException, UnknownHostException{
        this.servidor = servidor;
        this.usuario = usuario;
        this.nombre = usuario.getNombre();
        this.puertoUDPCliente = puertoUDPCliente;
        this.inetAddress = inetAddress;
        hebraTCP = new HebraCliente(this,usuario,socketCliente,in,out,servidor);
        hebraUDP = new HebraClienteUDP(this,usuario,socketUDP,puertoUDPCliente,inetAddress);
        hebraTCP.start();
        hebraUDP.start();
    }

    public ServidorJuego getServidor() {
        return servidor;
    }

    public void setServidor(ServidorJuego servidor) {
        this.servidor = servidor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuertoUDPCliente() {
        return puertoUDPCliente;
    }

    public void setPuertoUDPCliente(int puertoUDPCliente) {
        this.puertoUDPCliente = puertoUDPCliente;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }
    
    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public HebraCliente getHebraTCP() {
        return hebraTCP;
    }

    public void setHebraTCP(HebraCliente hebraTCP) {
        this.hebraTCP = hebraTCP;
    }

    public HebraClienteUDP getHebraUDP() {
        return hebraUDP;
    }

    public void setHebraUDP(HebraClienteUDP hebraUDP) {
        this.hebraUDP = hebraUDP;
    }

    public synchronized void enviarPaqueteUDP(DatagramPacket dato) {
        hebraUDP.addPaquete(dato);
        //System.out.println("Enviando paquete a "+nombre);
    }
}
