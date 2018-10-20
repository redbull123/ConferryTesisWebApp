package org.banco.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.Cliente;
import org.banco.model.MovimientosCuenta;
import org.banco.model.TarjetaDebito;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T12:36:30")
@StaticMetamodel(Cuentas.class)
public class Cuentas_ { 

    public static volatile SingularAttribute<Cuentas, Double> fondo;
    public static volatile CollectionAttribute<Cuentas, MovimientosCuenta> movimientosCuentaCollection;
    public static volatile SingularAttribute<Cuentas, Integer> tipo;
    public static volatile SingularAttribute<Cuentas, String> numero;
    public static volatile CollectionAttribute<Cuentas, TarjetaDebito> tarjetaDebitoCollection;
    public static volatile SingularAttribute<Cuentas, Cliente> clienteId;
    public static volatile SingularAttribute<Cuentas, Date> fechaApertura;
    public static volatile SingularAttribute<Cuentas, Integer> id;

}