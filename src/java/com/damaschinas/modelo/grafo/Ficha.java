/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo.grafo;

import java.io.Serializable;

/**
 *
 * @author carloaiza
 */
public class Ficha implements Serializable{
   
    private String color;
    private int nivel;

    public Ficha(String color, int nivel) {
        this.color = color;
        this.nivel= nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return " ";//Ficha{" + "color=" + color + ", nivel=" + nivel + '}';
    }

    
    
}
