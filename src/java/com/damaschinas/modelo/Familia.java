/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carloaiza
 */
@Entity
@Table(name = "familia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f"),
    @NamedQuery(name = "Familia.findByApellido", query = "SELECT f FROM Familia f WHERE f.apellido = :apellido"),
    @NamedQuery(name = "Familia.findByNumeroIntegrantes", query = "SELECT f FROM Familia f WHERE f.numeroIntegrantes = :numeroIntegrantes")})
public class Familia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_integrantes")
    private short numeroIntegrantes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familia")
    private List<Persona> personaList;

    public Familia() {
    }

    public Familia(String apellido) {
        this.apellido = apellido;
    }

    public Familia(String apellido, short numeroIntegrantes) {
        this.apellido = apellido;
        this.numeroIntegrantes = numeroIntegrantes;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public short getNumeroIntegrantes() {
        return numeroIntegrantes;
    }

    public void setNumeroIntegrantes(short numeroIntegrantes) {
        this.numeroIntegrantes = numeroIntegrantes;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apellido != null ? apellido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familia)) {
            return false;
        }
        Familia other = (Familia) object;
        if ((this.apellido == null && other.apellido != null) || (this.apellido != null && !this.apellido.equals(other.apellido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.apellido;
    }
    
}
