package org.banco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.Cuentas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-01T11:52:46")
@StaticMetamodel(TarjetaDebito.class)
public class TarjetaDebito_ { 

    public static volatile SingularAttribute<TarjetaDebito, String> marca;
    public static volatile SingularAttribute<TarjetaDebito, String> numero;
    public static volatile SingularAttribute<TarjetaDebito, Integer> codigoSeguridad;
    public static volatile SingularAttribute<TarjetaDebito, Date> fechaVencimiento;
    public static volatile SingularAttribute<TarjetaDebito, Integer> id;
    public static volatile SingularAttribute<TarjetaDebito, Cuentas> cuentasId;

}