package controller;

import algorithm.AlgoritmoGenetico;
import exceptions.CrossException;
import exceptions.IndividuoException;
import exceptions.SelectionException;
import model.IndividualType;
import views.MainWindow;

public class Controller {

    AlgoritmoGenetico ag;
    MainWindow mainWindow;

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
    
}
