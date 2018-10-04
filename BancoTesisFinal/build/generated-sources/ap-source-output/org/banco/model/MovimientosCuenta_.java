package org.banco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.Cuentas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-01T11:52:46")
@StaticMetamodel(MovimientosCuenta.class)
public class MovimientosCuenta_ { 

    public static volatile SingularAttribute<MovimientosCuenta, Date> fecha;
    public static volatile SingularAttribute<MovimientosCuenta, Integer> tipo;
    public static volatile SingularAttribute<MovimientosCuenta, Double> monto;
    public static volatile SingularAttribute<MovimientosCuenta, Integer> id;
    public static volatile SingularAttribute<MovimientosCuenta, Cuentas> cuentasId;

}