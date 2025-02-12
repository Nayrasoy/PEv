package algorithm.selection;

import java.util.List;

import model.Individuo;

public abstract class SelectionMethod {

    public abstract List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum);

    public abstract SelectionType getType();

    public abstract SelectionMethod create();
    
}
