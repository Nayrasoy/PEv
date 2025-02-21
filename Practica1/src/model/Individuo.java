package model;

import java.util.List;
import java.util.Random;

import config.Parameters;
import utils.Utils;

public abstract class Individuo<T> {

    protected List<T> cromosomas;
    protected List<Integer> tamGenes;
    protected List<Double> minValue;
    protected List<Double> maxValue;

    public Individuo() {}

    public Individuo(List<T> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue) {
        this.cromosomas = cromosomas;
        this.tamGenes = tamGenes;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public abstract double getFitness();

    public abstract double getFenotipo(int gen);

    public abstract Individuo create();

    public abstract Individuo copy();

    public abstract IndividualType getType();

    protected abstract void mutate(int i);

    public List<T> getGenotipo(){
        return this.cromosomas;
    }

    protected int tamGen(double min, double max) {
        return (int) (Math.log10(((max - min) / Math.pow(10, -Parameters.PRECISION)) + 1) / Math.log10(2));
    }

    @Override
    public String toString() {
        String s = "";
        
        s += "Numero de genes: " + this.tamGenes.size() + "\n";
        for (int i = 0; i < this.tamGenes.size(); i++) {
            s += "  - Gen " + (i + 1) + " tiene tamanyo " + this.tamGenes.get(i) + "\n";
        }
        s += "  Fitness: " + this.getFitness() + "\n";

        return s;
    }

    public List<T> getCromosomas() {
        return cromosomas;
    }

    public void checkForMutations(double mutationProbability) {
        for (int i = 0; i < this.cromosomas.size(); i++) {
            if (Utils.random.nextDouble() < mutationProbability) {
                mutate(i);
            }
        }
    }

}
