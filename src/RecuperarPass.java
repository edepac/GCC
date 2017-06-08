import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarPass {

	public static void EnviarCorreo(String correo,String contraseñaDevuelta){
		{
			try
			{
			// Aqui estan las propiedades de la conexion
			Properties props = new Properties();
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			//mediante el smtp de gmail
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			//validamos
			props.setProperty("mail.smtp.starttls.enable", "true");
			//puerto mediante el que realizamos la conexion smtp
			props.setProperty("mail.smtp.port", "587");
			//correo desde el que enviaremos el mensaje
			props.setProperty("mail.smtp.user", "gcc.inso@gmail.com");
			//validadamos la entrada al correo
			props.setProperty("mail.smtp.auth", "true");
			// Nos preparamos para realizar la conexion
			Session session = Session.getDefaultInstance(props);
			// Buildeamos el correo
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(//aqui añadmos el usuario al que le mandaremos el correo
			Message.RecipientType.TO,new InternetAddress(correo));
			message.setSubject("Recuperación de contraseña.");
			message.setText("Su contraseña en GCC es: "+contraseñaDevuelta+ "\n"+ "Esperamos que disfrute de nuestros servicios.");

				
				

			Transport t = session.getTransport("smtp");
			//validamos el user y la pass de nuestro correo
			t.connect("gcc.inso@gmail.com", "hlrdwtvmpjvrysnm");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			}
			catch (Exception e)
			{
			e.printStackTrace();
			}
			}
		
		
		
		
		
	}
	
}
