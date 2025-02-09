package model;

import java.util.List;
import java.util.Random;

import config.Parameters;

public abstract class Individuo<T> {

    protected static Random random = new Random(Parameters.SEED);
    protected List<T> cromosomas;
    protected List<Integer> tamGenes;
    protected List<Double> minValue;
    protected List<Double> maxValue;

    public Individuo() {}

    public abstract double getFitness();

    public abstract double getFenotipo(int gen);

    public List<T> getGenotipo(){
        return this.cromosomas;
    }

    protected int tamGen(double min, double max) {
        return (int) (Math.log10(((max - min) /Math.pow(10, -Parameters.PRECISION)) + 1) / Math.log10(2));
    }

    @Override
    public String toString() {
        String s = "";
        
        s += "Numero de genes: " + this.tamGenes.size() + "\n";
        for (int i = 0; i < this.tamGenes.size(); i++) {
            s += " - Gen " + (i + 1) + " tiene tamanyo " + this.tamGenes.get(i) + "\n";
        }

        return s;
    }

}
