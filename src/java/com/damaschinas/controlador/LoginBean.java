/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.controlador;
import com.damaschinas.controlador.util.FacesUtils;
import com.damaschinas.controlador.util.JsfUtil;
import com.damaschinas.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;


/**
 *
 * @author carloaiza
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{

    private Usuario usuario;
    @EJB   
    private UsuarioFacade usuarioFacade;   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    private void inicializar()
    {
        usuario= new Usuario();
    }
    
    public String ingresar()
    {       
    
        Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
        if(usuarioEncontrado != null)
        {
            if(usuario.getContrasena().compareTo(usuarioEncontrado.getContrasena())==0)
            {
                ControladorDamasChinas contDamas= (ControladorDamasChinas) FacesUtils.getManagedBean("controladorDamasChinas");
                contDamas.setUsuario(usuarioEncontrado);
                return "ingresar";
                
            }
            JsfUtil.addErrorMessage("Contraseña errada");
        }
        else
        {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        }
        return null;
    }
}
