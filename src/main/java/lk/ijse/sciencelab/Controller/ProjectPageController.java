package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sciencelab.DBConnection.DBConnection;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Projectmodel;
import lk.ijse.sciencelab.util.cartTm;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ProjectPageController {
    private final Projectmodel Pmodel = new Projectmodel();
    @FXML
    private Label lblProjectID;

    @FXML
    private TextField txtTitle;

    @FXML
    private DatePicker txtStartDate;

    @FXML
    private TextField txtFundingAmount;

    @FXML
    private Label txtScientistID;

    @FXML
    private DatePicker txtEndDate;

    @FXML
    private TextField txtDescription;

    @FXML
    private ComboBox<String> ComboBoxItems;

    @FXML
    private AnchorPane ancItemUI;

    @FXML
    private TableColumn<?, ?> ItemIDclm;

    @FXML
    private TableColumn<?, ?> Nameclm;

    @FXML
    private TableColumn<?, ?> Quantityclm;

    @FXML
    private TableColumn<?, ?> Actionclm;

    @FXML
    private TableView<cartTm> tblProject;

    private ObservableList<cartTm> list = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        setnextID();
        ComboBoxItems.setItems(FXCollections.observableArrayList("Equipment","Chemical"));
        //loadtable();
        setCellValueFactory();
    }

//    private void loadtable() throws SQLException, ClassNotFoundException {
//        ArrayList<ProjectDto> project = Pmodel.getAll();
//
//        ObservableList<ProjectDto> projectObservableList = FXCollections.observableArrayList();
//        for (ProjectDto p : project) {
//            projectObservableList.add(p);
//        }
//
//        tblProject.setItems(projectObservableList);
//    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Projectmodel.getText();
        lblProjectID.setText(nextID);

    }


    private void nevigateTo(String s) {
        try {
            ancItemUI.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ancItemUI.widthProperty());
            pane.prefHeightProperty().bind(ancItemUI.heightProperty());

            ancItemUI.getChildren().add(pane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page Not Found!").show();
            e.printStackTrace();

        }
    }

    public void ItemSearchOnAction(ActionEvent actionEvent) {
        if(ComboBoxItems.getSelectionModel().getSelectedItem().equals("Equipment")) {
            nevigateTo("/view/EquipmentCartPage.fxml");
        } else if (ComboBoxItems.getSelectionModel().getSelectedItem().equals( "Chemical")) {
            nevigateTo("/view/ChemicalCartPage.fxml");
        }
    }

    private void setCellValueFactory() {
        ItemIDclm.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        Nameclm.setCellValueFactory(new PropertyValueFactory<>("name"));
        Quantityclm.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Actionclm.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    public void AddItemOnAction(ActionEvent actionEvent) {
        loadData();
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String insertProjectSQL = "INSERT INTO project (project_Id, start_date, end_date, funding_Amount, title, description) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement projectStmt = connection.prepareStatement(insertProjectSQL);
            projectStmt.setString(1, lblProjectID.getText());
            projectStmt.setDate(2, Date.valueOf(txtStartDate.getValue()));
            projectStmt.setDate(3, Date.valueOf(txtEndDate.getValue()));
            projectStmt.setBigDecimal(4, new BigDecimal(txtFundingAmount.getText()));
            projectStmt.setString(5, txtTitle.getText());
            projectStmt.setString(6, txtDescription.getText());
            projectStmt.executeUpdate();

            for (cartTm tm : list) {
                String equipmentId = tm.getItemId();
                int reduceBy = Integer.parseInt(tm.getQuantity().trim());

                String selectQtySQL = "SELECT quantity FROM equipment WHERE equipment_Id = ?";
                PreparedStatement selectStmt = connection.prepareStatement(selectQtySQL);
                selectStmt.setString(1, equipmentId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    int currentQty = Integer.parseInt(rs.getString("quantity"));
                    System.out.println(currentQty);
                    System.out.println(reduceBy);
                    int newQty = currentQty - reduceBy;

                    System.out.println(newQty);
                    if (newQty < 0) {
                        throw new SQLException("Not enough quantity for equipment ID: " + equipmentId);
                    }

                    String updateQtySQL = "UPDATE equipment SET quantity = ? WHERE equipment_Id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateQtySQL);
                    updateStmt.setString(1, String.valueOf(newQty));
                    updateStmt.setString(2, equipmentId);
                    updateStmt.executeUpdate();
                } else {
                    throw new SQLException("Equipment with ID " + equipmentId + " not found.");
                }
            }

            connection.commit();
            System.out.println("Project inserted and equipment quantity updated successfully.");

        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("Transaction rolled back due to error.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) {
    }

    private void loadData() {
        Image img = new Image(getClass().getResource("/Icons/icons8-minimize-48.jpg").toExternalForm());
        ImageView view = new ImageView(img);
        view.setFitHeight(15);
        view.setFitWidth(15);
        Button button = new Button();
        button.setStyle("-fx-background-color: white;");
        button.setGraphic(view);

        button.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblProject.getSelectionModel().getSelectedIndex();
                try{
                    list.remove(selectedIndex);
                } catch (Exception exception){
                    new Alert(Alert.AlertType.INFORMATION,"Select Column And Remove !!").show();
                    return;
                }
                tblProject.refresh();
            }
        });

        String id = EquipmentCartPageController.id;
        String name = EquipmentCartPageController.name;
        String qty = EquipmentCartPageController.quantity;

        cartTm tm = new cartTm(id,name,qty, button);
        list.add(tm);
        tblProject.setItems(list);
    }
}