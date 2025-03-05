package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Controller;

public class SolutionPanel extends JPanel {

    private Controller controller;
    private JTextArea solution; 

    public SolutionPanel(Controller controller) {
        this.controller = controller;
        this.solution = new JTextArea();
        this.initGUI();
    }

    private void initGUI() {
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Solución:");
        this.add(label, BorderLayout.NORTH);

        this.solution.setEditable(false);
        this.solution.setLineWrap(true);
        this.solution.setWrapStyleWord(true);

        this.add(solution, BorderLayout.CENTER);
    }

    public void setSolution(String solution) {
        this.solution.setText(solution);
    }
}
