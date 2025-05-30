package algorithm.mutation;

import java.util.List;

import config.Parameters;
import model.Individuo;
import model.Node;
import model.Terminal;
import utils.Utils;

public class MutacionContraccion extends MutationMethod {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        if (Utils.random.nextDouble() < mutationProbability) {
            Individuo indv = individual.copy();
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodos = padre.getFunctionNodes(Parameters.DEFAULT_MIN_DEPTH, Parameters.DEFAULT_MAX_DEPTH);

            if (!nodos.isEmpty()) { 
                int indexNodo = Utils.random.nextInt(nodos.size());
                Node nodoSeleccionado = nodos.get(indexNodo);

                Terminal nuevoTerminal = Terminal.getRandomTerminal();

                nodoSeleccionado.setTerminal(nuevoTerminal);
            }
            return indv;
        }
        return individual;
    }

    @Override
    public MutationType getType() {
        return MutationType.CONTRACCION;
    }

    @Override
    public MutationMethod create() {
        return new MutacionContraccion();
    }
}
