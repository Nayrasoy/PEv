package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.cross.*;
import exceptions.CrossException;

public class CrossMethodFactory {
    
    private static final List<CrossMethod> methods = Arrays.asList(
		new MonoPunto(),
        new Uniforme(),
        new BlxAlfa(),
        new Aritmetico(),
        new PMX(),
        new OX(),
        new OXPP(),
        new CX(),
        new CO(),
<<<<<<< HEAD
        new Invent(),
=======
        new ERX()
>>>>>>> d02c90feb15df3f1792d85b7c434ea07fbbe881f
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
