package algorithm.mutation;

import java.lang.reflect.Parameter;
import java.util.List;

import config.Parameters;
import model.Individuo;
import model.Node;
import utils.Utils;

public class MutacionPermutacion extends MutationMethod  {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        // TODO: Implementar la mutación funcional
        if (Utils.random.nextDouble() < mutationProbability) {
            Individuo indv = individual.copy();
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodos = padre.getFunctionNodes(0, Parameters.DEFAULT_MAX_DEPTH); 

            if (!nodos.isEmpty()) {
                int indexNodo = Utils.random.nextInt(nodos.size());
                Node nodoSeleccionado = nodos.get(indexNodo);

                nodoSeleccionado.permutationTerminal();
            }
            return indv;
        }
        return individual;
    }

    @Override
    public MutationType getType() {
        return MutationType.PERMUTACION;
    }

    @Override
    public MutationMethod create() {
        return new MutacionPermutacion();
    }
}
