package lk.ijse.sciencelab.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class EMailFromController {

    @FXML
    private TextField txtSend;

    @FXML
    private TextField txtSub;

    @FXML
    private TextArea txtEmail;


    @FXML
    private AnchorPane EmailFrom;

    private void sendMail(String to, String subject, String text) {
        final String username = "sheharamalithi1@gmail.com";
        final String apppassword = "wpyhoqczymuavyiw";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, apppassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sheharamalithi1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }


        @FXML
        void SendButtonAc(ActionEvent event) {
            sendMail(txtSend.getText(), txtSub.getText(), txtEmail.getText());
        }

    @FXML
    void closeAction(ActionEvent  event) {
        close();
    }

    private void close() {
        Stage stage = (Stage) EmailFrom.getScene().getWindow();
        stage.close();
    }
    }
