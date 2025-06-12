package lk.ijse.sciencelab.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sciencelab.Appinitializer;

import java.io.IOException;

public class DashBordController {

    public Label labelTotalEmployees;
    public Label labelWorkersCount;
    public Label labelTotalEquipments;
    public Label labelSuppliersCount;
    public Label labelTotalChemicals;
    public Label labelTotalProjects;
    @FXML
    private AnchorPane ancDash;

    @FXML
    private AnchorPane ancPanel;

    @FXML
    private ImageView imgMain;

    @FXML
    private Label lblScienceLab;

    @FXML
    void btnChemicalOnAction(ActionEvent event) {
        nevigateTo("/view/Chemical.fxml");

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        nevigateTo("/view/Employee.fxml");

    }

    @FXML
    void btnEquipmentOnAction(ActionEvent event) {
        nevigateTo("/view/Equipment.fxml");

    }

    @FXML
    void btnFunderOnAction(ActionEvent event) {
        nevigateTo("/view/Funder.fxml");

    }

    @FXML
    void btnGroupOnAction(ActionEvent event) {
        nevigateTo("/view/Group.fxml");

    }

    @FXML
    void btnProgressOnAction(ActionEvent event) {
        nevigateTo("/view/Progress.fxml");

    }

    @FXML
    void btnProjectOnAction(ActionEvent event) {
        nevigateTo("/view/Project.fxml");

    }

    @FXML
    void btnScientistOnAction(ActionEvent event) {
        nevigateTo("/view/Scientist.fxml");

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        nevigateTo("/view/Supplier.fxml");

    }

    public void btnSetingsOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/Settings.fxml");
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

    public void BacktologinOnAction(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Appinitializer.class.getResource("/view/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) lblScienceLab.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


