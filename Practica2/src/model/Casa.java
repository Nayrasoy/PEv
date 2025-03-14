package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Casa {
    
    private Map<String, Coords> rooms;
    private List<Coords> obstacles;
    private Map<String, Map<String, List<Coords>>> caminimos;
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

    public List<Coords> getPath(String id1, String id2) {
        if (this.caminimos.get(id1).get(id2) != null) {
            return this.caminimos.get(id1).get(id2);
        }
        else {
            return null;
        }
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
        for (String id1 : this.rooms.keySet()) {
            this.caminimos.put(id1, new HashMap<>());
            for (String id2 : this.rooms.keySet()) {
                this.caminimos.get(id1).put(id2, null);
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
