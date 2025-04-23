package algorithm.initialization;

import model.Node;
import model.Terminal;
import utils.Utils;

public class Creciente extends InitializationMethod {

    @Override
    public Node initializate(int startingDepth, int minDepth, int maxDepth) {
        return generateGrowTree(startingDepth, minDepth, maxDepth);
    }
    
    private Node generateGrowTree(int currentDepth, int minDepth, int maxDepth) {
        boolean isAtMaxDepth = currentDepth >= maxDepth;
        boolean mustBeFunction = currentDepth < minDepth;
    
        if (isAtMaxDepth) {
            return new Node(Terminal.getRandomTerminal(), currentDepth);
        }
    
        if (mustBeFunction) {
            return randomFunctionNode(currentDepth, minDepth, maxDepth);
        }
    
        if (Utils.random.nextDouble() < 0.5) {
            return new Node(Terminal.getRandomTerminal(), currentDepth);
        } else {
            return randomFunctionNode(currentDepth, minDepth, maxDepth);
        }
    }

    private Node randomFunctionNode(int currentDepth, int minDepth, int maxDepth) {
        int r = Utils.random.nextInt(3);
        Node node = null;
        switch (r) {
            case 0:
                node = new Node(
                    Terminal.SICOMIDA,
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    currentDepth
                );
                node.getA().setParent(node);
                node.getB().setParent(node);
                break;
            case 1:
                node = new Node(
                    Terminal.PROG1,
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    currentDepth
                );
                node.getA().setParent(node);
                node.getB().setParent(node);
                break;
            case 2:
                node = new Node(
                    Terminal.PROG2,
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    generateGrowTree(currentDepth + 1, minDepth, maxDepth),
                    currentDepth
                );
                node.getA().setParent(node);
                node.getB().setParent(node);
                node.getC().setParent(node);
                break;
            default:
                throw new IllegalStateException("Unexpected function index: " + r);
        }
        return node;
    }

    @Override
    public InitializationType getType() {
        return InitializationType.CRECIENTE;
    }
    
}
