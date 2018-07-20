package org.tesis.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tesis.model.Itinerario;
import org.tesis.model.Pasajero;
import org.tesis.model.TipoBoleto;
import org.tesis.model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-20T00:16:43")
@StaticMetamodel(Boleto.class)
public class Boleto_ { 

    public static volatile SingularAttribute<Boleto, Itinerario> itinerarioId;
    public static volatile SingularAttribute<Boleto, Date> fecha;
    public static volatile SingularAttribute<Boleto, Date> hora;
    public static volatile CollectionAttribute<Boleto, Pasajero> pasajeroCollection;
    public static volatile SingularAttribute<Boleto, Integer> id;
    public static volatile SingularAttribute<Boleto, TipoBoleto> tipoBoletoId;
    public static volatile SingularAttribute<Boleto, Usuario> usuarioId;

}