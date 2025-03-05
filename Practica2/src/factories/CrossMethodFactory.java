package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.cross.Aritmetico;
import algorithm.cross.BlxAlfa;
import algorithm.cross.CrossMethod;
import algorithm.cross.CrossType;
import algorithm.cross.MonoPunto;
import algorithm.cross.Uniforme;
import exceptions.CrossException;

public class CrossMethodFactory {
    
    private static final List<CrossMethod> methods = Arrays.asList(
		new MonoPunto(),
        new Uniforme(),
        new BlxAlfa(),
        new Aritmetico()
	);
    
    public static CrossMethod getCrossMethod(CrossType type) throws CrossException {
        for (CrossMethod method : methods) {
            if (method.getType() == type) {
                return method.create();
            }
        }
        throw new CrossException("Metodo de seleccion " + type + " no encontrado");
    }

}
