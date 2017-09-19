/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocolo;

/**
 *
 * @author alex
 */
public class Protocolo {
	
	 public final static int mensajeIdentificacionCliente = 0;
	    public final static int mensajeConexionOK = 1;
	    public final static int mensajeConexionNOOK = 2;
	    public final static int mensajeChat = 3;
	    public final static int mensajePubChat = 4;
	    public final static String sp = "<";
	    public final static String cabFlecha = "F0";
	    public final static String cabAct = "A0";
	    public final static String cabTorrentacle = "M0";
		/**
		 * 
		 * CLIENTE CLIENTE CLIENTE CLIENTE CLIENTE CLIENTE CLIENTE
		 * */
		
		public static String mensajeIdentificacionCliente(String nombre, String clave,int puertoUDP){
			
			String mensaje = "HI"+sp+nombre+sp+clave+sp+puertoUDP;
			return mensaje;
		}
	        public static String mensajeChat(String mensaje){
	            return "CHAT"+sp+mensaje;
	        }
		
		/**
		 * 
		 * SERVIDOR SERVIDOR SEVIERDO SERVIDOR SERVIDOR SERVIDOR
		 * 
		 * */
		public static String mensajeConexionOK(int id,float posX, float posY,int nFoto,int tipoPersonaje){
			String mensaje = "OK"+sp+id+sp+posX+sp+posY+sp+nFoto+sp+tipoPersonaje;
			return mensaje;
		}
		public static String mensajeConexionNoOK(int codigoError){
			String mensaje = "NOOK"+sp+codigoError;
			return mensaje;
		}
	        public static String mensajeDifundirChat(String nombre,String mensaje){
	            String peticion = "PUB"+sp+nombre+sp+mensaje;
	            return peticion;
	        }
	        public static String formarMensajeChat(String solicitud){
	            String palabras[] = solicitud.split(sp);
	            String mensaje = palabras[1]+">"+palabras[2];
	            return mensaje;
	        }
	        /**
	         * Servidor
	         */
	        public static int identificarMensaje(String mensaje){
	            String[]palabras = mensaje.split(sp);
	            int tipo = -1;
	            if(palabras[0].equals("HI")){
	                tipo = mensajeIdentificacionCliente;
	            }else if(palabras[0].equals("CHAT")){
	                tipo = mensajeChat;
	            }
	            return tipo;
	        }
	        
	        /**
	         * Cliente
	         */
	        
	        public static int identificarMensajeCliente(String mensaje){
	            String[]palabras = mensaje.split(sp);
	            int tipo = -1;
	            if(palabras[0].equals("PUB")){
	                tipo = mensajePubChat;
	            }else if(palabras[0].equals("OK")){
	            	
	            	tipo = mensajeConexionOK;
	            }
	            return tipo;
	        }
	        public static String formarMensajeActualizacion(String nombre,int x, int y,int nFoto,int fila,int columna,int vida){
	        	
	        	String mensaje = "A0"+sp+nombre+sp+x+sp+y+sp+nFoto+sp+fila+sp+columna+sp+vida+sp+111;
	        	return mensaje;
	        }
	        public static String generarFlecha(String nombre, float xInicial, float yInicial, float xObj, float yObj, int direccion, int damage, float angulo){
	        	String mensaje = "F0"+sp+nombre+sp+xInicial+sp+yInicial+sp+xObj+sp+yObj+sp+direccion+sp+damage+sp+angulo+sp+111;
	        	return mensaje;
	        }
	        public static String generarTorrentacle(String nombre, float x, float y, int damage){
	        	String mensaje = "M0"+sp+nombre+sp+x+sp+y+sp+damage+sp+111;
	        	return mensaje;
	        }
	        
	        public static String getNombreActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return palabras[1];
	        }
	        public static int getXActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[2]);
	        }
	        public static int getYActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[3]);
	        }
	        public static int getNFotoActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[4]);
	        }
	        public static int getColumnaActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[6]);
	        }
	        public static int getFilaActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[5]);
	        }
	        public static int getVidaActualizacion(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[7]);
	        	//return 1000;
	        }
	        //String mensaje = "F0"+sp+nombre+sp+xInicial+sp+yInicial+sp+xObj+sp+yObj+sp+direccion+sp+damage+sp+angulo;
	        
	        public static String getNombreFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return palabras[1];
	        }
	        public static float getXInicialFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[2]);
	        	
	        }
	        public static float getYInicialFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[3]);
	        	
	        }
	        public static float getXObjFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[4]);
	        	
	        }
	        public static float getYObjFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[5]);
	        	
	        }
	        public static int getDireccionFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[6]);
	        	
	        }
	        public static int getDamageFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	System.out.println("problema "+mensaje);
	        	return Integer.parseInt(palabras[7]);
	        	
	        }
	        public static float getAnguloFlecha(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[8]);
	        }
	        public static String getCabecera(String mensaje){
	        	String palabras[] = mensaje.split(sp);
	        	return palabras[1];
	        }
			public static String getNombreTorrentacle(String mensaje) {
				// TODO Auto-generated method stub
				String palabras[] = mensaje.split(sp);
	        	return palabras[0];
			}
			public static int getDamageTorrentacle(String mensaje) {
				// TODO Auto-generated method stub
				String palabras[] = mensaje.split(sp);
	        	return Integer.parseInt(palabras[4]);
			}
			public static float getYTorrentacle(String mensaje) {
				// TODO Auto-generated method stub
				String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[3]);
			}
			public static float getXTorrentacle(String mensaje) {
				// TODO Auto-generated method stub
				String palabras[] = mensaje.split(sp);
	        	return Float.parseFloat(palabras[2]);
			}
}
//tipo mensaje
//mensaje actualizacion de posicion
//mensaje accion
