package algorithm.mutation;

import model.Individuo;
import utils.Utils;

public class MutacionFuncional extends MutationMethod {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        // TODO: Implementar la mutación funcional
        if (Utils.random.nextDouble() < mutationProbability) {
            
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.FUNCIONAL;
    }

    @Override
    public MutationMethod create() {
        return new MutacionFuncional();
    }
}
