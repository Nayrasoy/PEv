package model;

import java.lang.reflect.Parameter;

import config.Parameters;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Coords move(Coords coords) {
        switch (this) {
            case UP:
                if (coords.getY() == 0) {
                    return new Coords(coords.getX(), Parameters.GRID_HEIGHT - 1);
                }
                return new Coords(coords.getX(), coords.getY() - 1);
            case DOWN:
                if (coords.getY() == Parameters.GRID_HEIGHT - 1) {
                    return new Coords(coords.getX(), 0);
                }
                return new Coords(coords.getX(), coords.getY() + 1);
            case LEFT:
                if (coords.getX() == 0) {
                    return new Coords(Parameters.GRID_WIDTH - 1, coords.getY());
                }
                return new Coords(coords.getX() - 1, coords.getY());
            case RIGHT:
                if (coords.getX() == Parameters.GRID_WIDTH - 1) {
                    return new Coords(0, coords.getY());
                }
                return new Coords(coords.getX() + 1, coords.getY());
        }
        return null;
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
