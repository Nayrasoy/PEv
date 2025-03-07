package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class IndividuoFuncion2 extends IndividuoBooleano {

    public IndividuoFuncion2() {}

    public IndividuoFuncion2(Controller controller) {
        super(controller);
        this.minValue = new ArrayList<Double>();
        this.minValue.add(-10.000);
        this.minValue.add(-6.500);
        this.maxValue = new ArrayList<Double>();
        this.maxValue.add(0.000);
        this.maxValue.add(0.000);

        this.init();
    }

    public IndividuoFuncion2(Controller controller, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue, int nGenes) {
        super(controller, cromosomas, tamGenes, minValue, maxValue, nGenes);
    }

    @Override
    public double getFitness() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        return Math.sin(x2) * Math.exp(Math.pow(1 - Math.cos(x1), 2))
                + Math.cos(x1) * Math.exp(Math.pow(1 - Math.sin(x2), 2))
                + Math.pow(x1 - x2, 2);
    }

    @Override
    public Individuo create(Controller controller) {
        return new IndividuoFuncion2(controller);
    }

    @Override
    public Individuo copy() {
        return new IndividuoFuncion2(this.controller, new ArrayList<>(this.cromosomas), new ArrayList<>(this.tamGenes), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue), this.nGenes);
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION2;
    }

    @Override
    public int betterThan(double myFitness, double fitness) {
        return myFitness < fitness ? 1 : myFitness == fitness ? 0 : -1;
    }

    @Override
    public int getMinValue() {
        return -107;
    }
    
}
