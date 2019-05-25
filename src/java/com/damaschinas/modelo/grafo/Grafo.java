/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo.grafo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carloaiza
 */
public class Grafo implements Serializable{
    private List<Vertice> vertices;
    private List<Arista> aristas;

    public Grafo() {
        vertices= new ArrayList<>();
        aristas = new ArrayList<>();
    }

    
    
    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }
    
    public void adicionarVertice(Ficha ficha)
    {
        vertices.add(new Vertice(vertices.size()+1, ficha));
    }
    
    public void adicionarArista(int origen, int destino, int peso)
    {
        //Insertar la lógica validar exstencia
        aristas.add(new Arista(origen, destino, peso));
    }
    
    
}
