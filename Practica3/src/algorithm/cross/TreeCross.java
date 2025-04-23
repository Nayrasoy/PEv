package algorithm.cross;

import java.util.List;

import model.Individuo;
import model.Node;
import utils.Utils;

public class TreeCross extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        Node padre1 = (Node) individuo1.getCromosomas().get(0);
        Node padre2 = (Node) individuo2.getCromosomas().get(0);

        List<Node> nodos1 = padre1.getAllNodes();
        List<Node> nodos2 = padre2.getAllNodes();

        if (nodos1.size() <= 1 || nodos2.size() <= 1) {
            return List.of(individuo1, individuo2);
        }

        Node sub1 = nodos1.get((int)(Utils.random.nextDouble() * nodos1.size()));
        Node sub2 = nodos2.get((int)(Utils.random.nextDouble() * nodos2.size()));

        Node sub1Temp = sub1.copy();
        sub1.replaceSubtree(sub2.copy());
        sub2.replaceSubtree(sub1Temp);

        return List.of(individuo1.copy(), individuo2.copy());
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
