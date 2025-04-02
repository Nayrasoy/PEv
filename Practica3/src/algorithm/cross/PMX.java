package algorithm.cross;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;
import utils.Utils;

public class PMX extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> cromosomas2 = individuo2.getCromosomas();
        List<String> cambiados1 = new ArrayList<>();
        List<String> cambiados2 = new ArrayList<>();
        int p1 = Utils.random.nextInt(cromosomas1.size());
        int p2 = Utils.random.nextInt(cromosomas1.size() - p1) + p1;
        int indx, indxAux;
        
        for (int i = p1; i <= p2; i++) {
            cambiados1.add(cromosomas1.get(i));
            cambiados2.add(cromosomas2.get(i));
            cromosomas1.set(i, cromosomas2.get(i));
            cromosomas2.set(i, cambiados1.get(cambiados1.size() - 1));
        }

        for (int i = 0; i < cromosomas1.size(); i++) {
            if (i < p1 || i > p2) {
                indx = cambiados2.indexOf(cromosomas1.get(i));
                while (indx != -1) {
                    indxAux = cambiados2.indexOf(cambiados1.get(indx));
                    if (indxAux == -1) {
                        cromosomas1.set(i, cambiados1.get(indx));
                    }
                    indx = indxAux;
                }
                
                indx = cambiados1.indexOf(cromosomas2.get(i));
                while (indx != -1) {
                    indxAux = cambiados1.indexOf(cambiados2.get(indx));
                    if (indxAux == -1) {
                        cromosomas2.set(i, cambiados2.get(indx));
                    }
                    indx = indxAux;
                }
            }
        }

        /*
         * Caso en el que hace falta el while:
         * 
         * 1 2 E D 3 4
         * 1 2 D 3 E 4
         * 
         * 1 2 D 3 _ 4
         * 1 2 E D _ 4
         * 
         * 1 2 D 3 E 4
         * 1 2 E D D 4
         */

        newPoblation.add(individuo1.copy());
        newPoblation.add(individuo2.copy());

        return newPoblation;
    }

    @Override
    public CrossType getType() {
        return CrossType.PMX;
    }

    @Override
    public CrossMethod create() {
        return new PMX();
    }
    
}
