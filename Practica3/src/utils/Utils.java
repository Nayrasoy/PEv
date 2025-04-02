package utils;

import java.util.List;
import java.util.Random;

import config.Parameters;

public class Utils {
    
    public static Random random = new Random(Parameters.SEED);

    public static void searchForRepetitions(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.lastIndexOf(list.get(i)) != i) {
                System.out.println("Repeticion encontrada");
                break;
            }
        }
    }

}
