package model;

import java.util.ArrayList;
import java.util.List;

import config.Parameters;
import controller.Controller;
import utils.Utils;

public class Rumba extends Individuo<Double> {

    // Atributo estático con las rutas, de tal manera que se vayan calculando
    private List<List<List<Coords>>> routes;

    public Rumba() {}
    
    public Rumba(Controller controller) {
        super(controller);

        this.cromosomas = new ArrayList<>();
        List<Double> rooms = new ArrayList<>();
        for (Double i = 1.0; i <= Parameters.NUM_ROOMS; i++) {
            rooms.add(i);
        } 
        for (int i = 0; i < Parameters.NUM_ROOMS; i++) {
            int rand = Utils.random.nextInt(rooms.size());
            this.cromosomas.add(rooms.get(rand));
            rooms.remove(rand);
        }
    }

    public Rumba(Controller controller, List<Double> cromosomas, int nGenes) {
        super(controller, cromosomas, null, null, null, nGenes);
    }
    
    @Override
    public double getFitness() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFitness'");
    }
    
    @Override
    public double getFenotipo(int gen) {
        return this.cromosomas.get(gen);
    }
    
    @Override
    public Individuo create(Controller controller) {
        return new Rumba(controller);
    }
    
    @Override
    public Individuo copy() {
        return new Rumba(this.controller, new ArrayList<>(this.cromosomas), this.nGenes);
    }

    @Override
    public IndividualType getType() {
        return IndividualType.INDIVIDUAL_RUMBA;
    }

    @Override
    public int betterThan(double myFitness, double fitness) {
        return myFitness < fitness ? 1 : myFitness == fitness ? 0 : -1;
    }

    @Override
    public int getMinValue() {
        return 0;
    }
    
}
