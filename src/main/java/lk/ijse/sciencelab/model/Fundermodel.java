package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sciencelab.Dto.FunderDto;

import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fundermodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select funder_id from funder order by funder_id DESC limit 1");
        char tableCharactor = 'F';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format("F%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor + "001";
    }

    public boolean save(FunderDto funder) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into funder values (?,?,?,?,?)", funder.getFunderId(), funder.getFunderName(), funder.getAmount(), funder.getProject(), funder.getOrganization());
    }

    public boolean update(FunderDto funder) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update funder set funder_Name = ?,amount = ?,project = ?,organization = ? where funder_Id = ?", funder.getFunderName(), funder.getAmount(), funder.getProject(), funder.getOrganization(),funder.getFunderId() );
    }

    public ArrayList<FunderDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from funder");
        ArrayList<FunderDto> funderArrayList = new ArrayList<>();
        while (rs.next()) {
            FunderDto funder = new FunderDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            funderArrayList.add(funder);
        }
        return funderArrayList;
    }


    public boolean DeleteFunder(String funderID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from funder where funder_Id = ?", funderID);

    }

    public ObservableList getAllProjectID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select project_Id from project");
        ObservableList<String> projectDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            projectDtoArrayList.add(rs.getString("project_Id"));
        }
        return  projectDtoArrayList;

    }
}

