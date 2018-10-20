package org.banco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.Cliente;
import org.banco.model.MovimientoTarjeta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T12:36:30")
@StaticMetamodel(TarjetaCredito.class)
public class TarjetaCredito_ { 

    public static volatile SingularAttribute<TarjetaCredito, String> marca;
    public static volatile SingularAttribute<TarjetaCredito, Integer> cvc;
    public static volatile SingularAttribute<TarjetaCredito, String> numero;
    public static volatile CollectionAttribute<TarjetaCredito, MovimientoTarjeta> movimientoTarjetaCollection;
    public static volatile SingularAttribute<TarjetaCredito, Cliente> clienteId;
    public static volatile SingularAttribute<TarjetaCredito, Date> fechaVencimiento;
    public static volatile SingularAttribute<TarjetaCredito, Integer> id;
    public static volatile SingularAttribute<TarjetaCredito, Double> saldo;
    public static volatile SingularAttribute<TarjetaCredito, Double> limite;
    public static volatile SingularAttribute<TarjetaCredito, Integer> status;

}