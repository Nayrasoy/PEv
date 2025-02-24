package algorithm.selection;

import java.util.List;

import model.Individuo;

public class Restos extends SelectionMethod {

    // TODO: hay dos torneos??

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selection'");
    }

    @Override
    public SelectionType getType() {
        return SelectionType.RESTOS;
    }

    @Override
    public SelectionMethod create() {
        return new Restos();
    }
    
}
