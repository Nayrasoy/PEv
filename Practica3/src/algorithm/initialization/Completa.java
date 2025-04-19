package algorithm.initialization;

import model.Node;
import model.Terminal;
import utils.Utils;

public class Completa extends InitializationMethod {

    @Override
    public Node initializate(int minDepth, int maxDepth) {
        return generateFullTree(0, minDepth, maxDepth);
    }

    private Node generateFullTree(int currentDepth, int minDepth, int maxDepth) {
        if (currentDepth >= maxDepth) {
            Terminal[] hojas = {Terminal.AVANZA, Terminal.DERECHA, Terminal.IZQUIERDA};
            return new Node(hojas[(int) (Math.random() * hojas.length)]);
        }

        if (currentDepth < minDepth) {
            return generateFunctionNode(currentDepth, minDepth, maxDepth);
        }

        if (Math.random() < 0.5) {
            Terminal[] hojas = {Terminal.AVANZA, Terminal.DERECHA, Terminal.IZQUIERDA};
            return new Node(hojas[Utils.random.nextInt(hojas.length)]);
        } else {
            return generateFunctionNode(currentDepth, minDepth, maxDepth);
        }
    }

    private Node generateFunctionNode(int currentDepth, int minDepth, int maxDepth) {
        int r = Utils.random.nextInt(3);
        switch (r) {
            case 0:
                return new Node(
                    Terminal.SICOMIDA,
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth)
                );
            case 1:
                return new Node(
                    Terminal.PROG1,
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth)
                );
            case 2:
                return new Node(
                    Terminal.PROG2,
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth)
                );
            default:
                throw new IllegalStateException("Unexpected value: " + r);
        }
    }

    @Override
    public InitializationType getType() {
        return InitializationType.COMPLETA;
    }
    
}
