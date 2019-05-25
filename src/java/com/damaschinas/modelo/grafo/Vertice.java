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
public class Vertice implements Serializable{
    private int id;
    private Ficha ficha;

    public Vertice(int id, Ficha ficha) {
        this.id = id;
        this.ficha = ficha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    
    
    
}
