package algorithm.initialization;

import model.Terminal;

public abstract class InitializationMethod {

    public abstract Terminal initializate();

    public abstract InitializationType getType();
    
}
