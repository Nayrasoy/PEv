package algorithm.mutation;

import java.util.*;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class MutacionPorInsercion extends MutationMethod {

    @Override
    public <T> Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        if (Utils.random.nextDouble() < mutationProbability) {
            List<String> cromosomas = indv.getCromosomas();
            for (int i = 0; i < Parameters.DESPLAZAMIENTO_INSERCION; i++){
                int from = Utils.random.nextInt(cromosomas.size());
                int to = Utils.random.nextInt(cromosomas.size());

                String val = cromosomas.remove(from);
                cromosomas.add(to, val);
            }
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.POR_INSERCION;
    }

    @Override
    public MutationMethod create() {
        return new MutacionPorInsercion();
    }
    
}