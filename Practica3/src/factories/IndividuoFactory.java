package factories;

import java.util.Arrays;
import java.util.List;

import controller.Controller;
import exceptions.IndividuoException;
import model.*;

public class IndividuoFactory {

    private static final List<Individuo> individuos = Arrays.asList(
		new IndividuoFuncion1(),
        new IndividuoFuncion2(),
        new IndividuoFuncion3(),
        new IndividuoFuncion4(),
        new IndividuoFuncion5(),
        new Rumba(),
        new Ant()
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
