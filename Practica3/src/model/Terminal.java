package model;

public enum Terminal {

    AVANZA, DERECHA, IZQUIERDA, SICOMIDA, PROG1, PROG2;

    private Terminal a;
    private Terminal b;
    private Terminal c;

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

    public void execute() {
        switch (this) {
            case AVANZA:

            break;
            case DERECHA:

            break;
            case IZQUIERDA:

            break;
            case SICOMIDA:
                
            break;
            case PROG1:
                this.a.execute();
                this.b.execute();
            break;
            case PROG2:
                this.a.execute();
                this.b.execute();
                this.c.execute();
        }
    }

}
