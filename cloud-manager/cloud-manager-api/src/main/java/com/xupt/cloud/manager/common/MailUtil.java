package com.xupt.cloud.manager.common;

import com.xupt.cloud.manager.domain.vo.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by baihuaiyu on 2018/5/3
 */
public class MailUtil {

    public static void sendMail(User user, String title, String info) throws Exception{
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.sina.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");

        Session mailSession = Session.getInstance(properties);
        mailSession.setDebug(true);
        Transport ts = mailSession.getTransport();
        String mail_username = "xuptcal";
        String mail_password = "xuptb103";
        ts.connect(mail_username, mail_password);
        Message message = createEmail(mailSession, user, title, info);
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    private static Message createEmail(Session session, User user, String title, String info) throws Exception{
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("xuptcal@sina.com"));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        mimeMessage.setSubject(title);
        mimeMessage.setContent(info, "text/html;charset=UTF-8");
        mimeMessage.saveChanges();
        return mimeMessage;
    }

}
