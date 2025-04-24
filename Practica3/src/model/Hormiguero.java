package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hormiguero {
    
    private Set<Coords> food = null;
    private static Hormiguero hormiguero = null;

    private Hormiguero() {
        this.initFood();
    }

    public static Hormiguero getInstance() {
        if (hormiguero == null) {
            hormiguero = new Hormiguero();
        }
        return hormiguero;
    }

    private void initFood() {
        this.food = new HashSet<>();

        //this.food.add(new Coords(0, 0));
        this.food.add(new Coords(1, 0));
        this.food.add(new Coords(2, 0));
        this.food.add(new Coords(3, 0));
        this.food.add(new Coords(3, 1));
        this.food.add(new Coords(3, 2));
        this.food.add(new Coords(3, 3));
        this.food.add(new Coords(24, 3));
        this.food.add(new Coords(25, 2));
        this.food.add(new Coords(26, 2));
        this.food.add(new Coords(27, 2));
        this.food.add(new Coords(29, 3));
        this.food.add(new Coords(3, 4));
        this.food.add(new Coords(24, 4));
        this.food.add(new Coords(29, 4));
        this.food.add(new Coords(3, 5));
        this.food.add(new Coords(4, 5));
        this.food.add(new Coords(5, 5));
        this.food.add(new Coords(6, 5));
        this.food.add(new Coords(8, 5));
        this.food.add(new Coords(9, 5));
        this.food.add(new Coords(10, 5));
        this.food.add(new Coords(11, 5));
        this.food.add(new Coords(12, 5));
        this.food.add(new Coords(21, 5));
        this.food.add(new Coords(22, 5));
        this.food.add(new Coords(12, 6));
        this.food.add(new Coords(12, 15));
        this.food.add(new Coords(11, 24));
        this.food.add(new Coords(1, 28));
        this.food.add(new Coords(7, 29));
        this.food.add(new Coords(13, 27));
        this.food.add(new Coords(14, 27));
        this.food.add(new Coords(16, 26));
        this.food.add(new Coords(16, 25));
        this.food.add(new Coords(16, 24));
        this.food.add(new Coords(29, 6));
        this.food.add(new Coords(12, 7));
        this.food.add(new Coords(12, 8));
        this.food.add(new Coords(20, 8));
        this.food.add(new Coords(12, 9));
        this.food.add(new Coords(20, 9));
        this.food.add(new Coords(12, 10));
        this.food.add(new Coords(20, 10));
        this.food.add(new Coords(29, 9));
        this.food.add(new Coords(29, 12));
        this.food.add(new Coords(12, 12));
        this.food.add(new Coords(12, 13));
        this.food.add(new Coords(20, 15));
        this.food.add(new Coords(20, 11));
        this.food.add(new Coords(12, 14));
        this.food.add(new Coords(20, 14));
        this.food.add(new Coords(26, 14));
        this.food.add(new Coords(27, 14));
        this.food.add(new Coords(28, 14));
        this.food.add(new Coords(17, 16));
        this.food.add(new Coords(23, 15));
        this.food.add(new Coords(12, 18));
        this.food.add(new Coords(16, 18));
        this.food.add(new Coords(24, 18));
        this.food.add(new Coords(12, 19));
        this.food.add(new Coords(16, 19));
        this.food.add(new Coords(27, 19));
        this.food.add(new Coords(12, 20));
        this.food.add(new Coords(16, 17));
        this.food.add(new Coords(12, 21));
        this.food.add(new Coords(16, 21));
        this.food.add(new Coords(12, 22));
        this.food.add(new Coords(26, 22));
        this.food.add(new Coords(12, 23));
        this.food.add(new Coords(23, 23));
        this.food.add(new Coords(3, 24));
        this.food.add(new Coords(4, 24));
        this.food.add(new Coords(7, 24));
        this.food.add(new Coords(8, 24));
        this.food.add(new Coords(9, 24));
        this.food.add(new Coords(10, 24));
        this.food.add(new Coords(1, 25));
        this.food.add(new Coords(1, 26));
        this.food.add(new Coords(1, 27));
        this.food.add(new Coords(8, 27));
        this.food.add(new Coords(9, 27));
        this.food.add(new Coords(10, 27));
        this.food.add(new Coords(11, 27));
        this.food.add(new Coords(12, 27));
        this.food.add(new Coords(7, 28));
        this.food.add(new Coords(2, 30));
        this.food.add(new Coords(3, 30));
        this.food.add(new Coords(4, 30));
        this.food.add(new Coords(5, 30));
    }

    public Set<Coords> getFood() {
        return this.food;
    }

}
