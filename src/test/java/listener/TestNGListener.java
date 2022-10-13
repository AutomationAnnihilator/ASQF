package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.nio.file.Path;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TestNGListener implements ITestListener {
    @Override
    public void onFinish(ITestContext context) {
        sendEmailNotifications();
    }

    /**
     Outgoing Mail (SMTP) Server
     requires TLS or SSL: smtp.gmail.com (use authentication)
     Use Authentication: Yes
     Port for TLS/STARTTLS: 587
     */
    public static void sendEmailNotifications() {
        final String fromEmail = "testautomationdriven@gmail.com"; //requires valid gmail id
        final String password = "vbyqdxeetzpshyja"; // correct password for gmail id
        final String toEmail = "aditya.rawat@nagarro.com"; // can be any email id

        String body = "Hi,\n" +
                "\n" +
                "Please find the ASQF test automation report attached.\n" +
                "\n" +
                "Thanks,\n" +
                "Automation team\n" +
                "\n" +
                "Note: This is an automatic email please do not reply.\n";


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true"); //enable authentication

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        sendAttachmentEmail(session,toEmail,"ASQF",body);

    }

    public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body){
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", ""));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(body);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();


            DataSource source = new FileDataSource("./Reports/extentreport.html");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("ASQF Report");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            msg.setContent(multipart);

            // Send message
            Transport.send(msg);
            System.out.println("EMail Sent Successfully with attachment!!");
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
