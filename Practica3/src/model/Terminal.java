package model;

import utils.Utils;

public enum Terminal {

    AVANZA, DERECHA, IZQUIERDA, SICOMIDA, PROG1, PROG2;

    public static Direction direction;

    public boolean isTerminal() {
        return this == Terminal.AVANZA || this == Terminal.DERECHA || this == Terminal.IZQUIERDA;
    }

    public boolean isFunction() {
        return !isTerminal();
    }

    public static Terminal getRandomTerminal() {
        Terminal[] ts = {Terminal.AVANZA, Terminal.DERECHA, Terminal.IZQUIERDA};
        return ts[Utils.random.nextInt(ts.length)]; 
    }

    public static Terminal getRandomFunction() {
        Terminal[] ts = {Terminal.SICOMIDA, Terminal.PROG1, Terminal.PROG2};
        return ts[Utils.random.nextInt(ts.length)]; 
    }

}
