package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import utils.Utils;

public abstract class IndividuoBooleano extends Individuo<Boolean> {

    public IndividuoBooleano() {}
    
    public IndividuoBooleano(Controller controller) {
        super(controller);
    }

    public IndividuoBooleano(Controller controller, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue) {
        super(controller, cromosomas, tamGenes, minValue, maxValue);
    }

    protected void init() {
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

    @Override
    public double getFenotipo(int gen) {
        int n = tamGenes.get(gen);
        int intValue = 0;

        for (int i = 0; i < n; i++) {
            if (this.cromosomas.get(i)) {
                intValue += 1 << (n - 1 - i);
            }
        }

        return this.minValue.get(gen) + (intValue * (this.maxValue.get(gen) - this.minValue.get(gen)) / (Math.pow(2, n) - 1));
    }

    
    @Override
    protected void mutate(int i) {
        this.cromosomas.set(i, !this.cromosomas.get(i));
    }
    
    @Override
    public String toString() {
        String s = super.toString();

        s += "  Cromosoma: ";
        for (int i = 0; i < this.cromosomas.size(); i++) {
            s += cromosomas.get(i) ? "1 " : "0 ";
        }
        s += "\n";

        return s;
    }

}
