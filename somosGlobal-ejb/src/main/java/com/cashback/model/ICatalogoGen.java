package com.cashback.model;

import java.util.List;

public interface ICatalogoGen {

	List<CatalogoGen> recuperarCatalogoGenList(String tipoCg);

	CatalogoGen recuperarCatalogoGen(String tipoCg, String refCg);
	
	List<CatalogoGen> recuperarCatalogoGenList(String tipoCg, String ref02Cg);
	
	CatalogoGen recuperarCatalogoGen(int idCg);
	
	CatalogoGen findByTipoCg(String tipoCg);
	
	List<CatalogoGen> recuperarCatalogoGenList02(CatalogoGen catalogoGen, String tipoCg);
	
	List<CatalogoGen> findAllByFather(CatalogoGen padreCatalogoGen);
	
	
}
