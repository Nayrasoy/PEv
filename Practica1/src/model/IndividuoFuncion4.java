package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class IndividuoFuncion4 extends IndividuoBooleano {

    private int d;

    public IndividuoFuncion4() {}

    public IndividuoFuncion4(Controller controller, int d) {
        super(controller);
        this.d = d;
        this.minValue = new ArrayList<Double>();
        this.minValue.add(0.000);
        this.minValue.add(0.000);
        this.maxValue = new ArrayList<Double>();
        this.maxValue.add(Math.PI);
        this.maxValue.add(Math.PI);

        this.init();
    }

    public IndividuoFuncion4(Controller controller, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue) {
        super(controller, cromosomas, tamGenes, minValue, maxValue);
    }

    @Override
    public double getFitness() {
        return 0.0;
    }

    @Override
    public Individuo create(Controller controller) {
        return new IndividuoFuncion4(controller, controller.getDimension());
    }

    @Override
    public Individuo copy() {
        return new IndividuoFuncion4(this.controller, new ArrayList<>(this.cromosomas), new ArrayList<>(this.tamGenes), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue));
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION4;
    }

    @Override
    public boolean betterThan(double myFitness, double fitness) {
        return myFitness < fitness;
    }
    
}
