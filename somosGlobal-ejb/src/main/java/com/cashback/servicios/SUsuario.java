package com.cashback.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cashback.enums.AppMensajes;
import com.cashback.excepciones.ExcGuardarRegistro;
import com.cashback.interfaces.IUsuario;
import com.cashback.model.Usuario;

@Stateless
public class SUsuario extends AbstractService implements IUsuario {

	@Override
	public Usuario actualizarUsuario(Usuario usuario) throws ExcGuardarRegistro {
		if (emCashback.find(Usuario.class, usuario.getUsrId()) == null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_NO_EXISTE,
					"Usuario");
		}
		try {
			usuario.setUsrFecMod(new Date());
			return emCashback.merge(usuario);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcGuardarRegistro(AppMensajes.ERR_GUARDAR_REGISTRO,
					"Usuario");
		}
	}

	@Override
	public boolean crearUsuario(Usuario usuario) throws ExcGuardarRegistro {
		if (recuperarUsuario(usuario.getUsrNombre()) != null) {
			throw new ExcGuardarRegistro(AppMensajes.ERR_REGISTRO_YA_EXISTE,
					"Usuario");
		}
		usuario.setUsrFecCrea(new Date());
		emCashback.persist(usuario);
		return true;
	}

	@Override
	public List<Usuario> recuperarUsuarioList(String usrNombre) {
		String jpql = "SELECT u FROM Usuario u WHERE u.usrNombre LIKE :usrNombre";
		Query q = emCashback.createQuery(jpql);
		q.setParameter("usrNombre", "%" + usrNombre + "%");
		@SuppressWarnings("unchecked")
		List<Usuario> usuarioList = (List<Usuario>) q.getResultList();
		return usuarioList;
	}

	@Override
	public Usuario recuperarUsuario(String usrNombre, String usrPassword) {
		usrPassword = new Usuario().passwordToMd5(usrPassword);
		String jql = "SELECT u FROM Usuario u WHERE u.usrNombre =:usrNombre"
				+ " AND u.usrPassword =:usrPassword";
		Query q = emCashback.createQuery(jql);
		q.setParameter("usrNombre", usrNombre);
		q.setParameter("usrPassword", usrPassword);
		try {
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public Usuario recuperarUsuario(String usrNombre) {
		Query q = emCashback
				.createQuery("SELECT u FROM Usuario u WHERE u.usrNombre =:usrNombre");
		q.setParameter("usrNombre", usrNombre);
		try {
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
