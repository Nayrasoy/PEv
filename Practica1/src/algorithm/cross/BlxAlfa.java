package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class BlxAlfa extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<Double> cromosomas1 = individuo1.getCromosomas();
        List<Double> cromosomas2 = individuo2.getCromosomas();
        double Cmax, Cmin, I, alpha;
       
        for (int i = 0; i < cromosomas1.size(); i++) {
            Cmax = Math.max(cromosomas1.get(i), cromosomas2.get(i));
            Cmin = Math.min(cromosomas1.get(i), cromosomas2.get(i));
            I = Cmax - Cmin;
            alpha = Utils.random.nextDouble();

            Cmin -= I * alpha;
            Cmax += I * alpha;
            
            cromosomas1.set(i, Cmin + Utils.random.nextDouble() * (Cmax - Cmin));
        }
        newPoblation.add(individuo1);
        newPoblation.add(individuo1.copy());

        return newPoblation;
    }

    @Override
    public CrossType getType() {
        return CrossType.BLX_ALFA;
    }

    @Override
    public CrossMethod create() {
        return new BlxAlfa();
    }
    
}
