package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class CX extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> cromosomas2 = individuo2.getCromosomas();
        
        List<Boolean> visitado = this.processCromosomes(cromosomas1, cromosomas2);

        for (int i = 0; i < cromosomas1.size(); i++) {
            if (!visitado.get(i)) {
                String temp = cromosomas1.get(i);
                cromosomas1.set(i, cromosomas2.get(i));
                cromosomas2.set(i, temp);
            }
        }

        newPoblation.add(individuo1.copy());
        newPoblation.add(individuo2.copy());

        return newPoblation;
    }

    private List<Boolean> processCromosomes(List<String> cromosome1, List<String> cromosome2) {
        List<Boolean> visitado = new ArrayList<>();
        visitado.add(true);
        for (int i = 1; i < cromosome1.size(); i++) {
            visitado.add(false);
        }
        int indx = cromosome1.indexOf(cromosome2.getFirst());

        while (indx > 0) {
            visitado.set(indx, true);
            indx = cromosome1.indexOf(cromosome2.get(indx));
        }

        return visitado;
    }

    @Override
    public CrossType getType() {
        return CrossType.CX;
    }

    @Override
    public CrossMethod create() {
        return new CX();
    }
    
}
