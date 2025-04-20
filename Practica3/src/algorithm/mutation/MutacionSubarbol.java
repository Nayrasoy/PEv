package algorithm.mutation;

import java.util.List;

import algorithm.initialization.InitializationMethod;
import algorithm.selection.SelectionMethod;
import config.Parameters;
import exceptions.InitializationExeption;
import factories.InitializationMethodFactory;
import factories.SelectionMethodFactory;
import model.Individuo;
import model.Node;
import model.Terminal;
import utils.Utils;

public class MutacionSubarbol extends MutationMethod {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        if (Utils.random.nextDouble() < mutationProbability) {
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodos = padre.getFunctionNodes();

            if (!nodos.isEmpty()) {
                int indexNodo = Utils.random.nextInt(nodos.size());
                Node nodoSeleccionado = nodos.get(indexNodo);

                nodoSeleccionado.deleteNode();

                // Inicializar nodo
                try {
                    InitializationMethod sm = InitializationMethodFactory.getInitializationMethod(Parameters.DEFAULT_INITIALIZATION_METHOD);
                    // indv.addAll(sm.initializate(indexNodo, indexNodo));
                } catch (InitializationExeption e) {
                    e.printStackTrace();
                }
            }
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.SUBARBOL;
    }

    @Override
    public MutationMethod create() {
        return new MutacionSubarbol();
    }
}
