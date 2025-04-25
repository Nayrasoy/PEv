package algorithm.initialization;

import java.util.ArrayList;
import java.util.List;

import algorithm.AlgoritmoGenetico;
import config.Parameters;
import exceptions.InitializationExeption;
import factories.InitializationMethodFactory;
import model.Node;

public class RampedAndHalf extends InitializationMethod {

    private static boolean calculate = true;
    private static List<Integer> numIndv = null;
    private static int individuosInicializados = 0;
    private static int capaActual = 0;

    @Override
    public Node initializate(int startingDepth, int minDepth, int maxDepth) {
        // Si es el primer individuo, calculamos cuántos individuos se van a inicializar con qué métodos y con cuánta profundidad
        if (calculate) {
            int capas = Parameters.DEFAULT_MAX_DEPTH - Parameters.DEFAULT_MIN_DEPTH + 1;
            numIndv = new ArrayList<>();
            for (int i = 0; i < capas - 1; i++) {
                numIndv.add(AlgoritmoGenetico.tamPoblation / capas);
            }
            numIndv.add(AlgoritmoGenetico.tamPoblation / capas + AlgoritmoGenetico.tamPoblation % capas);
            individuosInicializados = 0;
            capaActual = 0;
            calculate = false;
        }
        try {
            // 
            InitializationMethod initializationMethod;
            if (numIndv.get(capaActual) / 2 < individuosInicializados) {
                initializationMethod = InitializationMethodFactory.getInitializationMethod(InitializationType.COMPLETA);
            }
            else {
                initializationMethod = InitializationMethodFactory.getInitializationMethod(InitializationType.CRECIENTE);
            }
            individuosInicializados++;
            if (numIndv.get(capaActual) == individuosInicializados) {
                capaActual++;
                if (capaActual == numIndv.size()) {
                    calculate = true;
                }
                individuosInicializados = 0;
            }
            return initializationMethod.initializate(startingDepth, minDepth, capaActual + Parameters.DEFAULT_MIN_DEPTH);
        } catch (InitializationExeption e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InitializationType getType() {
        return InitializationType.RAMPED_AND_HALF;
    }
    
}
