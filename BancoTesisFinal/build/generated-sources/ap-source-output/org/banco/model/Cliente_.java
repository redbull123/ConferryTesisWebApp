package org.banco.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.banco.model.Cuentas;
import org.banco.model.CuentasCredito;
import org.banco.model.TarjetaCredito;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-01T11:52:46")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile CollectionAttribute<Cliente, CuentasCredito> cuentasCreditoCollection;
    public static volatile CollectionAttribute<Cliente, Cuentas> cuentasCollection;
    public static volatile CollectionAttribute<Cliente, TarjetaCredito> tarjetaCreditoCollection;
    public static volatile SingularAttribute<Cliente, String> ci;
    public static volatile SingularAttribute<Cliente, String> apellido;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> email;

}