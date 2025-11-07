package memento;

public class Memento {
    private final double estado;

    public Memento(double estado) {
        this.estado = estado;
    }

    public double getEstado() {
        return estado;
    }
}
