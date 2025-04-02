package algorithm.initialization;

import model.Individuo;

public class Completa extends InitializationMethod {

    @Override
    public <T> Individuo mutate(Individuo individual) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mutate'");
    }

    @Override
    public InitializationType getType() {
        return InitializationType.COMPLETA;
    }
    
}
