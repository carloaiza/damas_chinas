/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.controlador;

import com.damaschinas.controlador.util.JsfUtil;
import com.damaschinas.modelo.Familia;
import com.damaschinas.modelo.Usuario;
import com.damaschinas.modelo.arboln.ArbolN;
import com.damaschinas.modelo.arboln.NodoN;
import com.damaschinas.modelo.arboln.PersonaDto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author carloaiza
 */
@Named(value = "controladorDamasChinas")
@SessionScoped
public class ControladorDamasChinas implements Serializable {

    private Usuario usuario;
    @EJB   
    private UsuarioFacade usuarioFacade;    
    @EJB
    private FamiliaFacade familiaFacade;
    private DefaultDiagramModel model;    
    private int numeroFamilias=2; 
    private ArbolN[] familias;

    public int getNumeroFamilias() {
        return numeroFamilias;
    }

    public void setNumeroFamilias(int numeroFamilias) {
        this.numeroFamilias = numeroFamilias;
    }
    
      
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    /**
     * Creates a new instance of ControladorDamasChinas
     */
    public ControladorDamasChinas() {
        
    }
    @PostConstruct
    private void inicializar()
    {
        usuario= new Usuario();
        //Hacemos el llamado al llenado del árbol en el evento del postconstruct 
        //para que se ejecute despues de instanciar el bean
        familias= new ArbolN[numeroFamilias];
        
        llenarFamilias();
        pintarArbol();
        
    }
    
    public void llenarFamilias()
    {
        //Debería ir por los datos a bds
        
        List<Familia> listaFamilias= familiaFacade.findAll();
        
        familias[0]= new ArbolN();
        familias[1]= new ArbolN();
        familias[0].adicionarNodo(new PersonaDto("75147236","Carlos Loaiza",
                (short)50), null);
        PersonaDto juanRam= new PersonaDto("15675675",
                "Juan Manuel Ramírez", 
                (short)20);
        familias[0].adicionarNodo(juanRam, familias[0].getRaiz().getDato());
        familias[0].adicionarNodo(new PersonaDto("12345","Valeria Loaiza", 
                (short)20), familias[0].getRaiz().getDato());
        familias[0].adicionarNodo(new PersonaDto("56789","Estefanía Loaiza", 
                (short)14), familias[0].getRaiz().getDato());
        
        familias[0].adicionarNodo(new PersonaDto("99999","Pedro Pérez", 
                (short)10), juanRam );
        
        familias[1].adicionarNodo(new PersonaDto("1058912728", 
                "Carlos Velásquez (The Bad)", (short)22), null);
        
        
        
    }
    /*
    private void pintarArbol()
    {
        // Introducir el código que inicializa el model del Diagram model = new DefaultDiagramModel();
        
        //Establece que nuestro diagrama puede tener n conectores (Líneas entre elementos)
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        // Las conexiones pueden crearse o quitarse visualmente (True =si False =no) 
        model.setConnectionsDetachable(false);
        
        //Crea un elemento con Texto CEO y lo ubica en x y y
        Element ceo = new Element("CEO", "25em", "6em");
        // Le crea al elemnto un punto donde se puede ubicar un conector y 
        // lo ubica en la parte de abajo del elemento
        ceo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
        //Adiciona el elemento al modelo
        model.addElement(ceo);
         
        
        Element cfo = new Element("CFO", "10em", "18em");
        cfo.addEndPoint(createEndPoint(EndPointAnchor.TOP));
        cfo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
         
        Element fin = new Element("FIN", "5em", "30em");
        fin.addEndPoint(createEndPoint(EndPointAnchor.TOP));
         
        Element pur = new Element("PUR", "20em", "30em");
        pur.addEndPoint(createEndPoint(EndPointAnchor.TOP));
         //Adicionamos 3 elementos mas al diagrama
         // UBicamos puntos de conexión en la parte de arriba y abajo de los elementos
        model.addElement(cfo);
        model.addElement(fin);
        model.addElement(pur);
         
        //CTO
        Element cto = new Element("CTO", "40em", "18em");
        cto.addEndPoint(createEndPoint(EndPointAnchor.TOP));
        cto.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
         
        Element dev = new Element("DEV", "35em", "30em");
        dev.addEndPoint(createEndPoint(EndPointAnchor.TOP));
         
        Element tst = new Element("TST", "50em", "30em");
        tst.addEndPoint(createEndPoint(EndPointAnchor.TOP));
        //Se adicionan 3 elementos mas y se ubican en x y y en em 
        model.addElement(cto);
        model.addElement(dev);
        model.addElement(tst);
         
        //Establecemos el tipo de conectores que queremos utilizar entre los elementos
        StraightConnector connector = new StraightConnector();
        //Damos estilo a la línea conectora
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
        //estilo para cuando el mouse se coloque sobre la línea
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
                         
        //connections
        //Establecemos las conexiones entre los puntos de los elementos (Notese que los endpoints de los elementos 
        //son un arreglo por lo cual se debe dar una posición
        model.connect(new Connection(ceo.getEndPoints().get(0), cfo.getEndPoints().get(0), connector));        
        model.connect(new Connection(ceo.getEndPoints().get(0), cto.getEndPoints().get(0), connector));
        model.connect(new Connection(cfo.getEndPoints().get(1), fin.getEndPoints().get(0), connector));
        model.connect(new Connection(cfo.getEndPoints().get(1), pur.getEndPoints().get(0), connector));
        model.connect(new Connection(cto.getEndPoints().get(1), dev.getEndPoints().get(0), connector));
        model.connect(new Connection(cto.getEndPoints().get(1), tst.getEndPoints().get(0), connector));
        
    }
    */
     public void pintarArbol() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbol(familias[0].getRaiz(), model, null, 30, 0);

    }

    private void pintarArbol(NodoN reco, DefaultDiagramModel model, 
            Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, 
                        elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);
            for(NodoN hijo:reco.getHijos())
            {
                pintarArbol(hijo, model, elementHijo, x - 10, y + 10);
                x=x+10;
            }
       
        }
    }

    
    
     //Este método crea los puntos de conexión en elemento, allí se define estilo
    private EndPoint createEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setStyle("{fillStyle:'#404a4e'}");
        endPoint.setHoverStyle("{fillStyle:'#20282b'}");
         
        return endPoint;
    }
     
    //Método getter para poder acceder al modelo desde la vista
    public DiagramModel getModel() {
        return model;
    }
    /*
    
    public String ingresar()
    {       
    
        Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
        if(usuarioEncontrado != null)
        {
            if(usuario.getContrasena().compareTo(usuarioEncontrado.getContrasena())==0)
            {
                return "ingresar";
            }
            JsfUtil.addErrorMessage("Contraseña errada");
        }
        else
        {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        }
        
        /*
        if(usuario.getCorreo().compareTo("carloaiza@gmail.com")==0)
        {
            if(usuario.getContrasena().compareTo("123456")==0)
            {
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

*/    
    
    
}
