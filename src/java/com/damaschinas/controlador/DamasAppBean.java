/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damaschinas.controlador;

import com.damaschinas.controlador.util.JsfUtil;
import com.damaschinas.modelo.Usuario;
import com.damaschinas.modelo.grafo.Arista;
import com.damaschinas.modelo.grafo.Ficha;
import com.damaschinas.modelo.grafo.Grafo;
import com.damaschinas.modelo.grafo.Vertice;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author carloaiza
 */
@Named(value = "damasAppBean")
@ApplicationScoped
public class DamasAppBean {

    private int altoFichas = 4;
    private int altoIntermedio = 7;
    private byte distancia = 10;
    private DefaultDiagramModel model;
    private Grafo tablero = new Grafo();

    public DamasAppBean() {
    }

    public void llenarAristas() {
        //Crear aristas
       int limite =(altoFichas + altoIntermedio / 2);
       int limiteSup=limite+1;
        for (Vertice vert : tablero.getVertices()) {
            if (vert.getFicha().getNivel() <= limite) {
                tablero.adicionarArista(vert.getId(), vert.getId() + vert.getFicha().getNivel(), 1);
                tablero.adicionarArista(vert.getId(), vert.getId() + vert.getFicha().getNivel() + 1, 1);
               if(vert.getId() < calcularRegresion(vert.getFicha().getNivel()))
                {
                    tablero.adicionarArista(vert.getId(), vert.getId() + 1, 2);
                }
            }
            else if (vert.getFicha().getNivel() == limiteSup)
            {
                if(vert.getId() < calcularRegresion(vert.getFicha().getNivel()))
                {
                    tablero.adicionarArista(vert.getId(), vert.getId() + 1, 2);                    
                }               
            }
            else
            {
               // tablero.adicionarArista(vert.getId(), vert.getId() - vert.getFicha().getNivel()+1, 1);
               // tablero.adicionarArista(vert.getId(), vert.getId() - vert.getFicha().getNivel()-2 , 1);
         
                
            }    
            
             
            
        }
        
    }
    
    private int calcularRegresion(int num){
        int suma=0;
        for(int i=1; i<= num;i++)
        {
            suma=suma+i;
        }    
        
        return suma;
    }
    

    @PostConstruct
    public void pintarTablero() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);

        int x = 35;
        int y = 0;
        String color = "Rojo";
        String styleColor = "ui-diagram-element-ficha";
        int contNivel=0;
        for (int i = 0; i <= (altoFichas + altoIntermedio / 2) + 1; i++) {
            x = 35 - (i * 2);
            if (i > altoFichas) {
                color = "blanca";
                styleColor = "ui-diagram-element-ficha-blanca";
            }

            for (int j = 1; j <= i; j++) {
                Ficha ficha=new Ficha(color, i);
                tablero.adicionarVertice(ficha);
                //Element ceo = new Element(ficha, x + "em", y + "em");
                Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                ceo.setDraggable(false);
                ceo.setStyleClass(styleColor);
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                model.addElement(ceo);
                x = x + 4;
            }
            y = y + 3;
            contNivel++;
        }
        
        for (int i = (altoFichas + altoIntermedio / 2); i >= 1; i--) {
            x = 35 - (i * 2);
            if (i <= altoFichas) {
                color = "azul";
                styleColor = "ui-diagram-element-ficha-azul";
            }

            for (int j = 1; j <= i; j++) {
                Ficha ficha=new Ficha(color, contNivel);
                tablero.adicionarVertice(ficha);                
                //Element ceo = new Element(ficha, x + "em", y + "em");
                Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                ceo.setStyleClass(styleColor);
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                model.addElement(ceo);
                x = x + 4;
            }
            y = y + 3;
            contNivel++;
        }
        llenarAristas();

        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        
                
        
        
        
        
        //recorrer aristas
        for (Arista arista : tablero.getAristas()) {
            Element origen = model.getElements().get(arista.getOrigen() - 1);
            Element destino = model.getElements().get(arista.getDestino() - 1);
            switch (arista.getPeso()) {
                case 1:
                    model.connect(new Connection(origen.getEndPoints().get(0), destino.getEndPoints().get(1)));
                    break;
                case 2:
                    
                    model.connect(new Connection(origen.getEndPoints().get(3), destino.getEndPoints().get(2)));
                    break;
            }

        }
    }

    public int getAltoFichas() {
        return altoFichas;
    }

    public void setAltoFichas(int altoFichas) {
        this.altoFichas = altoFichas;
    }

    public int getAltoIntermedio() {
        return altoIntermedio;
    }

    public void setAltoIntermedio(int altoIntermedio) {
        this.altoIntermedio = altoIntermedio;
    }

    public byte getDistancia() {
        return distancia;
    }

    public void setDistancia(byte distancia) {
        this.distancia = distancia;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
    
    
    public void simularJugada()
    {
        Element elem1=model.getElements().get(8-1);
        
        Element elem2=model.getElements().get(12-1);
        
        elem1.setStyleClass("ui-diagram-element-ficha-blanca");
        
        elem2.setStyleClass("ui-diagram-element-ficha");
    }

}
