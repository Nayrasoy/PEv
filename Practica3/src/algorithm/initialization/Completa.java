package algorithm.initialization;

import model.Terminal;
import utils.Utils;
import model.Node;

import java.util.*;

public class Completa extends InitializationMethod {

    private final Random random = new Random();

    @Override
    public List<Node> initializate(int minDepth, int maxDepth) {
        int depth = Utils.random.nextInt(maxDepth - minDepth + 1) + minDepth;
        List<Node> nodes = new ArrayList<>();
        generateFullTree(nodes, depth, 0);
        return nodes;
    }

    private void generateFullTree(List<Node> nodes, int maxDepth, int currentDepth) {
        Terminal tipo;

        if (currentDepth < maxDepth - 1) {
            tipo = Terminal.getRandomFunction();
            int arity = tipo.aridad();
            for (int i = 0; i < arity; i++) {
                generateFullTree(nodes, maxDepth, currentDepth + 1);
            }
        } 
        else {
            tipo = Terminal.getRandomTerminal();
        }

        nodes.add(new Node(tipo));
    }

    @Override
    public InitializationType getType() {
        return InitializationType.COMPLETA;
    }
}
