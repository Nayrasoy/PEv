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

    public IndividuoBooleano(Controller controller, List<Boolean> cromosomas, List<Integer> tamGenes, List<Double> minValue, List<Double> maxValue, int nGenes) {
        super(controller, cromosomas, tamGenes, minValue, maxValue, nGenes);
    }

    protected void init() {
        this.tamGenes = new ArrayList<>();
        int tamTotal = 0;
        for (int i = 0; i < minValue.size(); i++) {
            tamGenes.add(this.tamGen(minValue.get(i), maxValue.get(i)));
            tamTotal += tamGenes.get(i);
        }
        this.nGenes = this.tamGenes.size();
        this.cromosomas = new ArrayList<>();
        for (int i = 0; i < tamTotal; i++) {
            cromosomas.add(Utils.random.nextBoolean());
        }
    }

    @Override
    public Double getFenotipo(int gen) {
        int pos = 0;
        int intValue = 0;

        for (int i = 0; i < gen; i++) {
            pos += this.tamGenes.get(i);
        }

        
        int n = pos + this.tamGenes.get(gen);

        for (int i = pos; i < n; i++) {
            if (this.cromosomas.get(i)) {
                intValue += 1 << (n - 1 - i);
            }
        }

        return this.minValue.get(gen) + (intValue * (this.maxValue.get(gen) - this.minValue.get(gen)) / (Math.pow(2, this.tamGenes.get(gen)) - 1));
    }

}
