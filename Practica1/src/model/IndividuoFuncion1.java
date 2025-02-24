package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import utils.Utils;

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

        this.tamGenes = new ArrayList<>();
        int tamTotal = 0;
        for (int i = 0; i < minValue.size(); i++) {
            tamGenes.add(this.tamGen(minValue.get(i), maxValue.get(i)));
            tamTotal += tamGenes.get(i);
        }
        this.cromosomas = new ArrayList<>();
        for (int i = 0; i < tamTotal; i++) {
            cromosomas.add(Utils.random.nextBoolean());
        }
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
    protected void mutate(int i) {
        this.cromosomas.set(i, !this.cromosomas.get(i));
    }
    
}
