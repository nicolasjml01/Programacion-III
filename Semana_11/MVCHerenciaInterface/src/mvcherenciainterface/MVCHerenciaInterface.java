package mvcherenciainterface;

import controller.Controller;
import model.MemoryRepository;
import model.Model;
import model.PersistenceRepository;
import model.TSVRepository;
import view.AppView;
import view.ConsolaListadoView;
import view.ConsolaSubMenusView;

/**
 *
 * 
 * Este proyecto busca ejemplificar el uso de la herencia y las interfaces
 * dentro del patron MVC.
 * 
 * Algunos aspectos a tener en cuenta:
 * 
 * 1 - Se realiza una inicialización del MVC a partir de parametros de consola.
 * Dependiendo de los parametros que se le pasen al programa, ciertas partes
 * serán implementadas con unos objetos u otros. El resto de la aplicaicón sigue
 * funcionando exactamente igual ya que estan partes han sido abstraidas con
 * herencia en caso de la vista y con interfaces en el caso de la exportación/importación.
 * 
 * 2 - Seguid el flujo de ejecución del programa y revisad cada una de las clases
 * de este proyecto. Revisa como se vuelca la información con serialización al acabar
 * y se recupera al empezar.
 * 
 * 3 - Revisad las clases en el paquete de view y sus diferencias
 * 
 * 4 - El controlador es un mero intermediario y orquestador de la aplicación
 * 
 * 5 - Revisad las clases en el modelo, en especial las clases que implementan
 * PersistenceRepository. En la clase Model se puede ver que esta depende de
 * dicha interfaz, no de ninguna clase concreta (TSVRepository, etc)
 * 
 * 6 - Revisad las opciones de CRUD como se han hecho esta vez sobre la colección
 * y en Alumno la implementación de equals y hashcode para establecer como comparar
 * dos instancias de Alumno. Esto dota a las colecciones de toda su potencia y facilita
 * el desarrollo.
 * 
 * 
 * TODOs: Tomando como base este proyecto... 
 * 
 * 0 - Cambia los parámetros de entrada para ver las distintas formas de la app.
 * submenus tsv 
 * 1 - implementa nuevas formas de exportación como XML y JSON. ¿Cambia algo el resto de la aplicación?
 * 2 - Añade una vista nueva que esté en inglés
 *     o con la UI de consola con submenús de otra forma 
 *     o que utilice una biblioteca como esta para narrar la UI con el TTS del sistema: 
 *     USO: https://github.com/jonelo/jAdapterForNativeTTS
 *     JAR: https://github.com/jonelo/jAdapterForNativeTTS/releases/download/v0.12.0/jadapter-for-native-tts-0.12.0.jar
 *      
 * 
 * @author Loza
 */
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
