package algorithm.mutation;

import java.util.*;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class MutacionHeuristica extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        List<String> cromosomas = individual.getCromosomas();
        
        // TODO

        return individual;
    }

    @Override
    public MutationType getType() {
        return MutationType.HEURISTICA;
    }

    @Override
    public MutationMethod create() {
        return new MutacionHeuristica();
    }
    
}