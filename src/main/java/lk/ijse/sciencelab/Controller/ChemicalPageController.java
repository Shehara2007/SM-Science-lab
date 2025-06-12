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
import lk.ijse.sciencelab.Dto.ChemicalDto;
import lk.ijse.sciencelab.Dto.GroupDto;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Chemicalmodel;
import lk.ijse.sciencelab.model.Projectmodel;
import lk.ijse.sciencelab.util.Regex;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChemicalPageController {
    private final Chemicalmodel Cmodel = new Chemicalmodel();
    public TextField txtChemicalName;
    public TextField txtQuantity;
    public TextField txtConcentration;
    public TextField txtSupID;
    public TableView<ChemicalDto> tblChemical;
    public Label lblChemicalID;
    public TableColumn ChemicalIDclm;
    public TableColumn Quantityclm;
    public TableColumn ChemicalNameclm;
    public TableColumn Concentrationclm;
    public TableColumn SupIDclm;
    public ComboBox <String>ComboBoxSupplier;
    public ImageView btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnGenarateReport;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxSupplier.setItems(Cmodel.getAllSupplier());
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ChemicalDto> chemical = Cmodel.getAll();

        ObservableList<ChemicalDto> chemicalObservableList = FXCollections.observableArrayList();
        for (ChemicalDto p : chemical) {
            chemicalObservableList.add(p);
        }
        tblChemical.setItems(chemicalObservableList);
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Chemicalmodel.getText();
        lblChemicalID.setText(nextID);
    }

    private void setcellvaluefactory() {
        ChemicalIDclm.setCellValueFactory(new PropertyValueFactory<>("chemicalId"));
        ChemicalNameclm.setCellValueFactory(new PropertyValueFactory<>("ChemicalName"));
        Quantityclm.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Concentrationclm.setCellValueFactory(new PropertyValueFactory<>("Concentration"));
        SupIDclm.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
    }



    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (isValied()){
            String chemicalId = lblChemicalID.getText();
            String chemicalName = txtChemicalName.getText();
            String quantity =(txtQuantity.getText());
            String concentration = txtConcentration.getText();
            String supplierId = (String) ComboBoxSupplier.getValue();

            ChemicalDto chemical = new ChemicalDto(chemicalId, chemicalName, quantity, concentration, supplierId);
            boolean issave = Cmodel.save(chemical);

            if (issave) {
                new Alert(Alert.AlertType.INFORMATION, "Chemical Saved", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Chemical NotSaved", ButtonType.OK).show();
            }
        }

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String chemicalId = lblChemicalID.getText();

        // Check if chemical ID is selected
        if (chemicalId == null || chemicalId.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a chemical to delete.", ButtonType.OK).show();
            return;
        }

        // Confirmation Alert before deletion
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this employee? This action cannot be undone.");

        // Show the confirmation and wait for user action
        Optional<ButtonType> result = confirmAlert.showAndWait();

        // Proceed only if user clicks YES
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = Cmodel.DeleteChemical(chemicalId);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Chemical Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Chemical Not Deleted", ButtonType.OK).show();
            }
        }
    }

    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        lblChemicalID.setText("");
        txtChemicalName.setText("");
        txtQuantity.setText("");
        txtConcentration.setText("");
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String chemicalId = lblChemicalID.getText();
        String chemicalName = txtChemicalName.getText();
        String quantity = (txtQuantity.getText());
        String concentration = txtConcentration.getText();
        String supplierId = (String) ComboBoxSupplier.getValue();

        ChemicalDto chemical = new ChemicalDto(chemicalId, chemicalName, quantity, concentration, supplierId);
        boolean isupdate = Cmodel.update(chemical);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Chemical Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Chemical NotUpdate", ButtonType.OK).show();
        }
    }


    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        ChemicalDto selectedItem = tblChemical.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblChemicalID.setText(selectedItem.getChemicalId());
            txtChemicalName.setText(selectedItem.getChemicalName());
            txtQuantity.setText(selectedItem.getQuantity());
            txtConcentration.setText(selectedItem.getConcentration());
            ComboBoxSupplier.setValue(selectedItem.getSupplierId());

            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtChemicalName)) return false;
        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.QTY,txtQuantity)) return false;
//        if (!Regex.setTextColor(lk.ijse.sciencelab.util.TextField.QTY,txtConcentration)) return false;
        return true;
    }
    @FXML
    void txtChemicalnameKeyreleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.NAME,txtChemicalName);
    }
    @FXML
    void txtChemicalQtyKeyreleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.sciencelab.util.TextField.QTY,txtQuantity);
    }
}
