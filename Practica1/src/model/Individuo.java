package model;

import java.util.List;

import controller.Controller;
import utils.Utils;

public abstract class Individuo<T> {

    protected Controller controller;
    protected List<T> cromosomas;
    protected List<Integer> tamGenes;
    protected List<Double> minValue;
    protected List<Double> maxValue;

    public Individuo(Controller controller) {
        this.controller = controller;
    }

    public Individuo() {}

    public Individuo(Controller controller, List<T> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue) {
        this.controller = controller;
        this.cromosomas = cromosomas;
        this.tamGenes = tamGenes;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public abstract double getFitness();

    public abstract double getFenotipo(int gen);

    public abstract Individuo create(Controller controller);

    public abstract Individuo copy();

    public abstract IndividualType getType();

    public abstract boolean betterThan(double myFitness, double fitness);

    public abstract int getMinValue();

    public List<T> getGenotipo(){
        return this.cromosomas;
    }

    protected int tamGen(double min, double max) {
        return (int) (Math.log10(((max - min) / this.controller.getPrecision()) + 1) / Math.log10(2));
    }

    @Override
    public String toString() {
        String s = "";
        String formato = "%." + Math.round(- Math.log10(this.controller.getPrecision())) + "f";
        
        for (int i = 0; i < this.minValue.size(); i++) {
            s += "Variable X" + (i + 1) + " = " + String.format(formato, this.getFenotipo(i)) + ", ";
        }
        s += "Valor de la funcion = " + String.format(formato, this.getFitness());

        return s;
    }

    public List<T> getCromosomas() {
        return cromosomas;
    }

    public double getMinValueForGen(int gen) {
        return this.minValue.get(gen);
    }

    public double getMaxValueForGen(int gen) {
        return this.maxValue.get(gen);
    }

}
