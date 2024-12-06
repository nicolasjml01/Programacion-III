package controller;

import java.util.List;
import model.Alumno;
import model.Model;
import view.AppView;

/**
 *
 * @author Loza
 */
public class Controller {
    
    Model model;
    AppView view;

    public Controller(Model model, AppView view) {
        this.model = model;
        this.view = view;
        view.setController(this);
    }
     
    
    public void initApplication(){
        
        // Carga inicial programa
        if(model.cargarEstadoAplicación()){
            view.mostrarInicio("Cargado estado anterior con exito");
        }else{
            view.mostrarInicio("No se encontró fichero para carga del programa");
        }
        
        // Menú principal
        view.mostrarMenuPrincipal();
        
        
        // Guardado final del programa
        if(model.guardarEstadoAplicación()){
            view.mostrarFinPrograma("Guardado el estado de la aplicación.\nSaliendo...");
        }else{
            view.mostrarFinPrograma("No se pudo guardar el estado de la aplicación.\nSaliendo...");
        }
        
    }
    
    
    public boolean agregarAlumno(Alumno alumno) {
        return model.agregarAlumno(alumno);
    }

    public boolean eliminarAlumnoPorDNI(String DNI) {
        return model.eliminarAlumnoPorDNI(DNI);
    }

    public boolean importarAlumnos() {
        return model.importarAlumnos();
    }

    public boolean exportarAlumnos() {
        return  model.exportarAlumnos();
    }

    public List<Alumno> obtenerAlumnosDelModelo() {
        
        return model.obtenerAlumnosInmutable();
        
    }

    
}
