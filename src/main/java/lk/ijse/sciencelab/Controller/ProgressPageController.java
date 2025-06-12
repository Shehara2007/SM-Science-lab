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
import lk.ijse.sciencelab.Dto.ProgressDto;
import lk.ijse.sciencelab.model.Progressmodel;
import lk.ijse.sciencelab.model.Projectmodel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ProgressPageController{
    private final Progressmodel Pmodel = new Progressmodel();
    private final Projectmodel Pprojectmodel = new Projectmodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public TableColumn LastUpdatedDateclm;
    public TableColumn Statusclm;
    public TableColumn ProjectIDclm;
    public TableColumn ProgressIDclm;
    public Button btnUpdate;
    public DatePicker DPLastUpdatedDate;
    public Label lblProgressID;
    public ComboBox <String> ComboBoxProject;
    public TableView<ProgressDto> tblProgress;
    public TextField txtstatus;


    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxProject.setItems(Pprojectmodel.getAllProjectID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Progressmodel.getText();
        lblProgressID.setText(nextID);
    }

    private void setcellvaluefactory() {
        ProgressIDclm.setCellValueFactory(new PropertyValueFactory<>("progressId"));
        ProjectIDclm.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        Statusclm.setCellValueFactory(new PropertyValueFactory<>("status"));
        LastUpdatedDateclm.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedDate"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ProgressDto> progress = Pmodel.getAll();

        ObservableList<ProgressDto> ProgressObservableList = FXCollections.observableArrayList();
        for (ProgressDto Pr : progress) {
            ProgressObservableList.add(Pr);
        }
        tblProgress.setItems(ProgressObservableList);
    }


    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (isValied()){
            String progressID = lblProgressID.getText();
            String projectID = (String) ComboBoxProject.getValue();
            String status = (txtstatus.getText());
            String lastUpdatedDate = String.valueOf(DPLastUpdatedDate.getValue());

            ProgressDto progress = new ProgressDto(progressID, projectID, status, lastUpdatedDate);
            boolean issave = Pmodel.save(progress);

            if (issave) {
                new Alert(Alert.AlertType.INFORMATION, " Progress Saved", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, " Progress NotSaved", ButtonType.OK).show();
            }
        }

    }
    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        lblProgressID.setText("");
        txtstatus.setText("");

    }
    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String progressID = lblProgressID.getText();
        String projectID = (String) ComboBoxProject.getValue();
        String status =(txtstatus.getText());
        String lastUpdatedDate = String.valueOf(DPLastUpdatedDate.getValue());

        ProgressDto progress = new ProgressDto(progressID, projectID, status, lastUpdatedDate);
        boolean isupdate = Pmodel.update(progress);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, " Progress Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, " Progress NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String progressID = lblProgressID.getText();

        // Check if a progress record is selected
        if (progressID == null || progressID.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a progress record to delete.", ButtonType.OK).show();
            return;
        }

        // Show confirmation alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this progress record? This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Proceed with deletion
            boolean isDelete = Pmodel.DeleteProgress(progressID);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Progress Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Progress Not Deleted", ButtonType.OK).show();
            }
        } else {
            // User cancelled deletion
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled", ButtonType.OK).show();
        }
    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

    public void tableClicOnAction(MouseEvent mouseEvent) {
        ProgressDto selectedItem =  tblProgress.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblProgressID.setText(selectedItem.getProgressId());
            ComboBoxProject.setValue(selectedItem.getProjectId());
            txtstatus.setText(selectedItem.getStatus());
            DPLastUpdatedDate.setValue(LocalDate.parse(selectedItem.getLastUpdatedDate()));
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private boolean isValied() {
//        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.,txtstatus)) return false;
        return true;
    }
    @FXML
    void txtProjectStatusOnKeyRelease(KeyEvent event) {
//        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.PRICE,txtAmount);
    }
}
