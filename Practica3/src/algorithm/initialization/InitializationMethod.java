package algorithm.initialization;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class InitializationMethod {

    public abstract DefaultMutableTreeNode initializate();

    public abstract InitializationType getType();
    
}
