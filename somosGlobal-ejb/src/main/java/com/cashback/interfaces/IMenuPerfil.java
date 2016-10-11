package com.cashback.interfaces;

import java.util.List;

import com.cashback.model.Menu;
import com.cashback.model.Perfil;

public interface IMenuPerfil {
	List<Menu> recuperarMenuList(Perfil perfil);

	Menu recuperarMenu(Perfil perfil, Menu menu);
}
