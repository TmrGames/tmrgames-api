package com.tmr.tomoapi.utils;

import lombok.SneakyThrows;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    private static Session createSession() {
        String userName = "xinwang@tmrgames.io";//登录名
        String password = "ptac uins gnnh yiem";//登陆密码

        Properties pros = new Properties();
        pros.setProperty("proxySet", "true");
        pros.setProperty("socksProxyHost", "127.0.0.1");
        pros.setProperty("socksProxyPort", "7890");
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port", "587");
        pros.put("mail.transport.protocol", "smtp");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(pros, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(userName, password);
            }
        });
        System.out.println(session);
        session.setDebug(true);
        return session;
    }

    @SneakyThrows
    public static void send(String subject, String text, String to) {
        Session session = createSession();
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setContent(text, "text/html;charset=utf-8");
        //message.setText(text, "utf-8");
        message.setFrom(new InternetAddress("xinwang@tmrgames.io"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        Transport.send(message);
    }

    public static void main(String[] args) {
        send("a11a", "111", "ipanel@163.com");
    }
}
