package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

public class MainWindow extends JFrame {

    public MainWindow() {
		super("Evolucion");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

        mainPanel.add(new ControlPanel(), BorderLayout.NORTH);
        mainPanel.add(new ConfigPanel(), BorderLayout.WEST);
        mainPanel.add(createPlot(), BorderLayout.CENTER); // TODO

		this.pack();
		this.setVisible(true);
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    private Plot2DPanel createPlot(){
        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabel(0, "Generacion");
        plot.setAxisLabel(1, "Evaluacion");

        // define your data
        double[] x = { 1, 2, 3, 4, 5, 6 };
        double[] y = { 45, 89, 6, 32, 63, 12 };
        
        // create your PlotPanel (you can use it as a JPanel)
    
        // define the legend position
        plot.addLegend("SOUTH");

        // add a line plot to the PlotPanel
        plot.addLinePlot("my plot", x, y);
        // put the PlotPanel in a JFrame like a JPanel
        return plot;
    }

}
