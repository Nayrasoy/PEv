package model;

import utils.Utils;

public enum Terminal {

    AVANZA, DERECHA, IZQUIERDA, SICOMIDA, PROG1, PROG2;

    public static Direction direction;

    public static Terminal getRandomTerminal() {
        Terminal[] ts = {Terminal.AVANZA, Terminal.DERECHA, Terminal.IZQUIERDA};
        return ts[Utils.random.nextInt(ts.length)]; 
    }

}
