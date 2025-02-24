package views;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import controller.Controller;

public class MainWindow extends JFrame {

    private Controller controller;
    private ControlPanel controlPanel;
    private ConfigPanel configPanel;
    private PlotPanel plot;

    public MainWindow(Controller controller) {
		super("Evolucion");
        this.controller = controller;
        this.controlPanel = new ControlPanel(controller);
        this.configPanel = new ConfigPanel(controller);
        this.plot = new PlotPanel(controller);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

        mainPanel.add(this.controlPanel, BorderLayout.NORTH);
        mainPanel.add(this.configPanel, BorderLayout.WEST);
        mainPanel.add(this.plot, BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
        this.setSize(800, 800);
        ImageIcon imgIcon = new ImageIcon("img/icons/app_icon.png");
        this.setIconImage(imgIcon.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    public void refreshPlot(double[][] averageFitness, double[][] actualBest, double[][] overallBest) {
        this.plot.refreshPlot(averageFitness, actualBest, overallBest);
        this.repaint();
    }

}
