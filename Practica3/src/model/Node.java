package model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Terminal tipo;
    private Node a;
    private Node b;
    private Node c;

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

    public int execute(Coords coords) {
        switch (tipo) {
            case AVANZA:
                Terminal.direction.move(coords);
                return Hormiguero.getInstance().getFood().contains(coords) ? 1 : 0;

            case DERECHA:
                Terminal.direction = Terminal.direction.turnRight();
                break;

            case IZQUIERDA:
                Terminal.direction = Terminal.direction.turnLeft();
                break;

            case SICOMIDA:
                if (Hormiguero.getInstance().getFood().contains(coords)) {
                    return a.execute(coords);
                } else {
                    return b.execute(coords);
                }

            case PROG1:
                a.execute(coords);
                return b.execute(coords);

            case PROG2:
                a.execute(coords);
                b.execute(coords);
                return c.execute(coords);
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
