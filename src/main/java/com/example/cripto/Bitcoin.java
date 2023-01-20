package com.example.cripto;

/**
 *
 */
public class Bitcoin {
    private double m15;
    private double last;
    private double sell;
    private String simbolo;
    public Bitcoin(double m15, double last, double sell,
                   String simbolo) {
        this.m15 = m15;
        this.last = last;
        this.sell = sell;
        this.simbolo = simbolo;
    }
    public double getM15() {
        return m15;
    }
    public void setM15(double m15) {
        this.m15 = m15;
    }
    public double getLast() {
        return last;
    }
    public void setLast(double last) {
        this.last = last;
    }
    public double getSell() {
        return sell;
    }
    public void setSell(double sell) {
        this.sell = sell;
    }
    public String getSimbolo() {
        return simbolo;
    }
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}

