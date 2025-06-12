package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.sciencelab.Dto.EmployeeDto;
import lk.ijse.sciencelab.model.Employeemodel;
import lk.ijse.sciencelab.model.Groupmodel;
import lk.ijse.sciencelab.util.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeePageController {
    private final Employeemodel Emodel = new Employeemodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public Button btnUpdate;
    public TableColumn Emailclm;
    public TableColumn Groupidclm;
    public TableColumn Roleclm;
    public TableColumn Contactclm;
    public TableColumn EmployeeNameclm;
    public TableColumn Employeeidclm;
    public TableView tblEmployee;
    public TextField txtEmail;
    public TextField txtRole;
    public TextField txtContact;
    public TextField txtEmployeeName;
    public Label lblEmployeeID;
    public ComboBox ComboBoxGroupID;
    public ComboBox ComboBoxRole;


    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxRole.setItems(FXCollections.observableArrayList("Technician", " Lab Assistant", "Scientist", " Lab Manager", " Safety Officer", "Data Analyst / Bioinformatician", " Scientific Project Manager", " Histology Technician", " Lab IT Specialist", "Robotics Technician"));
        ComboBoxGroupID.setItems(Groupmodel.getAllGroupID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Employeemodel.getText();
        lblEmployeeID.setText(nextID);
    }

    private void setcellvaluefactory() {
        Employeeidclm.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        Roleclm.setCellValueFactory(new PropertyValueFactory<>("role"));
        EmployeeNameclm.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        Contactclm.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Groupidclm.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        Emailclm.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employee = (ArrayList<EmployeeDto>) Emodel.getAll();

        ObservableList<EmployeeDto> employeeObservableList = FXCollections.observableArrayList();
        for (EmployeeDto E : employee) {
            employeeObservableList.add(E);
        }
        tblEmployee.setItems(employeeObservableList);
    }

    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
  if (isValied()){
      String employeeID = lblEmployeeID.getText();
      String role = (String) ComboBoxRole.getValue();
      String employeeName = txtEmployeeName.getText();
      String contact = txtContact.getText();
      String groupID = (String) ComboBoxGroupID.getValue();
      String email = txtEmail.getText();


      EmployeeDto employee = new EmployeeDto(employeeID, role, employeeName, contact, groupID, email);
      boolean issave = Emodel.save(employee);

      if (issave) {
          new Alert(Alert.AlertType.INFORMATION, "Employee Saved", ButtonType.OK).show();
          loadtable();
      } else {
          new Alert(Alert.AlertType.ERROR, "Employee NotSaved", ButtonType.OK).show();
      }
  }

    }
    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        lblEmployeeID.setText("");
        ComboBoxRole.setValue("");
        txtEmployeeName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String employeeID = lblEmployeeID.getText();
        String role = (String) ComboBoxRole.getValue();
        String employeeName = txtEmployeeName.getText();
        String contact = txtContact.getText();
        String groupID = (String) ComboBoxGroupID.getValue();
        String email = txtEmail.getText();

        EmployeeDto employee = new EmployeeDto(employeeID, role, employeeName, contact, groupID, email);
        boolean isupdate = Emodel.update(employee);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeID = lblEmployeeID.getText();
        boolean isDelete = Emodel.DeleteEmployee(employeeID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Not Deleted", ButtonType.OK).show();
        }

    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        EmployeeDto selectedItem = (EmployeeDto) tblEmployee.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblEmployeeID.setText(selectedItem.getEmployeeId());
            ComboBoxRole.setValue( selectedItem.getRole());
            txtEmployeeName.setText(String.valueOf(selectedItem.getEmployeeName()));
            txtContact.setText(selectedItem.getContact());
            ComboBoxGroupID.setValue(selectedItem.getGroupId());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enableF
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void btnEmailFrom(ActionEvent event) {
            Scene scene = null;
            try {
                scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/EmailSend_form.fxml")));
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }

    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtEmployeeName)) return false;
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.CONTACT,txtContact)) return false;
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.EMAIL,txtEmail)) return false;
        return true;
    }
    @FXML
    void txtEmployeeContactKeyreleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.CONTACT,txtContact);
    }

    @FXML
    void txtEmployeeEmailKeyreleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.EMAIL,txtEmail);
    }

    @FXML
    void txtEmployeeNameKeyreleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtEmployeeName);
    }
}