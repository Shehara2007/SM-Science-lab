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
import lk.ijse.sciencelab.Dto.FunderDto;
import lk.ijse.sciencelab.model.Fundermodel;
import lk.ijse.sciencelab.util.Regex;

import java.sql.SQLException;
import java.util.ArrayList;

public class FunderPageController {
    private final Fundermodel Fmodel = new Fundermodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public TableView tblFunder;
    public TableColumn FunderIDclm;
    public TableColumn FunderNameclm;
    public TableColumn Projectclm;
    public TableColumn Amountclm;
    public TableColumn Organizationclm;
    public TextField txtOrganization;
    public TextField txtProject;
    public TextField txtAmount;
    public TextField txtFunderName;
    public Label lblFunderID;
    public Button btnUpdate;
    public ComboBox<String> ComboBoxProject;
    public ComboBox ComboBoxOrganization;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxOrganization.setItems(FXCollections.observableArrayList("University of Colombo – Science Faculty Labs", "University of Moratuwa – Engineering & Tech labs", " Medical Research Institute (MRI)", " NASA (USA) – Space & aerospace research labs", " Mayo Clinic Labs (USA)", " Hemas Pharmaceuticals Lab", " Central Environmental Authority (CEA) Labs", " Allen Institute for Brain Science (USA)"));
        ComboBoxProject.setItems(Fmodel.getAllProjectID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Fundermodel.getText();
        lblFunderID.setText(nextID);
    }

    private void setcellvaluefactory() {
        FunderIDclm.setCellValueFactory(new PropertyValueFactory<>("funderId"));
        FunderNameclm.setCellValueFactory(new PropertyValueFactory<>("funderName"));
        Amountclm.setCellValueFactory(new PropertyValueFactory<>("amount"));
            Projectclm.setCellValueFactory(new PropertyValueFactory<>("project"));
        Organizationclm.setCellValueFactory(new PropertyValueFactory<>("organization"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<FunderDto> funder = Fmodel.getAll();

        ObservableList<FunderDto> funderObservableList = FXCollections.observableArrayList();
        for (FunderDto F : funder) {
            funderObservableList.add(F);
        }
        tblFunder.setItems(funderObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        FunderDto selectedItem = (FunderDto) tblFunder.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblFunderID.setText(selectedItem.getFunderId());
            txtFunderName.setText(selectedItem.getFunderName());
            txtAmount.setText(String.valueOf(selectedItem.getAmount()));
            ComboBoxProject.setValue(selectedItem.getProject());
            txtOrganization.setText(selectedItem.getOrganization());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
if (isValied()){
    String funderId = lblFunderID.getText();
    String funderName = txtFunderName.getText();
    Double amount = Double. valueOf(txtAmount.getText());
    String project = (String) ComboBoxProject.getValue();
    String organization = txtOrganization.getText();

    FunderDto funder = new FunderDto(funderId, funderName, amount, project, organization);
    boolean issave = Fmodel.save(funder);

    if (issave) {
        new Alert(Alert.AlertType.INFORMATION, "Funder Saved", ButtonType.OK).show();
        loadtable();
    } else {
        new Alert(Alert.AlertType.ERROR, "Funder NotSaved", ButtonType.OK).show();
    }
}

    }
    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        lblFunderID.setText("");
        txtFunderName.setText("");
        txtAmount.setText("");
        txtOrganization.setText("");
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String funderId = lblFunderID.getText();
        String funderName = txtFunderName.getText();
        Double amount = Double. valueOf(txtAmount.getText());
        String project = (String) ComboBoxProject.getValue();
        String organization = txtOrganization.getText();

        FunderDto funder = new FunderDto(funderId, funderName, amount, project, organization);
        boolean isupdate = Fmodel.update(funder);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Funder Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Funder NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String funderID = lblFunderID.getText();
        boolean isDelete = Fmodel.DeleteFunder(funderID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Funder Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Funder Not Deleted", ButtonType.OK).show();
        }

    }



    public void btnGenarateROnAction (ActionEvent actionEvent){
    }
    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtFunderName)) return false;
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.PRICE,txtAmount)) return false;
        return true;
    }

    @FXML
    void txtFunderAmountKeyRelease(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.PRICE,txtAmount);
    }

    @FXML
    void txtFunderNameKeyRelease(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtFunderName);
    }

}
