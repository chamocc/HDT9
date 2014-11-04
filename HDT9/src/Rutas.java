
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Rutas {
    private Grafo<String> ciudades;
    public Rutas(){
        ciudades=new Grafo<String>();
    }
    
    public void abrirCarretera(String ciudad1, String ciudad2, int largo){
        ciudades.setRelacion(ciudad1, ciudad2, largo);
    }
    
    public void cerrarCarretera(String ciudad1, String ciudad2){
        ciudades.setRelacion(ciudad2, ciudad2, 10000);
    }
    
    public void rutaMasCorta(String ciudad1, String ciudad2){
        ArrayList<String> rutaL=ciudades.floyd(ciudad2, ciudad2);
        if(rutaL==null){
            System.out.println("No hay conexion entre las ciudades");
        }else{
            String ruta="";
            for (int i=0; i<rutaL.size();i++){
                ruta+=rutaL.get(i);
                if(i!=rutaL.size()-1){
                    ruta+="->";
                }
            }
            System.out.println(ruta);
        }
    }
    
    public void centro(){
        String centro=ciudades.calcularCentro();
        System.out.println(centro);
    }
    
    public void agregarCiudad(String ciudad){
        ciudades.agregarNodo(ciudad);
    }
}
