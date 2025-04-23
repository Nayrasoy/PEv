package model;

import java.util.*;

public class Node {
    public Terminal tipo;

    public Node(Terminal tipo) {
        this.tipo = tipo;
    }

    public boolean isTerminal() {
        return tipo == Terminal.AVANZA || tipo == Terminal.DERECHA || tipo == Terminal.IZQUIERDA;
    }

    @Override
    public String toString() {
        return tipo.name();
    }

    // Evaluador en postorden
    public static int execute(List<Node> nodes, List<Coords> coords, boolean getPath) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<List<Coords>> states = new ArrayDeque<>();
        Set<Coords> food = new HashSet<>(Hormiguero.getInstance().getFood());

        for (Node node : nodes) {
            switch (node.tipo) {
                case AVANZA: {
                    Coords coord = new Coords(coords.get(coords.size() - 1));
                    coord = Terminal.direction.move(coord);
                    if (getPath) {
                        coords.add(coord); 
                    }
                    else {
                        coords.set(0, coord);
                    }
                    if (food.remove(coord)) {
                        stack.push(1);
                    } else {
                        stack.push(0);
                    }
                    break;
                }
                case DERECHA:
                    Terminal.direction = Terminal.direction.turnRight();
                    stack.push(0);
                    break;

                case IZQUIERDA:
                    Terminal.direction = Terminal.direction.turnLeft();
                    stack.push(0);
                    break;

                case SICOMIDA: {
                    int falseBranch = stack.pop();
                    int trueBranch = stack.pop();

                    Coords coord = coords.get(coords.size() - 1);
                    if (food.contains(coord)) {
                        stack.push(trueBranch);
                    } else {
                        stack.push(falseBranch);
                    }
                    break;
                }
                case PROG1: {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case PROG2: {
                    int c = stack.pop();
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b + c);
                    break;
                }
            }
        }
        return stack.pop();
    }

    public static List<Node> copy(List<Node> nodes) {   
        List<Node> copy = new ArrayList<>();
        for (Node node : nodes) {
            copy.add(new Node(node.tipo));
        }
        return copy;
    }

}
