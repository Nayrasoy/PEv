import config.Parameters;
import model.IndividuoFuncion1;

public class MainP1 {

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Individuo " + i);
            IndividuoFuncion1 ind = new IndividuoFuncion1();
            String formato = "%." + Parameters.PRECISION + "f";
            System.out.println(String.format(formato, ind.getFenotipo(0)));
            System.out.println(String.format(formato, ind.getFenotipo(1)));
            System.out.println(String.format(formato, ind.getFitness()));
        }
    }
    
}