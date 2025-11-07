package calculadora;

import command.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControladorCalculadora extends JFrame implements ActionListener {

    private JTextField display;
    private JButton[] numeros = new JButton[10];
    private JButton btnSuma, btnResta, btnMult, btnDiv, btnClear, btnUndo, btnRedo, btnIgual;

    private final Calculadora calculadora;
    private final GestorComandos gestor;

    private double valorTemporal = 0;
    private String operacionPendiente = "";

    public ControladorCalculadora() {
        this.calculadora = new Calculadora();
        this.gestor = new GestorComandos();
        inicializarUI();
    }

    private void inicializarUI() {
        setTitle("Calculadora con Command & Memento");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(5, 4, 5, 5));
        panel.add(botones, BorderLayout.CENTER);

        for (int i = 0; i <= 9; i++) {
            numeros[i] = new JButton(String.valueOf(i));
            numeros[i].addActionListener(this);
            botones.add(numeros[i]);
        }

        btnSuma = crearBoton("+", botones);
        btnResta = crearBoton("-", botones);
        btnMult = crearBoton("×", botones);
        btnDiv = crearBoton("÷", botones);
        btnIgual = crearBoton("=", botones);
        btnClear = crearBoton("C", botones);
        btnUndo = crearBoton("Undo", botones);
        btnRedo = crearBoton("Redo", botones);

        setVisible(true);
    }

    private JButton crearBoton(String texto, JPanel panel) {
        JButton b = new JButton(texto);
        b.addActionListener(this);
        panel.add(b);
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("\\d")) {
            if (display.getText().equals("0")) display.setText(cmd);
            else display.setText(display.getText() + cmd);
        } else if ("+".equals(cmd) || "-".equals(cmd) || "×".equals(cmd) || "÷".equals(cmd)) {
            valorTemporal = Double.parseDouble(display.getText());
            calculadora.setValor(valorTemporal);
            operacionPendiente = cmd;
            display.setText("0");
        } else if ("=".equals(cmd)) {
            double segundo = Double.parseDouble(display.getText());
            Command comando = crearComando(operacionPendiente, segundo);

            if (comando != null) {
                gestor.ejecutarComando(comando);
                display.setText(String.valueOf(calculadora.getValor()));
            }

        } else if ("Undo".equals(cmd)) {
            gestor.deshacer();
            display.setText(String.valueOf(calculadora.getValor()));
        } else if ("Redo".equals(cmd)) {
            gestor.rehacer();
            display.setText(String.valueOf(calculadora.getValor()));
        } else if ("C".equals(cmd)) {
            calculadora.setValor(0);
            display.setText("0");
        }
    }

    private Command crearComando(String op, double v) {
        return switch (op) {
            case "+" -> new SumarCommand(calculadora, v);
            case "-" -> new RestaCommand(calculadora, v);
            case "×" -> new MultiplicarCommand(calculadora, v);
            case "÷" -> new DividirCommand(calculadora, v);
            default -> null;
        };
    }
}
