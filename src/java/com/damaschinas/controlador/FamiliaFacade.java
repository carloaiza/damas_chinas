/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.controlador;

import com.damaschinas.modelo.Familia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author carloaiza
 */
@Stateless
public class FamiliaFacade extends AbstractFacade<Familia> {

    @PersistenceContext(unitName = "damaschinasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FamiliaFacade() {
        super(Familia.class);
    }
    
}
