package algorithm.mutation;

import java.util.List;

import algorithm.initialization.InitializationMethod;
import config.Parameters;
import exceptions.InitializationExeption;
import factories.InitializationMethodFactory;
import model.Individuo;
import model.Node;
import utils.Utils;

public class MutacionSubarbol extends MutationMethod {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        if (Utils.random.nextDouble() < mutationProbability) {
            Individuo indv = individual.copy();
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodos = padre.getFunctionNodes(0, Parameters.DEFAULT_MAX_DEPTH); // Obtenemos todos los nodos del árbol

            if (!nodos.isEmpty()) {
                int indexNodo = Utils.random.nextInt(nodos.size());
                Node nodoSeleccionado = nodos.get(indexNodo);

                try {
                    InitializationMethod sm = InitializationMethodFactory.getInitializationMethod(Parameters.DEFAULT_INITIALIZATION_METHOD);
                    Node nuevoSubarbol = (Node) sm.initializate(nodoSeleccionado.getDepth(), Parameters.DEFAULT_MIN_DEPTH, Parameters.DEFAULT_MAX_DEPTH); // Generamos un solo Node

                    // Reemplazamos el subárbol dentro del árbol
                    nodoSeleccionado.replaceSubtree(nuevoSubarbol);

                } catch (InitializationExeption e) {
                    e.printStackTrace();
                }
            }
            return indv;
        }
        return individual;
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
