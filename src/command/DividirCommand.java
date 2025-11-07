package command;
import calculadora.Calculadora;
import memento.Memento;
public class DividirCommand implements Command{

    private double valor;
    private Calculadora calculadora;
    private Memento memento;

    public DividirCommand(Calculadora calculadora, double valor) {
        this.calculadora = calculadora;
        this.valor = valor;
    }

    @Override
    public void execute() {
        memento = calculadora.crearMemento();
        calculadora.sumar(valor);
    }

    @Override
    public void undo() {
        calculadora.restaurar(memento);
    }
}
