package algorithm.mutation;

import java.util.List;

import model.Individuo;
import model.Terminal;
import model.Node;
import utils.Utils;

public class MutacionTerminal extends MutationMethod {

    @Override
    public Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        if (Utils.random.nextDouble() < mutationProbability) {
            Node padre = (Node) indv.getCromosomas().get(0);
            List<Node> nodosTerminales = padre.getTerminalNodes();

            if (!nodosTerminales.isEmpty()) {
                int indexNodo = Utils.random.nextInt(nodosTerminales.size());
                Node nodoSeleccionado = nodosTerminales.get(indexNodo);

                Terminal nuevoTerminal = Terminal.getRandomTerminal();

                nodoSeleccionado.setTerminal(nuevoTerminal);
            }
        }

        return indv;
    }

    @Override
    public MutationType getType() {
        return MutationType.TERMINAL;
    }

    @Override
    public MutationMethod create() {
        return new MutacionTerminal();
    }
}
