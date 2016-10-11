package com.cashback.model;

import com.cashback.model.Hobbie;
import com.cashback.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(HobbiePersona.class)
public class HobbiePersona_ { 

    public static volatile SingularAttribute<HobbiePersona, Hobbie> hobbie;
    public static volatile SingularAttribute<HobbiePersona, Integer> hpId;
    public static volatile SingularAttribute<HobbiePersona, String> hpEstado;
    public static volatile SingularAttribute<HobbiePersona, Persona> persona;

}