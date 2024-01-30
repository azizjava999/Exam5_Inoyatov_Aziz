package org.example.service.impl;

import org.example.domain.User;
import org.example.service.UserService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import static javax.mail.Session.*;

public class UserServiceImpl implements UserService {
    static {
        User user = User.builder().userName("Aziz").password("aaa").build();
        USERS.add(user);
    }

    @Override
    public boolean register(String email) {
        try {
            confirm(email);
        }catch (Exception e) {
            throw new RuntimeException("Something went wrong!");
        }
        return true;
    }

    @Override
    public boolean login(String name, String password) {
        for(User user:USERS) {
            if(user.getUserName().equals(name) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean confirm(String gmail) throws MessagingException, InterruptedException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String username = "a88840934@gmail.com";
        String password = "unsdxperucyjljpl";

        Session session = getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Double confirmation = 100000 + Math.random() + 999999;

        sendEmail(gmail, session,confirmation);
        System.out.println("Success");
        return true;
    }

    @Override
    public boolean sendEmail(String email, Session session, Double code) throws MessagingException, InterruptedException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("a88840934@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Confirmation code");

        Double conf = 100000 + Math.random() + 999999;
        Thread.sleep(4000);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(conf, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        return conf.equals(code);
    }
}
