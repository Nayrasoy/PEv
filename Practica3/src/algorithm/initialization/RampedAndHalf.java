package algorithm.initialization;

import model.Node;
import model.Terminal;
import utils.Utils;

public class RampedAndHalf extends InitializationMethod{

    @Override
    public Node initializate(int minDepth, int maxDepth) {
        int depth = minDepth + (int) (Utils.random.nextDouble(maxDepth - minDepth + 1));
        boolean useFull = Utils.random.nextDouble() < 0.5;

        if (useFull) {
            return generateFullTree(0, depth);
        } else {
            return generateGrowTree(0, minDepth, depth);
        }
    }

    private Node generateFullTree(int currentDepth, int maxDepth) {
        if (currentDepth == maxDepth) {
            return randomTerminalNode();
        } else {
            return randomFunctionNode(currentDepth, maxDepth, maxDepth); 
        }
    }

    private Node randomTerminalNode() {
        Terminal[] hojas = {Terminal.AVANZA, Terminal.DERECHA, Terminal.IZQUIERDA};
        return new Node(hojas[Utils.random.nextInt(hojas.length)]);
    }

    private Node generateGrowTree(int currentDepth, int minDepth, int maxDepth) {
        boolean isAtMaxDepth = currentDepth >= maxDepth;
        boolean mustBeFunction = currentDepth < minDepth;
    
        if (isAtMaxDepth) {
            return randomTerminalNode();
        }
    
        if (mustBeFunction) {
            return randomFunctionNode(currentDepth, minDepth, maxDepth);
        }
    
        if (Utils.random.nextDouble() < 0.5) {
            return randomTerminalNode();
        } else {
            return randomFunctionNode(currentDepth, minDepth, maxDepth);
        }
    }

    private Node randomFunctionNode(int currentDepth, int minDepth, int maxDepth) {
        int r = Utils.random.nextInt(3);
        switch (r) {
            case 0:
                return new Node(
                    Terminal.SICOMIDA,
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth)
                );
            case 1:
                return new Node(
                    Terminal.PROG1,
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth)
                );
            case 2:
                return new Node(
                    Terminal.PROG2,
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth)
                );
            default:
                throw new IllegalStateException("Unexpected function index: " + r);
        }
    }

    @Override
    public InitializationType getType() {
        return InitializationType.RAMPED_AND_HALF;
    }
    
}
