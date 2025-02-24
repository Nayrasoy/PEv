package controller;

import java.lang.reflect.Parameter;

import algorithm.AlgoritmoGenetico;
import algorithm.cross.CrossType;
import algorithm.mutation.MutationType;
import algorithm.selection.SelectionType;
import config.Parameters;
import exceptions.CrossException;
import exceptions.IndividuoException;
import exceptions.SelectionException;
import model.IndividualType;
import views.MainWindow;

public class Controller {

    AlgoritmoGenetico ag;
    MainWindow mainWindow;
    int dimension = Parameters.DEFAULT_DIMENSION;

    public void run() {
        try {
            this.ag.run();
        } catch (IndividuoException | SelectionException | CrossException e) {
            System.out.println(e);
        }
    }

    public void refreshPlot(double[][] averageFitness, double[][] actualBest, double[][] overallBest) {
        this.mainWindow.refreshPlot(averageFitness, actualBest, overallBest);
    }

    // {1, 2, 3, 4, 5}
    // {5 , 4, 3, 2, 1}

    // {1, 4}{2, 7}{3, 28}

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setAG(AlgoritmoGenetico ag) {
        this.ag = ag;
    }

    public void setPopulationSize(int populationSize) {
        this.ag.setPopulationSize(populationSize);
    }

    public void setGenerations(int generations) {
        this.ag.setGenerations(generations);
    }

    public void setPrecision(double precision) {
        this.ag.setPrecision(precision);
    }

    public double getPrecision() {
        return this.ag.getPrecision();
    }

    public void setIndividual(IndividualType individual) {
        this.ag.setIndividual(individual);
    }

    public void setCrossProbability(int crossProbability) {
        this.ag.setCrossProbability(crossProbability);
    }

    public void setMutationProbability(int mutationProbability) {
        this.ag.setMutationProbability(mutationProbability);
    }

    public void setSelectionMethod(SelectionType selectionMethod) {
        this.ag.setSelectionMethod(selectionMethod);
    }

    public void setCrossMethod(CrossType crossMethod) {
        this.ag.setCrossMethod(crossMethod);
    }

    public void setMutationMethod(MutationType mutationMethod) {
        this.ag.setMutationMethod(mutationMethod);
    }

    public void setElitismPercentage(int elitismPercentage) {
        this.ag.setElitismPercentage(elitismPercentage);
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    public void setSolution(String solution) {
        this.mainWindow.setSolution(solution);
    }
    
}
