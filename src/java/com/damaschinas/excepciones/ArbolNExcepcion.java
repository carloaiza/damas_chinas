/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.excepciones;

/**
 *
 * @author carloaiza
 */
public class ArbolNExcepcion extends Exception {

    public ArbolNExcepcion() {
    }

    public ArbolNExcepcion(String message) {
        super(message);
    }

    public ArbolNExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

    public ArbolNExcepcion(Throwable cause) {
        super(cause);
    }
    
}
