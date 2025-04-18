package model;

import java.util.ArrayList;
import java.util.List;

import algorithm.initialization.InitializationMethod;
import config.Parameters;
import controller.Controller;
import exceptions.InitializationExeption;
import factories.InitializationMethodFactory;

public class Ant extends Individuo<Terminal> {

    Hormiguero hormiguero;

    public Ant() {}
    
    public Ant(Controller controller) {
        super(controller);

        this.hormiguero = Hormiguero.getInstance();

        this.cromosomas = new ArrayList<>();
        try {
            InitializationMethod initializationMethod = InitializationMethodFactory.getInitializationMethod(this.controller.getInitializationMethod());
            this.cromosomas.add(initializationMethod.initializate());
        } catch (InitializationExeption e) {
            e.printStackTrace();
        }
    }

    public Ant(Controller controller, List<Terminal> cromosomas) {
        super(controller, cromosomas, null, null, null, 1);
        this.hormiguero = Hormiguero.getInstance();
    }

    @Override
    public double getFitness() {
        int food = 0;
        Coords coords = new Coords(0, 0);
        Terminal.direction = Direction.RIGHT;
        for (int i = 0; i < Parameters.MAX_ANT_TIME; i++) {
            food += this.cromosomas.get(0).execute(coords);
            if (food == this.hormiguero.getFood().size()) {
                break;
            }
        }
        return food;
    }

    @Override
    public Object getFenotipo(int gen) {
        return this.cromosomas.get(gen);
    }

    @Override
    public Individuo create(Controller controller) {
        return new Ant(controller);
    }
    
    @Override
    public Individuo copy() {
        return new Ant(this.controller, new ArrayList<>(this.cromosomas));
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_ANT;
    }

    @Override
    public int betterThan(double myFitness, double fitness) {
        return myFitness > fitness ? 1 : myFitness == fitness ? 0 : -1;
    }

    @Override
    public int getMinValue() {
        return 0;
    }

    @Override
    public String toString() {
        // TODO: hacer
        return "";
    }
    
}
