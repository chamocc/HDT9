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
    private HashMap<Integer, E> tablaInv;
    private ArrayList<ArrayList<Integer>> primeraFila;
    private ArrayList<Nodo<E>>nodos;
    private int cantNodos;
    
    public Grafo(){
        cantNodos=0;
        tabla=new HashMap<E, Integer>(16, 0.4f);
        tablaInv=new HashMap<Integer, E>(16, 0.4f);
        primeraFila=new ArrayList<ArrayList<Integer>>();
        nodos=new ArrayList<Nodo<E>>();
    }
    
    public void agregarNodo(E dato){
        nodos.add(new Nodo<E>(dato));
        tabla.put(nodos.get(cantNodos).getDato(), cantNodos);
        tablaInv.put(cantNodos, nodos.get(cantNodos).getDato());
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
    
    private int[][] hacerMatrizAdj(){
        int[][] matriz=new int[cantNodos+1][cantNodos+1];
        for (int i=0; i<=cantNodos; i++){
            for (int j=0; j<=cantNodos; j++){
                matriz[i][j]=primeraFila.get(i).get(j);
            }
        }
        return matriz;
    }
    
    private int[][] hacerMatrizPath(){
        int[][] path = new int[cantNodos][cantNodos];
        int[][] m=hacerMatrizAdj();
        // Inicializar con el vértice anterior para cada borde. -1 indica
        // no tal vertice.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (m[i][j] == 10000) {
                    path[i][j] = -1;
                } else {
                    path[i][j] = i;
                }
            }
        }

        // Esto significa que no tiene que ir a ninguna parte para ir de i a i.
        for (int i = 0; i < 5; i++) {
            path[i][i] = i;
        }
        return path;
    }
    
    public ArrayList<int[][]> shortestpath(int[][] adj, int[][] path) {

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
        ArrayList<int[][]> resultado=new ArrayList<int[][]>();
        resultado.add(ans);
        resultado.add(path);
        return resultado;
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
    
    public ArrayList<E>floyd (E saleDe, E vaA){
        int[][] path=hacerMatrizPath();
        int[][] adj=hacerMatrizAdj();
        int[][] resultadoPath;
        int[][] resultadoAdj;
        ArrayList<int[][]> lista=shortestpath(adj, path);
        resultadoAdj=lista.get(0);
        resultadoPath=lista.get(1);
        
        int sale=tabla.get(saleDe);
        int llega=tabla.get(vaA);
        ArrayList<Integer> resInt=new ArrayList<Integer>();
        resInt.add(sale);
        while(resultadoPath[llega][sale]!=0){
            int nuevoSale=resultadoPath[llega][sale];
            sale=nuevoSale;
            resInt.add(nuevoSale);
        }
        resInt.add(llega);
        ArrayList<E> listaFinal=new ArrayList<E>();
        for(int i=0;i<resInt.size();i++){
            listaFinal.add(tablaInv.get(resInt.get(i)));
        }
        return listaFinal;
    }
}
