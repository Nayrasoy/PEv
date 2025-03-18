package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class OX extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> originalCromosome1 = new ArrayList<>(cromosomas1);
        List<String> cromosomas2 = individuo2.getCromosomas();
        List<String> originalCromosome2 = new ArrayList<>(cromosomas2);
        List<String> cambiados1 = new ArrayList<>();
        List<String> cambiados2 = new ArrayList<>();
        int p1 = Utils.random.nextInt(cromosomas1.size() - 1);
        int p2 = Utils.random.nextInt(cromosomas1.size() - p1) + p1;
        int indx, indxAux;
        
        for (int i = p1; i <= p2; i++) {
            cambiados1.add(cromosomas1.get(i));
            cambiados2.add(cromosomas2.get(i));
            cromosomas1.set(i, cromosomas2.get(i));
            cromosomas2.set(i, cambiados1.get(cambiados1.size() - 1));
        }

        cromosomas1 = this.processCromosome(cromosomas1, originalCromosome1, p1, p2, cambiados2);
        cromosomas2 = this.processCromosome(cromosomas2, originalCromosome2, p1, p2, cambiados1);

        newPoblation.add(individuo1.copy());
        newPoblation.add(individuo2.copy());

        return newPoblation;
    }

    private List<String> processCromosome(List<String> cromosome, List<String> originalCromosome, int p1, int p2, List<String> cambiados) {
        int i = p2 + 1;
        int j = p2 + 1;

        while (i != p1) {
            if (i >= cromosome.size()) {
                i = 0;
            }
            else if (j >= cromosome.size()) {
                j = 0;
            }
            else {
                if (!cambiados.contains(originalCromosome.get(j))) {
                    cromosome.set(i, originalCromosome.get(j));
                    i++;
                }
                j++;
            }
        }

        return cromosome;
    }

    @Override
    public CrossType getType() {
        return CrossType.OX;
    }

    @Override
    public CrossMethod create() {
        return new OX();
    }
    
}
