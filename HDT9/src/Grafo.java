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
    
    public void setRelacion(E saleDe, E vaA, int peso){
        int numSale, numVa;
        numSale=tabla.get(saleDe);
        numVa=tabla.get(vaA);
        
        primeraFila.get(numVa).set(numSale, peso);
    }
    
    private int[][] hacerMatriz(){
        int[][] matriz=new int[cantNodos+1][cantNodos+1];
        for (int i=0; i<=cantNodos; i++){
            for (int j=0; j<=cantNodos; j++){
                matriz[i][j]=primeraFila.get(i).get(j);
            }
        }
        return matriz;
    }
    
}
