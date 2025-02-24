package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class IndividuoFuncion5 extends Individuo<Double> {

    public IndividuoFuncion5() {}

    public IndividuoFuncion5(Controller controller) {
        super(controller);
        // TODO
    }

    @Override
    public double getFitness() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFitness'");
    }

    @Override
    public double getFenotipo(int gen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFenotipo'");
    }

    @Override
    public Individuo create(Controller controller) {
        return new IndividuoFuncion5(controller);
    }

    @Override
    public Individuo copy() {
        return null;
        // return new IndividuoFuncion5(this.controller, new ArrayList<>(this.cromosomas), new ArrayList<>(this.tamGenes), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue));
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION5;
    }

    @Override
    public boolean betterThan(double myFitness, double fitness) {
        return true;
    }

    @Override
    protected void mutate(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mutate'");
    }
    
}
