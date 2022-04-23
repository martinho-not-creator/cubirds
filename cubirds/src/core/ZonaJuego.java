package core;

import java.util.ArrayList;
import java.util.List;

import pila.EnlazadaPila;
import pila.Pila;

public class ZonaJuego<E> {

    private List<Pila<E>> zona;

    public ZonaJuego() {
        zona = new ArrayList<Pila<E>>();
    }

    public int existePilaAve(E elemento) {
        for (int i = 0; i < zona.size(); i++) {
            Pila<E> pila = zona.get(i);
            if (!pila.esVacio() && pila.top().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(E elemento) {
        int pos = existePilaAve(elemento);
        if (pos != -1) {
            zona.get(pos).push(elemento);
        } else {
            Pila<E> nuevaPila = new EnlazadaPila<>();
            nuevaPila.push(elemento);
            zona.add(nuevaPila);
        }
    }

    public String cartasPorEspecie() {
        String m = "";
        for (Pila<E> pila : zona) {
            if (!pila.esVacio()) {
                Carta carta = (Carta) pila.top();
                m += carta.getNombre() + " - " + pila.tamaño() + "\n";
            }
        }
        return m;
    }

    public int getNumEspecies() {
        int numEspecies = 0;
        for (Pila<E> pila : zona) {
            if (!pila.esVacio()) {
                numEspecies++;
            }
        }
        return numEspecies;
    }

    public int getNumCartas() {
        int total = 0;
        for (Pila<E> pila : zona) {
            total += pila.tamaño();
        }
        return total;
    }

    public <Carta> void pintar() {
        for (Pila<E> pila : zona) {
            System.out.println("Pila de " + pila.top() + " | " + pila.tamaño());
        }
    }
    
//    public int numCartas(){
//        int toRet = 0;
//        for (int i = 0; i < zona.size(); i++) {
//            Pila<E> pila = zona.get(i); 
//            toRet += pila.tamaño();
//        }
//        return toRet;
//    }
            
    public int numEspeciesDistintas(){
        return zona.size();
    }
    
    public void colocarCarta(Carta c){
        int i = existePilaElemento((E)c);
        if(i == -1){
            Pila<E> p = new EnlazadaPila<>();
            p.push((E)c);
            zona.add(p);
        }else{
            Pila<E> pila = zona.get(i);
            pila.push((E)c);
        }
    }
           
//    public void pintar(){
//        String toRet=" ";
//        for (int i = 0; i < zona.size(); i++) {
//            Pila<E> pila = zona.get(i); 
//            toRet += "Especie: "+pila.top().getClass()+", cantidad: "+pila.tamaño()+"\n";
//        }
//        System.out.println(toRet);
//    }
    
    public int existePilaElemento(E elemento) {
        for (int i = 0; i < zona.size(); i++) {
            Pila<E> pila = zona.get(i);
            if (!pila.esVacio() && pila.top().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }
}
