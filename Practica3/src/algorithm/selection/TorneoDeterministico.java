package algorithm.selection;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class TorneoDeterministico extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        List<Individuo> newPoblation = new ArrayList<>();
        int k = Parameters.TOURNAMENT_K;
        double minValue = poblation.get(0).getMinValue();

        if (minValue < 0) {
            fitnessSum = 0;
            for (int i = 0; i < fitness.size(); i++) {
                fitness.set(i, -minValue - fitness.get(i));
                fitnessSum += fitness.get(i);
            }
        }
        
        while (newPoblation.size() < n) {
            List<Individuo> tournament = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                int random = Utils.random.nextInt(n);
                tournament.add(poblation.get(random));
            }

            int best = 0;
            for (int i = 1; i < k; i++) {
                if (tournament.get(i).betterThan(tournament.get(i).getFitness(), tournament.get(best).getFitness()) == 1) {
                    best = i;
                }
            }

            newPoblation.add(tournament.get(best).copy());
        }

        return newPoblation;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.TORNEO_DETERMINISTICO;
    }

    @Override
    public SelectionMethod create() {
        return new TorneoDeterministico();
    }
    
}
