package com.cashback.interfaces;

import java.util.List;

import com.cashback.model.Licencia;

public interface ILicencia {
	List<Licencia> recuperarLicenciaList();
	Licencia recuperarLicencia(int licId);
}
