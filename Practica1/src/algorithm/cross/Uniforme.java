package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class Uniforme extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<T> cromosomas1 = individuo1.getCromosomas();
        List<T> cromosomas2 = individuo2.getCromosomas();

        for (int i = 0; i < cromosomas1.size(); i++) {
            if (Utils.random.nextDouble() < Parameters.DEFAULT_UNIFORM_CROSS_PROBABILITY) {
                T temp = cromosomas1.get(i);
                cromosomas1.set(i, cromosomas2.get(i));
                cromosomas2.set(i, temp);
            }
        }
        newPoblation.add(individuo1);
        newPoblation.add(individuo2);

        return newPoblation;
    }

    @Override
    public CrossType getType() {
        return CrossType.UNIFORME;
    }

    @Override
    public CrossMethod create() {
        return new Uniforme();
    }
    
}
