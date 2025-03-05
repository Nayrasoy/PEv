package algorithm.mutation;

import java.util.List;

import model.Individuo;
import utils.Utils;

public class MutacionSobreBooleanos extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        List<Boolean> cromosomas = individual.getCromosomas();
        for (int i = 0; i < cromosomas.size(); i++) {
            if (Utils.random.nextDouble() < mutationProbability) {
                cromosomas.set(i, !cromosomas.get(i));
            }
        }
        return individual;
    }

    @Override
    public MutationType getType() {
        return MutationType.SOBRE_BOOLEANOS;
    }

    @Override
    public MutationMethod create() {
        return new MutacionSobreBooleanos();
    }
    
}
