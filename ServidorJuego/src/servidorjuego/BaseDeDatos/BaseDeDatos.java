/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego.BaseDeDatos;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author alex
 */
public class BaseDeDatos {
    private Map <String,Usuario> listaUsuarios;
    
    public BaseDeDatos(){
        listaUsuarios = LectorDatos.getListaUsuarios();
        System.out.println(listaUsuarios.size());
    }
    
    public Map getListaUsuarios(){
        return listaUsuarios;
    }
    public Usuario getUsuarioID(int id){
        
        return listaUsuarios.get(id);
    }
    public Usuario getUsuarioNombre(String nombre){
        Usuario resultado = null;
        resultado = listaUsuarios.get(nombre);
        /*ArrayList<Usuario> valueList = new ArrayList<>(listaUsuarios.values());
        for(int i=0;i<listaUsuarios.size();i++){
                    Usuario usu = valueList.get(i);
                    System.out.println("probando "+usu.getNombre());
                   if(usu.getNombre().equals(nombre)){
                       resultado = usu;
                       break;
                   }*/
        
        return resultado;
    }
    public boolean existeUsuario(String nombre){
        boolean valor = false;
        if(getUsuarioNombre(nombre)!=null)
            valor = true;
        
        return valor;
    }
     public boolean coincideLoginPassUsuario(String nombre,String clave){
        boolean valor = false;
        Usuario usuario = getUsuarioNombre(nombre);
        
        if(usuario != null && usuario.getClave().equals(clave)){
            valor = true;
        }
        return valor;
    }
    public void cambiarPosicionUsuario(String usuario,float x,float y){
        
       Usuario usu = listaUsuarios.get(usuario);
       usu.setPosX(x);
       usu.setPosY(y);
       System.out.println("posicion del usuario "+usuario+" cambiada con exito");
    }
    
    
    
}
