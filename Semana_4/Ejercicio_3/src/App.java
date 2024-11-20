import com.coti.tools.Esdia;

public class App {
    
    public static void main(String[] args){
        Fruteria manzanas = null;
        Fruteria peras = null;
        try {
            double precioManzanas = Esdia.readFloat("Introduce el precio por kilo de las manzanas (sin IVA): ");
            double precioPeras = Esdia.readFloat("Introduce el precio por kilo de las peras (sin IVA): ");

            if (precioManzanas <= 0 || precioPeras <= 0)
            {
                System.out.println("ERROR: Introduce un precio positivo");
                return;
            }

            manzanas = new Fruteria("Manzanas", precioManzanas);
            peras = new Fruteria("Peras", precioPeras);


        } catch (Exception e) {
            System.out.println("Debes introducir un valor valido en los precios.");
            return;
        }

        System.out.println("Precios del dia almacenados correctamente");

        // Parte de los clientes
        boolean salir = false;
        int numCliente = 1;
        
        do{
            System.out.println("Cliente " + numCliente);
            System.out.println();

            try {
                double cantidadManzanas = Esdia.readFloat("Introduce los kg de manzanas que deseas comprar: ");
                double cantidadPeras = Esdia.readFloat("Introduce los kg de peras que deseas comprar: ");

                if (cantidadManzanas < 0 || cantidadPeras < 0)
                {
                    System.out.println("ERROR, la cantidad de peras o manzanas debe ser mayor o igual que 0");
                    return;
                }

                // Calculamos el precio con IVA
                double precioPeras = cantidadPeras * peras.getPrecioConIVA();
                double precioManzanas = cantidadManzanas * manzanas.getPrecioConIVA();
                double totalFactura = precioManzanas + precioPeras;
                
                // Mostrar la factura en forma de tabla
                System.out.println("\nFactura:");
                System.out.printf("%-10s %-10s %-15s %-15s%n", "Producto", "Cantidad", "Precio/kg (IVA)", "Total");
                System.out.printf("%-10s %-10.2f %-15.2f %-15.2f%n", 
                                    manzanas.getNombre(), cantidadManzanas, manzanas.getPrecioConIVA(), precioManzanas);
                System.out.printf("%-10s %-10.2f %-15.2f %-15.2f%n", 
                                    peras.getNombre(), cantidadPeras, peras.getPrecioConIVA(), precioPeras);
                System.out.println("-------------------------------------------");
                System.out.printf("Total a pagar: %.2f €%n", totalFactura);
                
                numCliente++;
            } catch (Exception e) {
                System.out.println("Introduce un numero valido");
            }
            // Preguntar si desea atender otro cliente
            salir = Esdia.yesOrNo("¿Desea salir? (y/n): ");
        }while(!salir);
    }
}
