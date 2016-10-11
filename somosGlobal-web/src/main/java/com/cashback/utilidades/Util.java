/*  
 * Util.java  
 * Creado el 3 de noviembre de 2005, 04:37 PM  
 */
package com.cashback.utilidades;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * T�tulo: Util Descripci�n: Varias utilidades Copyright: Copyright (c) 2005 -
 * GPL License. Este archivo se distribuye bajo licencia GPL de la Free Software
 * Foundation GNU.
 * 
 * @author Luis Antonio Burbano.
 * @version 0.1
 */
public class Util {
	/**
	 * Numero de Provincias del Ecuador
	 */

	public static final int NUMERO_DE_PROVINCIAS = 24;// 22;

	/**
	 * Este m�todo permite verificar si una c�dula de identidad es verdadera
	 * retorna true si es v�lida, caso contrario retorna false.
	 * 
	 * @param cedula
	 *            C�dula de Identidad Ecuatoriana de 10 digitos.
	 * @return Si es verdadera true, si es falsa false
	 */

	public static boolean esCedulaValida(String cedula) {
		// verifica que tenga 10 d�gitos y que contenga solo valores num�ricos
		if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
			return false;
		}

		// verifica que los dos primeros d�gitos correspondan a un valor entre 1
		// y NUMERO_DE_PROVINCIAS

		int prov = Integer.parseInt(cedula.substring(0, 2));

		if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {

			return false;

		}

		// verifica que el �ltimo d�gito de la c�dula sea v�lido

		int[] d = new int[10];

		// Asignamos el string a un array

		for (int i = 0; i < d.length; i++) {

			d[i] = Integer.parseInt(cedula.charAt(i) + "");

		}

		int imp = 0;

		int par = 0;

		// sumamos los duplos de posici�n impar

		for (int i = 0; i < d.length; i += 2) {

			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);

			imp += d[i];

		}

		// sumamos los digitos de posici�n par

		for (int i = 1; i < (d.length - 1); i += 2) {

			par += d[i];

		}

		// Sumamos los dos resultados

		int suma = imp + par;

		// Restamos de la decena superior

		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) +

		"0") - suma;

		// Si es diez el d�cimo d�gito es cero

		d10 = (d10 == 10) ? 0 : d10;

		// si el d�cimo d�gito calculado es igual al digitado la c�dula es
		// correcta

		return d10 == d[9];

	}

	public static boolean esRucValidoPublico(String cedula) {
		int suma;
		int prov = Integer.parseInt(cedula.substring(0, 2));
		int residuo, digitoVerificador;
		boolean pri = false, pub = false;

		if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
			return false;
		}

		Integer d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
		Integer p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0, p8 = 0, p9 = 0;
		d1 = Integer.parseInt(cedula.substring(0, 1));
		d2 = Integer.parseInt(cedula.substring(1, 2));
		d3 = Integer.parseInt(cedula.substring(2, 3));
		d4 = Integer.parseInt(cedula.substring(3, 4));
		d5 = Integer.parseInt(cedula.substring(4, 5));
		d6 = Integer.parseInt(cedula.substring(5, 6));
		d7 = Integer.parseInt(cedula.substring(6, 7));
		d8 = Integer.parseInt(cedula.substring(7, 8));
		d9 = Integer.parseInt(cedula.substring(8, 9));
		d10 = Integer.parseInt(cedula.substring(9, 10));

		int intPos3 = Integer.parseInt(cedula.substring(2, 3));
		if (intPos3 == 6) {
			pub = true;
			p1 = d1 * 3;
			p2 = d2 * 2;
			p3 = d3 * 7;
			p4 = d4 * 6;
			p5 = d5 * 5;
			p6 = d6 * 4;
			p7 = d7 * 3;
			p8 = d8 * 2;
			p9 = 0;
		}
		if (intPos3 == 9) {
			pri = true;
			p1 = d1 * 4;
			p2 = d2 * 3;
			p3 = d3 * 2;
			p4 = d4 * 7;
			p5 = d5 * 6;
			p6 = d6 * 5;
			p7 = d7 * 4;
			p8 = d8 * 3;
			p9 = d9 * 2;
		}

		suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
		residuo = suma % 11;

		if (residuo == 0) {
			digitoVerificador = 0;
		} else {
			digitoVerificador = 11 - residuo;
		}

		if (pub) {
			if (digitoVerificador != d9) {
				return false;
			}

			if (cedula.substring(9, 13).compareTo("0001") != 0) {
				return false;
			}
		}
		if (pri) {
			if (digitoVerificador != d10) {
				return false;
			}

			if (cedula.substring(10, 13).compareTo("001") != 0) {
				return false;
			}
		}
		return true;
	}
	
	 public static void enviarCorreoHtml(List<String> paraList, String de, String asunto,  String contenidoHTML) {
		    // La direcci�n de env�o (to)
		    // para = "csolano@asertec.com.ec";

		    // La direcci�n de la cuenta de env�o (from)
		   // de = "csolano@asertec.com.ec";

		    // El servidor (host). En este caso usamos localhost
		    String host = "192.168.1.59";

		    // Obtenemos las propiedades del sistema
		    Properties propiedades = System.getProperties();

		    // Configuramos el servidor de correo
		    propiedades.setProperty("mail.smtp.host", host);

		    // Obtenemos la sesi�n por defecto
		    Session sesion = Session.getDefaultInstance(propiedades);

		    try{
		      // Creamos un objeto mensaje tipo MimeMessage por defecto.
		      MimeMessage mensaje = new MimeMessage(sesion);

		      // Asignamos el �de o from� al header del correo.
		      mensaje.setFrom(new InternetAddress(de));

		      // Asignamos el �para o to� al header del correo.
		      for(String para : paraList){
		    	  mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
			}
		      
		      
		      // Asignamos el asunto
		      mensaje.setSubject(asunto);

		      // Asignamos el mensaje como tal
		      //mensaje.setText("El mensaje de nuestro primer correo");
		      // Asignamos el contenido HTML, tan grande como nosotros queramos
		      mensaje.setContent(contenidoHTML,"text/html" );


		      // Enviamos el correo
		      Transport.send(mensaje);
		      System.out.println("Mensaje enviado");
		    } catch (MessagingException e) {
		      e.printStackTrace();
		    }
		  }
	

public class EnviadorMail {
    final String miCorreo = "ferxell123@gmail.com";
    final String miContrasena = "321cba321cba";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    String mailReceptor = null;
    String asunto = null;
    String cuerpo = null;

    public EnviadorMail(String mailReceptor, String asunto,
            String cuerpo) {
        this.mailReceptor = mailReceptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;

        Properties props = new Properties();
        props.put("mail.smtp.user", miCorreo);
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoEnvio);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", puertoEnvio);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new autentificadorSMTP();
            Session session = Session.getInstance(props, auth);
            // session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(cuerpo);
            msg.setSubject(asunto);
            msg.setFrom(new InternetAddress(miCorreo));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    mailReceptor));
            Transport.send(msg);
        } catch (Exception mex) {
            mex.printStackTrace();
        }

    }

    private class autentificadorSMTP extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContrasena);
        }
    }

    /**
     * @param args
     */
   
  
}
}