package algorithm;

import java.util.ArrayList;
import java.util.List;

import exceptions.IndividuoException;
import model.Individuo;
import model.IndividuoFactory;

public class AlgoritmoGenetico {

    private int tamPoblacion;
    private List<Individuo> poblacion;
    private int individuoType;
    private List<Double> fitness;
    private int maxGeneraciones;
    private double probCruce;
    private double probMutacion;
    private int tamTorneo;
    private Individuo elMejor;
    private int pos_mejor;

    public AlgoritmoGenetico(int tamPoblacion, int individuoType, int maxGeneraciones, double probCruce, double probMutacion, int tamTorneo) {
        this.tamPoblacion = tamPoblacion;
        this.individuoType = individuoType;
        this.maxGeneraciones = maxGeneraciones;
        this.probCruce = probCruce;
        this.probMutacion = probMutacion;
        this.tamTorneo = tamTorneo;
        this.poblacion = new ArrayList<>();
        this.fitness = new ArrayList<>(tamPoblacion);
        this.elMejor = null;
        pos_mejor = 0; 
    }

    public Individuo run() throws IndividuoException {
        init();
        evalue();
        for (int i = 0; i < this.maxGeneraciones; i++) {
            selecction();
            cross();
            mutation();
            evalue();
        }
        return this.elMejor;
    }

    private void init() throws IndividuoException {
        for (int i = 0; i < this.tamPoblacion; i++) {
            poblacion.add(IndividuoFactory.getIndividuo(individuoType));
        }
    }

    private void evalue() {

    }

    private void selecction() {

    }

    private void cross() {

    }

    private void mutation() {

    }

}
