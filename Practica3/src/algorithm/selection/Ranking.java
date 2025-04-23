package algorithm.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class Ranking extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        List<Double> probabilities = new ArrayList<>();
        List<Individuo> newPoblation = new ArrayList<>(); 
        double probability, lastProbability = 0, random;
        double minValue = poblation.get(0).getMinValue();

        if (minValue < 0) {
            fitnessSum = 0;
            for (int i = 0; i < fitness.size(); i++) {
                fitness.set(i, -minValue - fitness.get(i));
                fitnessSum += fitness.get(i);
            }
        }

        // Ordenar fitness
        Collections.sort(poblation, (a, b) -> poblation.get(0).betterThan(b.getFitness(), a.getFitness()));
    
        for (int i = 1; i <= fitness.size(); i++) {
            probability = -2;
            probability *= (Parameters.RANKING_BETA - 1);
            probability *= (double)(i - 1) / (double)(fitness.size() - 1);
            probability += Parameters.RANKING_BETA;
            probability /= fitness.size();
            probability += lastProbability;
            probabilities.add(probability);
            lastProbability = probability;
        }

        for (int i = 0; i < n; i++) {
            random = Utils.random.nextDouble();
            for (int j = 0; j < probabilities.size(); j++) {
                Double p = probabilities.get(j);
                if (random < p) {
                    newPoblation.add(poblation.get(j).copy());
                    break;
                }
            }
        }

        return newPoblation;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.RANKING;
    }

    @Override
    public SelectionMethod create() {
        return new Ranking();
    }
    
}
