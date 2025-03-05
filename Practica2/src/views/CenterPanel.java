package views;

import java.awt.CardLayout;

import javax.swing.JPanel;

import controller.Controller;

public class CenterPanel extends JPanel {
    
    private Controller controller;
    private CardLayout layout;
    private PlotPanel plotPanel;
    private MapPanel mapPanel;
    
    public CenterPanel(Controller controller) {
        this.controller = controller;
        this.layout = new CardLayout();
        this.plotPanel = new PlotPanel(controller);
        this.mapPanel = new MapPanel(controller);
		this.initGUI();
    }

	private void initGUI() {
        this.setLayout(layout);
        this.add(plotPanel, "Plot");
        this.add(mapPanel, "Map");
        this.setVisible(true);
	}

    public void showPlot() {
        this.layout.show(this, "Plot");
    }

    public void showMap() {
        this.layout.show(this, "Map");
    }

    public void refreshPlot(double[][] averageFitness, double[][] actualBest, double[][] overallBest) {
        this.plotPanel.refreshPlot(averageFitness, actualBest, overallBest);
        this.repaint();
    }

}
