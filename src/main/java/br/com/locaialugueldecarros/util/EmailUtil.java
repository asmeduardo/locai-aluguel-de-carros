package br.com.locaialugueldecarros.util;

import br.com.locaialugueldecarros.car_rental.model.entities.Messages;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;


public class EmailUtil {

    private static final String FROM_EMAIL = "seu_email_de_remetente_aqui";
    private static final String FROM_EMAIL_PASSWORD = "sua_senha_de_aplicativo_aqui";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    private static Session createSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_EMAIL_PASSWORD);
            }
        });
    }

    public static boolean sendConfirmationEmail(String email) {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("E-mail de confirmação");
            message.setText("Parabéns!\n\n" + "Sua conta foi criada com sucesso na nossa plataforma. Agora você " +
                    "pode aproveitar todos os nossos recursos e ter acesso a uma ampla gama de opções de locação de veículos.\n"
                    + "Obrigado por escolher nosso serviço. Caso tenha alguma dúvida, não hesite em entrar em contato conosco. " +
                    "Estamos à disposição para ajudá-lo em tudo o que precisar.\n" + "Agradecemos sua preferência.\n" +
                    "Equipe LocAí - Aluguel de Carros.");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean sendPasswordRecoveryEmail(String email, String currentPassword) {
        try {
            MimeMessage message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Recuperação de Senha");
            message.setText("Você solicitou a recuperação de sua senha.\nSua senha atual é: " + currentPassword);

            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    public static void sendContactMessage(Messages msg) {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(FROM_EMAIL));
            message.setSubject("Mensagem do Usuário");
            message.setText("Nome: " + msg.getFirstName() + " "
                    + msg.getLastName() + "\n" +
                    "E-mail: " + msg.getEmail() + "\n" +
                    "Mensagem: " + msg.getMessage());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}