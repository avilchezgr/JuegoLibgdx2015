/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego.BaseDeDatos;

/**
 *
 * @author alex
 */
public class Usuario {
    String nombre;
    String clave;
    int id;
    float posX;
    float posY;
    float vida;
    int accion;
    int nFoto;
    int tipoPersonaje;
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
    
    public Usuario(String nombre, String clave, int id, float posX, float posY,int nFoto,int tipoPersonaje){
        this.nombre = nombre;
        this.clave = clave;
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.nFoto = nFoto;
        this.tipoPersonaje = tipoPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public int getId() {
        return id;
    }
    public int getNFoto(){
        return nFoto;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
    public int getTipoPersonaje(){
        return tipoPersonaje;
    }        
}
