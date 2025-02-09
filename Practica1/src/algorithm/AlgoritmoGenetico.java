package algorithm;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;

public class AlgoritmoGenetico {

    private int tamPoblacion;
    private List<Individuo> poblacion;
    private List<Double> fitness;
    private int maxGeneraciones;
    private double probCruce;
    private double probMutacion;
    private int tamTorneo;
    private Individuo elMejor;
    private int pos_mejor;

    public AlgoritmoGenetico(int tamPoblacion, int maxGeneraciones, double probCruce, double probMutacion, int tamTorneo) {
        this.tamPoblacion = tamPoblacion;
        this.maxGeneraciones = maxGeneraciones;
        this.probCruce = probCruce;
        this.probMutacion = probMutacion;
        this.tamTorneo = tamTorneo;
        this.poblacion = new ArrayList<>();
        this.fitness = new ArrayList<>(tamPoblacion);
        this.elMejor = null;
        pos_mejor = 0; 
    }

    public Individuo run() {
        /*
            Iniciar poblacion
            Evaluar población
            Para cada generación {
            //Seleccion
            //Cruce
            //Mutacion
            Evaluar población
            }
            Devolver mejor
         */
        return this.elMejor;
    }

}
