package command;

import java.util.Stack;

public class GestorComandos {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void ejecutarComando(Command c) {
        c.execute();
        undoStack.push(c);
        redoStack.clear();
    }

    public void deshacer() {
        if (!undoStack.isEmpty()) {
            Command c = undoStack.pop();
            c.undo();
            redoStack.push(c);
        }
    }

    public void rehacer() {
        if (!redoStack.isEmpty()) {
            Command c = redoStack.pop();
            c.execute();
            undoStack.push(c);
        }
    }
}
