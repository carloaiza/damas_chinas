/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo.arboln;

import java.io.Serializable;

/**
 *
 * @author carloaiza
 */
public class PersonaDto implements Serializable{

    private String identificacion;
    private String nombre;
    private short edad;

    public PersonaDto(String identificacion, String nombre, short edad) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return this.identificacion;
    }    
    
}
