package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import model.Individuo;
import utils.Utils;

public class OXPP extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> originalCromosome1 = new ArrayList<>(cromosomas1);
        List<String> cromosomas2 = individuo2.getCromosomas();
        List<String> originalCromosome2 = new ArrayList<>(cromosomas2);
        List<String> cambiados1 = new ArrayList<>();
        List<String> cambiados2 = new ArrayList<>();
        List<Integer> randoms = new ArrayList<>();
        for (int i = 1; i <= Parameters.OXPP_RANDOM_POINTS; i++) {
            int random;
            if (randoms.isEmpty()) {
                random = Utils.random.nextInt(0, cromosomas1.size() - Parameters.OXPP_RANDOM_POINTS + i);
            }
            else {
                random = Utils.random.nextInt(randoms.getLast() + 1, cromosomas1.size() - Parameters.OXPP_RANDOM_POINTS + i);
            }
            randoms.add(random);
        }
        /* comentar cuando vaya bien */
        // Utils.searchForRepetitions(randoms);
        
        for (Integer p : randoms) {
            cambiados1.add(cromosomas1.get(p));
            cambiados2.add(cromosomas2.get(p));
            cromosomas1.set(p, cromosomas2.get(p));
            cromosomas2.set(p, cambiados1.get(cambiados1.size() - 1));
        }

        cromosomas1 = this.processCromosome(cromosomas1, originalCromosome1, randoms, cambiados2);
        cromosomas2 = this.processCromosome(cromosomas2, originalCromosome2, randoms, cambiados1);

        newPoblation.add(individuo1.copy());
        newPoblation.add(individuo2.copy());

        return newPoblation;
    }

    private List<String> processCromosome(List<String> cromosome, List<String> originalCromosome, List<Integer> randoms, List<String> cambiados) {
        int p = randoms.getLast();
        int i = p + 1;
        int j = p + 1;

        while (i != p) {
            if (i >= cromosome.size()) {
                i = 0;
            }
            else if (j >= cromosome.size()) {
                j = 0;
            }
            else {
                if (randoms.contains(i)) {
                    i++;
                }
                else {
                    if (!cambiados.contains(originalCromosome.get(j))) {
                        cromosome.set(i, originalCromosome.get(j));
                        i++;
                    }
                    j++;
                }
            }
        }

        /* Comentar cuando vaya bien */
        // Utils.searchForRepetitions(cromosome);

        return cromosome;
    }

    @Override
    public CrossType getType() {
        return CrossType.OXPP;
    }

    @Override
    public CrossMethod create() {
        return new OXPP();
    }
    
}
