package algorithm;

import java.util.ArrayList;
import java.util.List;

import algorithm.cross.CrossMethod;
import algorithm.cross.CrossType;
import algorithm.selection.SelectionMethod;
import algorithm.selection.SelectionType;
import exceptions.CrossException;
import exceptions.IndividuoException;
import exceptions.SelectionException;
import factories.CrossMethodFactory;
import factories.IndividuoFactory;
import factories.SelectionMethodFactory;
import model.Individuo;
import utils.Utils;

public class AlgoritmoGenetico {

    private int tamPoblation;
    private List<Individuo> poblation;
    private int individuoType;
    private List<Double> fitness;
    private double fitnessSum;
    private SelectionType selectionType;
    private CrossType crossType;
    private int maxGeneraciones;
    private double crossProbability;
    private double mutationProbability;
    private int tamTorneo;
    private Individuo overallBest;
    private int actualBest;
    private double averageFitness;

    public AlgoritmoGenetico(int tamPoblation, int individuoType, int maxGeneraciones, double crossProbability, double mutationProbability, int tamTorneo) {
        this.tamPoblation = tamPoblation;
        this.individuoType = individuoType;
        this.selectionType = SelectionType.RULETA;
        this.crossType = CrossType.MONO_PUNTO;
        this.maxGeneraciones = maxGeneraciones;
        this.crossProbability = crossProbability;
        this.mutationProbability = mutationProbability;
        this.tamTorneo = tamTorneo;
        this.poblation = new ArrayList<>();
        this.fitness = new ArrayList<>();
        for (int i = 0; i < this.tamPoblation; i++) {
            this.fitness.add(null);
        }
        this.overallBest = null;
        this.actualBest = 0; 
        this.averageFitness = 0; 
    }

    public Individuo run() throws IndividuoException, SelectionException, CrossException {
        this.init();
        this.evalue();
        System.out.println("INICIAL:\n" +
            "- Fitness promedio: " + this.averageFitness + "\n" +
            "- Mejor individuo: " + this.poblation.get(this.actualBest));
        for (int i = 0; i < this.maxGeneraciones; i++) {
            this.selection();
            this.cross();
            this.mutation();
            this.evalue();
            System.out.println("Iteracion " + (i + 1) + ":\n" +
                "- Fitness promedio: " + this.averageFitness + "\n" +
                "- Mejor individuo: " + this.poblation.get(this.actualBest));
        }
        System.out.println("FINAL\n\nMejor individuo: \n" + this.overallBest);
        return this.overallBest;
    }

    private void init() throws IndividuoException {
        for (int i = 0; i < this.tamPoblation; i++) {
            poblation.add(IndividuoFactory.getIndividuo(individuoType));
        }
    }

    private void evalue() {
        double fitness, bestFitness = 0;
        Individuo individuo;
        boolean first = true;
        this.fitnessSum = 0;
        this.actualBest = 0;

        for (int i = 0; i < this.poblation.size(); i++) {
            individuo = this.poblation.get(i);
            fitness = individuo.getFitness();
            this.fitness.set(i, fitness);
            this.fitnessSum += fitness;
            if (first || fitness > bestFitness) {
                bestFitness = fitness;
                this.actualBest = i;
                if (this.overallBest == null || this.overallBest.getFitness() < fitness) {
                    this.overallBest = this.poblation.get(i).copy();
                }
            }
            first = false;
        }
        this.averageFitness = this.fitnessSum / this.poblation.size();
    }

    private void selection() throws SelectionException {
        SelectionMethod selectionMethod = SelectionMethodFactory.getSelectionMethod(this.selectionType);
        this.poblation = selectionMethod.selection(poblation, fitness, fitnessSum);
    }

    private void cross() throws CrossException {
        List<Integer> list = new ArrayList<>();
        List<Individuo> newPoblation;
        CrossMethod crossMethod = CrossMethodFactory.getCrossMethod(this.crossType);

        for (int i = 0; i < this.tamPoblation; i++) {
            if (Utils.random.nextDouble() < this.crossProbability) {
                list.add(i);
            }
        }

        for (int i = 0; i < list.size() - 1; i += 2) {
            newPoblation = crossMethod.cross(this.poblation.get(list.get(i)), this.poblation.get(list.get(i + 1)));
            this.poblation.set(list.get(i), newPoblation.get(0));
            this.poblation.set(list.get(i + 1), newPoblation.get(1));
        }
    }

    private void mutation() {
        for (Individuo i: this.poblation) {
            i.checkForMutations(this.mutationProbability);
        }
    }

}