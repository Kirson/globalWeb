package com.cashback.model;

import com.cashback.model.MenuPerfil;
import com.cashback.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, Integer> prfId;
    public static volatile SingularAttribute<Perfil, String> prfEstado;
    public static volatile ListAttribute<Perfil, Usuario> usuarioList;
    public static volatile ListAttribute<Perfil, MenuPerfil> menuPerfils;
    public static volatile SingularAttribute<Perfil, String> prfNombre;
    public static volatile SingularAttribute<Perfil, String> prfCodigo;

}