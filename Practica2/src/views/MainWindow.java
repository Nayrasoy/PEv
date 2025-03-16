package views;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Individuo;

public class MainWindow extends JFrame {

    private Controller controller;
    private ControlPanel controlPanel;
    private ConfigPanel configPanel;
    private CenterPanel centerPanel;
    private SolutionPanel solutionPanel;

    public MainWindow(Controller controller) {
		super("Evolucion");
        this.controller = controller;
        this.controlPanel = new ControlPanel(controller);
        this.configPanel = new ConfigPanel(controller);
        this.centerPanel = new CenterPanel(controller);
        this.solutionPanel = new SolutionPanel(controller);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

        mainPanel.add(this.controlPanel, BorderLayout.NORTH);
        mainPanel.add(this.configPanel, BorderLayout.WEST);
        mainPanel.add(this.centerPanel, BorderLayout.CENTER);
        mainPanel.add(this.solutionPanel, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
        this.setSize(1200, 800);
        ImageIcon imgIcon = new ImageIcon("img/icons/app_icon.png");
        this.setIconImage(imgIcon.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    public void refreshPlot(double[][] averageFitness, double[][] actualBest, double[][] overallBest) {
        this.centerPanel.refreshPlot(averageFitness, actualBest, overallBest);
    }

    public void setSolution(String solution) {
        this.solutionPanel.setSolution(solution);;
    }

    public void showPlot() {
        this.centerPanel.showPlot();
    }

    public void showMap() {
        this.centerPanel.showMap();
    }

    public void refreshHouse(Individuo bestIndividual) {
        this.centerPanel.refreshHouse(bestIndividual);
    }

}
