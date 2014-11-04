
//import static PruebaFLoyd.shortestpath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ed. Chamo
 */
public class Main {
    static private ArrayList<String> datos=new ArrayList<String>();
    static private Rutas ciudades=new Rutas();
    public static void main(String[] args) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      Scanner entrada=new Scanner(System.in);
      
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (args[0]);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         int ind=0;
         while((linea=br.readLine())!=null){
            datos.add(linea);
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      //termina codigo tomado dd internet
      llenarGrafo();
      boolean bandera=true;
      while(bandera==true){
            System.out.println("Ingrese Accion a realizar: \n0: Cerrar Carretera\n1: Abrir Carretera\n2: Ruta Corta\n3: Centro\n4: Terminar\n");
            int param=entrada.nextInt();
            entrada.nextLine();
            String ciu1, ciu2;
            switch(param){
                case 0:
                    System.out.println("Ingrese ciudad1, ciudad2");
                    ciu1=entrada.next();
                    entrada.nextLine();
                    ciu2=entrada.next();
                    ciudades.cerrarCarretera(ciu1, ciu2);
                    break;
                case 1:
                    System.out.println("Ingrese ciudad1, ciudad2");
                    ciu1=entrada.next();
                    entrada.nextLine();
                    ciu2=entrada.next();
                    ciudades.cerrarCarretera(ciu1, ciu2);
                    break;
                case 2:
                    System.out.println("Ingrese ciudad1, ciudad2");
                    ciu1=entrada.next();
                    entrada.nextLine();
                    ciu2=entrada.next();
                    ciudades.rutaMasCorta(ciu1, ciu2);
                    break;
                case 3:
                    ciudades.centro();
                    break;
                case 4:
                    bandera=false;
                    break;
            }
      }
      
    }
    
    public static void llenarGrafo(){
        String[] data=new String[3];
        ciudades=new Rutas();
        data=datos.get(0).split(" ");
        for(int i=0; i<data.length; i++){
            ciudades.agregarCiudad(data[i]);
        }
        
        for(int i=1;i<datos.size();i++){
            data=datos.get(i).split(" ");
            System.out.println(Integer.parseInt(data[2]));
            ciudades.abrirCarretera(data[0], data[1],2);
        }
    }
    
}