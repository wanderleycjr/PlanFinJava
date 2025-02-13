package com.pcmoney.util;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class EnviaEmailConfig {

    // Configurações de servidor SMTP
    private final String host = "mail.pcmoney.com.br";
    private final String port = "465";
    private final String username = "naoresponda@pcmoney.com.br";  // seu email
    private final String password = "pdhc yujc ecla tbjc";  // sua senha

    public void sendEmail(String recipient, String subject, String body) throws MessagingException {
        // Configurações de propriedades do servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");  // Usar SSL

        // Criar uma sessão de e-mail autenticada
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        // Criar a mensagem de e-mail
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);


        // Enviar a mensagem
        Transport.send(message);
    }
}


