package org.tesis.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tesis.model.Boleto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-21T12:35:40")
@StaticMetamodel(TipoBoleto.class)
public class TipoBoleto_ { 

    public static volatile SingularAttribute<TipoBoleto, Double> precio;
    public static volatile CollectionAttribute<TipoBoleto, Boleto> boletoCollection;
    public static volatile SingularAttribute<TipoBoleto, Date> fechaStatus;
    public static volatile SingularAttribute<TipoBoleto, Integer> id;
    public static volatile SingularAttribute<TipoBoleto, String> nombre;
    public static volatile SingularAttribute<TipoBoleto, Integer> status;

}