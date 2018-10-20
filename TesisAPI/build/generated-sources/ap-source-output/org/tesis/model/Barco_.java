package org.tesis.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tesis.model.Itinerario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-18T10:15:57")
@StaticMetamodel(Barco.class)
public class Barco_ { 

    public static volatile SingularAttribute<Barco, Integer> capacidadMoto;
    public static volatile SingularAttribute<Barco, Integer> capacidadPersonas;
    public static volatile SingularAttribute<Barco, Integer> capacidadAutos;
    public static volatile CollectionAttribute<Barco, Itinerario> itinerarioCollection;
    public static volatile SingularAttribute<Barco, Integer> id;
    public static volatile SingularAttribute<Barco, Integer> capacidadCarga;
    public static volatile SingularAttribute<Barco, String> nombre;
    public static volatile SingularAttribute<Barco, Integer> capacidadAutobus;

}