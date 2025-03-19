package algorithm.cross;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithm.selection.SelectionMethod;
import algorithm.selection.SelectionType;
import exceptions.CrossException;
import factories.CrossMethodFactory;
import factories.SelectionMethodFactory;
import model.Individuo;
import utils.Utils;

public class CO extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> cromosomas2 = individuo2.getCromosomas();
        List<String> originalList = new ArrayList<>(cromosomas1);
        int rand = Utils.random.nextInt(cromosomas1.size());
        
        Collections.sort(originalList);
        encodeCromosome(originalList, cromosomas1);
        encodeCromosome(originalList, cromosomas2);

        CrossMethod monoPunto = new MonoPunto();
        newPoblation = monoPunto.cross(individuo1, individuo2);
        
        decodeCromosome(originalList, newPoblation.getFirst().getCromosomas());
        decodeCromosome(originalList, newPoblation.getLast().getCromosomas());

        return newPoblation;
    }

    private List<String> encodeCromosome(List<String> originaList, List<String> cromosome) {
        List<String> list = new ArrayList<>(originaList);
        
        for (int i = 0; i < cromosome.size(); i++) {
            int index = list.indexOf(cromosome.get(i));
            cromosome.set(i, String.valueOf(index));
            list.remove(index);
        }

        return cromosome;
    }

    private List<String> decodeCromosome(List<String> originaList, List<String> cromosome) {
        List<String> list = new ArrayList<>(originaList);
        
        for (int i = 0; i < cromosome.size(); i++) {
            int index = Integer.parseInt(cromosome.get(i));
            cromosome.set(i, list.get(index));
            list.remove(index);
        }

        return cromosome;
    }

    @Override
    public CrossType getType() {
        return CrossType.CO;
    }

    @Override
    public CrossMethod create() {
        return new CO();
    }
    
}
