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
        /*if (Utils.random.nextDouble() < mutationProbability) {
            Individuo indv = individual.copy();
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodos = padre.getFunctionNodes();

            if (!nodos.isEmpty()) {
                int indexNodo = Utils.random.nextInt(nodos.size());
                Node nodoSeleccionado = nodos.get(indexNodo);

                try {
                    InitializationMethod sm = InitializationMethodFactory.getInitializationMethod(Parameters.DEFAULT_INITIALIZATION_METHOD);
                    Node nuevoSubarbol = (Node) sm.initializate(1, 3); // Generamos un solo Node

                    // Reemplazamos el subárbol dentro del árbol
                    Node nuevoArbol = padre.replaceSubtree(nodoSeleccionado, nuevoSubarbol);
                    indv.getCromosomas().set(0, nuevoArbol); // Actualizamos el árbol principal del individuo

                } catch (InitializationExeption e) {
                    e.printStackTrace();
                }
            }
            return indv;
        }*/
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
