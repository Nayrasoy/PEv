package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.mutation.MutacionPorInversion;
import algorithm.mutation.MutacionSobreBooleanos;
import algorithm.mutation.MutacionSobreReales;
import algorithm.mutation.MutationMethod;
import algorithm.mutation.MutationType;
import exceptions.SelectionException;


public class MutationMethodFactory {
    
    private static final List<MutationMethod> methods = Arrays.asList(
		new MutacionSobreBooleanos(),
        new MutacionSobreReales(),
        new MutacionPorInversion()
	);
    
    public static MutationMethod getSelectionMethod(MutationType type) throws SelectionException {
        for (MutationMethod method : methods) {
            if (method.getType() == type) {
                return method.create();
            }
        }
        throw new SelectionException("Metodo de mutacion " + type + " no encontrado");
    }

}
