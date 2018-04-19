package mx.com.itam.adsi.ejercicio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Clase de tarea para contar lineas ejecutables y lineas de comentarios en un codigo java
 * @version 1.0
 * @author Miguel Reyes
 */
public class Ejercicio {

	private final static Logger LOG = Logger.getLogger(Ejercicio.class);
	
	/**
	 * Esta funcion cuenta todas las lineas no vacias del codigo
	 * @param archivo - el archivo a analizar
	 * @return lineasTotales - el total de lineas vacias
	 */
	public static int lineasNoVacias(String archivo){
		int lineasTotales = 0;

		BufferedReader br = null;

		String lineaActual;

		try{
			//cosas necesarias para leer el archivo
                        InputStream is = Ejercicio.class.getResourceAsStream(archivo);
			br = new BufferedReader(new InputStreamReader(is));

			//mientras siga habiendo lineas solo checamos que tenga texto
			while((lineaActual = br.readLine()) != null) {
				if(!lineaActual.trim().isEmpty()){
					lineasTotales++;
				}
			}

		}catch(IOException e){
			e.printStackTrace();
		}

		return lineasTotales;
	}

	/**
	 * Esta funcion cuenta las lineas que tienen tanto comentarios, como ejecutables al mismo tiempo
	 * @param archivo - el archivo a analizar
	 * @return lineasTotales - total lineas repetidas
	 */
	public static int lineasRepetidas(String archivo){
		int lineasTotales = 0;

		BufferedReader br = null;

		String lineaActual;

		try{
			//cosas necesarias para leer el archivo
                        InputStream is = Ejercicio.class.getResourceAsStream(archivo);
			br = new BufferedReader(new InputStreamReader(is));

			//vemos que sigan habiendo lineas
			while((lineaActual = br.readLine()) != null) {
				/*eliminamos lo que este dentro de comillas 
				 con el fin de no contar simbolos // o /* como comentarios*/
				lineaActual = eliminarComillas(lineaActual);
				if(!lineaActual.trim().isEmpty()){
					//vemos que las lineas tengan comentarios pero tambien ejecutables
					if(lineaActual.contains("//") || lineaActual.contains("/*")){
						if(!lineaActual.trim().substring(0,1).equals("/")){
							lineasTotales++;
						}
					}else if(lineaActual.contains("*/")){
						if(!lineaActual.trim().substring(lineaActual.trim().length()-1).equals("/")){
							lineasTotales++;
						}
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return lineasTotales;
	}
	
	/**
	 * Esta funcion cuenta todas las lineas que tengan comentarios
	 * @param archivo - el archivo a analizar
	 * @return lineasTotales - numero de lineas con comentarios
	 */
	public static int lineasComentarios(String archivo){
		int lineasTotales = 0;

		BufferedReader br = null;

		String lineaActual;

		try{
			//cosas necesarias para leer el archivo
                        InputStream is = Ejercicio.class.getResourceAsStream(archivo);
			br = new BufferedReader(new InputStreamReader(is));

			while((lineaActual = br.readLine()) != null) {
				lineaActual = eliminarComillas(lineaActual);
				//de la misma manera que la funcion anterior pero solo nos fijamos que tengan comentarios
				if(lineaActual.contains("//")){
					lineasTotales++;
				}else if(lineaActual.contains("/*")){
					lineasTotales++;
					if(lineaActual.contains("*/")){
						
					}else{
						while((lineaActual = br.readLine()) != null && !lineaActual.contains("*/")){
							lineasTotales++;
						}
						lineasTotales++;
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return lineasTotales;
	}

	/**
	 * Esta funcion cuenta las lineas de comentarios que no tienen texto
	 * @param archivo - el archivo a analizar
	 * @return lineasTotales - numero de lineas de comentarios vacias
	 */
	public static int cuentaComentariosVacios(String archivo){
		int lineasTotales = 0;

		BufferedReader br = null;

		String lineaActual;

		try{
			//cosas necesarias para leer el archivo
                        InputStream is = Ejercicio.class.getResourceAsStream(archivo);
			br = new BufferedReader(new InputStreamReader(is));

			while((lineaActual = br.readLine()) != null) {
				//contamos todas las lineas dentro de un comentario multilinea que no tengan texto
				if(lineaActual.contains("/*")){
					if(!lineaActual.contains("*/")){
						while((lineaActual = br.readLine()) != null && !lineaActual.contains("*/")){
							if(lineaActual.trim().isEmpty()){ lineasTotales++;}
						}
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return lineasTotales;
	}
	
	/**
	 * Esta funcion calcula el numero de lineas ejecutables
	 * @param archivo - el archivo a analizar
	 * @return el total de lineas ejecutables
	 */
	public static int lineasEjecutables(String archivo){
		//solo es necesario llamar a funciones ya creadas
		return lineasNoVacias(archivo) + lineasRepetidas(archivo) - lineasComentarios(archivo) + cuentaComentariosVacios(archivo);	
	}

	/**
	 * Esta funcion calcula el numero de lineas totales tanto ejecutables, como comentarios
	 * @param archivo - el archivo a analizar
	 * @return total de lineas que tienen o comentarios o ejecutables o ambos
	 */
	public static int lineasTotales(String archivo){
		//solo es necesario llamar a funciones ya creadas
		return lineasNoVacias(archivo) + cuentaComentariosVacios(archivo);
	}

	/**
	 * Toma un texto y le quita todo lo que este dentro de comillas 
	 * @param str - cadena de texto
	 * @return respuesta - la cadena sin las cosas que esten detro de comillas
	 */
	public static String eliminarComillas(String str){
		//separamos el string en varios strings a traves de las comillas
		String[] linea = str.split('"'+"");
		StringBuilder respuesta = new StringBuilder();
		
		//incluimos las posiciones pares solamente
		for(int i = 0; i < linea.length; i+=2){
			respuesta.append(linea[i]);
		}
		
		return respuesta.toString(); 
	}

	public static void main(String...argv){
		Ejercicio e = new Ejercicio();

		LOG.info("lineas totales (Archivo01.java): " + e.lineasTotales("/Archivo01.java"));
		LOG.info("lineas ejecutables (Archivo01.java): " + e.lineasEjecutables("/Archivo01.java"));
		LOG.info("lineas comentarios (Archivo01.java): "+ e.lineasComentarios("/Archivo01.java"));

		
	}
}
