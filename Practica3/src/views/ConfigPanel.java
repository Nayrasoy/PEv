package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import algorithm.cross.CrossType;
import algorithm.initialization.InitializationType;
import algorithm.mutation.MutationType;
import algorithm.selection.SelectionType;
import config.Parameters;
import controller.Controller;
import model.IndividualType;

public class ConfigPanel extends JPanel {

    private Controller controller;
    private JTextField populationSizeField;
    private JTextField generationsField;
    private JSlider crossPercentageSlider;
    private JSlider mutationPercentageSlider;
    private JTextField precisionField;
    private JComboBox<String> selectionMethodComboBox;
    private JComboBox<String> selectionFunctionComboBox;
    private JComboBox<String> crossMethodComboBox;
    private JComboBox<String> mutationMethodComboBox;
    private JComboBox<String> initializationMethodComboBox;
    private JSlider elitismPercentageSlider;
    private JTextField functionDimensionComboBox;

    public ConfigPanel(Controller controller) {
        this.controller = controller;
        this.initGUI();
    }

    private void initGUI() {
        this.setLayout(new GridLayout(11, 1));
        JPanel p;

        // Seleccion de la funcion
        p = new JPanel();
        p.add(new JLabel("Seleccionar funcion:"));
        String s = "";
        for (IndividualType st : IndividualType.values()) {
            s += st.toString() + " ";
        }
        selectionFunctionComboBox = new JComboBox<>(s.split(" "));
        selectionFunctionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setIndividual(IndividualType.valueOf((String) selectionFunctionComboBox.getSelectedItem()));
            }
        });
        selectionFunctionComboBox.setSelectedItem(Parameters.DEFAULT_INDIVIDUAL_TYPE.toString());
        p.add(selectionFunctionComboBox);
        this.add(p);

        // Tamaño de la población
        p = new JPanel();
        p.add(new JLabel("Tamaño de la población:"));
        populationSizeField = new JTextField(String.valueOf(Parameters.DEFAULT_TAM_POBLATION));
        this.populationSizeField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateIntTextField(populationSizeField.getText());
                } catch (Exception ex) {
                    System.out.println("El tamanyo de la poblacion debe ser un entero superior a 0");
                    populationSizeField.setText(String.valueOf(Parameters.DEFAULT_TAM_POBLATION));
                } finally {
                    controller.setPopulationSize(Integer.parseInt(populationSizeField.getText()));
                }
            }
            
        });
        this.populationSizeField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateIntTextField(populationSizeField.getText());
                } catch (Exception ex) {
                    System.out.println("El tamanyo de la poblacion debe ser un entero superior a 0");
                    populationSizeField.setText(String.valueOf(Parameters.DEFAULT_TAM_POBLATION)); 
                } finally {
                    controller.setPopulationSize(Integer.parseInt(populationSizeField.getText()));
                }
            }
            
        });
        p.add(populationSizeField);
        this.add(p);

        // Número de generaciones
        p = new JPanel();
        p.add(new JLabel("Número de generaciones:"));
        generationsField = new JTextField(String.valueOf(Parameters.DEFAULT_GENERATIONS_NUMBER));
        this.generationsField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateIntTextField(generationsField.getText());
                } catch (Exception ex) {
                    System.out.println("El numero de generaciones debe ser un entero superior a 0");
                    generationsField.setText(String.valueOf(Parameters.DEFAULT_GENERATIONS_NUMBER));
                } finally {
                    controller.setGenerations(Integer.parseInt(generationsField.getText()));
                }
            }
            
        });
        this.generationsField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateIntTextField(generationsField.getText());
                } catch (Exception ex) {
                    System.out.println("El numero de generaciones debe ser un entero superior a 0");
                    generationsField.setText(String.valueOf(Parameters.DEFAULT_GENERATIONS_NUMBER));
                } finally {
                    controller.setGenerations(Integer.parseInt(generationsField.getText()));
                }
            }
            
        });
        p.add(generationsField);
        this.add(p);

        // Porcentaje de cruces
        p = new JPanel();
        p.add(new JLabel("Porcentaje de cruces:"));
        crossPercentageSlider = createPercentageSlider();
        this.crossPercentageSlider.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                controller.setCrossProbability(crossPercentageSlider.getValue());
            }

        });
        p.add(crossPercentageSlider);
        this.add(p);

        // Porcentaje de mutaciones
        p = new JPanel();
        p.add(new JLabel("Porcentaje de mutaciones:"));
        this.mutationPercentageSlider = createPercentageSlider();
        this.mutationPercentageSlider.setValue(Parameters.DEFAULT_MUTATION_PROBABILITY);
        this.mutationPercentageSlider.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                controller.setMutationProbability(mutationPercentageSlider.getValue());
            }

        });
        p.add(mutationPercentageSlider);
        this.add(p);

        // Precisión de la representación
        p = new JPanel();
        p.add(new JLabel("Precisión de la representación:"));
        precisionField = new JTextField(String.valueOf(Parameters.DEFAULT_PRECISION));
        this.precisionField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateDoubleTextField(precisionField.getText());
                } catch (Exception ex) {
                    System.out.println("El numero de generaciones debe ser un entero superior a 0");
                    precisionField.setText(String.valueOf(Parameters.DEFAULT_PRECISION));
                } finally {
                    controller.setPrecision(Double.parseDouble(precisionField.getText()));
                }
            }
            
        });
        this.precisionField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateDoubleTextField(precisionField.getText());
                } catch (Exception ex) {
                    System.out.println("El numero de generaciones debe ser un entero superior a 0");
                    precisionField.setText(String.valueOf(Parameters.DEFAULT_PRECISION));
                } finally {
                    controller.setPrecision(Double.parseDouble(precisionField.getText()));
                }
            }
            
        });
        p.add(precisionField);
        this.add(p);

        // Método de Selección
        p = new JPanel();
        p.add(new JLabel("Método de Selección:"));
        s = "";
        for (SelectionType st : SelectionType.values()) {
            s += st.toString() + " ";
        }
        selectionMethodComboBox = new JComboBox<>(s.split(" "));
        selectionMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setSelectionMethod(SelectionType.valueOf((String) selectionMethodComboBox.getSelectedItem()));
            }
        });
        selectionMethodComboBox.setSelectedItem(Parameters.DEFAULT_SELECTION_METHOD.toString());
        p.add(selectionMethodComboBox);
        this.add(p);

        // Método de Cruce
        p = new JPanel();
        p.add(new JLabel("Método de Cruce:"));
        s = "";
        for (CrossType ct : CrossType.values()) {
            s += ct.toString() + " ";
        }
        crossMethodComboBox = new JComboBox<>(s.split(" "));
        crossMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setCrossMethod(CrossType.valueOf((String) crossMethodComboBox.getSelectedItem()));
            }
        });
        crossMethodComboBox.setSelectedItem(Parameters.DEFAULT_CROSS_METHOD.toString());
        p.add(crossMethodComboBox);
        this.add(p);

        // Método de Mutación
        p = new JPanel();
        p.add(new JLabel("Método de Mutación:"));
        s = "";
        for (MutationType mt : MutationType.values()) {
            s += mt.toString() + " ";
        }
        mutationMethodComboBox = new JComboBox<>(s.split(" "));
        mutationMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setMutationMethod(MutationType.valueOf((String) mutationMethodComboBox.getSelectedItem()));
            }
        });
        mutationMethodComboBox.setSelectedItem(Parameters.DEFAULT_MUTATION_METHOD.toString());
        p.add(mutationMethodComboBox);
        this.add(p);

        // Método de Inicializacion
        p = new JPanel();
        p.add(new JLabel("Método de Inicializacion:"));
        s = "";
        for (InitializationType mt : InitializationType.values()) {
            s += mt.toString() + " ";
        }
        initializationMethodComboBox = new JComboBox<>(s.split(" "));
        initializationMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setInitializationMethod(InitializationType.valueOf((String) initializationMethodComboBox.getSelectedItem()));
            }
        });
        initializationMethodComboBox.setSelectedItem(Parameters.DEFAULT_INITIALIZATION_METHOD.toString());
        p.add(initializationMethodComboBox);
        this.add(p);

        // Porcentaje de elitismo
        p = new JPanel();
        p.add(new JLabel("Porcentaje de elitismo:"));
        elitismPercentageSlider = createPercentageSlider();
        elitismPercentageSlider.setMaximum(10);
        elitismPercentageSlider.setValue(Parameters.DEFAULT_ELITISM);
        this.elitismPercentageSlider.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                controller.setElitismPercentage(elitismPercentageSlider.getValue());
            }

        });
        p.add(elitismPercentageSlider);
        this.add(p);

        // Selección de la dimensión de las funciones 4 y 5
        p = new JPanel();
        p.add(new JLabel("Dimensión de funciones 4 y 5:"));
        functionDimensionComboBox = new JTextField(String.valueOf(Parameters.DEFAULT_DIMENSION));
        this.functionDimensionComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateIntTextField(functionDimensionComboBox.getText());
                } catch (Exception ex) {
                    System.out.println("El tamanyo de la poblacion debe ser un entero superior a 0");
                    functionDimensionComboBox.setText(String.valueOf(Parameters.DEFAULT_DIMENSION));
                } finally {
                    controller.setDimension(Integer.parseInt(functionDimensionComboBox.getText()));
                }
            }
            
        });
        this.functionDimensionComboBox.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateIntTextField(functionDimensionComboBox.getText());
                } catch (Exception ex) {
                    System.out.println("El tamanyo de la poblacion debe ser un entero superior a 0");
                    functionDimensionComboBox.setText(String.valueOf(Parameters.DEFAULT_DIMENSION)); 
                } finally {
                    controller.setDimension(Integer.parseInt(functionDimensionComboBox.getText()));
                }
            }
            
        });
        p.add(this.functionDimensionComboBox);
        // this.add(p);
    }

    private JSlider createPercentageSlider() {
        JSlider slider = new JSlider(0, 100, 60); // Rango de 0 a 100, valor inicial en 60
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private boolean validateIntTextField(String text) {
        return text != null && !text.isEmpty() && Integer.parseInt(text) > 0;
    }

    private boolean validateDoubleTextField(String text) {
        return text != null && !text.isEmpty() && Double.parseDouble(text) > 0;
    }

}
