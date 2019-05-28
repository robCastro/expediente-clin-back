package bad.xcl.models.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MensajeServiceImpl implements IMensajeService {
	/*
	 * Basado en 
	 * 
	 * https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
	 * 
	 * Ademas del tuto, dentro la cuenta Gmail permitir acceso de apps poco seguras
	 * 
	 * https://www.google.com/settings/security/lesssecureapps
	 * 
	 * */
	
	@Override
	public boolean sendMsj(String mensaje, String asunto, String destino) {
		boolean resultado = false;
		String remitente = "sistema.xcl.g11@gmail.com";
		String password = "Admin123$";
		//Properties props = System.getProperties();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google.
		//props.put("mail.smtp.host", "aspmx.l.google.com");
		//props.put("mail.smtp.user", remitente);
		//props.put("mail.smtp.clave", "Admin123$");    //La clave de la cuenta.
		props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave.
		props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP.
		props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google.
		//props.put("mail.smtp.port", "25");
		//props.put("mail.smtp.ssl.trust", "*"); 
		//props.put("mail.smtp.connectiontimeout", "10000");
		//Session session = Session.getDefaultInstance(props);
		Session session = Session.getInstance(props, 
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(remitente, password);
					}
		});
		MimeMessage message = new MimeMessage(session);
		try {
	        	message.setFrom(new InternetAddress(remitente));
		        //message.addRecipients(Message.RecipientType.TO,destino);
		        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
	        	message.setSubject(asunto);
	        	message.setText(mensaje);
	        	//Transport transport = session.getTransport("smtp");
	        	//transport.connect("smtp.gmail.com", remitente, "Sistema XCL: Expediente Clínico en Línea");
	        	//transport.sendMessage(message, message.getAllRecipients());
	        	//transport.close();
	        	Transport.send(message);
			resultado = true;
		}
	    	catch (MessagingException me) {
	               System.out.println("Error: "+me.getMessage());   //Si se produce un error.
	    	}
		return resultado;
	}
	
}
