package views;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Coords;
import model.Hormiguero;
import controller.Controller;

public class AntPanel extends JPanel {

    private Controller controller;
    private Hormiguero hormiguero;
    private static final int GRID_WIDTH = 32; // Número de columnas
    private static final int GRID_HEIGHT = 32; // Número de filas

    public AntPanel(Controller controller) {
        this.controller = controller;
        this.hormiguero = Hormiguero.getInstance();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawFood(g);
    }

    private void drawGrid(Graphics g) {
        int tileSize = getTileSize();
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= GRID_WIDTH; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, GRID_HEIGHT * tileSize);
        }
        for (int j = 0; j <= GRID_HEIGHT; j++) {
            g.drawLine(0, j * tileSize, GRID_WIDTH * tileSize, j * tileSize);
        }
    }

    private void drawFood(Graphics g) {
        List<Coords> food = hormiguero.getFood();
        int tileSize = getTileSize();
        g.setColor(Color.BLUE);
        for (Coords c : food) {
            int x = c.getX() * tileSize;
            int y = c.getY() * tileSize;
            g.fillRect(x, y, tileSize, tileSize);
        }
    }

    private int getTileSize() {
        int width = getWidth();
        int height = getHeight();
        return Math.min(width / GRID_WIDTH, height / GRID_HEIGHT);
    }
}
