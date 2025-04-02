package algorithm.initialization;

import model.Individuo;

public abstract class InitializationMethod {

    public abstract <T> Individuo mutate(Individuo individual);

    public abstract InitializationType getType();
    
}
