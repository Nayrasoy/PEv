package config;

import algorithm.cross.CrossType;
import algorithm.mutation.MutationType;
import algorithm.selection.SelectionType;
import model.IndividualType;

public class Parameters {
    

    public static final int SEED = 123;

    public static final int DEFAULT_TAM_POBLATION = 100;
    public static final int DEFAULT_GENERATIONS_NUMBER = 100;
    public static final IndividualType DEFAULT_INDIVIDUAL_TYPE = IndividualType.INDIVIDUAL_RUMBA;
    public static final SelectionType DEFAULT_SELECTION_METHOD = SelectionType.RULETA;
    public static final CrossType DEFAULT_CROSS_METHOD = CrossType.PMX;
    public static final MutationType DEFAULT_MUTATION_METHOD = MutationType.POR_INVERSION;
    public static final double DEFAULT_CROSS_PROBABILITY = 0.6;
    public static final double DEFAULT_PRECISION = 0.001;
    public static final int DEFAULT_MUTATION_PROBABILITY = 5;
    public static final int DEFAULT_TAM_TOURNAMENT = 3;
    public static final int DEFAULT_DIMENSION = 5;
    public static final double DEFAULT_UNIFORM_CROSS_PROBABILITY = 0.5;
    public static final int DEFAULT_ELITISM = 3;
    public static final double PROPORSION_TRUNCAMIENTO = 0.1;
    public static final int TOURNAMENT_K = 3;
    public static final double TOURNAMENT_P = 0.5;

    public static final double NUM_ROOMS = 20;
    public static final int SIZE = 15;
    public static final int CELL_SIZE = 40;
    public static final int OXPP_RANDOM_POINTS = 3;

}
