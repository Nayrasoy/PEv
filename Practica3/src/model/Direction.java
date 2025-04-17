package model;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public void move(Coords coords) {
        switch (this) {
            case UP:
                coords = new Coords(coords.getX(), coords.getY() + 1);
            break;
            case DOWN:
                coords = new Coords(coords.getX(), coords.getY() - 1);
            break;
            case LEFT:
                coords = new Coords(coords.getX() - 1, coords.getY());
            break;
            case RIGHT:
                coords = new Coords(coords.getX() + 1, coords.getY());
            break;
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
        }
        return RIGHT;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
        }
        return RIGHT;
    }

}
