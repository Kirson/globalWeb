package com.cashback.model;

import com.cashback.model.ActorReferencia;
import com.cashback.model.ActorRol;
import com.cashback.model.Localidad;
import com.cashback.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Actor.class)
public class Actor_ { 

    public static volatile SingularAttribute<Actor, String> usrModAct;
    public static volatile SingularAttribute<Actor, Integer> idAct;
    public static volatile SingularAttribute<Actor, Date> fecNacAct;
    public static volatile SingularAttribute<Actor, String> numPreAct;
    public static volatile SingularAttribute<Actor, Date> fecModAct;
    public static volatile SingularAttribute<Actor, String> latitudAct;
    public static volatile SingularAttribute<Actor, String> sloganAct;
    public static volatile SingularAttribute<Actor, String> usrCreaAct;
    public static volatile SingularAttribute<Actor, String> representanteAct;
    public static volatile SingularAttribute<Actor, String> palabrasClaveAct;
    public static volatile ListAttribute<Actor, Usuario> usuarios;
    public static volatile SingularAttribute<Actor, String> urlDireccionAct;
    public static volatile SingularAttribute<Actor, String> logoAct;
    public static volatile SingularAttribute<Actor, String> servicioAct;
    public static volatile SingularAttribute<Actor, String> fotoAct;
    public static volatile SingularAttribute<Actor, Localidad> localidad;
    public static volatile SingularAttribute<Actor, String> mailAct;
    public static volatile SingularAttribute<Actor, String> sexoAct;
    public static volatile SingularAttribute<Actor, String> razonSocialAct;
    public static volatile SingularAttribute<Actor, String> nombresAct;
    public static volatile SingularAttribute<Actor, String> tipoAct;
    public static volatile SingularAttribute<Actor, String> apellidosAct;
    public static volatile SingularAttribute<Actor, String> calSecAct;
    public static volatile SingularAttribute<Actor, String> actividadAct;
    public static volatile SingularAttribute<Actor, String> nombreComercialAct;
    public static volatile SingularAttribute<Actor, String> urlSViewAct;
    public static volatile SingularAttribute<Actor, String> sectorAct;
    public static volatile SingularAttribute<Actor, String> longitudAct;
    public static volatile ListAttribute<Actor, ActorReferencia> actorReferencias;
    public static volatile SingularAttribute<Actor, String> catId;
    public static volatile SingularAttribute<Actor, String> calPrinAct;
    public static volatile SingularAttribute<Actor, Integer> rankingAct;
    public static volatile ListAttribute<Actor, ActorRol> actorRols;
    public static volatile SingularAttribute<Actor, String> estadoAct;
    public static volatile SingularAttribute<Actor, String> cedrucpasAct;
    public static volatile SingularAttribute<Actor, Date> fecCreaAct;

}