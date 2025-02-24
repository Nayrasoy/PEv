package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.type.NullType;

import algorithm.cross.CrossMethod;
import algorithm.cross.CrossType;
import algorithm.selection.SelectionMethod;
import algorithm.selection.SelectionType;
import config.Parameters;
import controller.Controller;
import exceptions.CrossException;
import exceptions.IndividuoException;
import exceptions.SelectionException;
import factories.CrossMethodFactory;
import factories.IndividuoFactory;
import factories.SelectionMethodFactory;
import model.IndividualType;
import model.Individuo;
import utils.Utils;

public class AlgoritmoGenetico {

    private Controller controller;
    private int tamPoblation;
    private List<Individuo> poblation;
    private IndividualType individualType;
    private List<Double> fitness;
    private double fitnessSum;
    private SelectionType selectionType;
    private CrossType crossType;
    private int maxGeneraciones;
    private double crossProbability;
    private double mutationProbability;
    private int tamTorneo;
    private double[][] overallBest;
    private double[][] actualBest;
    private double[][] averageFitness;
    private int iteration;
    private double precision;

    public AlgoritmoGenetico(Controller controller, int tamPoblation, IndividualType individualType, int maxGeneraciones, double crossProbability, double mutationProbability, int tamTorneo, double precision) {
        this.controller = controller;
        this.tamPoblation = tamPoblation;
        this.individualType = individualType;
        this.selectionType = SelectionType.RULETA;
        this.crossType = CrossType.MONO_PUNTO;
        this.maxGeneraciones = maxGeneraciones;
        this.crossProbability = crossProbability;
        this.mutationProbability = mutationProbability;
        this.tamTorneo = tamTorneo;
        this.precision = precision;
        this.poblation = new ArrayList<>();
        this.fitness = new ArrayList<>();
        for (int i = 0; i < this.tamPoblation; i++) {
            this.fitness.add(null);
        }
    }

    public void run() throws IndividuoException, SelectionException, CrossException {
        this.init();
        this.evalue();
        this.iteration++;
        while (this.iteration <= this.maxGeneraciones) {
            this.selection();
            this.cross();
            this.mutation();
            this.evalue();
            
            this.iteration++;
        }

        String formato = "%." + Math.round(- Math.log10(this.precision)) + "f";
        System.out.println("FINAL\n\nMejor individuo: \n" + String.format(formato, this.overallBest[this.iteration - 1][1]));
        
        this.controller.refreshPlot(this.averageFitness, this.actualBest, this.overallBest);
    }

    private void init() throws IndividuoException {
        this.poblation.clear();
        for (int i = 0; i < this.tamPoblation; i++) {
            poblation.add(IndividuoFactory.getIndividuo(individualType, this.controller));
        }
        
        this.overallBest = new double[this.maxGeneraciones + 1][2];
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
    }

    private void evalue() {
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
            if (first || individuo.betterThan(fitness, bestFitness)) {
                bestFitness = fitness;
                this.actualBest[this.iteration][1] = bestFitness;
                if ((first && this.iteration == 0) || individuo.betterThan(fitness, this.overallBest[this.iteration][1])) {
                    this.overallBest[this.iteration][1] = fitness;
                }
            }
            first = false;
        }
        this.averageFitness[this.iteration][1] = this.fitnessSum / this.poblation.size();
        this.printIteration();
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
    
    private void printIteration() {
        String formato = "%." + Math.round(- Math.log10(this.precision)) + "f";
        System.out.println("Iteracion " + this.iteration + ":\n" +
            "- Fitness promedio: " + String.format(formato, this.averageFitness[this.iteration][1]) + "\n" +
            "- Fitness del mejor individuo de la iteracion: " + String.format(formato, this.actualBest[this.iteration][1]) + "\n" +
            "- Fitness del mejor individuo: " + String.format(formato, this.overallBest[this.iteration][1]));
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

}