package factories;

import java.util.Arrays;
import java.util.List;

import algorithm.selection.*;
import exceptions.SelectionException;


public class SelectionMethodFactory {
    
    private static final List<SelectionMethod> methods = Arrays.asList(
		new Ruleta(), 
        new TorneoDeterministico(),
        new TorneoProbabilistico(),
        new EstocasticoUniversal(),
        new Truncamiento(),
        new Restos()
	);
    
    public static SelectionMethod getSelectionMethod(SelectionType type) throws SelectionException {
        for (SelectionMethod method : methods) {
            if (method.getType() == type) {
                return method.create();
            }
        }
        throw new SelectionException("Metodo de seleccion " + type + " no encontrado");
    }

}
