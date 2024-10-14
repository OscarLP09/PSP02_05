import java.util.ArrayList;
import java.util.List;
public class Nodo extends Thread {
    private List<Integer> lista;
    private List<Integer> listaOrdenada;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    public void run() {
        if (lista.size() <= 1) {
            listaOrdenada = lista;  // La lista ya está ordenada
        } else {
            int mitad = lista.size() / 2;
            // Crear dos sublistas
            List<Integer> listaIzquierda = new ArrayList<>(lista.subList(0, mitad));
            List<Integer> listaDerecha = new ArrayList<>(lista.subList(mitad, lista.size()));
            // Crear dos nodos hijos
            Nodo nodoIzquierda = new Nodo(listaIzquierda);
            Nodo nodoDerecha = new Nodo(listaDerecha);
            // Iniciar las hebras
            nodoIzquierda.start();
            nodoDerecha.start();
            // Esperar a que terminen
            try {
                nodoIzquierda.join();
                nodoDerecha.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Mezclar las dos mitades ordenadas
            listaOrdenada = mezcla(nodoIzquierda.getListaOrdenada(), nodoDerecha.getListaOrdenada());
        }
    }

    // Método para obtener la lista ordenada
    public List<Integer> getListaOrdenada() {
        return listaOrdenada;
    }

    // Método para mezclar dos listas ordenadas
    private List<Integer> mezcla(List<Integer> l1, List<Integer> l2) {
        List<Integer> resultado = new ArrayList<>();
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                resultado.add(l1.get(i));
                i++;
            } else {
                resultado.add(l2.get(j));
                j++;
            }
        }
        // Añadir los elementos restantes
        while (i < l1.size()) {
            resultado.add(l1.get(i));
            i++;
        }
        while (j < l2.size()) {
            resultado.add(l2.get(j));
            j++;
        }
        return resultado;
    }
}
