package com.cashback.servicios;

import javax.ejb.Stateless;

import com.cashback.interfaces.IParametrosGen;
import com.cashback.model.ParametrosGen;
@Stateless
public class SParametrosGen extends AbstractService implements IParametrosGen {

	@Override
	public ParametrosGen recuperarParamentrosGen(String parCodigo) {
		return emCashback.find(ParametrosGen.class, parCodigo);
	}
}
