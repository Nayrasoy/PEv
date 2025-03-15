package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return this.calculatePath(actualCoords, destinyCoords).getDistance();
        }
    }

    private Path calculatePath(Coords actual, Coords destiny) {
        Path path = null;
    
        if (!this.caminimos.get(actual).containsKey(destiny)) {
            if (actual.equals(destiny)) {
                path = new Path(0, new Coords(actual));
            }
            else {
                for (DirectionType dt: DirectionType.values()) {
                    Coords c = new Coords(actual.getX() + dt.getDx(), actual.getY() + dt.getDy());
                    if (this.isInBounds(c) && !this.isObstacle(c)) {
                        Path p = this.calculatePath(c, destiny);
                        if (path == null || p.betterThan(path)) {
                            path = p;
                        }
                    }
                }
                path = new Path(path.getDistance() + 1, actual);
            }
            this.caminimos.get(actual).put(destiny, path);
        }
        else {
            path = this.caminimos.get(actual).get(destiny);
        }

        return path;
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
