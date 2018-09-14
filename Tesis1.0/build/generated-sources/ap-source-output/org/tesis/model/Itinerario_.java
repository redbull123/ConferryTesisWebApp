package org.tesis.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tesis.model.Barco;
import org.tesis.model.Boleto;
import org.tesis.model.Ruta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-13T11:23:08")
@StaticMetamodel(Itinerario.class)
public class Itinerario_ { 

    public static volatile SingularAttribute<Itinerario, Date> fecha;
    public static volatile CollectionAttribute<Itinerario, Boleto> boletoCollection;
    public static volatile SingularAttribute<Itinerario, Ruta> rutaId;
    public static volatile SingularAttribute<Itinerario, Barco> barcoId;
    public static volatile SingularAttribute<Itinerario, Integer> id;
    public static volatile SingularAttribute<Itinerario, Date> time;

}