/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carloaiza
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "contrasena")
    private String contrasena;
    @JoinColumn(name = "persona", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String correo) {
        this.correo = correo;
    }

    public Usuario(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.damaschinas.modelo.Usuario[ correo=" + correo + " ]";
    }
    
}
