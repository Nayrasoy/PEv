package algorithm.cross;

import java.util.List;

import model.Individuo;
import model.Node;

public class TreeCross extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        /*Node padre1 = (Node) individuo1.getCromosomas().get(0);
        Node padre2 = (Node) individuo2.getCromosomas().get(0);

        List<Node> nodos1 = padre1.getAllNodes();
        List<Node> nodos2 = padre2.getAllNodes();

        if (nodos1.size() <= 1 || nodos2.size() <= 1) {
            return List.of(individuo1, individuo2);
        }

        Node sub1 = nodos1.get((int)(Math.random() * nodos1.size()));
        Node sub2 = nodos2.get((int)(Math.random() * nodos2.size()));

        padre1 = padre1.replaceSubtree(sub1, sub2.copy());
        padre2 = padre2.replaceSubtree(sub2, sub1.copy());

        return List.of(individuo1.copy(), individuo2.copy());*/
        return null;
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
