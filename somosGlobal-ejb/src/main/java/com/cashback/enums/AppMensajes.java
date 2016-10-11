package com.cashback.enums;

public enum AppMensajes {
	INF_OPERACION_EXITO("INF0001"), ERR_CREAR_REGISTRO("ERR0001"), ERR_GUARDAR_REGISTRO(
			"ERR0002"), ERR_REGISTRO_NO_EXISTE("ERR0003"), ERR_REGISTRO_YA_EXISTE(
			"ERR0004"), INF_NO_RESULTADOS("INF0002"), INF_ELIMINAR_REGISTRO(
			"INF0003");
	private String messageCode;

	private AppMensajes(String s) {
		messageCode = s;
	}

	public String getMessageCode() {
		return messageCode;
	}

}
