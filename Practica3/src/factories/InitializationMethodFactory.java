package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.initialization.*;
import exceptions.InitializationExeption;

public class InitializationMethodFactory {
    
    private static final List<InitializationMethod> methods = Arrays.asList(
		new Creciente(),
        new Completa(),
        new RampedAndHalf()
	);
    
    public static InitializationMethod getSelectionMethod(InitializationType type) throws InitializationExeption {
        for (InitializationMethod method : methods) {
            if (method.getType() == type) {
                return method;
            }
        }
        throw new InitializationExeption("Metodo de inicializacion " + type + " no encontrado");
    }

}
