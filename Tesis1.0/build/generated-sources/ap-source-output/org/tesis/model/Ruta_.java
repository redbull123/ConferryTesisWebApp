package org.tesis.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tesis.model.Itinerario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-29T23:49:41")
@StaticMetamodel(Ruta.class)
public class Ruta_ { 

    public static volatile SingularAttribute<Ruta, String> ruta;
    public static volatile CollectionAttribute<Ruta, Itinerario> itinerarioCollection;
    public static volatile SingularAttribute<Ruta, Integer> id;

}