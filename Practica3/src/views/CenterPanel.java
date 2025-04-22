package views;

import java.awt.CardLayout;

import javax.swing.JPanel;

import controller.Controller;
import model.Individuo;

public class CenterPanel extends JPanel {
    
    private Controller controller;
    private CardLayout layout;
    private PlotPanel plotPanel;
    private MapPanel mapPanel;
    private AntPanel antPanel;
    
    public CenterPanel(Controller controller) {
        this.controller = controller;
        this.layout = new CardLayout();
        this.plotPanel = new PlotPanel(controller);
        this.mapPanel = new MapPanel(controller);
        this.antPanel = new AntPanel(controller);
		this.initGUI();
    }

	private void initGUI() {
        this.setLayout(layout);
        this.add(plotPanel, "Plot");
        this.add(mapPanel, "Map");
        this.add(antPanel, "Ant");
        this.setVisible(true);
	}

    public void showPlot() {
        this.layout.show(this, "Plot");
    }

    public void showMap() {
        this.layout.show(this, "Map");
    }

    public void showAnt() {
        this.layout.show(this, "Ant");
    }

    public void refreshPlot(double[][] averageFitness, double[][] actualBest, double[][] overallBest) {
        this.plotPanel.refreshPlot(averageFitness, actualBest, overallBest);
        this.repaint();
    }

    public void refreshHouse(Individuo bestIndividual) {
        this.mapPanel.refreshHouse(bestIndividual);
        this.repaint();
    }

    public void refreshAntMap(Individuo bestIndividual) {
        this.antPanel.refreshAntMap(bestIndividual);
        this.repaint();
    }

}
