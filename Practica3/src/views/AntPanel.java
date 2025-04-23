package views;

import javax.swing.*;

import config.Parameters;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Coords;
import model.Direction;
import model.Hormiguero;
import model.Individuo;
import model.Node;
import model.Terminal;
import controller.Controller;

public class AntPanel extends JPanel {

    private Controller controller;
    private Hormiguero hormiguero;
    private List<Coords> coords = null;

    public AntPanel(Controller controller) {
        this.controller = controller;
        this.hormiguero = Hormiguero.getInstance();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawFood(g);
        drawPath(g);
    }

    private void drawGrid(Graphics g) {
        int tileSize = getTileSize();
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= Parameters.GRID_WIDTH; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, Parameters.GRID_HEIGHT * tileSize);
        }
        for (int j = 0; j <= Parameters.GRID_HEIGHT; j++) {
            g.drawLine(0, j * tileSize, Parameters.GRID_WIDTH * tileSize, j * tileSize);
        }
    }

    private void drawFood(Graphics g) {
        Set<Coords> food = hormiguero.getFood();
        int tileSize = getTileSize();
        g.setColor(Color.BLUE);
        for (Coords c : food) {
            int x = c.getX() * tileSize;
            int y = c.getY() * tileSize;
            g.fillRect(x, y, tileSize, tileSize);
        }
    }

    private void drawPath(Graphics g) {
        if (this.coords != null) {
            int tileSize = getTileSize();
            g.setColor(Color.YELLOW);
            for (Coords c : this.coords) {
                int x = c.getX() * tileSize;
                int y = c.getY() * tileSize;
                g.fillRect(x, y, tileSize, tileSize);
            }
        }
    }

    private int getTileSize() {
        int width = getWidth();
        int height = getHeight();
        return Math.min(width / Parameters.GRID_WIDTH, height / Parameters.GRID_HEIGHT);
    }

    public void refreshAntMap(Individuo bestIndividual) {
        int food = 0;
        Node node = (Node) bestIndividual.getCromosomas().get(0);
        coords = new ArrayList<>();
        coords.add(new Coords(0, 0));
        Terminal.direction = Direction.RIGHT;
        for (int i = 0; i < Parameters.MAX_ANT_TIME; i++) {
            food += node.execute(coords);
            if (food == this.hormiguero.getFood().size()) {
                break;
            }
        }
        Node.food = new HashSet<>(Hormiguero.getInstance().getFood());
        this.repaint();
    }
}
