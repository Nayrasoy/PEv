package model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Terminal tipo;
    private Node a;
    private Node b;
    private Node c;

    public static List<Coords> food = new ArrayList<>(Hormiguero.getInstance().getFood());

    public Node(Terminal tipo) {
        this.tipo = tipo;
    }

    public Node(Terminal tipo, Node a, Node b) {
        this.tipo = tipo;
        this.a = a;
        this.b = b;
    }

    public Node(Terminal tipo, Node a, Node b, Node c) {
        this.tipo = tipo;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int execute(List<Coords> coords) {
        Coords coord = new Coords(coords.get(coords.size() - 1));
        switch (tipo) {
            case AVANZA:
                coord = Terminal.direction.move(coord);
                coords.add(coord);
                int indx = Node.food.indexOf(coord);
                if (indx != -1) {
                    Node.food.remove(indx);
                    return 1;
                }
                return 0;

            case DERECHA:
                Terminal.direction = Terminal.direction.turnRight();
                break;

            case IZQUIERDA:
                Terminal.direction = Terminal.direction.turnLeft();
                break;

            case SICOMIDA:
                if (Node.food.contains(coord)) {
                    return a.execute(coords);
                } else {
                    return b.execute(coords);
                }

            case PROG1:
                int food = a.execute(coords);
                return b.execute(coords) + food;

            case PROG2:
                int food2 = a.execute(coords);
                food2 += b.execute(coords);
                return c.execute(coords) + food2;
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
                return new Node(tipo);
            case SICOMIDA:
            case PROG1:
                return new Node(tipo, a.copy(), b.copy());
            case PROG2:
                return new Node(tipo, a.copy(), b.copy(), c.copy());
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

    public List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        collectNodes(this, nodes);
        return nodes;
    }

    private void collectNodes(Node node, List<Node> nodes) {
        nodes.add(node);
        for (Node child : node.getChildren()) {
            collectNodes(child, nodes);
        }
    }

    public boolean isTerminal() {
        return this.tipo == Terminal.AVANZA || this.tipo == Terminal.DERECHA || this.tipo == Terminal.IZQUIERDA;
    }

    public boolean isFunction() {
        return this.tipo == Terminal.PROG1 || this.tipo == Terminal.PROG2;
    }

    public List<Node> getTerminalNodes() {
        List<Node> nodes = new ArrayList<>();
        collectTerminalNodes(this, nodes);
        return nodes;
    }

    private void collectTerminalNodes(Node node, List<Node> nodes) {
        if (node.isTerminal()) {
            nodes.add(node);
        }
        for (Node child : node.getChildren()) {
            collectTerminalNodes(child, nodes);
        }
    }

    public List<Node> getFunctionNodes() {
        List<Node> nodes = new ArrayList<>();
        collectFunctionNodes(this, nodes);
        return nodes;
    }

    private void collectFunctionNodes(Node node, List<Node> nodes) {
        if (node.isFunction()) {
            nodes.add(node);
        }
        for (Node child : node.getChildren()) {
            collectFunctionNodes(child, nodes);
        }
    }

    public void permutationTerminal() {
        Node temp = this.a;
        this.a = this.b;
        this.b = temp;
    }

    public Node replaceSubtree(Node target, Node replacement) {
        if (this == target) return replacement.copy();

        switch (tipo) {
            case AVANZA:
            case DERECHA:
            case IZQUIERDA:
                return new Node(tipo);

            case SICOMIDA:
                return new Node(tipo,
                        a.replaceSubtree(target, replacement),
                        b.replaceSubtree(target, replacement));

            case PROG1:
                return new Node(tipo,
                        a.replaceSubtree(target, replacement),
                        b.replaceSubtree(target, replacement));

            case PROG2:
                return new Node(tipo,
                        a.replaceSubtree(target, replacement),
                        b.replaceSubtree(target, replacement),
                        c.replaceSubtree(target, replacement));

            default:
                throw new IllegalStateException("Tipo desconocido: " + tipo);
        }
    }
}
