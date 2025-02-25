package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import utils.Utils;

public class IndividuoFuncion5 extends Individuo<Double> {

    private int d;
    private int m = 10;
    private double minFitness = 0;

    public IndividuoFuncion5() {}

    public IndividuoFuncion5(Controller controller, int d) {
        super(controller);
        this.d = d;
        this.minValue = new ArrayList<Double>();
        this.maxValue = new ArrayList<Double>();
        for (int i = 0; i < d; i++) {
            this.minValue.add(0.000);
            this.maxValue.add(Math.PI);
        }

        this.cromosomas = new ArrayList<>();
        for (int i = 0; i < d; i++) {
            cromosomas.add(this.minValue.get(i) + (Utils.random.nextDouble() * (this.maxValue.get(i) - this.minValue.get(i))));
        }
    }

    public IndividuoFuncion5(Controller controller, int d, List<Double> cromosomas, List<Double> minValue, List<Double> maxValue) {
        super(controller, cromosomas, null, minValue, maxValue);
        this.d = d;
    }
    
    @Override
    public double getFitness() {
        double suma = 0.0;

        for (int i = 1; i <= this.d; i++) {
            double xi = this.getFenotipo(i - 1);
            suma += Math.sin(xi) * Math.pow(Math.sin((i * xi * xi) / Math.PI), 2 * this.m);
        }

        if (-suma < this.minFitness) {
            this.minFitness = -suma;
        }

        return -suma;
    }

    @Override
    public double getFenotipo(int gen) {
        return this.cromosomas.get(gen);
    }

    @Override
    public Individuo create(Controller controller) {
        return new IndividuoFuncion5(controller, controller.getDimension());
    }

    @Override
    public Individuo copy() {
        return new IndividuoFuncion5(this.controller, this.d, new ArrayList<>(this.cromosomas), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue));
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION5;
    }

    @Override
    public boolean betterThan(double myFitness, double fitness) {
        return myFitness < fitness;
    }

    @Override
    public int getMinValue() {
        return (int) Math.round(this.minFitness);
    }
    
}
