package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import config.Parameters;

public class Hormiguero {
    
    private List<Coords> antPath;
    private List<Coords> food;
    private static Hormiguero casa = null;

    private Hormiguero() {
        this.initFood();
    }

    public static Hormiguero getInstance() {
        if (casa == null) {
            casa = new Hormiguero();
        }
        return casa;
    }

    private void initFood() {
        this.food = new ArrayList<>();

        this.food.add(new Coords(0, 0));
    this.food.add(new Coords(0, 0));
    this.food.add(new Coords(1, 0));
    this.food.add(new Coords(2, 0));
    this.food.add(new Coords(3, 0));
    this.food.add(new Coords(3, 1));
    this.food.add(new Coords(3, 2));
    this.food.add(new Coords(3, 3));
    this.food.add(new Coords(3, 4));
    this.food.add(new Coords(3, 5));
    this.food.add(new Coords(4, 5));
    this.food.add(new Coords(4, 6));
    this.food.add(new Coords(4, 7));
    this.food.add(new Coords(4, 9));
    this.food.add(new Coords(4, 10));
    this.food.add(new Coords(4, 11));
    this.food.add(new Coords(4, 12));
    this.food.add(new Coords(4, 13));
    this.food.add(new Coords(5, 13));
    this.food.add(new Coords(6, 13));
    this.food.add(new Coords(7, 13));
    this.food.add(new Coords(8, 13));
    this.food.add(new Coords(9, 13));
    this.food.add(new Coords(11, 13));
    this.food.add(new Coords(12, 13));
    this.food.add(new Coords(13, 13));
    this.food.add(new Coords(14, 13));
    this.food.add(new Coords(16, 13));
    this.food.add(new Coords(17, 13));
    this.food.add(new Coords(18, 13));
    this.food.add(new Coords(19, 13));
    this.food.add(new Coords(20, 13));
    this.food.add(new Coords(21, 13));
    this.food.add(new Coords(22, 12));
    this.food.add(new Coords(22, 11));
    this.food.add(new Coords(22, 10));
    this.food.add(new Coords(22, 9));
    this.food.add(new Coords(22, 8));
    this.food.add(new Coords(22, 5));
    this.food.add(new Coords(22, 4));
    this.food.add(new Coords(23, 12));
    }

}
