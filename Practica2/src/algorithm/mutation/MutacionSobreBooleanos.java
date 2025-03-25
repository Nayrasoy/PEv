package algorithm.mutation;

import java.util.List;

import model.Individuo;
import utils.Utils;

public class MutacionSobreBooleanos extends MutationMethod {

    @Override
    public <T> Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        List<Boolean> cromosomas = indv.getCromosomas();
        for (int i = 0; i < cromosomas.size(); i++) {
            if (Utils.random.nextDouble() < mutationProbability) {
                cromosomas.set(i, !cromosomas.get(i));
            }
        }
        return indv;
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
