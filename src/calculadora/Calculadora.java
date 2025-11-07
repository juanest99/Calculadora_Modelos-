package calculadora;
import command.*;
import memento.Memento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;
    private JButton btnSuma, btnResta, btnMult, btnDiv, btnClear, btnUndo, btnRedo, btnIgual;
    private JButton[] numeros = new JButton[10];

    public Calculadora() {
        setTitle("calculadora.Calculadora con Command & Memento");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciar();
        setVisible(true);
    }

    private void iniciar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        add(panel);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        panel.add(display, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(panelBotones, BorderLayout.CENTER);

        // Números
        for (int i = 0; i <= 9; i++) {
            numeros[i] = new JButton(String.valueOf(i));
            numeros[i].setFont(new Font("Arial", Font.BOLD, 20));
            numeros[i].setBackground(Color.DARK_GRAY);
            numeros[i].setForeground(Color.WHITE);
            numeros[i].addActionListener(this);
            panelBotones.add(numeros[i]);
        }

        btnSuma = crearBoton("+", panelBotones);
        btnResta = crearBoton("-", panelBotones);
        btnMult = crearBoton("×", panelBotones);
        btnDiv = crearBoton("÷", panelBotones);
        btnClear = crearBoton("C", panelBotones);
        btnUndo = crearBoton("Undo", panelBotones);
        btnRedo = crearBoton("Redo", panelBotones);
        btnIgual = crearBoton("=", panelBotones);



    }

    private JButton crearBoton(String texto, JPanel panel) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Arial", Font.BOLD, 18));
        b.setBackground(new Color(47, 174, 229));
        b.setForeground(Color.WHITE);
        b.addActionListener(this);
        panel.add(b);
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("\\d")) {
            if (display.getText().equals("0")) {
                display.setText(cmd);
            } else {
                display.setText(display.getText() + cmd);
            }
        } else if (cmd.equals("C")) {
            display.setText("0");
        }
    }

    public void sumar(double valor) {
        double actual = Double.parseDouble(display.getText());
        double resultado = actual + valor;
        display.setText(String.valueOf(resultado));
    }

    public void restar(double valor) {
        double actual = Double.parseDouble(display.getText());
        double resultado = actual - valor;
        display.setText(String.valueOf(resultado));
    }

    public void multiplicar(double valor) {
        double actual = Double.parseDouble(display.getText());
        double resultado = actual * valor;
        display.setText(String.valueOf(resultado));
    }

    public void division(double valor) {
        double actual = Double.parseDouble(display.getText());
        if (valor == 0) {
            JOptionPane.showMessageDialog(this, "Error: división por cero");
            return;
        }
        double resultado = actual / valor;
        display.setText(String.valueOf(resultado));
    }

    public void borrar() {
        display.setText("0");
    }

    public void undo() {
    }

    public void redo() {
    }


    public Memento crearMemento() {
        return null;
    }

    public void restaurar(Memento memento) {
    }
}
