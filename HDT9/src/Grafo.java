import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Grafo<E> {
    private HashMap<E, Integer> tabla;
    private ArrayList<ArrayList<Integer>> primeraFila;
    private ArrayList<Nodo<E>>Nodos;
    private int cantNodos;
    
    public Grafo(){
        cantNodos=0;
    }
    
    public void agregarNodo(E dato){
        Nodos.add(new Nodo<E>(dato));
        tabla.put(Nodos.get(cantNodos).getDato(), cantNodos);
        ArrayList<Integer> nuevaLista=new ArrayList<Integer>();
        primeraFila.add(nuevaLista);
        for (int i=0; i<=cantNodos-1;i++){
            primeraFila.get(cantNodos).add(10000);
        }
        for (int i=0; i<=cantNodos; i++){
            primeraFila.get(i).add(10000);
        }
        cantNodos++;
    }
    
}
