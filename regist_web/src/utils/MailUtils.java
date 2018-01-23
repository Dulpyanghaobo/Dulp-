package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.search.RecipientStringTerm;

public class MailUtils {
	/*
	 * �����ʼ��ķ���
	 * @param to:��˭���ʼ�
	 * @param code:�ʼ��ļ�����
	 * */
public static void sendMail(String to,String code) throws AddressException, MessagingException {
	//1.�������Ӷ������ӵ����������
	Properties props = new Properties();
	Session session = Session.getInstance(props, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("service@dulp.com", "123");
		}
	
	
	});
	//2.����һ���ʼ�����
	Message mess = new MimeMessage(session);
	//2.1���÷�����:
	mess.setFrom(new InternetAddress("service@dulp.com"));
	//2.2�����ռ���:
	mess.setRecipient(RecipientType.TO,new InternetAddress(to));
	//2.3�����ʼ�������
	mess.setSubject("�����ʼ���ӭ����Dulp world");
	//2.4.����һ�⼤���ʼ�
	mess.setContent("<h1>����Dulp��վ�ļ����ʼ�,������������:</h1><h3><a href='http://localhost:8080/regist_web/ActiveServlet?code="+code+"'>http://localhost:8080/regist_web/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=utf-8");
	//3.����һ�⼤���ʼ�
	Transport.send(mess);
}
}
