package com.cashback.model;

import com.cashback.model.Actor;
import com.cashback.model.Perfil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usrNombre;
    public static volatile SingularAttribute<Usuario, Actor> actor;
    public static volatile SingularAttribute<Usuario, String> usrModPor;
    public static volatile SingularAttribute<Usuario, Date> usrFecMod;
    public static volatile SingularAttribute<Usuario, String> usrEstado;
    public static volatile SingularAttribute<Usuario, String> usrNombre2;
    public static volatile SingularAttribute<Usuario, Integer> usrId;
    public static volatile SingularAttribute<Usuario, String> usrCreadoPor;
    public static volatile SingularAttribute<Usuario, String> usrCambiaPassword;
    public static volatile SingularAttribute<Usuario, String> usrPassword;
    public static volatile SingularAttribute<Usuario, Date> usrFecCrea;
    public static volatile SingularAttribute<Usuario, Perfil> perfil;

}