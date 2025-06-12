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
import lk.ijse.sciencelab.Dto.GroupDto;
import lk.ijse.sciencelab.model.Groupmodel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class GroupPageController{
    private final Groupmodel Gmodel = new Groupmodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public Button btnUpdate;
    public TableColumn ScientistIDclm;
    public TableColumn ResearchOfProgressclm;
    public TableColumn Memberclm;
    public TableColumn Progressclm;
    public TableColumn GroupNameclm;
    public TableColumn <?,?> GroupIDclm;
    public TableView <GroupDto> tblGroup;
    public TextField txtResearchOfProgress;
    public TextField txtMember;
    public TextField txtProgress;
    public TextField txtGroupName;
    public Label lblGroupID;
    public ComboBox <String> ComboBoxScientist;


    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
ComboBoxScientist.setItems(Gmodel.getAllProjectID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Groupmodel.getText();
        lblGroupID.setText(nextID);
    }

    private void setcellvaluefactory() {
        GroupIDclm.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        GroupNameclm.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        Progressclm.setCellValueFactory(new PropertyValueFactory<>("progress"));
        Memberclm.setCellValueFactory(new PropertyValueFactory<>("member"));
        ResearchOfProgressclm.setCellValueFactory(new PropertyValueFactory<>("researchProgress"));
        ScientistIDclm.setCellValueFactory(new PropertyValueFactory<>("scientistId"));

    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<GroupDto> group = (ArrayList<GroupDto>) Gmodel.getAll();

        ObservableList<GroupDto> groupObservableList = FXCollections.observableArrayList();
        for (GroupDto E : group) {
            groupObservableList.add(E);
        }
        tblGroup.setItems(groupObservableList);
    }
    @FXML
    void tableClickOnAction(MouseEvent event) {
        System.out.println("clickOnAction");
        GroupDto selectedItem = tblGroup.getSelectionModel().getSelectedItem();

        System.out.println("clicked");

        System.out.println(selectedItem.getGroupName());

        if (selectedItem != null) {
            lblGroupID.setText(selectedItem.getGroupId());
            txtGroupName.setText(selectedItem.getGroupName());
            txtProgress.setText(selectedItem.getProgress());
            txtMember.setText(selectedItem.getMember());
            txtResearchOfProgress.setText(selectedItem.getResearchProgress());
            ComboBoxScientist.setValue(selectedItem.getScientistId());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void clickOnAction (MouseEvent mouseEvent){
        System.out.println("clickOnAction");
        GroupDto selectedItem = tblGroup.getSelectionModel().getSelectedItem();

        System.out.println("clicked");

        System.out.println(selectedItem.getGroupName());

        if (selectedItem != null) {
            lblGroupID.setText(selectedItem.getGroupId());
            txtGroupName.setText(selectedItem.getGroupName());
            txtProgress.setText(selectedItem.getProgress());
            txtMember.setText(selectedItem.getMember());
            txtResearchOfProgress.setText(selectedItem.getResearchProgress());
            //ComboBoxScientist.setValue(selectedItem.getScientistId());

            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String groupID = lblGroupID.getText();
        String groupName = txtGroupName.getText();
        String progress = txtProgress.getText();
        String member = txtMember.getText();
        String researchOfProgress = txtResearchOfProgress.getText();
        String scientistID = (String) ComboBoxScientist.getValue();


        GroupDto group = new GroupDto(groupID, groupName, progress, member, researchOfProgress, scientistID);
        boolean issave = Gmodel.save(group);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Group Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Group NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){clear();
    }
    private void clear() {
        lblGroupID.setText("");
        txtGroupName.setText("");
        txtProgress.setText("");
        txtMember.setText("");
        txtResearchOfProgress.setText("");
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String groupID = lblGroupID.getText();
        String groupName = txtGroupName.getText();
        String progress = txtProgress.getText();
        String member = txtMember.getText();
        String researchOfProgress = txtResearchOfProgress.getText();
        String scientistID = (String) ComboBoxScientist.getValue();

        GroupDto group = new GroupDto(groupID, groupName, progress, member, researchOfProgress, scientistID);
        boolean isupdate = Gmodel.update(group);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Group Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Group NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String groupID = lblGroupID.getText();

        // Check if a group is selected
        if (groupID == null || groupID.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a group to delete.", ButtonType.OK).show();
            return;
        }

        // Show confirmation alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this group? This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If confirmed, delete
            boolean isDelete = Gmodel.DeleteGroup(groupID);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Group Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Group Not Deleted", ButtonType.OK).show();
            }
        } else {
            // If cancelled
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled", ButtonType.OK).show();
        }
    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }
    @FXML
    void txtGroupNameOnKeyRelese(KeyEvent event) {

    }

    @FXML
    void txtMemberOnKeyRelese(KeyEvent event) {

    }

    @FXML
    void txtProgressOnKeyRelese(KeyEvent event) {

    }

    @FXML
    void txtResearchOfProgressOnKeyRelese(KeyEvent event) {

    }

}
