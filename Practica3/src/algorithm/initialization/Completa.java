package algorithm.initialization;

import model.Node;
import model.Terminal;
import utils.Utils;

public class Completa extends InitializationMethod {

    @Override
    public Node initializate(int startingDepth, int minDepth, int maxDepth) {
        return generateFullTree(startingDepth, minDepth, maxDepth);
    }

    private Node generateFullTree(int currentDepth, int minDepth, int maxDepth) {
        if (currentDepth >= maxDepth) {
            return new Node(Terminal.getRandomTerminal(), currentDepth);
        }
        else {
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
                    //currentDepth < minDepth ? generateGrowTree(currentDepth, minDepth, maxDepth) : new Node(Terminal.AVANZA, currentDepth + 1),
                    new Node(Terminal.AVANZA, currentDepth + 1),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    currentDepth
                );
                node.getA().setParent(node);
                node.getB().setParent(node);
                break;
            case 1:
                node = new Node(
                    Terminal.PROG1,
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    currentDepth
                );
                node.getA().setParent(node);
                node.getB().setParent(node);
                break;
            case 2:
                node = new Node(
                    Terminal.PROG2,
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
                    generateFullTree(currentDepth + 1, minDepth, maxDepth),
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
        return InitializationType.COMPLETA;
    }
    
}
