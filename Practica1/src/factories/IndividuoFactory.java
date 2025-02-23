package factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Controller;
import exceptions.IndividuoException;
import model.IndividualType;
import model.Individuo;
import model.IndividuoFuncion1;

public class IndividuoFactory {

    private static final List<Individuo> individuos = Arrays.asList(
		new IndividuoFuncion1()
	);
    
    public static Individuo getIndividuo(IndividualType type, Controller controller) throws IndividuoException {
        for (Individuo individuo : individuos) {
            if (individuo.getType() == type) {
                return individuo.create(controller);
            }
        }
        throw new IndividuoException("Tipo de individuo " + type + " no encontrado");
    }

}
