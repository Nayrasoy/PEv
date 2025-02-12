import algorithm.AlgoritmoGenetico;

public class MainP1 {

    public static void main(String args[]) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(10, 1, 10, 0.2, 0.1, 10);
        try {
            ag.run();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
}