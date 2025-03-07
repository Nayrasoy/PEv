package factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Controller;
import exceptions.IndividuoException;
import model.IndividualType;
import model.Individuo;
import model.IndividuoFuncion1;
import model.IndividuoFuncion2;
import model.IndividuoFuncion3;
import model.IndividuoFuncion4;
import model.IndividuoFuncion5;
import model.Rumba;

public class IndividuoFactory {

    private static final List<Individuo> individuos = Arrays.asList(
		new IndividuoFuncion1(),
        new IndividuoFuncion2(),
        new IndividuoFuncion3(),
        new IndividuoFuncion4(),
        new IndividuoFuncion5(),
        new Rumba()
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
