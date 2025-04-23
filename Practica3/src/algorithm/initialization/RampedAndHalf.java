package algorithm.initialization;

import java.util.List;

import model.Node;
import model.Terminal;
import utils.Utils;

public class RampedAndHalf extends InitializationMethod{

    @Override
    public List<Node> initializate(int minDepth, int maxDepth) {
        /*int depth = minDepth + (int) (Math.random() * (maxDepth - minDepth + 1));
        boolean useFull = Math.random() < 0.5;

        if (useFull) {
            return generateFullTree(0, depth);
        } else {
            return generateGrowTree(0, minDepth, depth);
        }*/
        return null;
    }

    @Override
    public InitializationType getType() {
        return InitializationType.RAMPED_AND_HALF;
    }
    
}
