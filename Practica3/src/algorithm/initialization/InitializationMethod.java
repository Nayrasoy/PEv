package algorithm.initialization;

import java.util.List;

import model.Node;

public abstract class InitializationMethod {

    public abstract List<Node> initializate(int minDepth, int maxDepth);

    public abstract InitializationType getType();
    
}
