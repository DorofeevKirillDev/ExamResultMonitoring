import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailNotificator implements Notificator{
    private Properties props;
    private Session session;
    private String username;

    /**
     * Sets default Rambler SMTP parameters (host - smtp.rambler.ru, port - 465)
     */
    public void setRamblerSMTPProperties() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.rambler.ru"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
    }

    public void authenticate(String username, String password) {
        this.username = username;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     *
     * @param recipient might be a comma separated value, e.g. "res1@gmail.com,res2@gmail.com"
     * @param textMessage text message that will be sent
     * @return true if message was sent, false if wasn't
     */
    @Override
    public boolean sendNotification(String recipient, String textMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setText(textMessage);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
