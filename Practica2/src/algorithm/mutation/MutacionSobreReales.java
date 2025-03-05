package algorithm.mutation;

import java.util.List;

import algorithm.cross.CrossMethod;
import algorithm.cross.CrossType;
import model.Individuo;
import utils.Utils;

public class MutacionSobreReales extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        List<Double> cromosomas = individual.getCromosomas();
        for (int i = 0; i < cromosomas.size(); i++) {
            if (Utils.random.nextDouble() < mutationProbability) {
                Double min = individual.getMinValueForGen(i), max = individual.getMaxValueForGen(i);
                cromosomas.set(i, min + Utils.random.nextDouble() * (max - min));
            }
        }
        return individual;
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
