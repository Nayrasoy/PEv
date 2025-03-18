package algorithm.mutation;

import java.util.*;

import model.Individuo;
import utils.Utils;

public class MutacionPorIntercambio extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        List<String> cromosomas = individual.getCromosomas();
        int num1 = Utils.random.nextInt(cromosomas.size());
        int num2 = Utils.random.nextInt(cromosomas.size());

        while (num1 == num2) {
            num2 = Utils.random.nextInt(cromosomas.size());
        }

        Collections.swap(cromosomas, num1, num2);

        return individual;
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