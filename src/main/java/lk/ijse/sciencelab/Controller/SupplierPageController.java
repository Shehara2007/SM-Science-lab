package lk.ijse.sciencelab.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.Dto.SupplierDto;
import lk.ijse.sciencelab.model.Suppliermodel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class SupplierPageController {
    private final Suppliermodel Smodel = new Suppliermodel();
    public Label txtScientistName;
    public TextField txtSupplierName;
    public TextField txtContact;
    public TextField txtEquipment;
    public TextField txtTypeOfSupplier;
    public TableColumn TypeOfSupplierclm;
    public TableColumn Equipmentclm;
    public TableColumn Contactclm;
    public TableColumn SupplierNameclm;
    public TableColumn SupplierIDclm;
    public Label lblSupplierID;
    public TableView tblSupplier;
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public HBox btnUpdate;
    public ComboBox <String>ComboBoxTypeOfSupplier;
    public ComboBox <String> ComboBoxEquipment;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxEquipment.setItems(FXCollections.observableArrayList( "Measuring Instruments","Glassware","Heating Equipment","Microscopes","Safety Equipment","Mixing and Stirring","Storage Equipment","Analytical Instruments","Miscellaneous","pH Meter","Wash Bottle","Wash Bottle","Lab Timer / Stopwatch","Anemometer"));
        ComboBoxTypeOfSupplier.setItems(FXCollections.observableArrayList("Chemical Supplier ", "Equipment Supplier"));
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
            String nextID = Suppliermodel.getText();
            lblSupplierID.setText(nextID);
    }

    private void setcellvaluefactory() {
        SupplierIDclm.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        SupplierNameclm.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        Contactclm.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Equipmentclm.setCellValueFactory(new PropertyValueFactory<>("equipment"));
        TypeOfSupplierclm.setCellValueFactory(new PropertyValueFactory<>("typeOfSupplier"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> supplier = Smodel.getAll();

        ObservableList<SupplierDto> supplierObservableList = FXCollections.observableArrayList();
        for (SupplierDto s : supplier) {
            supplierObservableList.add(s);
        }
        tblSupplier.setItems(supplierObservableList);
    }


        public void clickOnAction (MouseEvent mouseEvent){
            SupplierDto selectedItem = (SupplierDto) tblSupplier.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                lblSupplierID.setText(selectedItem.getSupplierId());
                txtSupplierName.setText(selectedItem.getSupplierName());
                txtContact.setText(selectedItem.getContact());
                ComboBoxEquipment.setValue(selectedItem.getEquipment());
                ComboBoxTypeOfSupplier.setValue(selectedItem.getTypeOfSupplier());
                // save button disable
                btnSave.setDisable(true);
                // update, delete button enable
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            }
        }
            public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
                String supplierId = lblSupplierID.getText();
                String supplierName = txtSupplierName.getText();
                String contact = txtContact.getText();
                String equipment = ComboBoxEquipment.getValue();
                String typeOfSupplier = ComboBoxTypeOfSupplier.getValue();

                SupplierDto supplier = new SupplierDto(supplierId, supplierName, contact, equipment, typeOfSupplier);
                boolean issave = Smodel.save(supplier);

                if (issave) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved", ButtonType.OK).show();
                    loadtable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Supplier NotSaved", ButtonType.OK).show();
                }

            }
    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        lblSupplierID.setText("");
        txtSupplierName.setText("");
        txtContact.setText("");
    }
            public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

                String supplierId = lblSupplierID.getText();
                String supplierName = txtSupplierName.getText();
                String contact = txtContact.getText();
                String equipment = ComboBoxEquipment.getValue();
                String typeOfSupplier = ComboBoxTypeOfSupplier.getValue();

                SupplierDto supplier = new SupplierDto(supplierId, supplierName, contact, equipment, typeOfSupplier);
                boolean isupdate = Smodel.update(supplier);

                if (isupdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Update", ButtonType.OK).show();
                    loadtable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Supplier NotUpdate", ButtonType.OK).show();
                }
            }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supplierID = lblSupplierID.getText();

        // Check if supplierID is selected
        if (supplierID == null || supplierID.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a supplier to delete.", ButtonType.OK).show();
            return;
        }

        // Confirmation alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this supplier? This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = Smodel.DeleteSupplier(supplierID);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Deleted", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled", ButtonType.OK).show();
        }
    }


    public void btnGenarateROnAction (ActionEvent actionEvent){
            }

    }
