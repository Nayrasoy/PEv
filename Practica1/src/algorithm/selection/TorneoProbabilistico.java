package algorithm.selection;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;

public class TorneoProbabilistico extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        List<Individuo> newPoblation = new ArrayList<>();
        
        

        return newPoblation;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.TORNEO_PROBABILISTICO;
    }

    @Override
    public SelectionMethod create() {
        return new TorneoProbabilistico();
    }
    
}
