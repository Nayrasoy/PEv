package algorithm.cross;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import model.Individuo;
import utils.Utils;

public class ERX extends CrossMethod {

    @Override
    public <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2) {
        List<Individuo> newPoblation = new ArrayList<>();
        List<String> cromosomas1 = individuo1.getCromosomas();
        List<String> cromosomas2 = individuo2.getCromosomas();
        Map<String, Set<String>> adjacencyMap = new HashMap<>();
        
        for (int i = 0; i < cromosomas1.size(); i++) {
            String gen1 = cromosomas1.get(i);
            String gen2 = cromosomas2.get(i);

            adjacencyMap.putIfAbsent(gen1, new HashSet<>());
            adjacencyMap.putIfAbsent(gen2, new HashSet<>());

            if (i > 0) {
                adjacencyMap.get(gen1).add(cromosomas1.get(i - 1));
                adjacencyMap.get(gen2).add(cromosomas2.get(i - 1));
            }
            if (i < cromosomas1.size() - 1) {
                adjacencyMap.get(gen1).add(cromosomas1.get(i + 1));
                adjacencyMap.get(gen2).add(cromosomas2.get(i + 1));
            }
        }

        cromosomas1 = generateSon(cromosomas1, new HashMap<String, Set<String>>(adjacencyMap));
        cromosomas2 = generateSon(cromosomas2, adjacencyMap);

        newPoblation.add(individuo1.copy());
        newPoblation.add(individuo2.copy());

        return newPoblation;
    }

    private List<String> generateSon(List<String> cromosome, Map<String, Set<String>> adjacencyMap) {
        List<String> son = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        String current = cromosome.get(0); 
        son.add(current);
        visited.add(current);

        while (son.size() < cromosome.size()) {
            for (Set<String> neighbors : adjacencyMap.values()) {
                neighbors.remove(current);
            }

            Set<String> neighbors = adjacencyMap.get(current);
            adjacencyMap.remove(current);

            String next;
            if (neighbors == null || neighbors.isEmpty()) {
                List<String> available = new ArrayList<>(adjacencyMap.keySet());
                next = available.get(Utils.random.nextInt(available.size()));
            } 
            else {
                next = Collections.min(neighbors, Comparator.comparingInt(n -> adjacencyMap.get(n).size()));
            }

            son.add(next);
            visited.add(next);
            current = next;
        }

        return son;
    }

    @Override
    public CrossType getType() {
        return CrossType.ERX;
    }

    @Override
    public CrossMethod create() {
        return new ERX();
    }
    
}
