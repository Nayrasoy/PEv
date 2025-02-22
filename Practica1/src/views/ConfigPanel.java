package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import algorithm.cross.CrossType;
import algorithm.selection.SelectionType;
import config.Parameters;
import controller.Controller;

public class ConfigPanel extends JPanel {

    private Controller controller;
    private JTextField populationSizeField;
    private JTextField generationsField;
    private JSlider crossPercentageSlider;
    private JSlider mutationPercentageSlider;
    private JTextField precisionField;
    private JComboBox<String> selectionMethodComboBox;
    private JComboBox<String> crossMethodComboBox;
    private JComboBox<String> mutationMethodComboBox;
    private JSlider elitismPercentageSlider;
    private JComboBox<String> functionDimensionComboBox;

    public ConfigPanel(Controller controller) {
        this.controller = controller;
        this.initGUI();
    }

    private void initGUI() {
        this.setLayout(new GridLayout(10, 2));

        // Tamaño de la población
        add(new JLabel("Tamaño de la población:"));
        populationSizeField = new JTextField(String.valueOf(Parameters.DEFAULT_TAM_POBLATION));
       this.populationSizeField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateTextField(populationSizeField.getText());
                } catch (Exception ex) {
                    System.out.println("El tamanyo de la poblacion debe ser un entero superior a 0");
                    populationSizeField.setText(String.valueOf(Parameters.DEFAULT_TAM_POBLATION));
                } finally {
                    controller.setPopulationSize(Integer.getInteger(populationSizeField.getText()));
                }
            }
            
        });
        this.populationSizeField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateTextField(populationSizeField.getText());
                } catch (Exception ex) {
                    System.out.println("El tamanyo de la poblacion debe ser un entero superior a 0");
                    populationSizeField.setText(String.valueOf(Parameters.DEFAULT_TAM_POBLATION));
                } finally {
                    controller.setPopulationSize(Integer.getInteger(populationSizeField.getText()));
                }
            }
            
        });
        add(populationSizeField);

        // Número de generaciones
        add(new JLabel("Número de generaciones:"));
        generationsField = new JTextField(String.valueOf(Parameters.DEFAULT_GENERATIONS_NUMBER));
        /*this.generationsField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateTextField(generationsField.getText());
                } catch (Exception ex) {
                    System.out.println("El numero de generaciones debe ser un entero superior a 0");
                    generationsField.setText(String.valueOf(Parameters.DEFAULT_GENERATIONS_NUMBER));
                } finally {
                    controller.setGenerations(Integer.getInteger(generationsField.getText()));
                }
            }
            
        })*/
        this.generationsField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateTextField(generationsField.getText());
                } catch (Exception ex) {
                    System.out.println("El numero de generaciones debe ser un entero superior a 0");
                    generationsField.setText(String.valueOf(Parameters.DEFAULT_GENERATIONS_NUMBER));
                } finally {
                    controller.setGenerations(Integer.getInteger(generationsField.getText()));
                }
            }
            
        });
        add(generationsField);

        // Porcentaje de cruces
        add(new JLabel("Porcentaje de cruces:"));
        crossPercentageSlider = createPercentageSlider();
        add(crossPercentageSlider);

        // Porcentaje de mutaciones
        add(new JLabel("Porcentaje de mutaciones:"));
        mutationPercentageSlider = createPercentageSlider();
        add(mutationPercentageSlider);

        // Precisión de la representación
        add(new JLabel("Precisión de la representación:"));
        precisionField = new JTextField("0.001");
        add(precisionField);

        // Método de Selección
        add(new JLabel("Método de Selección:"));
        String s = "";
        for (SelectionType st : SelectionType.values()) {
            s += st.toString() + " ";
        }
        selectionMethodComboBox = new JComboBox<>(s.split(" "));
        add(selectionMethodComboBox);

        // Método de Cruce
        add(new JLabel("Método de Cruce:"));
        s = "";
        for (CrossType st : CrossType.values()) {
            s += st.toString() + " ";
        }
        crossMethodComboBox = new JComboBox<>(s.split(" "));
        add(crossMethodComboBox);

        /* TODO: Metodo de Mutación
        add(new JLabel("Método de Mutación:"));
        mutationMethodComboBox = new JComboBox<>(new String[] { "Aleatoria", "Intercambio", "Inversión" });
        add(mutationMethodComboBox);
        */

        // Porcentaje de elitismo
        add(new JLabel("Porcentaje de elitismo:"));
        elitismPercentageSlider = createPercentageSlider();
        add(elitismPercentageSlider);

        /* TODO: Selección de la dimensión de las funciones 4 y 5
        add(new JLabel("Dimensión de funciones 4 y 5:"));
        functionDimensionComboBox = new JComboBox<>(new String[] { "2D", "3D", "4D" });
        add(functionDimensionComboBox);
        */
    }

    private JSlider createPercentageSlider() {
        JSlider slider = new JSlider(0, 100, 60); // Rango de 0 a 100, valor inicial en 60
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private boolean validateTextField(String text) {
        return text != null && !text.isEmpty() && Integer.parseInt(text) > 0;
    }

}
