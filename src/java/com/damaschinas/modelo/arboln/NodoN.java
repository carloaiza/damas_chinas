/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo.arboln;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carloaiza
 */
public class NodoN implements Serializable{
    private PersonaDto dato;
    private List<NodoN> hijos;

    public NodoN(PersonaDto dato) {
        this.dato = dato;
        hijos= new ArrayList<>();
    }

    public PersonaDto getDato() {
        return dato;
    }

    public void setDato(PersonaDto dato) {
        this.dato = dato;
    }

    public List<NodoN> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoN> hijos) {
        this.hijos = hijos;
    }
    
    
    
    
}
