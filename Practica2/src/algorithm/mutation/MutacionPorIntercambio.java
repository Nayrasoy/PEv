package algorithm.mutation;

import java.util.*;

import model.Individuo;
import utils.Utils;

public class MutacionPorIntercambio extends MutationMethod {

    @Override
    public <T> Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        if (Utils.random.nextDouble() < mutationProbability) {
            List<String> cromosomas = indv.getCromosomas();
            int num1 = Utils.random.nextInt(cromosomas.size());
            int num2 = Utils.random.nextInt(cromosomas.size());

            while (num1 == num2) {
                num2 = Utils.random.nextInt(cromosomas.size());
            }

            Collections.swap(cromosomas, num1, num2);
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.POR_INTERCAMBIO;
    }

    @Override
    public MutationMethod create() {
        return new MutacionPorIntercambio();
    }
    
}