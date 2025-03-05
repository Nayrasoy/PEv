package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class IndividuoFuncion1 extends IndividuoBooleano {

    public IndividuoFuncion1() {}

    public IndividuoFuncion1(Controller controller) {
        super(controller);
        this.minValue = new ArrayList<Double>();
        this.minValue.add(-3.000);
        this.minValue.add(4.100);
        this.maxValue = new ArrayList<Double>();
        this.maxValue.add(12.100);
        this.maxValue.add(5.800);
        this.init();
    }
    
    public IndividuoFuncion1(Controller controller, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue) {
        super(controller, cromosomas, tamGenes, minValue, maxValue);
    }

    @Override
    public double getFitness() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
    }

    @Override
    public Individuo create(Controller controller) {
        return new IndividuoFuncion1(controller);
    }

    @Override
    public Individuo copy() {
        return new IndividuoFuncion1(this.controller, new ArrayList<>(this.cromosomas), new ArrayList<>(this.tamGenes), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue));
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION1;
    }

    @Override
    public int betterThan(double myFitness, double fitness) {
        return myFitness > fitness ? 1 : myFitness == fitness ? 0 : -1;
    }

    @Override
    public int getMinValue() {
        return 0;
    }
    
}
