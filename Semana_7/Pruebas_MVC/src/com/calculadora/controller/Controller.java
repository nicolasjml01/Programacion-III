package com.calculadora.controller;

import com.calculadora.model.CalculadoraModel;
import com.calculadora.view.View;

public class Controller {
    View v;
    CalculadoraModel m;

    public Controller(View v, CalculadoraModel m) {
        this.v = v;
        this.m = m;
        v.setController(this);

    }

    public void init() {
        // Cargar datos en el modelo....
        // Cualquier cosa para preparar la app

        // Mostrar Menu
        v.showMainMenu();

        // Finalizacion de la aplicación
    }

    public void setNum1(int num1) {
        m.setNum1(num1);
    }

    public void setNum2(int num2) {
        m.setNum2(num2);
    }

    public void realizarCalculos() {
        m.sumar();
    }

    public int getNum1() {
        return m.getNum1();
    }

    public int getNum2() {
        return m.getNum2();
    }

    public int getAns() {
        return m.getAns();
    }

}
