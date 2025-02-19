package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ControlPanel extends JPanel {
    
    public ControlPanel() {
        this.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.PAGE_START);

        JButton runButton = new JButton();
        runButton.setIcon(new ImageIcon("img/icons/run.png"));
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("Boton pulsado por un guapini");
			}
			
		});
        runButton.setFocusPainted(false);
		toolBar.add(runButton);
    }

}
