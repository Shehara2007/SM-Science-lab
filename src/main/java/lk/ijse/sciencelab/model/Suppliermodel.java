package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sciencelab.Dto.SupplierDto;
import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Suppliermodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select supplier_id from supplier order by supplier_id DESC limit 1");
        char tableCharactor = 'S';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format("S%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor + "001";
    }

    public static ObservableList<String> getAllSupID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select supplier_Id from supplier");
        ObservableList<String> supplierArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            supplierArrayList.add(rs.getString("supplier_Id"));
        }
        return  supplierArrayList;
    }

    public boolean save(SupplierDto supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into supplier values (?,?,?,?,?)", supplier.getSupplierId(), supplier.getSupplierName(), supplier.getContact(), supplier.getEquipment(), supplier.getTypeOfSupplier());
    }

    public boolean update(SupplierDto supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update supplier set supplier_Name = ?,supplier_Contact = ?,equipment = ?,type_of_supplier = ? where supplier_Id = ?", supplier.getSupplierName(), supplier.getContact(), supplier.getEquipment(), supplier.getTypeOfSupplier(),supplier.getSupplierId() );
    }

    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from supplier");
        ArrayList<SupplierDto> supplierArrayList = new ArrayList<>();
        while (rs.next()) {
            SupplierDto supplier = new SupplierDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            supplierArrayList.add(supplier);
        }
        return supplierArrayList;
    }

    public boolean DeleteSupplier(String supplierID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from supplier where supplier_id = ?", supplierID);

    }
}
