package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class MonoPunto extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<T> cromosomas1 = individuo1.getCromosomas();
        List<T> cromosomas2 = individuo2.getCromosomas();

        int division = Utils.random.nextInt(cromosomas1.size());
        for (int i = division; i < cromosomas1.size(); i++) {
            T temp = cromosomas1.get(i);
            cromosomas1.set(i, cromosomas2.get(i));
            cromosomas2.set(i, temp);
        }
        newPoblation.add(individuo1);
        newPoblation.add(individuo2);

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
