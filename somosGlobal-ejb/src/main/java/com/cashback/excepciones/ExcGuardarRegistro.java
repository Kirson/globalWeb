package com.cashback.excepciones;

import javax.ejb.ApplicationException;

import com.cashback.enums.AppMensajes;

@ApplicationException(rollback = true)
public class ExcGuardarRegistro extends Exception {

	private static final long serialVersionUID = 1L;
	private String messageCode;
	private String messageText;

	public ExcGuardarRegistro(AppMensajes appmensajes) {
		super();
		this.messageCode = appmensajes.getMessageCode();
	}

	public ExcGuardarRegistro(AppMensajes appmensajes, String messageText) {
		super();
		this.messageCode = appmensajes.getMessageCode();
		this.messageText = messageText;
	}
	public String getMessageCode() {
		return messageCode;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}
