package com.cashback.model;

import com.cashback.model.Actor;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(ActorRol.class)
public class ActorRol_ { 

    public static volatile SingularAttribute<ActorRol, Actor> actor;
    public static volatile SingularAttribute<ActorRol, Integer> idArol;
    public static volatile SingularAttribute<ActorRol, ActorRol> actorRol;
    public static volatile ListAttribute<ActorRol, ActorRol> actorRols;
    public static volatile SingularAttribute<ActorRol, String> estadoArol;
    public static volatile SingularAttribute<ActorRol, Date> fecCreaArol;
    public static volatile SingularAttribute<ActorRol, CatalogoGen> catalogoGen;
    public static volatile SingularAttribute<ActorRol, BigDecimal> prcArol;
    public static volatile SingularAttribute<ActorRol, Date> fecModArol;

}