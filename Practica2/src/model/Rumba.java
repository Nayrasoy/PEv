package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import config.Parameters;
import controller.Controller;
import utils.Utils;

public class Rumba extends Individuo<String> {

    Casa casa;

    public Rumba() {}
    
    public Rumba(Controller controller) {
        super(controller);

        this.casa = Casa.getInstance();

        this.cromosomas = new ArrayList<>();
        List<String> roomsAux = new ArrayList<>();
        Map<String, Coords> rooms = casa.getRooms();
        for (String id : rooms.keySet()) {
            roomsAux.add(id);
        } 
        for (int i = 0; i < Parameters.NUM_ROOMS; i++) {
            int rand = Utils.random.nextInt(roomsAux.size());
            this.cromosomas.add(roomsAux.get(rand));
            roomsAux.remove(rand);
        }
    }

    public Rumba(Controller controller, List<String> cromosomas, int nGenes) {
        super(controller, cromosomas, null, null, null, nGenes);
    }
    
    @Override
    public double getFitness() {
        double fitness = 0;
        for (int i = 0; i < this.cromosomas.size() - 1; i++) {
            fitness += casa.getDistance(this.cromosomas.get(i), this.cromosomas.get(i + 1));
        }
        return fitness;
    }
    
    @Override
    public String getFenotipo(int gen) {
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
