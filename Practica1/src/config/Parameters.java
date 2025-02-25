package config;

import algorithm.cross.CrossType;
import algorithm.mutation.MutationType;
import algorithm.selection.SelectionType;
import model.IndividualType;

public class Parameters {
    

    public static int SEED = 123;

    public static int DEFAULT_TAM_POBLATION = 100;
    public static int DEFAULT_GENERATIONS_NUMBER = 100;
    public static IndividualType DEFAULT_INDIVIDUAL_TYPE = IndividualType.INDIVIDUAL_FUNCTION1;
    public static SelectionType DEFAULT_SELECTION_METHOD = SelectionType.RULETA;
    public static CrossType DEFAULT_CROSS_METHOD = CrossType.MONO_PUNTO;
    public static MutationType DEFAULT_MUTATION_METHOD = MutationType.SOBRE_BOOLEANOS;
    public static double DEFAULT_CROSS_PROBABILITY = 0.6;
    public static double DEFAULT_PRECISION = 0.001;
    public static double DEFAULT_MUTATION_PROBABILITY = 0.05;
    public static int DEFAULT_TAM_TOURNAMENT = 3;
    public static int DEFAULT_DIMENSION = 5;
    public static double DEFAULT_UNIFORM_CROSS_PROBABILITY = 0.5;
    public static int DEFAULT_ELITISM = 3;
    public static double PROPORSION_TRUNCAMIENTO = 0.5;
    public static int TOURNAMENT_K = 3;
    public static double TOURNAMENT_P = 0.5;

}
