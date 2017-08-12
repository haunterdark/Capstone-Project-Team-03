package services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {

	final String username = "vanlang@webgiasu.com";
	final String password = "Admin-123";
		
	public void sendMail(String email,String phone,String name ) {
		

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", "mail.webgiasu.com");
		
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vanlang@webgiasu.com"));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("trandaithanh258@gmail.com"));
			message.setSubject("New register ! ");
			message.setText("Dear ," + "\n\n You have a new register!"
					+ "\n\n Phone: "+ phone+ "\n\n Name: "+ name + "\n\n Email: "+email );

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
public void sendMail1(String uname, String mail) {
		
        
        
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", "mail.webgiasu.com");
		
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vanlang@webgiasu.com"));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mail));
			message.setSubject("New register ! ");
			message.setText("Dear ," + "\n\n Tài khoản của bạn đã được tạo."
					+ "\n\n Username: "+ uname+ "\n\n Password: "+ "123456" + "\n\n "+"Vui lòng đổi mật khẩu sau khi đăng nhập để đảm bảo an toàn thông tin. Cảm ơn." );

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}