package mvcherenciainterface;

import controller.Controller;
import model.MemoryRepository;
import model.Model;
import model.PersistenceRepository;
import model.TSVRepository;
import view.AppView;
import view.ConsolaListadoView;
import view.ConsolaSubMenusView;

public class MVCHerenciaInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PersistenceRepository repository;
        AppView view;
        
        // LLamada esperada java -jar app.jar view repository
        // por ejemplo: java -jar app.jar submenus tsv
        if(args.length == 2){
            view = getViewForoption(args[0]);
            repository = getRepositoryForOption(args[1]);
            
        }else{
            // Opciones por defecto:
            view = new ConsolaListadoView();
            repository = new MemoryRepository(20);
        }
        
        Model model = new Model(repository);
        Controller c = new Controller(model, view);
        
        c.initApplication();  
    }

    private static PersistenceRepository getRepositoryForOption(String argumento) {
        switch (argumento) {
            case "tsv":
                return new TSVRepository();
            default:
                return new MemoryRepository(20);
        }
    }

    private static AppView getViewForoption(String argumento) {
        switch (argumento) {
            case "listado":
                return new ConsolaListadoView();
            case "submenus":
                return new ConsolaSubMenusView();
            default:
                return new ConsolaListadoView();
        }
    }
    
}
