package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class Invent extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> cromosomas2 = individuo2.getCromosomas();
        List<Integer> posChange = new ArrayList<>();
        List<String> numChange = new ArrayList();
        // int selectIndividuo = Utils.random.nextInt(2);

        // Buscamos los numeros random a elegir
        while (posChange.size() < Parameters.CROSS_INVENT_RAND) {
            int rand = Utils.random.nextInt(cromosomas1.size());
            if (!posChange.contains(rand)){
                posChange.add(rand);
            }
        }
        
        // Buscamos la posicion del cromosoma 2
        for (Integer ind : posChange) {
            numChange.add(cromosomas1.get(ind));
        }

        // Intercambiamos los valores
        for (String val : numChange) {
            int pos1 = cromosomas1.indexOf(val);
            int pos2 = cromosomas2.indexOf(val);

            cromosomas1.remove(pos1);
            cromosomas1.add(pos2, val);
            cromosomas2.remove(pos2);
            cromosomas2.add(pos1, val);
        }

        newPoblation.add(individuo1.copy());
        newPoblation.add(individuo2.copy());

        return newPoblation;
    }

    @Override
    public CrossType getType() {
        return CrossType.INVENT;
    }

    @Override
    public CrossMethod create() {
        return new Invent();
    }
    
}
