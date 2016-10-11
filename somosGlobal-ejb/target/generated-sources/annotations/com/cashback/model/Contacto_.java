package com.cashback.model;

import com.cashback.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Contacto.class)
public class Contacto_ { 

    public static volatile SingularAttribute<Contacto, Persona> persona;
    public static volatile SingularAttribute<Contacto, Integer> conId;
    public static volatile SingularAttribute<Contacto, String> conDescripcion;
    public static volatile SingularAttribute<Contacto, Integer> conTipo;

}