package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithm.cross.CrossMethod;
import algorithm.cross.CrossType;
import algorithm.initialization.InitializationType;
import algorithm.mutation.MutationMethod;
import algorithm.mutation.MutationType;
import algorithm.selection.SelectionMethod;
import algorithm.selection.SelectionType;
import config.Parameters;
import controller.Controller;
import exceptions.CrossException;
import exceptions.IndividuoException;
import exceptions.SelectionException;
import factories.CrossMethodFactory;
import factories.IndividuoFactory;
import factories.MutationMethodFactory;
import factories.SelectionMethodFactory;
import model.IndividualType;
import model.Individuo;
import model.Node;
import utils.BloatingUtils;
import utils.Utils;

public class AlgoritmoGenetico {

    private Controller controller;
    public static int tamPoblation;
    private List<Individuo> poblation;
    private List<Individuo> elitePoblation;
    private IndividualType individualType;
    private List<Double> fitness;
    private List<Object> fenotipo;
    private double fitnessSum;
    private SelectionType selectionType;
    private CrossType crossType;
    private MutationType mutationMethod;
    private InitializationType initializationMethod;
    private int maxGeneraciones;
    private double crossProbability;
    private double mutationProbability;
    private double[][] overallBest;
    private double mostFood;
    private double[][] actualBest;
    private double[][] averageFitness;
    private int iteration;
    private double precision;
    private Individuo bestIndividual;
    private Individuo worstIndividual;
    private int elitism;
    private int numCruces;
    private int numMutaciones;

    public AlgoritmoGenetico(Controller controller, int tamPoblation, IndividualType individualType, int maxGeneraciones, double crossProbability, double mutationProbability, double precision) {
        this.controller = controller;
        this.tamPoblation = tamPoblation;
        this.individualType = individualType;
        this.selectionType = Parameters.DEFAULT_SELECTION_METHOD;
        this.crossType = Parameters.DEFAULT_CROSS_METHOD;
        this.mutationMethod = Parameters.DEFAULT_MUTATION_METHOD;
        this.initializationMethod = Parameters.DEFAULT_INITIALIZATION_METHOD;
        this.maxGeneraciones = maxGeneraciones;
        this.crossProbability = crossProbability;
        this.mutationProbability = mutationProbability / 100;
        this.precision = precision;
        this.elitism = Parameters.DEFAULT_ELITISM;
        this.poblation = new ArrayList<>();
        this.fitness = new ArrayList<>();
        this.fenotipo = new ArrayList<>();
        this.elitePoblation = new ArrayList<>();
    }

    public double run() throws IndividuoException, SelectionException, CrossException {
        this.init();
        this.evalue();
        this.iteration++;
        while (this.iteration <= this.maxGeneraciones) {
            this.generateElite();
            this.selection();
            this.cross();
            this.mutation();
            this.introduceElite();
            this.evalue();
            this.iteration++;
        }

        String solution = this.bestIndividual.toString() + 
            "\nNumero de cruces: " + this.numCruces +
            "\nNumero de mutaciones: " + this.numMutaciones + 
            "\nFitness del peor individuo: " + this.worstIndividual.getFitness();
        this.controller.setSolution(solution);
        System.out.println("FINAL\n\nSolucion:\n" + solution);
        
        this.controller.refreshPlot(this.averageFitness, this.actualBest, this.overallBest);
        this.controller.refreshHouse(this.bestIndividual);
        this.controller.refreshAntMap(bestIndividual);
        return mostFood;
    }

    private void init() throws IndividuoException {
        this.poblation.clear();
        for (int i = 0; i < this.tamPoblation; i++) {
            poblation.add(IndividuoFactory.getIndividuo(individualType, this.controller));
        }

        this.elitePoblation.clear();
        for (int i = 0; i < this.tamPoblation; i++) {
            elitePoblation.add(IndividuoFactory.getIndividuo(individualType, this.controller));
        }

        this.fitness.clear();
        for (int i = 0; i < this.tamPoblation; i++) {
            this.fitness.add(null);
        }

        this.fenotipo.clear();
        for (int i = 0; i < this.tamPoblation * 2; i++) {
            this.fenotipo.add(null);
        }
        
        this.overallBest = new double[this.maxGeneraciones + 1][2];
        mostFood = 0;
        this.actualBest = new double[this.maxGeneraciones + 1][2];
        this.averageFitness = new double[this.maxGeneraciones + 1][2];
        for (int i = 0; i <= this.maxGeneraciones; i++) {
            this.overallBest[i][1] = Double.POSITIVE_INFINITY;
            this.overallBest[i][0] = i;
            this.actualBest[i][1] = Double.POSITIVE_INFINITY;
            this.actualBest[i][0] = i;
            this.averageFitness[i][1] = Double.POSITIVE_INFINITY;
            this.averageFitness[i][0] = i;
        }
        this.iteration = 0;
        this.numCruces = 0;
        this.numMutaciones = 0;
    }

    private void bloating() {
        double fitness, bestFitness = 0;
        Individuo individuo;
        boolean first = true;
        this.fitnessSum = 0;

        List<Double> lengths = new ArrayList<>();
        List<Double> rawFitnesses = new ArrayList<>();

        for (int i = 0; i < this.poblation.size(); i++) {
            Individuo ind = this.poblation.get(i);
            Node node = (Node) ind.getCromosomas().get(0);
            lengths.add((double) node.getAllNodes(0, Parameters.DEFAULT_MAX_DEPTH).size());
            rawFitnesses.add(ind.getFitness());
        }

        double k = BloatingUtils.calculateK(lengths, rawFitnesses);

        if (this.iteration > 0) {
            this.overallBest[this.iteration][1] = this.overallBest[this.iteration - 1][1];
        }

        for (int i = 0; i < this.poblation.size(); i++) {
            individuo = this.poblation.get(i);
            
            double rawFitness = rawFitnesses.get(i);
            fitness = rawFitness + k * lengths.get(i);
            this.fitness.set(i, fitness);
            this.fitnessSum += fitness;

            if (first || individuo.betterThan(fitness, bestFitness) == 1) {
                bestFitness = fitness;
                this.actualBest[this.iteration][1] = bestFitness;

                if ((first && this.iteration == 0) || individuo.betterThan(fitness, this.overallBest[this.iteration][1]) == 1) {
                    this.bestIndividual = individuo.copy();
                    this.overallBest[this.iteration][1] = fitness;
                    mostFood = rawFitness;
                }
            }

            if ((first && this.iteration == 0) || individuo.betterThan(fitness, this.worstIndividual.getFitness()) == -1) {
                this.worstIndividual = individuo.copy();
            }

            first = false;
        }

        this.averageFitness[this.iteration][1] = this.fitnessSum / this.poblation.size();
        this.printIteration();

        this.regenerateIndividualsBloating(lengths);
    }

    private void regenerateIndividualsBloating(List<Double> lengths) {
        double mean = BloatingUtils.mean(lengths);
        for (int i = 0; i < this.poblation.size(); i++) {
            if (lengths.get(i) > mean && Utils.random.nextDouble() < 0.5) {
                try {
                    this.poblation.set(i, IndividuoFactory.getIndividuo(individualType, this.controller));
                } catch (IndividuoException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void evalue() {
        if (Parameters.BLOATING) {
            this.bloating();
            return;
        }
        double fitness, bestFitness = 0;
        Individuo individuo;
        boolean first = true;
        this.fitnessSum = 0;

        if (this.iteration > 0) {
            this.overallBest[this.iteration][1] = this.overallBest[this.iteration - 1][1];
        }

        for (int i = 0; i < this.poblation.size(); i++) {
            individuo = this.poblation.get(i);
            fitness = individuo.getFitness();
            fitness = Math.round(fitness / this.precision);
            fitness *= this.precision;
            this.fitness.set(i, fitness);
            this.fitnessSum += fitness;
            if (first || individuo.betterThan(fitness, bestFitness) == 1) {
                bestFitness = fitness;
                this.actualBest[this.iteration][1] = bestFitness;
                if ((first && this.iteration == 0) || individuo.betterThan(fitness, this.overallBest[this.iteration][1]) == 1) {
                    this.bestIndividual = individuo.copy();
                    this.overallBest[this.iteration][1] = fitness;
                }
            }
            if ((first && this.iteration == 0) || individuo.betterThan(fitness, this.worstIndividual.getFitness()) == -1) {
                this.worstIndividual = individuo.copy();
            }
            first = false;
        }
        this.averageFitness[this.iteration][1] = this.fitnessSum / this.poblation.size();
        this.printIteration();
    }

    private void generateElite() {
        if (this.elitism > 0) {
            List<Individuo> poblation = new ArrayList<>(this.poblation);
            Collections.sort(poblation, (a, b) -> this.poblation.get(0).betterThan(b.getFitness(), a.getFitness()));
            for (int i = 0; i < this.elitism; i++) {
                this.elitePoblation.set(i, poblation.get(i).copy());
            }
        }
    }

    private void selection() throws SelectionException {
        SelectionMethod selectionMethod = SelectionMethodFactory.getSelectionMethod(this.selectionType);
        this.poblation = selectionMethod.selection(poblation, fitness, fitnessSum, this.poblation.size());
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
            this.numCruces++;
        }
    }

    private void mutation() throws SelectionException {
        MutationMethod mutationMethod = MutationMethodFactory.getSelectionMethod(this.mutationMethod);
        for (int i = 0; i < this.poblation.size(); i++) {
            Individuo indv = mutationMethod.mutate(this.poblation.get(i), this.mutationProbability);
            if (!indv.equals(this.poblation.get(i))) {
                this.numMutaciones++;
                this.poblation.set(i, indv);
            }
        }
    }

    private void introduceElite() {
        if (this.elitism > 0) {
            for (int i = 0; i < this.elitism; i++) {
                int worstIndex = 0;
                double worstFitness = this.poblation.get(0).getFitness();
                for (int j = 1; j < this.poblation.size(); j++) {
                    double currentFitness = this.poblation.get(j).getFitness();
                    if (this.elitePoblation.get(i).betterThan(worstFitness, currentFitness) == 1) {
                        worstFitness = currentFitness;
                        worstIndex = j;
                    }
                }
                if (this.elitePoblation.get(i).betterThan(this.elitePoblation.get(i).getFitness(), worstFitness) == 1) {
                    this.poblation.set(worstIndex, this.elitePoblation.get(i));
                }
            }
        }
    }
    
    private void printIteration() {
        String formato = "%." + Math.round(- Math.log10(this.precision)) + "f";
        System.out.println("Iteracion " + this.iteration + ":\n" +
            "- Fitness promedio: " + String.format(formato, this.averageFitness[this.iteration][1]) + "\n" +
            "- Fitness del mejor individuo de la iteracion: " + String.format(formato, this.actualBest[this.iteration][1]) + "\n" +
            "- Fitness del mejor individuo: " + String.format(formato, this.overallBest[this.iteration][1]));
    }

    private void refreshFitness() {
        for (int i = 0; i < this.tamPoblation; i++) {
            this.fitness.set(i, this.poblation.get(i).getFitness());
        }
    }

    private void refreshFenotipo() {
        for (int i = 0; i < this.tamPoblation; i++) {
            this.fenotipo.set(i * 2, this.poblation.get(i).getFenotipo(0));
            this.fenotipo.set(i * 2 + 1, this.poblation.get(i).getFenotipo(1));
        }
    }

    public void setPopulationSize(int populationSize) {
        this.tamPoblation = populationSize;
    }

    public void setGenerations(int generations) {
        this.maxGeneraciones = generations;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getPrecision() {
        return this.precision;
    }

    public void setIndividual(IndividualType individual) {
        this.individualType = individual;
    }

    public void setCrossProbability(int crossProbability) {
        this.crossProbability = (double) crossProbability / 100;
    }

    public void setMutationProbability(int mutationProbability) {
        this.mutationProbability = (double) mutationProbability / 100;
    }

    public void setSelectionMethod(SelectionType selectionMethod) {
        this.selectionType = selectionMethod;
    }

    public void setCrossMethod(CrossType crossMethod) {
        this.crossType = crossMethod;
    }

    public void setMutationMethod(MutationType mutationMethod) {
        this.mutationMethod = mutationMethod;
    }

    public void setMutationMethod(InitializationType initializationMethod) {
        this.initializationMethod = initializationMethod;
    }

    public void setElitismPercentage(int elitismPercentage) {
        this.elitism = elitismPercentage > 0 ? Math.round((this.tamPoblation / 100) * elitismPercentage) : 0;
    }

    public InitializationType getInitializationMethod() {
        return this.initializationMethod;
    }

}