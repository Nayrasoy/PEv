package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class IndividuoFuncion3 extends IndividuoBooleano {

    public IndividuoFuncion3() {}

    public IndividuoFuncion3(Controller controller) {
        super(controller);
        this.minValue = new ArrayList<Double>();
        this.minValue.add(-10.000);
        this.minValue.add(-10.000);
        this.maxValue = new ArrayList<Double>();
        this.maxValue.add(10.000);
        this.maxValue.add(10.000);

        this.init();
    }

    public IndividuoFuncion3(Controller controller, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue, int nGenes) {
        super(controller, cromosomas, tamGenes, minValue, maxValue, nGenes);
    }

    @Override
    public double getFitness() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        double sum1 = 0, sum2 = 0;

        for (int i = 1; i <= 5; i++) {
            sum1 += i * Math.cos((i + 1) * x1 + i);
            sum2 += i * Math.cos((i + 1) * x2 + i);
        }

        return sum1 * sum2;
    }

    @Override
    public Individuo create(Controller controller) {
        return new IndividuoFuncion3(controller);
    }

    @Override
    public Individuo copy() {
        return new IndividuoFuncion3(this.controller, new ArrayList<>(this.cromosomas), new ArrayList<>(this.tamGenes), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue), this.nGenes);
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION3;
    }

    @Override
    public int betterThan(double myFitness, double fitness) {
        return myFitness < fitness ? 1 : myFitness == fitness ? 0 : -1;
    }

    @Override
    public int getMinValue() {
        return -187;
    }
    
}
