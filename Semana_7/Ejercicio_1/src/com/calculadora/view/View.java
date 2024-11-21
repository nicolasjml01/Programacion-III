package com.calculadora.view;

import com.calculadora.controller.Controller;
import com.coti.tools.Esdia;

public class View {
    Controller c ;

    public void setController(Controller controller) {
        this.c = controller;
    }

    public void showMainMenu() {
        // Bucle con el menu de usuario
        boolean exit = false;
        while (!exit)
        {
            System.out.println("Menu calculadora MVC");
            System.out.println("1) Introduce el primer numero");
            System.out.println("2) Introduce el segundo numero");
            System.out.println("3) Realizar Calculos (cambia estado del modelo)");
            System.out.println("4) Mostrar resultados (recupera el estado del modelo)");
            System.out.println("5) Salir");
            int opcion = Esdia.readInt("Elija la opcion: ");

            switch (opcion) {
                case 1:
                    // Introduce el primer numero y cambiar el estado del modelo
                    int num1 = Esdia.readInt("Introduce el primer numero: ");
                    c.setNum1(num1);
                    System.out.println("Se ha establecido el valor del primer numero " + num1);
                    break;
                case 2:
                    int num2 = Esdia.readInt("Introduce el segundo numero: ");
                    c.setNum2(num2);
                    System.out.println("Se ha establecido el valor del segundo numero " + num2);
                    break;
                case 3:
                    // Realizar calculos
                    c.realizarCalculos();
                    break;
                case 4:
                    // Recuperar estado del modelo
                    int num1Obtenido = c.getNum1();
                    int num2Obtenido = c.getNum2();
                    int ans = c.getAns();
                    // Representarlo
                    System.out.println("Num1 = " + num1Obtenido);
                    System.out.println("Num2 = " + num2Obtenido);
                    System.out.println("Suma = " + ans);
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }

   
}
