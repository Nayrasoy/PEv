package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.Controller;

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
				controller.run();
			}
			
		});
        runButton.setFocusPainted(false);
		toolBar.add(runButton);

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
