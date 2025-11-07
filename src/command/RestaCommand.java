package command;


import calculadora.Calculadora;

public class RestaCommand implements Command {

    private double valor;
    private Calculadora calculadora;
    private Memento memento;

    public RestaCommand(Calculadora calculadora, double valor) {
        this.calculadora = calculadora;
        this.valor = valor;
    }

    @Override
    public void execute() {
        mementoPrevio = calculadora.crearMemento();
        calculadora.sumar(valor);
    }

    @Override
    public void undo() {
        calculadora.restaurar(mementoPrevio);
    }
}
