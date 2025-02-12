package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.cross.CrossMethod;
import algorithm.cross.CrossType;
import algorithm.cross.MonoPunto;
import exceptions.CrossException;

public class CrossMethodFactory {
    
    private static final List<CrossMethod> methods = Arrays.asList(
		new MonoPunto()
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
