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

import algorithm.mutation.MutationType;
import config.Parameters;
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
				controller.run(true);
			}
			
		});
        runButton.setFocusPainted(false);
		toolBar.add(runButton);

		// Vista grafica
        JButton graphButton = new JButton("Grafíca");
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
		JButton antButton = new JButton("Mapa Hormiga");
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
		//toolBar.add(mapButton);

		JButton testExecutionsButton = new JButton("Test 20");
		testExecutionsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de testeo de 20 ejecuciones");
				List<Double> list = new ArrayList<>();
				for (int i = 0; i < 20; i++) {
					list.add(controller.run(false));
					String s = "Ejecuciones completadas: " + (i + 1) + "/20";
					System.out.println(s);
				}
				String s = "\nResultados: " + BloatingUtils.mean(list) + "/89 comidas";
				System.out.println(s);
				controller.setSolution(s);
			}
			
		});
        testExecutionsButton.setFocusPainted(false);
		toolBar.add(testExecutionsButton);

		JButton testMutationsButton = new JButton("Test Mutaciones");
		testMutationsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de testeo de mutaciones");
				List<Double> mut1 = new ArrayList<>();
				List<Double> mut2 = new ArrayList<>();
				List<Double> mut3 = new ArrayList<>();
				List<Double> mut4 = new ArrayList<>();
				for (int i = 0; i < 80; i++) {
					if (i % 4 == 0) {
						controller.setMutationMethod(MutationType.TERMINAL);
						mut1.add(controller.run(false));
					}
					else if (i % 4 == 1) {
						controller.setMutationMethod(MutationType.PERMUTACION);
						mut2.add(controller.run(false));
					}
					else if (i % 4 == 2) {
						controller.setMutationMethod(MutationType.SUBARBOL);
						mut3.add(controller.run(false));
					}
					else {
						controller.setMutationMethod(MutationType.CONTRACCION);
						mut4.add(controller.run(false));
					}
					String s = "Ejecuciones completadas: " + (i + 1) + "/80";
					System.out.println(s);
				}
				String s = "\nResultados:\n" + "\t- Terminal: " + BloatingUtils.mean(mut1) + "\n\t- Permutacion: " + BloatingUtils.mean(mut2) + "\n\t- Subarbol: " + BloatingUtils.mean(mut3) + "\n\t- Contraccion: " + BloatingUtils.mean(mut4);
				System.out.println(s);
				controller.setSolution(s);
			}
			
		});
        testMutationsButton.setFocusPainted(false);
		toolBar.add(testMutationsButton);

		JButton testBloatingButton = new JButton("Test Bloating");
		testBloatingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Se ha pulsado el boton de testeo de bloating");
				List<Double> funmented = new ArrayList<>();
				List<Double> tarpeian = new ArrayList<>();
				for (int i = 0; i < 40; i++) {
					if (i % 2 == 0) {
						Parameters.TARPEIAN_BLOATING = false;
						Parameters.WELL_FUMENTED_BLOATING = true;
						funmented.add(controller.run(false));
					}
					else {
						Parameters.TARPEIAN_BLOATING = true;
						Parameters.WELL_FUMENTED_BLOATING = false;
						tarpeian.add(controller.run(false));
					}
					String s = "Ejecuciones completadas: " + (i + 1) + "/40";
					System.out.println(s);
				}
				String s = "\nResultados:\n" + "\t- Penalizacion bien fundamentada: " + BloatingUtils.mean(funmented) + "\n\t- Tarpeian bloating: " + BloatingUtils.mean(tarpeian);
				System.out.println(s);
				controller.setSolution(s);
			}
			
		});
        testBloatingButton.setFocusPainted(false);
		toolBar.add(testBloatingButton);
	}

}
