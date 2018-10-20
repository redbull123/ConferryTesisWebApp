package org.banco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.Cliente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T12:36:30")
@StaticMetamodel(CuentasCredito.class)
public class CuentasCredito_ { 

    public static volatile SingularAttribute<CuentasCredito, String> numero;
    public static volatile SingularAttribute<CuentasCredito, Cliente> clienteId;
    public static volatile SingularAttribute<CuentasCredito, String> fechaApertura;
    public static volatile SingularAttribute<CuentasCredito, Integer> id;

}