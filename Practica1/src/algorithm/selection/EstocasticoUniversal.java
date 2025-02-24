package algorithm.selection;

import java.util.List;

import model.Individuo;

public class EstocasticoUniversal extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selection'");
    }

    @Override
    public SelectionType getType() {
        return SelectionType.ESTOCASTICO_UNIVERSAL;
    }

    @Override
    public SelectionMethod create() {
        return new EstocasticoUniversal();
    }
    
}
