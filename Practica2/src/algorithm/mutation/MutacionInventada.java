package algorithm.mutation;

import java.lang.reflect.Parameter;
import java.util.*;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class MutacionInventada extends MutationMethod {

    @Override
    public <T> Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();
        List<T> cromosomas = indv.getCromosomas();

        if (Utils.random.nextDouble() < mutationProbability) {
            for (int i = 0; i < cromosomas.size() - 1; i++) {
                if (Utils.random.nextDouble() < Parameters.INVENTED_MUTATION_PROBABILITY) {
                    Collections.swap(cromosomas, i, i + 1);
                }
            }
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.INVENCION;
    }

    @Override
    public MutationMethod create() {
        return new MutacionInventada();
    }
    
}