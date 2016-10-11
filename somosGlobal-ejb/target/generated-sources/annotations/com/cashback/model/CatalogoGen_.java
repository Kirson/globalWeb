package com.cashback.model;

import com.cashback.model.ActorReferencia;
import com.cashback.model.ActorRol;
import com.cashback.model.CatalogoGen;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(CatalogoGen.class)
public class CatalogoGen_ { 

    public static volatile SingularAttribute<CatalogoGen, String> nombreCg;
    public static volatile SingularAttribute<CatalogoGen, String> refCg;
    public static volatile SingularAttribute<CatalogoGen, String> ref02Cg;
    public static volatile SingularAttribute<CatalogoGen, Date> fecModCg;
    public static volatile SingularAttribute<CatalogoGen, Integer> idCg;
    public static volatile ListAttribute<CatalogoGen, ActorReferencia> actorReferencias;
    public static volatile ListAttribute<CatalogoGen, ActorRol> actorRols;
    public static volatile SingularAttribute<CatalogoGen, String> tipoCg;
    public static volatile SingularAttribute<CatalogoGen, CatalogoGen> catalogoGen;
    public static volatile ListAttribute<CatalogoGen, CatalogoGen> catalogoGens;
    public static volatile SingularAttribute<CatalogoGen, Date> fecCreaCg;
    public static volatile SingularAttribute<CatalogoGen, String> usrCreaCg;
    public static volatile SingularAttribute<CatalogoGen, String> usrModCg;

}