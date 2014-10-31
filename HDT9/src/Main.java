
//import static PruebaFLoyd.shortestpath;
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
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        PruebaFloyd prueba;
        prueba = new PruebaFloyd();
        
        // Pruebas fuera con algoritmo gráfico mostrado en clase.
        int[][] m = new int[5][5];
        m[0][0] = 0;
        m[0][1] = 3;
        m[0][2] = 8;
        m[0][3] = 10000;
        m[0][4] = -4;
        m[1][0] = 10000;
        m[1][1] = 0;
        m[1][2] = 10000;
        m[1][3] = 1;
        m[1][4] = 7;
        m[2][0] = 10000;
        m[2][1] = 4;
        m[2][2] = 0;
        m[2][3] = 10000;
        m[2][4] = 10000;
        m[3][0] = 2;
        m[3][1] = 10000;
        m[3][2] = -5;
        m[3][3] = 0;
        m[3][4] = 10000;
        m[4][0] = 10000;
        m[4][1] = 10000;
        m[4][2] = 10000;
        m[4][3] = 6;
        m[4][4] = 0;

        int[][] shortpath;
        int[][] path = new int[5][5];

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

        shortpath = prueba.shortestpath(m, path);

        // Imprime distancias más cortas.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(shortpath[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("De dónde a dónde usted quiere encontrar el camino más corto?(0 to 4)");
        int start = stdin.nextInt();
        int end = stdin.nextInt();

        // La ruta finalizará siempre en fin.
        String myPath = end + "";

        // Recorrer cada vértice anterior hasta que vuelvas a empezar.
        while (path[start][end] != start) {
            myPath = path[start][end] + " -> " + myPath;
            end = path[start][end];
        }

        // Sólo tiene que añadir comienzo de nuestra cadena y de impresión.
        myPath = start + " -> " + myPath;
        System.out.println("Este es el camino " + myPath);
        // TODO code application logic here
    }
}
