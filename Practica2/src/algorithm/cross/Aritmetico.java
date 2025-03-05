package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class Aritmetico extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<Double> cromosomas1 = individuo1.getCromosomas();
        List<Double> cromosomas2 = individuo2.getCromosomas();
        double p = Utils.random.nextDouble();
        
        if (p < 0.5) {
            for (int i = 0; i < cromosomas1.size(); i++) {
                cromosomas1.set(i, (cromosomas1.get(i) + cromosomas2.get(i)) / 2);
            }
        }
        else {
            for (int i = 0; i < cromosomas1.size(); i++) {
                cromosomas1.set(i, cromosomas1.get(i) * p + cromosomas2.get(i) * (1 - p));
            }
        }
        newPoblation.add(individuo1);
        newPoblation.add(individuo1.copy());

        return newPoblation;
    }

    @Override
    public CrossType getType() {
        return CrossType.ARITMETICO;
    }

    @Override
    public CrossMethod create() {
        return new Aritmetico();
    }
    
}
