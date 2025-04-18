package algorithm.mutation;

import model.Individuo;
import utils.Utils;

public class MutacionSubarbol extends MutationMethod {
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
        return MutationType.SUBARBOL;
    }

    @Override
    public MutationMethod create() {
        return new MutacionSubarbol();
    }
}
