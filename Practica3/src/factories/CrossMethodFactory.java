package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.cross.*;
import exceptions.CrossException;

public class CrossMethodFactory {
    
    private static final List<CrossMethod> methods = Arrays.asList(
        new TreeCross()
	);
    
    public static CrossMethod getCrossMethod(CrossType type) throws CrossException {
        for (CrossMethod method : methods) {
            if (method.getType() == type) {
                return method;
            }
        }
        throw new CrossException("Metodo de cruce " + type + " no encontrado");
    }

}
