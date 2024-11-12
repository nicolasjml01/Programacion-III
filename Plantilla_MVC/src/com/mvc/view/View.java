package com.mvc.view;

import com.mvc.controller.Controller;

public class View {
    Controller c;

    public void setController(Controller controller) {
        this.c = controller;
    }

    public void showMainMenu() {
        // Menu por consola en bucle
        System.out.println("Menu Quiniela...");
    }
}
