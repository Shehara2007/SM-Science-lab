package lk.ijse.sciencelab.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.sciencelab.DBConnection.DBConnection;

import java.sql.*;

public class EquipmentCartPageController {
    public ComboBox  <String> ComboBoxEquipmentID;
    public Label lblName;
    public TextField txtQuantity;

    public static String id;
    public static String name;
    public static String quantity;

    public void initialize() {
        loadEquipmentIDs();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            // Your repeated logic
            quantity = txtQuantity.getText();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void loadEquipmentIDs() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT equipment_id FROM equipment";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<String> equipmentIDs = FXCollections.observableArrayList();

            while (resultSet.next()) {
                equipmentIDs.add(resultSet.getString("equipment_id"));
            }

            ComboBoxEquipmentID.setItems(equipmentIDs);

            resultSet.close();
            statement.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
        String selectedEquipmentID = ComboBoxEquipmentID.getValue();

        if (selectedEquipmentID == null || selectedEquipmentID.isEmpty()) {
            lblName.setText("Please select an Equipment ID.");
            return;
        }

        try {
            // Establish database connection
            Connection connection = DBConnection.getInstance().getConnection();

            // Prepare the SQL query
            String sql = "SELECT equipment_id,equipment_name,quantity FROM equipment WHERE equipment_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, selectedEquipmentID);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // If found, set the name to the label
            if (resultSet.next()) {
                name = resultSet.getString("equipment_name");
                id = resultSet.getString("equipment_id");
                quantity = resultSet.getString("quantity");
                lblName.setText(name);
            } else {
                lblName.setText("Equipment not found.");
            }

            // Close resources
            resultSet.close();
            statement.close();

        } catch (SQLException | ClassNotFoundException e) {
            lblName.setText("Database error.");
            e.printStackTrace();
        }
    }

}