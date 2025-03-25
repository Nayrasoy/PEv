package algorithm.mutation;

import java.util.*;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class MutacionHeuristica extends MutationMethod {

    @Override
    public <T> Individuo mutate(Individuo individual, double mutationProbability) {
        Individuo indv = individual.copy();

        if (Utils.random.nextDouble() < mutationProbability) {
            List<String> cromosomas = indv.getCromosomas();
            List<Integer> index = new ArrayList();
            List<String> selection = new ArrayList();
            List<List<String>> permutaciones = new ArrayList();

            // Buscar n numeros distintos
            while (index.size() < Parameters.N_HEURISTICA) {
                int rand = Utils.random.nextInt(cromosomas.size());
                if (!index.contains(rand)) {
                    index.add(rand);
                }
            }

            for (Integer ind: index) {
                selection.add(cromosomas.get(ind));
            }

            // Hacer las permutaciones
            generarPermutaciones(selection, 0, permutaciones);

            // Quedarnos con el que mejor fitnes tenga
            Individuo mejorIndividuo = null;
            double bestFitness = -1, actualFitness;
            for (List<String> permutacion: permutaciones) {
                for (int i = 0; i < permutacion.size(); i++) {
                    cromosomas.set(index.get(i), permutacion.get(i));
                }
                actualFitness = indv.getFitness();
                if (mejorIndividuo == null || indv.betterThan(actualFitness, bestFitness) == 1) {
                    bestFitness = actualFitness;
                    mejorIndividuo = indv.copy();
                }
            }
            indv = mejorIndividuo;
            // Utils.searchForRepetitions(mejorIndividuo.getCromosomas());
        }

        return indv;
    }

    private void generarPermutaciones(List<String> lista, int index, List<List<String>> resultados) {
        if (index == lista.size() - 1) {
            resultados.add(new ArrayList<>(lista));
            return;
        }
        for (int i = index; i < lista.size(); i++) {
            Collections.swap(lista, i, index);
            generarPermutaciones(lista, index + 1, resultados);
            Collections.swap(lista, i, index); // Restaurar la lista
        }
    }

    @Override
    public MutationType getType() {
        return MutationType.HEURISTICA;
    }

    @Override
    public MutationMethod create() {
        return new MutacionHeuristica();
    }
    
}