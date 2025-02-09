package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.IndividuoException;

public class IndividuoFactory {

    private static List<Individuo> individuos;

    public IndividuoFactory() {
        this.individuos = new ArrayList<>();
        this.individuos.add(new IndividuoFuncion1());
    }
    
    public static Individuo getIndividuo(int type) throws IndividuoException {
        for (Individuo individuo : individuos) {
            if (individuo.getType() == type) {
                return individuo.create();
            }
        }
        throw new IndividuoException("Tipo de individuo " + type + " no encontrado");
    }

}
