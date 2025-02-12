package algorithm.selection;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class Ruleta extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum) {
        List<Double> probabilities = new ArrayList<>();
        List<Individuo> newPoblation = new ArrayList<>(); 
        double actual = 0, probability, random;
    
        for (int i = 0; i < fitness.size(); i++) {
            probability = fitness.get(i) / fitnessSum;
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
