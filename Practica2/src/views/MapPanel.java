package views;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import controller.Controller;
import model.Casa;
import model.Coords;

public class MapPanel extends JPanel {

    private static final int SIZE = 15;
    private static final int CELL_SIZE = 40;
    private Controller controller;
    private Casa casa;

    // Mapa con habitaciones (1-20), base (B) y obstáculos (■)
    private String[][] grid = new String[SIZE][SIZE];

    public MapPanel(Controller controller) {
        this.controller = controller;
        this.casa = Casa.getInstance();
        this.initMap();
    }

    private void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = " ";
            }
        }

        Map<String, Coords> rooms = this.casa.getRooms();
        for (String id : rooms.keySet()) {
            Coords coords = rooms.get(id);
            grid[coords.getX()][coords.getY()] = id;
        }

        // Base
        grid[7][7] = "B";

        // Obstáculos
        for (Coords coord : this.casa.getObstacles()) {
            grid[coord.getX()][coord.getY()] = "■";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Dibujar celda
                g.setColor(Color.WHITE);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

                // Dibujar contenido de la celda
                if (!grid[row][col].equals(" ")) {
                    Color color;
                    switch (grid[row][col]) {
                        case "*":
                            color = Color.BLUE;
                            break;
                        case "■":
                            color = Color.RED;
                            break;
                        default:
                            color = Color.BLACK;
                    }
                    g.setColor(color);
                    g.drawString(grid[row][col], x + 15, y + 25);
                }
            }
        }
    }

    // Método para actualizar el mapa con la ruta del robot
    public void setRuta(int[][] ruta) {
        for (int[] pos : ruta) {
            grid[pos[0]][pos[1]] = "*";
        }
        repaint();
    }
}
