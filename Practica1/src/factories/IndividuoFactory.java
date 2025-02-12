package factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.IndividuoException;
import model.Individuo;
import model.IndividuoFuncion1;

public class IndividuoFactory {

    private static final List<Individuo> individuos = Arrays.asList(
		new IndividuoFuncion1()
	);
    
    public static Individuo getIndividuo(int type) throws IndividuoException {
        for (Individuo individuo : individuos) {
            if (individuo.getType() == type) {
                return individuo.create();
            }
        }
        throw new IndividuoException("Tipo de individuo " + type + " no encontrado");
    }

}
