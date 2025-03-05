package views;

import java.awt.Color;

import org.math.plot.Plot2DPanel;
import org.math.plot.plots.LinePlot;

import controller.Controller;

public class PlotPanel extends Plot2DPanel {
    
    private Controller controller;
    private LinePlot averageFitness;
    private LinePlot generationsBestFitness;
    private LinePlot maxFitness;
    
    public PlotPanel(Controller controller) {
        this.controller = controller;
        this.averageFitness = new LinePlot("Media de la generacion", Color.GREEN, new double[][] { {0, 0}});
        this.generationsBestFitness = new LinePlot("Mejor de la generacion", Color.RED, new double[][] { {0, 0} });
        this.maxFitness = new LinePlot("Mejor absoluto", Color.BLUE, new double[][] { {0, 0} });
		this.initGUI();
    }

	private void initGUI() {
        this.setAxisLabel(0, "Generacion");
        this.setAxisLabel(1, "Evaluacion");
        this.addLegend("SOUTH");
        this.addPlot(this.averageFitness);
        this.addPlot(this.generationsBestFitness);
        this.addPlot(this.maxFitness);
	}

    public void refreshPlot(double[][] averageFitness, double[][] actualBest, double[][] overallBest) {
        this.averageFitness.setData(averageFitness);
        this.generationsBestFitness.setData(actualBest);
        this.maxFitness.setData(overallBest);

        this.removeAllPlots();
    
    // Re-agregamos los gráficos actualizados
        this.addPlot(this.averageFitness);
        this.addPlot(this.generationsBestFitness);
        this.addPlot(this.maxFitness);

        this.revalidate();
        this.repaint();
    }

}
