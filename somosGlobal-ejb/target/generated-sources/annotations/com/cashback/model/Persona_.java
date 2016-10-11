package com.cashback.model;

import com.cashback.model.Contacto;
import com.cashback.model.HobbiePersona;
import com.cashback.model.LocalVenta;
import com.cashback.model.Puntos;
import com.cashback.model.Transaccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Integer> perId;
    public static volatile SingularAttribute<Persona, String> perCedRucPas;
    public static volatile SingularAttribute<Persona, Integer> perSexo;
    public static volatile SingularAttribute<Persona, String> perNombres;
    public static volatile ListAttribute<Persona, Puntos> puntos;
    public static volatile ListAttribute<Persona, Transaccion> transaccions;
    public static volatile ListAttribute<Persona, HobbiePersona> hobbiePersonas;
    public static volatile ListAttribute<Persona, LocalVenta> localVentas;
    public static volatile SingularAttribute<Persona, String> perEstado;
    public static volatile SingularAttribute<Persona, String> perApellidos;
    public static volatile SingularAttribute<Persona, Date> perFecNac;
    public static volatile SingularAttribute<Persona, String> perFoto;
    public static volatile ListAttribute<Persona, Contacto> contactos;
    public static volatile SingularAttribute<Persona, String> perEmail;

}