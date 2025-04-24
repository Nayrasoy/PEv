package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import model.Individuo;
import model.Node;
import utils.Utils;

public class TreeCross extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> offspringPool = new ArrayList<>();

        for (int i = 0; i < Parameters.N; i++) {
            Individuo child1 = individuo1.copy();
            Individuo child2 = individuo2.copy();

            Node padre1 = (Node) child1.getCromosomas().get(0);
            Node padre2 = (Node) child2.getCromosomas().get(0);

            List<Node> nodos1 = padre1.getAllNodes(Parameters.DEFAULT_MIN_DEPTH, Parameters.DEFAULT_MAX_DEPTH);
            if (nodos1.size() <= 1) continue;

            Node sub1 = nodos1.get((int) (Utils.random.nextDouble() * nodos1.size()));
            List<Node> nodos2 = padre2.getAllNodes(sub1.getDepth(), sub1.getDepth());
            if (nodos2.size() <= 1) continue;

            Node sub2 = nodos2.get((int) (Utils.random.nextDouble() * nodos2.size()));

            Node sub1Temp = sub1.copy();
            sub1.replaceSubtree(sub2.copy());
            sub2.replaceSubtree(sub1Temp);

            // Evaluar fitness de los hijos generados
            offspringPool.add(child1);
            offspringPool.add(child2);
        }

        // Si no se generaron hijos válidos, devolvemos los originales
        if (offspringPool.isEmpty()) {
            return List.of(individuo1, individuo2);
        }

        // Ordenar los hijos según fitness (mejor primero)
        offspringPool.sort((a, b) -> b.betterThan(a.getFitness(), b.getFitness()));

        // Devolver los dos mejores hijos
        return offspringPool.subList(0, Math.min(2, offspringPool.size()));
    }


    @Override
    public CrossType getType() {
        return CrossType.TREE_CROSS;
    }

    @Override
    public CrossMethod create() {
        return new TreeCross();
    }
    
}
