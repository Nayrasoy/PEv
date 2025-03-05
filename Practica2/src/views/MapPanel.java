package views;

import javax.swing.*;
import java.awt.*;
import controller.Controller;

public class MapPanel extends JPanel {
    private static final int SIZE = 15;
    private static final int CELL_SIZE = 40;
    private Controller controller;

    // Mapa con habitaciones (1-20), base (B) y obstáculos (■)
    private String[][] grid = new String[SIZE][SIZE];

    public MapPanel(Controller controller) {
        this.controller = controller;
        this.initMap();
    }

    private void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = " ";
            }
        }

        // Base
        grid[7][7] = "B";

        // Habitaciones
        grid[2][2] = "1";
        grid[2][12] = "2";
        grid[12][2] = "3";
        grid[12][12] = "4";
        grid[2][7] = "5";
        grid[7][2] = "6";
        grid[7][12] = "7";
        grid[12][7] = "8";
        grid[0][7] = "9";
        grid[7][0] = "A";
        grid[14][7] = "B";
        grid[7][14] = "C";
        grid[0][0] = "D";
        grid[0][14] = "E";
        grid[14][0] = "F";
        grid[14][14] = "G";
        grid[4][4] = "H";
        grid[4][12] = "I";
        grid[10][4] = "J";
        grid[10][12] = "K";

        // Obstáculos
        int[][] obstaculos = {
            {5,5}, {5,6}, {5,7}, {5,8}, {5,9}, {8,10}, {9,10}, {10,10}, {11,10}, {12,10},
            {10,3}, {11,4}, {10,6}, {11,6}, {12,6}, {13,6}, {8,1}, {8,2}, {8,3}, {8,4},
            {0,13}, {1,13}, {3,8}, {3,9}, {3,10}, {3,11}
        };
        for (int[] obs : obstaculos) {
            grid[obs[0]][obs[1]] = "■";
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
