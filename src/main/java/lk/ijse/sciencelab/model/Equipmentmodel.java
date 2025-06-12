package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sciencelab.Dto.EquipmentDto;

import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Equipmentmodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select equipment_id from equipment order by equipment_id DESC limit 1");
        char tableCharactor = 'E';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format("E%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor + "001";
    }

    public boolean save(EquipmentDto equipment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into equipment values (?,?,?,?,?)", equipment.getEquipmentId(), equipment.getEquipmentName(), equipment.getQuantity(), equipment.getType(), equipment.getSupplierId());
    }

    public boolean update(EquipmentDto equipment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update equipment set equipment_Name = ?,quantity = ?,type = ?,supplier_Id = ? where equipment_Id = ?", equipment.getEquipmentName(), equipment.getQuantity(), equipment.getType(), equipment.getSupplierId(),equipment.getEquipmentId() );
    }

    public ArrayList<EquipmentDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from equipment");
        ArrayList<EquipmentDto> equipmentArrayList = new ArrayList<>();
        while (rs.next()) {
            EquipmentDto equipment = new EquipmentDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            equipmentArrayList.add(equipment);
        }
        return equipmentArrayList;
    }


    public boolean DeleteEquipment(String equipmentID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from equipment where equipment_Id = ?", equipmentID);

    }


}

