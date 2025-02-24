package algorithm.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Individuo;

public class Truncamiento extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum) {
        List<Individuo> newPoblation = new ArrayList<>();
        int selectionCont = (int) (poblation.size() * 0.5); // TODO: cambiar el 0.5
        
        // Ordenar el array
        Collections.sort(poblation, (a, b) -> Double.compare(b.getFitness(), a.getFitness()));

        // Seleccionamos los mejores individuos
        List<Individuo> selectedIndividuals = new ArrayList<>();
        for (int i = 0; i < selectionCont; i++) {
            selectedIndividuals.add(poblation.get(i));
        }
        // Rellenar con los mismo individuos
        int selectionNum = (int) (1 / 0.5); // TODO: cambiar el 0.5
        for (Individuo ind : selectedIndividuals) {
            for (int j = 0; j < selectionNum; j++) {
                newPoblation.add(ind);
            }
        }

        return newPoblation;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.TRUNCAMIENTO;
    }

    @Override
    public SelectionMethod create() {
        return new Truncamiento();
    }
    
}
