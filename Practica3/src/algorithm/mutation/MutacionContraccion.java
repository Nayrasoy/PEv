package algorithm.mutation;

import java.util.List;

import model.Individuo;
import model.Node;
import model.Terminal;
import utils.Utils;

public class MutacionContraccion extends MutationMethod {
    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        if (Utils.random.nextDouble() < mutationProbability) {
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodos = padre.getFunctionNodes();
            nodos.remove(0);

            if (!nodos.isEmpty()) { 
                int indexNodo = Utils.random.nextInt(nodos.size());
                Node nodoSeleccionado = nodos.get(indexNodo);

                Terminal nuevoTerminal = Terminal.getRandomTerminal();

                nodoSeleccionado.setTerminal(nuevoTerminal);
            }
        }

        return indv;
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
