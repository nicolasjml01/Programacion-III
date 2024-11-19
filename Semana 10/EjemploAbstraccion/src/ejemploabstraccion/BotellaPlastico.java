package ejemploabstraccion;

/**
 *
 * @author Loza
 */
public class BotellaPlastico implements Reciclable{

    @Override
    public void reciclar() {
        System.out.println("Una botella de plástico, al contenedor amarillo.");
    }
    
}
