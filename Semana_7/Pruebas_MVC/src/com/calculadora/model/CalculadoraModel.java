package com.calculadora.model;

public class CalculadoraModel {
    private int num1, num2, ans;

    public CalculadoraModel()
    {
        num1 = 0;
        num2 = 0;
        ans = 0;
    }

    // Business Logic
    public void sumar()
    {
        this.ans = num1 + num2;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    
}
