package algorithm.initialization;

import model.Node;

public abstract class InitializationMethod {

    public abstract Node initializate(int startingDepth, int minDepth, int maxDepth);

    public abstract InitializationType getType();
    
}
