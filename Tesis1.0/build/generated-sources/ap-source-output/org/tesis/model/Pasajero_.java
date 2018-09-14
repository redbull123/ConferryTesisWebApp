package org.tesis.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tesis.model.Boleto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-13T11:23:08")
@StaticMetamodel(Pasajero.class)
public class Pasajero_ { 

    public static volatile SingularAttribute<Pasajero, Date> fechaNacimiento;
    public static volatile SingularAttribute<Pasajero, String> cedulaIdentidad;
    public static volatile SingularAttribute<Pasajero, String> apellido;
    public static volatile SingularAttribute<Pasajero, Integer> id;
    public static volatile SingularAttribute<Pasajero, Boleto> boletoId;
    public static volatile SingularAttribute<Pasajero, String> nombre;

}