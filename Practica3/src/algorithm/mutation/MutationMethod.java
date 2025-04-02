package algorithm.mutation;

import model.Individuo;

public abstract class MutationMethod {

    public abstract <T> Individuo mutate(Individuo individual, double mutationProbability);

    public abstract MutationType getType();

    public abstract MutationMethod create();
    
}
