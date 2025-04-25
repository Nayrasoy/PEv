package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.Controller;
import utils.BloatingUtils;

public class ControlPanel extends JPanel {

	private Controller controller;
    
    public ControlPanel(Controller controller) {
        this.controller = controller;
		this.initGUI();
    }

	private void initGUI() {
		this.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.PAGE_START);

        JButton runButton = new JButton("Run");
        runButton.setIcon(new ImageIcon("img/icons/run.png"));
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de run");
				List<Double> twoElitism = new ArrayList<>();
				List<Double> threeElitism = new ArrayList<>();
				List<Double> fourElitism = new ArrayList<>();
				for (int i = 0; i < 60; i++) {
					controller.setElitismPercentage(i % 3 + 2);
					if (i % 3 == 0) {
						twoElitism.add(controller.run());
					}
					else if (i % 3 == 1) {
						threeElitism.add(controller.run());
					}
					else {
						fourElitism.add(controller.run());
					}
				}
				System.out.println("\nResultados:\n\t- 2%: " + BloatingUtils.mean(twoElitism));
				System.out.println("\t- 3%: " + BloatingUtils.mean(threeElitism));
				System.out.println("\t- 4%: " + BloatingUtils.mean(fourElitism));
			}
			
		});
        runButton.setFocusPainted(false);
		toolBar.add(runButton);

		// Vista grafica
        JButton graphButton = new JButton("Show graph");
		graphButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de grafo");
				controller.showPlot();
			}
			
		});
        graphButton.setFocusPainted(false);
		toolBar.add(graphButton);

		// Vista mapa hormiga
		JButton antButton = new JButton("Show ant map");
		antButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de ant mapa");
				controller.showAnt();
			}
			
		});
        antButton.setFocusPainted(false);
		toolBar.add(antButton);

		// Vista Mapa rumba
        JButton mapButton = new JButton("Show map");
		mapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de mapa");
				controller.showMap();
			}
			
		});
        mapButton.setFocusPainted(false);
		toolBar.add(mapButton);
	}

}
