package org.banco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.TarjetaCredito;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T12:36:30")
@StaticMetamodel(MovimientoTarjeta.class)
public class MovimientoTarjeta_ { 

    public static volatile SingularAttribute<MovimientoTarjeta, Date> fecha;
    public static volatile SingularAttribute<MovimientoTarjeta, Integer> tipo;
    public static volatile SingularAttribute<MovimientoTarjeta, Double> monto;
    public static volatile SingularAttribute<MovimientoTarjeta, TarjetaCredito> tarjetaCreditoId;
    public static volatile SingularAttribute<MovimientoTarjeta, Integer> id;

}