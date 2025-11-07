package command;
import memento.Memento;
import calculadora.Calculadora;

public class MultiplicarCommand implements Command{

    private double valor;
    private Calculadora calculadora;
    private Memento memento;

    public MultiplicarCommand(Calculadora calculadora, double valor) {
        this.calculadora = calculadora;
        this.valor = valor;
    }

    @Override
    public void execute() {
        memento= calculadora.crearMemento();
        calculadora.sumar(valor);
    }

    @Override
    public void undo() {
        calculadora.restaurar(memento);
    }
}
