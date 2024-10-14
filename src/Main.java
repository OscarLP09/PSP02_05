import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear una lista de números aleatorios
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lista.add((int) (Math.random() * 100));
        }
        // Crear el nodo raíz y ordenar la lista
        Nodo nodoRaiz = new Nodo(lista);
        nodoRaiz.start();
        try {
            nodoRaiz.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Imprimir la lista ordenada
        System.out.println(nodoRaiz.getListaOrdenada());
    }
}