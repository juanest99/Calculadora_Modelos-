package calculadora;

import memento.Memento;

public class Calculadora {
    private double valor = 0;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void sumar(double v) {
        valor += v;
    }

    public void restar(double v) {
        valor -= v;
    }

    public void multiplicar(double v) {
        valor *= v;
    }

    public void dividir(double v) {
        if (v == 0) throw new ArithmeticException("División por cero");
        valor /= v;
    }

    public Memento crearMemento() {
        return new Memento(valor);
    }

    public void restaurar(Memento m) {
        valor = m.getEstado();
    }
}
