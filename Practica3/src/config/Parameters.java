package config;

import algorithm.cross.CrossType;
import algorithm.initialization.InitializationType;
import algorithm.mutation.MutationType;
import algorithm.selection.SelectionType;
import model.IndividualType;

public class Parameters {
    

    public static final int SEED = 123;

    public static final int DEFAULT_TAM_POBLATION = 100;
    public static final int DEFAULT_GENERATIONS_NUMBER = 200;
    public static final IndividualType DEFAULT_INDIVIDUAL_TYPE = IndividualType.INDIVIDUAL_ANT;
    public static final SelectionType DEFAULT_SELECTION_METHOD = SelectionType.RANKING;
    public static final CrossType DEFAULT_CROSS_METHOD = CrossType.TREE_CROSS;
    public static final MutationType DEFAULT_MUTATION_METHOD = MutationType.TERMINAL;
    public static final double DEFAULT_CROSS_PROBABILITY = 0.6;
    public static final double DEFAULT_PRECISION = 0.001;
    public static final int DEFAULT_MUTATION_PROBABILITY = 25;
    public static final int DEFAULT_TAM_TOURNAMENT = 3;
    public static final int DEFAULT_DIMENSION = 5;
    public static final double DEFAULT_UNIFORM_CROSS_PROBABILITY = 0.5;
    public static final int DEFAULT_ELITISM = 3;
    public static final double PROPORSION_TRUNCAMIENTO = 0.1;
    public static final int TOURNAMENT_K = 3;
    public static final double TOURNAMENT_P = 0.5;

    public static final int DESPLAZAMIENTO_INSERCION = 2;
    public static final int N_HEURISTICA = 2; // Entre 2 y 3
    public static final int CROSS_INVENT_RAND = 2;

    public static final double NUM_ROOMS = 20;
    public static final int SIZE = 15;
    public static final int CELL_SIZE = 40;
    public static final int OXPP_RANDOM_POINTS = 3;
    public static final double RANKING_BETA = 2;
    public static final double INVENTED_MUTATION_PROBABILITY = 0.5;

    public static final InitializationType DEFAULT_INITIALIZATION_METHOD = InitializationType.CRECIENTE;
    public static final int MAX_ANT_TIME = 400;
    public static final int DEFAULT_MIN_DEPTH = 2;
    public static final int DEFAULT_MAX_DEPTH = 3;
    public static final int GRID_WIDTH = 32;
    public static final int GRID_HEIGHT = 32;

}
