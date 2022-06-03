package core;

import java.util.Stack;

public class MontonDescartes {

    private Stack<Carta> elementos;

    public MontonDescartes() {
        this.elementos = new Stack<>();
    }

    public void insertar(Carta carta) {
        this.elementos.push(carta);
    }
    
}
