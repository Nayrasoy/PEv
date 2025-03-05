import algorithm.AlgoritmoGenetico;
import config.Parameters;
import controller.Controller;
import views.MainWindow;

public class MainP2 {

    public static void main(String args[]) {
        Controller controller = new Controller();
        AlgoritmoGenetico ag = new AlgoritmoGenetico(controller, Parameters.DEFAULT_TAM_POBLATION, Parameters.DEFAULT_INDIVIDUAL_TYPE, 
            Parameters.DEFAULT_GENERATIONS_NUMBER, Parameters.DEFAULT_CROSS_PROBABILITY, Parameters.DEFAULT_MUTATION_PROBABILITY, 
            Parameters.DEFAULT_PRECISION);
        MainWindow mainWindow = new MainWindow(controller);
        controller.setAG(ag);
        controller.setMainWindow(mainWindow);
    }
    
}