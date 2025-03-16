package views;

import javax.swing.*;

import config.Parameters;

import java.awt.*;
import java.util.Map;
import java.util.List;

import controller.Controller;
import model.Casa;
import model.Coords;
import model.Individuo;

public class MapPanel extends JPanel {

    private Controller controller;
    private Casa casa;

    // Mapa con habitaciones (1-20), base (B) y obstáculos (■)
    private String[][] grid = new String[Parameters.SIZE][Parameters.SIZE];

    public MapPanel(Controller controller) {
        this.controller = controller;
        this.casa = Casa.getInstance();
        this.initMap();
    }

    private void initMap() {
        for (int i = 0; i < Parameters.SIZE; i++) {
            for (int j = 0; j < Parameters.SIZE; j++) {
                grid[i][j] = " ";
            }
        }

        Map<String, Coords> rooms = this.casa.getRooms();
        for (String id : rooms.keySet()) {
            Coords coords = rooms.get(id);
            grid[coords.getX()][coords.getY()] = id;
        }

        // Base
        grid[7][7] = "Base";

        // Obstáculos
        for (Coords coord : this.casa.getObstacles()) {
            grid[coord.getX()][coord.getY()] = "■";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Obtener dimensiones del panel
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Determinar el tamaño de las celdas dinámicamente
        int cellWidth = panelWidth / Parameters.SIZE;
        int cellHeight = panelHeight / Parameters.SIZE;

        for (int row = 0; row < Parameters.SIZE; row++) {
            for (int col = 0; col < Parameters.SIZE; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;

                // Dibujar celda
                g.setColor(Color.WHITE);
                g.fillRect(x, y, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellWidth, cellHeight);

                // Dibujar contenido de la celda
                if (!grid[row][col].equals(" ")) {
                    Color color = null;
                    boolean bold = false;

                    switch (grid[row][col]) {
                        case "*":
                            color = Color.BLUE;
                            break;
                        case "■":
                            color = Color.RED;
                            break;
                        case "Base":
                            color = Color.BLACK;
                            bold = true;
                            break;
                        default:
                            color = Color.BLACK;
                            bold = true;
                    }

                    g.setColor(color);

                    // Si es el caso default, usar negrita
                    Font originalFont = g.getFont();
                    if (bold) {
                        g.setFont(originalFont.deriveFont(Font.BOLD));
                    }

                    // Centrar el texto dentro de la celda
                    FontMetrics fm = g.getFontMetrics();
                    int textX = x + (cellWidth - fm.stringWidth(grid[row][col])) / 2;
                    int textY = y + (cellHeight + fm.getAscent()) / 2 - 2;

                    g.drawString(grid[row][col], textX, textY);

                    // Restaurar fuente original
                    if (bold) {
                        g.setFont(originalFont);
                    }
                }
            }
        }
    }

    public void refreshHouse(Individuo bestIndividual) {
        List<String> cromosoma = bestIndividual.getCromosomas();

        this.printPath(this.casa.getPath(new Coords(7, 7), this.casa.getRooms().get(cromosoma.get(0))));
        for (int i = 0; i < cromosoma.size() - 1; i++) {
            List<Coords> path = this.casa.getPath(this.casa.getRooms().get(cromosoma.get(i)), this.casa.getRooms().get(cromosoma.get(i + 1)));
            this.printPath(path);
        }
        this.printPath(this.casa.getPath(this.casa.getRooms().get(cromosoma.get(0)), new Coords(7, 7)));

        this.repaint();
    }

    private void printPath(List<Coords> path) {
        for (Coords c : path) {
            if (grid[c.getX()][c.getY()].equals(" ")) {
                grid[c.getX()][c.getY()] = "*";
            }
        }
    }

}
