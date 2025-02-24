package algorithm.selection;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class Ruleta extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        List<Double> probabilities = new ArrayList<>();
        List<Individuo> newPoblation = new ArrayList<>(); 
        double actual = 0, probability, random;
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
            actual += probability;
            probabilities.add(actual);
        }

        for (int i = 0; i < fitness.size(); i++) {
            random = Utils.random.nextDouble();
            for (int j = 0; j < probabilities.size(); j++) {
                Double p = probabilities.get(j);
                if (random < p) {
                    newPoblation.add(poblation.get(j));
                    break;
                }
            }
        }

        return newPoblation;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.RULETA;
    }

    @Override
    public SelectionMethod create() {
        return new Ruleta();
    }
    
}
