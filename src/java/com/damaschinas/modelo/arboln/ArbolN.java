/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo.arboln;

import com.damaschinas.excepciones.ArbolNExcepcion;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolN implements Serializable {

    private NodoN raiz;
    private int cantidadNodos;

    public NodoN getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoN raiz) {
        this.raiz = raiz;
    }

    public int getCantidadNodos() {
        return cantidadNodos;
    }

    public void setCantidadNodos(int cantidadNodos) {
        this.cantidadNodos = cantidadNodos;
    }

    public String adicionarNodo(PersonaDto dato, PersonaDto padre) // throws ArbolNExcepcion
    {
        if (raiz == null) {
            raiz = new NodoN(dato);
            cantidadNodos++;
            return "La persona ha sido adicionada";
        } else {
            if (buscarPersonaxIdentificacion(dato.getIdentificacion()) == null) {

                try {
                    adicionarNodo(dato, padre, raiz);
                    cantidadNodos++;
                    return "La persona ha sido adicionada";
                } catch (ArbolNExcepcion ex) {
                    Logger.getLogger(ArbolN.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                    return ex.getMessage();
                }
            }
            else
            {
                return "La persona a adicionar ya existe";
            }

        }

    }

    public boolean adicionarNodo(PersonaDto dato, PersonaDto padre, NodoN pivote) throws ArbolNExcepcion {
        //si en el nodo que estoy actualmente es el padre
        if (pivote.getDato().getIdentificacion().compareTo(padre.getIdentificacion()) == 0) {
            pivote.getHijos().add(new NodoN(dato));
            return true;
        } else {
            ///Llamamos la recursividad 
            for (NodoN hijo : pivote.getHijos()) {
                if (adicionarNodo(dato, padre, hijo)) {
                    return true;
                }
            }

            throw new ArbolNExcepcion("El padre ingresado no existe " + padre.getIdentificacion());
        }

    }

    public PersonaDto buscarPersonaxIdentificacion(String identificacion) {

        return buscarPersonaxIdentificacion(identificacion, raiz);
    }

    private PersonaDto buscarPersonaxIdentificacion(String identificacion, NodoN pivote) {
        if (pivote.getDato().getIdentificacion().compareTo(identificacion) == 0) {
            return pivote.getDato();
        } else {
            PersonaDto datoEncontrado = null;
            for (NodoN hijo : pivote.getHijos()) {
                datoEncontrado = buscarPersonaxIdentificacion(identificacion, hijo);
                if (datoEncontrado != null) {
                    return datoEncontrado;
                }
            }
        }

        return null;
    }

}
