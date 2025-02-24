package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class IndividuoFuncion4 extends IndividuoBooleano {

    private int d;
    private int m = 10;
    private double minFitness = 0;

    public IndividuoFuncion4() {}

    public IndividuoFuncion4(Controller controller, int d) {
        super(controller);
        this.d = d;
        this.minValue = new ArrayList<Double>();
        this.maxValue = new ArrayList<Double>();
        for (int i = 0; i < d; i++) {
            this.minValue.add(0.000);
            this.maxValue.add(Math.PI);
        }

        this.init();
    }

    public IndividuoFuncion4(Controller controller, int d, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue) {
        super(controller, cromosomas, tamGenes, minValue, maxValue);
        this.d = d;
    }

    public IndividuoFuncion4(Controller controller, int d2, ArrayList arrayList, ArrayList arrayList2,
            ArrayList arrayList3) {
        //TODO Auto-generated constructor stub
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
    public Individuo create(Controller controller) {
        return new IndividuoFuncion4(controller, controller.getDimension());
    }

    @Override
    public Individuo copy() {
        return new IndividuoFuncion4(this.controller, this.d, new ArrayList<>(this.cromosomas), new ArrayList<>(this.tamGenes), new ArrayList<>(this.minValue), new ArrayList<>(this.maxValue));
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_FUNCTION4;
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
