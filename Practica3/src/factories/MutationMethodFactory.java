package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.mutation.*;
import exceptions.SelectionException;


public class MutationMethodFactory {
    
    private static final List<MutationMethod> methods = Arrays.asList(
		new MutacionSobreBooleanos(),
        new MutacionSobreReales(),
        new MutacionPorInversion(),
        new MutacionPorInsercion(),
        new MutacionHeuristica(),
        new MutacionPorIntercambio(),
        new MutacionInventada()
	);
    
    public static MutationMethod getSelectionMethod(MutationType type) throws SelectionException {
        for (MutationMethod method : methods) {
            if (method.getType() == type) {
                return method;
            }
        }
        throw new SelectionException("Metodo de mutacion " + type + " no encontrado");
    }

}
