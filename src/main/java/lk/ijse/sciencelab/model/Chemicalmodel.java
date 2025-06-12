package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sciencelab.Dto.ChemicalDto;
import lk.ijse.sciencelab.Dto.GroupDto;
import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Chemicalmodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select chemical_Id from chemical order by chemical_Id DESC limit 1");
        char tableCharactor = 'C';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format("C%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor + "001";
    }


    public boolean save(ChemicalDto chemical) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into chemical values (?,?,?,?,?)", chemical.getChemicalId(), chemical.getChemicalName(), chemical.getQuantity(), chemical.getConcentration(), chemical.getSupplierId());
    }

    public boolean update(ChemicalDto chemical) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update chemical set chemical_Name = ?,quantity = ?,concentration = ?,supplier_Id = ? where chemical_Id = ? ", chemical.getChemicalName(), chemical.getQuantity(), chemical.getConcentration(), chemical.getSupplierId(),chemical.getChemicalId());
    }


    public ArrayList<ChemicalDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from chemical");
        ArrayList<ChemicalDto> chemicalArrayList = new ArrayList<>();
        while (rs.next()) {
            ChemicalDto chemical = new ChemicalDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            chemicalArrayList.add(chemical);
        }
        return chemicalArrayList;
    }

    public boolean DeleteChemical(String ChemicalID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from chemical where chemical_Id = ? ", ChemicalID);
    }

    public ObservableList getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select supplier_Id from supplier");
        ObservableList<String> supplierDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            supplierDtoArrayList.add(rs.getString("supplier_Id"));
        }
        return supplierDtoArrayList;

    }


}
