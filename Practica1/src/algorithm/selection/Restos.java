package algorithm.selection;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class Restos extends SelectionMethod {

    // TODO: hay dos torneos??

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum) {
        List<Individuo> newPoblation = new ArrayList<>(); 
        int k = poblation.size();
        double probability, random;
        double minValue = poblation.get(0).getMinValue();

        if (minValue < 0) {
            fitnessSum += minValue * poblation.size();
        }
    
        for (int i = 0; i < fitness.size(); i++) {
            probability = fitness.get(i);
            if (minValue < 0) {
                probability += minValue;
            }
            probability /= fitnessSum;
            if (Math.floor(probability*k)){

            }
            else {
                lista ordenada
            }
            for (int j = 0; j < ; j++){
                newPoblation.add(poblation.get(i));
            }
        }

        for (int i = newPoblation.size(); i < k, i++){
            seleccionamos
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
