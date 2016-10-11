package com.cashback.model;

import com.cashback.model.Actor;
import com.cashback.model.Localidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Localidad.class)
public class Localidad_ { 

    public static volatile ListAttribute<Localidad, Localidad> localidads;
    public static volatile ListAttribute<Localidad, Actor> actors;
    public static volatile SingularAttribute<Localidad, String> locEstado;
    public static volatile SingularAttribute<Localidad, String> locNombre;
    public static volatile SingularAttribute<Localidad, Localidad> localidad;
    public static volatile SingularAttribute<Localidad, Integer> locId;

}