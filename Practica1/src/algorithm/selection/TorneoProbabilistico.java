package algorithm.selection;

import java.util.List;

import model.Individuo;

public class TorneoProbabilistico extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selection'");
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
