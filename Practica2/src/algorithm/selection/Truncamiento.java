package algorithm.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.Parameters;
import model.Individuo;

public class Truncamiento extends SelectionMethod {

    @Override
    public List<Individuo> selection(List<Individuo> poblation, List<Double> fitness, double fitnessSum, int n) {
        List<Individuo> newPoblation = new ArrayList<>();
        int selectionCont = (int) (n * Parameters.PROPORSION_TRUNCAMIENTO);
        double minValue = poblation.get(0).getMinValue();

        if (minValue < 0) {
            fitnessSum = 0;
            for (int i = 0; i < fitness.size(); i++) {
                fitness.set(i, -minValue - fitness.get(i));
                fitnessSum += fitness.get(i);
            }
        }
        
        // Ordenar el array
        Collections.sort(poblation, (a, b) -> poblation.get(0).betterThan(b.getFitness(), a.getFitness()));

        // Seleccionamos los mejores individuos
        List<Individuo> selectedIndividuals = new ArrayList<>();
        for (int i = 0; i < selectionCont; i++) {
            selectedIndividuals.add(poblation.get(i));
        }
        // Rellenar con los mismo individuos
        int selectionNum = (int) (1 / Parameters.PROPORSION_TRUNCAMIENTO);
        for (Individuo ind : selectedIndividuals) {
            for (int j = 0; j < selectionNum; j++) {
                newPoblation.add(ind.copy());
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
