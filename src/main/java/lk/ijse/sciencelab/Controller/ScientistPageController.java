package lk.ijse.sciencelab.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.ScientistDto;
import lk.ijse.sciencelab.model.Employeemodel;
import lk.ijse.sciencelab.model.Scientistmodel;
import lk.ijse.sciencelab.util.Regex;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ScientistPageController{
    private final Scientistmodel Scmodel = new Scientistmodel();
    private final Employeemodel employeemodel = new Employeemodel();
    public Button btnGenarateReport;
    public TextField txtContact;
    public TableColumn Contactclm;
    public TableColumn Specializaionclm;
    public TableColumn Employeeclm;
    public TableColumn ScientistNameclm;
    public TableColumn ScientistIDclm;
    public TableView tblScientist;
    public Label lblScientistID;
    public Button btnReset;
    public Button btnUpdate;
    public ImageView btnDelete;
    public TextField txtScientistName;
    public Button btnSave;
    public TextField txtspecialization;
    public ComboBox <String> ComboBoxEmployee;
    public ComboBox <String>ComboBoxSpecialization;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxEmployee.setItems(employeemodel.getAllEmployeeId());
        ComboBoxSpecialization.setItems(FXCollections.observableArrayList( "Biologist","Biologist","Physicist","Microbiologist","Geneticist","Biochemist","Pharmacologist","Nanotechnologist","Immunologist","Materials Scientist","Neuroscientist"));
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Scientistmodel.getText();
        lblScientistID.setText(nextID);
    }

    private void setcellvaluefactory() {
        ScientistIDclm.setCellValueFactory(new PropertyValueFactory<>("scientistId"));
        ScientistNameclm.setCellValueFactory(new PropertyValueFactory<>("scientistName"));
        Contactclm.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Employeeclm.setCellValueFactory(new PropertyValueFactory<>("employee"));
        Specializaionclm.setCellValueFactory(new PropertyValueFactory<>("specialization"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ScientistDto> scientist = Scmodel.getAll();

        ObservableList<ScientistDto> scientistDtoObservableList = FXCollections.observableArrayList();
        for (ScientistDto s : scientist) {
            scientistDtoObservableList.add(s);
        }
        tblScientist.setItems(scientistDtoObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        ScientistDto selectedItem = (ScientistDto) tblScientist.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblScientistID.setText(selectedItem.getScientistId());
            txtScientistName.setText(selectedItem.getScientistName());
            txtContact.setText(selectedItem.getContact());
            ComboBoxEmployee.setValue(selectedItem.getEmployee());
            ComboBoxSpecialization.setValue(selectedItem.getSpecialization());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
if (isValied()){
    String scientistID = lblScientistID.getText();
    String scientistName = txtScientistName.getText();
    String contact = txtContact.getText();
    String employee = ComboBoxEmployee.getValue();
    String specialization = ComboBoxSpecialization.getValue();

    ScientistDto scientist = new ScientistDto(scientistID, scientistName, contact, employee, specialization);
    boolean issave = Scmodel.save(scientist);

    if (issave) {
        new Alert(Alert.AlertType.INFORMATION, "Scientist Saved", ButtonType.OK).show();
        loadtable();
    } else {
        new Alert(Alert.AlertType.ERROR, "Scientist NotSaved", ButtonType.OK).show();
    }
}
    }

    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        txtScientistName.setText("");
        txtContact.setText("");
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String scientistID = lblScientistID.getText();
        String scientistName = txtScientistName.getText();
        String contact = txtContact.getText();
        String employee = ComboBoxEmployee.getValue();
        String specialization = ComboBoxSpecialization.getValue();

        ScientistDto scientist = new ScientistDto(scientistID, scientistName, contact, employee, specialization);
        boolean isupdate = Scmodel.update(scientist);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Scientist Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Scientist NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String scientistID = lblScientistID.getText();

        // Check if a scientist ID is selected
        if (scientistID == null || scientistID.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a scientist to delete.", ButtonType.OK).show();
            return;
        }

        // Confirmation popup
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this scientist? This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Proceed with deletion
            boolean isDelete = Scmodel.DeleteScientist(scientistID);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Scientist Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Scientist Not Deleted", ButtonType.OK).show();
            }
        } else {
            // User cancelled
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled", ButtonType.OK).show();
        }
    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }
    private boolean isValied() {
    if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtScientistName)) return false;
    if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.CONTACT,txtContact)) return false;
    return true;
    }

    @FXML
    void txtScientistContactKeyRelease(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.CONTACT,txtContact);
    }

    @FXML
    void txtScientistNameKeyRelease(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtScientistName);
    }

}

