package model;

public enum Terminal {

    AVANZA, DERECHA, IZQUIERDA, SICOMIDA, PROG1, PROG2;

    private Terminal a;
    private Terminal b;
    private Terminal c;

    public static Direction direction;

    Terminal() {}

    Terminal(Terminal a, Terminal b) {
        this.a = a;
        this.b = b;
    }

    Terminal(Terminal a, Terminal b, Terminal c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int execute(Coords coords) {
        switch (this) {
            case AVANZA:
                direction.move(coords);
                if (Hormiguero.getInstance().getFood().contains(coords)) {
                    return 1;
                }
            break;
            case DERECHA:
                direction = direction.turnRight();
            break;
            case IZQUIERDA:
                direction = direction.turnLeft();
            break;
            case SICOMIDA:
                if (Hormiguero.getInstance().getFood().contains(coords)) {
                    this.a.execute(coords);
                }
                else {
                    this.b.execute(coords);
                }
            break;
            case PROG1:
                this.a.execute(coords);
                this.b.execute(coords);
            break;
            case PROG2:
                this.a.execute(coords);
                this.b.execute(coords);
                this.c.execute(coords);
        }
        return 0;
    }

}
