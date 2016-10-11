package com.cashback.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Nota.class)
public class Nota_ { 

    public static volatile SingularAttribute<Nota, Integer> catId;
    public static volatile SingularAttribute<Nota, Integer> perId;
    public static volatile SingularAttribute<Nota, String> notCuerpo;
    public static volatile SingularAttribute<Nota, Integer> notValorizacion;
    public static volatile SingularAttribute<Nota, String> notEncabezado;
    public static volatile SingularAttribute<Nota, String> notComentarioAdmin;
    public static volatile SingularAttribute<Nota, Date> notFecha;
    public static volatile SingularAttribute<Nota, Integer> notId;

}