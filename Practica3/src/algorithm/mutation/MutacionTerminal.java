package algorithm.mutation;

import java.util.List;

import model.Individuo;
import utils.Utils;

public class MutacionTerminal extends MutationMethod {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        // TODO: Implementar la mutación funcional
        if (Utils.random.nextDouble() < mutationProbability) {
            List<String> cromosomas = indv.getCromosomas();
            
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.TERMINAL;
    }

    @Override
    public MutationMethod create() {
        return new MutacionTerminal();
    }
}
