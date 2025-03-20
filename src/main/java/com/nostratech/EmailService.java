package com.nostratech;

import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class EmailService {
    // private String username;
    // private String password;

    private final Session session;

    // private final Properties prop;

    public EmailService(Session session) {
        super();
        this.session = session;

        // We move to ApplicationContext.xml
        // prop = new Properties();
        // prop.put("mail.smtp.auth", true);
        // prop.put("mail.smtp.starttls.enable", "true");
        // prop.put("mail.smtp.host", host);
        // prop.put("mail.smtp.port", port);
        // prop.put("mail.smtp.ssl.trust", host);

        // We move to ApplicationContext.xml
        // this.username = username;
        // this.password = password;
    }

    public void sendMail() throws Exception {

        // We move in Annotation Base or Java Config
        // Session session = getSession();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
        message.setSubject("Mail Subject");
        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }


    // We move to ApplicationContext.xml
    // private Session getSession() {
    //     Session session = Session.getInstance(prop, new Authenticator() {
    //         @Override
    //         protected PasswordAuthentication getPasswordAuthentication() {
    //             return new PasswordAuthentication(username, password);
    //         }
    //     });
    //     return session;
    // }
}
