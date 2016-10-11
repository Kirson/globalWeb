package com.cashback.model;

import com.cashback.model.LocalVenta;
import com.cashback.model.Persona;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Transaccion.class)
public class Transaccion_ { 

    public static volatile SingularAttribute<Transaccion, Integer> traPtosAcreditados;
    public static volatile SingularAttribute<Transaccion, String> traFactura;
    public static volatile SingularAttribute<Transaccion, Persona> persona;
    public static volatile SingularAttribute<Transaccion, String> traEstado;
    public static volatile SingularAttribute<Transaccion, BigDecimal> traValorFac;
    public static volatile SingularAttribute<Transaccion, Integer> traId;
    public static volatile SingularAttribute<Transaccion, Integer> traTipo;
    public static volatile SingularAttribute<Transaccion, LocalVenta> localVenta;

}