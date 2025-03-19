package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import config.Parameters;

public class Casa {

    private class Path {

        private int distance;
        private Coords from;

        public Path(int distance, Coords from) {
            this.distance = distance;
            this.from =  from;
        }

        public int getDistance() {
            return this.distance;
        }

        public Coords getFrom() {
            return this.from;
        }

        public boolean betterThan(Path path) {
            return this.distance < path.getDistance();
        }

    }

    private enum DirectionType {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);
    
        private final int dx;
        private final int dy;
    
        DirectionType(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    
        public int getDx() {
            return this.dx;
        }
    
        public int getDy() {
            return this.dy;
        }
    }
    
    private Map<String, Coords> rooms;
    private List<Coords> obstacles;
    private Map<Coords, Map<Coords, Path>> caminimos;
    private static Casa casa = null;
    private List<List<Boolean>> visitado;

    private Casa() {
        this.initRooms();
        this.initObstacles();
        this.initCaminimos();
    }

    public static Casa getInstance() {
        if (casa == null) {
            casa = new Casa();
        }
        return casa;
    }

    public int getDistance(String id1, String id2) {
        Coords actualCoords = this.rooms.get(id1);
        Coords destinyCoords = this.rooms.get(id2);
        if (this.caminimos.get(actualCoords).containsKey(destinyCoords)) {
            return this.caminimos.get(actualCoords).get(destinyCoords).getDistance();
        }
        else {
            List<Boolean> booleanList = new ArrayList<>();
            for (int i = 0; i < Parameters.SIZE; i++) {
                booleanList.add(false);
            }
            this.visitado = new ArrayList<>();
            for (int i = 0; i < Parameters.SIZE; i++) {
                this.visitado.add(new ArrayList<>(booleanList));
            }
            return this.calculatePath(actualCoords, destinyCoords).getDistance();
        }
    }

    public List<Coords> getPath(Coords coord1, Coords coord2) {
        List<Coords> lista = new ArrayList<>();

        Path p = this.caminimos.get(coord1).get(coord2);
        while (!p.getFrom().equals(coord2)) {
            lista.add(p.getFrom());
            p = this.caminimos.get(p.getFrom()).get(coord2);
        }

        return lista;
    }

    private Path calculatePath(Coords start, Coords end) {
        // Si ya calculamos este camino, lo devolvemos directamente
        if (caminimos.get(start).containsKey(end)) {
            return caminimos.get(start).get(end);
        }
    
        if (start.equals(end)) {
            Path directPath = new Path(0, start);
            caminimos.get(start).put(end, directPath);
            return directPath;
        }
    
        Queue<Coords> queue = new LinkedList<>();
        Map<Coords, Path> paths = new HashMap<>();
        queue.add(start);
        paths.put(start, new Path(0, null));
    
        while (!queue.isEmpty()) {
            Coords current = queue.poll();
            int currentDistance = paths.get(current).getDistance();
    
            for (DirectionType dt : DirectionType.values()) {
                Coords neighbor = new Coords(current.getX() + dt.getDx(), current.getY() + dt.getDy());
    
                if (isInBounds(neighbor) && !isObstacle(neighbor) && !paths.containsKey(neighbor)) {
                    paths.put(neighbor, new Path(currentDistance + 1, current));
                    queue.add(neighbor);
    
                    if (neighbor.equals(end)) { // Si encontramos el destino, terminamos
                        Path finalPath = reconstructPath(paths, start, end);
                        caminimos.get(start).put(end, finalPath); // Guardamos en cache
                        return finalPath;
                    }
                }
            }
        }
    
        return null; // No hay camino disponible
    }
    
    // Método para reconstruir el camino desde el mapa de caminos
    private Path reconstructPath(Map<Coords, Path> paths, Coords start, Coords end) {
        Coords current = end;
        Coords previous = null;
        int distance = 0;
    
        while (current != null && !current.equals(start)) {
            this.caminimos.get(current).put(end, new Path(distance, previous));
            previous = current;
            current = paths.get(current).getFrom();
            distance++;
        }
    
        Path bestPath = new Path(distance, previous);
        
        // Guardar el camino en cache para cada nodo intermedio
        /*current = end;
        while (!current.equals(start)) {
            Coords from = paths.get(current).getFrom();
            caminimos.get(from).put(current, new Path(paths.get(current).getDistance(), from));
            current = from;
        }*/
    
        return bestPath;
    }
    

    private boolean isInBounds(Coords c) {
        return c.getX() < Parameters.SIZE && c.getX() >= 0 &&
            c.getY() < Parameters.SIZE && c.getY() >= 0;
    }

    private boolean isObstacle(Coords c) {
        for (Coords obs : this.obstacles) {
            if (obs.equals(c)) {
                return true;
            }
        }
        return false;
    }

    private void initRooms() {
        this.rooms = new HashMap<>();
        
        this.rooms.put("1", new Coords(2, 2));
        this.rooms.put("2", new Coords(2, 12));
        this.rooms.put("3", new Coords(12, 2));
        this.rooms.put("4", new Coords(12, 12));
        this.rooms.put("5", new Coords(2, 7));
        this.rooms.put("6", new Coords(7, 2));
        this.rooms.put("7", new Coords(7, 12));
        this.rooms.put("8", new Coords(12, 7));
        this.rooms.put("9", new Coords(0, 7));
        this.rooms.put("A", new Coords(7, 0));
        this.rooms.put("B", new Coords(14, 7));
        this.rooms.put("C", new Coords(7, 14));
        this.rooms.put("D", new Coords(0, 0));
        this.rooms.put("E", new Coords(0, 14));
        this.rooms.put("F", new Coords(14, 0));
        this.rooms.put("G", new Coords(14, 14));
        this.rooms.put("H", new Coords(4, 4));
        this.rooms.put("I", new Coords(4, 12));
        this.rooms.put("J", new Coords(10, 4));
        this.rooms.put("K", new Coords(10, 12));
        this.rooms.put("Base", new Coords(7, 7));
    }

    private void initObstacles() {
        this.obstacles = new ArrayList<>();

        this.obstacles.add(new Coords(5, 5));
        this.obstacles.add(new Coords(5, 6));
        this.obstacles.add(new Coords(5, 7));
        this.obstacles.add(new Coords(5, 8));
        this.obstacles.add(new Coords(5, 9));
        this.obstacles.add(new Coords(8, 10));
        this.obstacles.add(new Coords(9, 10));
        this.obstacles.add(new Coords(10, 10));
        this.obstacles.add(new Coords(11, 10));
        this.obstacles.add(new Coords(12, 10));
        this.obstacles.add(new Coords(10, 3));
        this.obstacles.add(new Coords(11, 4));
        this.obstacles.add(new Coords(10, 6));
        this.obstacles.add(new Coords(11, 6));
        this.obstacles.add(new Coords(12, 6));
        this.obstacles.add(new Coords(13, 6));
        this.obstacles.add(new Coords(8, 1));
        this.obstacles.add(new Coords(8, 2));
        this.obstacles.add(new Coords(8, 3));
        this.obstacles.add(new Coords(8, 4));
        this.obstacles.add(new Coords(0, 13));
        this.obstacles.add(new Coords(1, 13));
        this.obstacles.add(new Coords(3, 8));
        this.obstacles.add(new Coords(3, 9));
        this.obstacles.add(new Coords(3, 10));
        this.obstacles.add(new Coords(3, 11));
    }

    private void initCaminimos() {
        this.caminimos = new HashMap<>();
        for (int x = 0; x < Parameters.SIZE; x++) {
            for (int y = 0; y < Parameters.SIZE; y++) {
                this.caminimos.put(new Coords(x, y), new HashMap<>());
            }   
        }
    }

    public Map<String, Coords> getRooms() {
        return this.rooms;
    }

    public List<Coords> getObstacles() {
        return this.obstacles;
    }
    
}
