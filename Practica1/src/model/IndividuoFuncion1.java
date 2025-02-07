package model;

import java.util.ArrayList;

public class IndividuoFuncion1 extends Individuo<Boolean> {

    public IndividuoFuncion1() {
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
            cromosomas.add(Individuo.random.nextBoolean());
        }
    }

    public double getValor() {
        // double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        return 0;//(21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
    }

    @Override
    public double getFitness() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFitness'");
    }

    @Override
    public double getFenotipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFenotipo'");
    }
    @Override
    public String toString() {
        String s = super.toString();

        s += "Cromosoma: ";
        for (int i = 0; i < this.cromosomas.size(); i++) {
            s += cromosomas.get(i) ? "1 " : "0 ";
        }
        s += "\n";

        return s;
    }
    
}
