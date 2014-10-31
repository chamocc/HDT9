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
    
    public int[][] shortestpath(int[][] adj, int[][] path) {

        int n = adj.length;
        int[][] ans = new int[n][n];

        // Implementar el algoritmo en una matriz de copia de modo que la adyacencia no es
        //destruido.
        copy(ans, adj);

        // Calcular rutas sucesivamente a través de una mejor k vértices.
        for (int k = 0; k < n; k++) {

            // Es así que entre cada par de puntos posibles.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {


                    if (ans[i][k] + ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k] + ans[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        // Devuelva la matriz camino más corto.
        return ans;
    }

    //Copia el contenido del array b en un array. Se asume que tanto a como
    //B es una matriz 2D de dimensiones idénticas.
    public void copy(int[][] a, int[][] b) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    // Devuelve el menor de a y b.
    public int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }
    
}
