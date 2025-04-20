package algorithm.selection;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import exceptions.SelectionException;
import factories.SelectionMethodFactory;
import model.Individuo;

public class Restos extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        List<Individuo> newPoblation = new ArrayList<>(), extraPoblation = new ArrayList<>(); 
        List<Double> newFitness = new ArrayList<>();
        double newFitnessSum = 0;
        int k = poblation.size();
        double probability, random;
        double minValue = poblation.get(0).getMinValue();

        if (minValue < 0) {
            fitnessSum = 0;
            for (int i = 0; i < fitness.size(); i++) {
                fitness.set(i, -minValue - fitness.get(i));
                fitnessSum += fitness.get(i);
            }
        }
    
        for (int i = 0; i < fitness.size(); i++) {
            probability = fitness.get(i);
            probability /= fitnessSum;
            if (Math.floor(probability*k) >= 1){
                for (int j = 0; j < Math.floor(probability*k); j++){
                    newPoblation.add(poblation.get(i).copy());
                }
            }
            else {
                extraPoblation.add(poblation.get(i).copy());
                newFitness.add(fitness.get(i));
                newFitnessSum += fitness.get(i);
            }
        }

        try {
            SelectionMethod sm = SelectionMethodFactory.getSelectionMethod(Parameters.DEFAULT_SELECTION_METHOD);
            newPoblation.addAll(sm.selection(extraPoblation, newFitness, newFitnessSum, n - newPoblation.size()));
        } catch (SelectionException e) {
            System.out.println(e);
        }

        return newPoblation;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.RESTOS;
    }

    @Override
    public SelectionMethod create() {
        return new Restos();
    }
    
}
