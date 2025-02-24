package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class SolutionPanel extends JPanel {

    private Controller controller;
    private JTextField solution; 

    public SolutionPanel(Controller controller) {
        this.controller = controller;
        this.solution = new JTextField();
        this.initGUI();
    }

    private void initGUI() {
        this.add(new JLabel("Solucion:"));
        this.solution.setEditable(false);
        this.add(solution);
    }

    public void setSolution(String solution) {
        this.solution.setText(solution);
    }
    
}
