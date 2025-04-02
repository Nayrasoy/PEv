package algorithm.mutation;

import java.util.List;

import model.Individuo;
import utils.Utils;

public class MutacionSobreReales extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        List<Double> cromosomas = indv.getCromosomas();
        for (int i = 0; i < cromosomas.size(); i++) {
            if (Utils.random.nextDouble() < mutationProbability) {
                Double min = indv.getMinValueForGen(i), max = indv.getMaxValueForGen(i);
                cromosomas.set(i, min + Utils.random.nextDouble() * (max - min));
            }
        }
        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.SOBRE_REALES;
    }

    @Override
    public MutationMethod create() {
        return new MutacionSobreReales();
    }
    
}
