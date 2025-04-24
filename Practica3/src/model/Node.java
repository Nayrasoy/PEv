package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private Terminal tipo;
    private int depth;
    private Node parent = null;
    private Node a;
    private Node b;
    private Node c;

    public static Set<Coords> food = new HashSet<>(Hormiguero.getInstance().getFood());

    public Node(Terminal tipo, int depth) {
        this.depth = depth;
        this.tipo = tipo;
    }

    public Node(Terminal tipo, Node a, Node b, int depth) {
        this.depth = depth;
        this.tipo = tipo;
        this.a = a;
        this.b = b;
    }

    public Node(Terminal tipo, Node a, Node b, Node c, int depth) {
        this.depth = depth;
        this.tipo = tipo;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int execute(List<Coords> coords) {
        Coords coord = coords.get(coords.size() - 1);
        switch (tipo) {
            case AVANZA:
                coord = Terminal.direction.move(coord);
                Node.food.remove(coord);
                if (coords.contains(coord)) {
                    coords.add(coord);
                    return 50;
                }
                coords.add(coord);
                return 1;

            case DERECHA:
                Terminal.direction = Terminal.direction.turnRight();
                return 1;

            case IZQUIERDA:
                Terminal.direction = Terminal.direction.turnLeft();
                return 1;

            case SICOMIDA:
                Coords sigCoord = Terminal.direction.move(coord);
                if (Node.food.contains(sigCoord)) {
                    return a.execute(coords);
                } 
                else {
                    return b.execute(coords);
                }

            case PROG1:
                int movimientos = a.execute(coords);
                return b.execute(coords) + movimientos;

            case PROG2:
                int movimientos2 = a.execute(coords);
                movimientos2 += b.execute(coords);
                return c.execute(coords) + movimientos2;
        }
        return 0;
    }

    public Terminal getTipo() {
        return tipo;
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }

    public Node getC() {
        return c;
    }

    public void setTerminal(Terminal t) {
        this.tipo = t;
        this.a = null;
        this.b = null;
        this.c  = null;
    }

    public void deleteNode() {
        this.tipo = null;
        this.a = null;
        this.b = null;
        this.c  = null;
    }

    public Node copy() {
        switch (tipo) {
            case AVANZA:
            case DERECHA:
            case IZQUIERDA:
                return new Node(tipo, depth);
            case SICOMIDA:
            case PROG1:
                Node node = new Node(tipo, a.copy(), b.copy(), depth);
                node.a.setParent(node);
                node.b.setParent(node);
                return node;
            case PROG2:
                Node node2 = new Node(tipo, a.copy(), b.copy(), c.copy(), depth);
                node2.a.setParent(node2);
                node2.b.setParent(node2);
                node2.c.setParent(node2);
                return node2;
            default:
                throw new IllegalStateException("Tipo desconocido: " + tipo);
        }
    }

    @Override
    public String toString() {
        return buildString(this);
    }

    private String buildString(Node node) {
        if (node.getChildren().isEmpty()) {
            return node.getTipo().name();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("(").append(node.getTipo());
        for (Node child : node.getChildren()) {
            sb.append(" ").append(buildString(child));
        }
        sb.append(")");
        return sb.toString();
    }

    public List<Node> getChildren() {
        List<Node> list = new ArrayList<>();
        switch (tipo) {
            case SICOMIDA:
            case PROG1:
                list.add(a);
                list.add(b);
                break;
            case PROG2:
                list.add(a);
                list.add(b);
                list.add(c);
                break;
            default:
                // AVANZA, DERECHA, IZQUIERDA → no tienen hijos
                break;
        }
        return list;
    }

    public List<Node> getAllNodes(int minDepth, int maxDepth) {
        List<Node> nodes = new ArrayList<>();
        collectNodes(this, nodes, minDepth, maxDepth);
        return nodes;
    }

    private void collectNodes(Node node, List<Node> nodes, int minDepth, int maxDepth) {
        if (node.getDepth() >= minDepth && node.getDepth() <= maxDepth) nodes.add(node);
        if (node.a != null) collectNodes(node.a, nodes, minDepth, maxDepth);
        if (node.b != null) collectNodes(node.b, nodes, minDepth, maxDepth);
        if (node.c != null) collectNodes(node.c, nodes, minDepth, maxDepth);
    }

    public List<Node> getTerminalNodes() {
        List<Node> nodes = new ArrayList<>();
        collectTerminalNodes(this, nodes);
        return nodes;
    }

    private void collectTerminalNodes(Node node, List<Node> nodes) {
        if (node.getTipo().isTerminal()) {
            nodes.add(node);
        }
        for (Node child : node.getChildren()) {
            collectTerminalNodes(child, nodes);
        }
    }

    public List<Node> getFunctionNodes(int minDepth, int maxDepth) {
        List<Node> nodes = new ArrayList<>();
        collectFunctionNodes(this, nodes, minDepth, maxDepth);
        return nodes;
    }

    private void collectFunctionNodes(Node node, List<Node> nodes, int minDepth, int maxDepth) {
        if (node.getTipo().isFunction() && node.getDepth() >= minDepth && node.getDepth() <= maxDepth) {
            nodes.add(node);
        }
        for (Node child : node.getChildren()) {
            collectFunctionNodes(child, nodes, maxDepth, maxDepth);
        }
    }

    public void permutationTerminal() {
        Node temp = this.a;
        this.a = this.b;
        this.b = temp;
    }

    public void replaceSubtree(Node replacement) {
        this.a = replacement.a;
        this.b = replacement.b;
        this.c = replacement.c;
        this.tipo = replacement.tipo;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

}
