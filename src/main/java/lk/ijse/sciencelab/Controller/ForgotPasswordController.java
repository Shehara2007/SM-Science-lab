package lk.ijse.sciencelab.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sciencelab.model.ForgotPasswordmodel;


public class ForgotPasswordController {

    public AnchorPane ancPanel;
    @FXML
    private TextField txtUsernameOrEmail;

    @FXML
    private Label lblMessage;

    private ForgotPasswordmodel model = new ForgotPasswordmodel();

    @FXML
    private void onSendResetLink() {
        String input = txtUsernameOrEmail.getText().trim();

        if (input.isEmpty()) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Please enter your username or email.");
            return;
        }

        boolean isSent = model.sendResetLink(input);

        if (isSent) {
            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText("Reset link sent! Check your email.");
        } else {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("User not found or email error.");
        }
    }
    private void nevigateTo(String s) {
        try {
            ancPanel.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ancPanel.widthProperty());
            pane.prefHeightProperty().bind(ancPanel.heightProperty());

            ancPanel.getChildren().add(pane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page Not Found!").show();
            e.printStackTrace();

        }
    }
    public void btnBacktoLogin(ActionEvent actionEvent) {
       nevigateTo("/view/Login.fxml");
    }

}
