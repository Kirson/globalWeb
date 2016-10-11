package com.cashback.model;

import java.util.List;

public interface ILocalidad {

	List<Localidad> recuperarLocalidad(Localidad localidad);

	Localidad recuperarLocalidad(int locId);
}
