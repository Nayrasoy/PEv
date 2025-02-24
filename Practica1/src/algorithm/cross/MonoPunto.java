package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;

public class MonoPunto extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        
        

        return newPoblation;
    }

    @Override
    public CrossType getType() {
        return CrossType.MONO_PUNTO;
    }

    @Override
    public CrossMethod create() {
        return new MonoPunto();
    }
    
}
