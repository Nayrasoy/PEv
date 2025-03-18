package algorithm.mutation;

import java.util.*;

import model.Individuo;
import utils.Utils;

public class MutacionPorInversion extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        List<String> cromosomas = individual.getCromosomas();
        int num1 = Utils.random.nextInt(cromosomas.size());
        int num2 = Utils.random.nextInt(cromosomas.size());
        
        if (num1 > num2) {
            int aux = num1;
            num1 = num2;
            num2 = aux;
        }

        Collections.reverse(cromosomas.subList(num1, num2));

        return individual;
    }

    @Override
    public MutationType getType() {
        return MutationType.POR_INVERSION;
    }

    @Override
    public MutationMethod create() {
        return new MutacionPorInversion();
    }
    
}
