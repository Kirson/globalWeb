package com.cashback.model;

import com.cashback.model.LocalVenta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-09T19:33:41")
@StaticMetamodel(HorarioAtencion.class)
public class HorarioAtencion_ { 

    public static volatile SingularAttribute<HorarioAtencion, String> haEstado;
    public static volatile SingularAttribute<HorarioAtencion, Integer> haId;
    public static volatile SingularAttribute<HorarioAtencion, String> haApertura;
    public static volatile SingularAttribute<HorarioAtencion, String> haDia;
    public static volatile SingularAttribute<HorarioAtencion, String> haCierre;
    public static volatile SingularAttribute<HorarioAtencion, LocalVenta> localVenta;

}