package com.cashback.model;

import com.cashback.model.Ciudad;
import com.cashback.model.Ubicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Barrio.class)
public class Barrio_ { 

    public static volatile SingularAttribute<Barrio, Ciudad> ciudad;
    public static volatile ListAttribute<Barrio, Ubicacion> ubicacions;
    public static volatile SingularAttribute<Barrio, String> barNombre;
    public static volatile SingularAttribute<Barrio, Integer> barId;

}