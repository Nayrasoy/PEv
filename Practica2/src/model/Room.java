package model;

public class Room {

    private Coords coords;
    private String id;

    public Room(Coords coords, String id) {
        this.coords = coords;
        this.id = id;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getId() {
        return id;
    }
    
}
