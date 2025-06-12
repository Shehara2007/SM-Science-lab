package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lk.ijse.sciencelab.Dto.ProgressDto;
import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Progressmodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select progress_id from progress order by progress_id DESC limit 1");
        char tableCharactor = 'P';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format("P%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor + "001";
    }

    public boolean save(ProgressDto progress) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into progress values (?,?,?,?)", progress.getProgressId(), progress.getProjectId(), progress.getStatus(), progress.getLastUpdatedDate());
    }

    public boolean update(ProgressDto progress) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update progress set project_ID = ?,status = ?,last_Updated_Date = ? where progress_Id = ?", progress.getProjectId(), progress.getStatus(), progress.getLastUpdatedDate(), progress.getProgressId() );
    }

    public ArrayList<ProgressDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from progress");
        ArrayList<ProgressDto> progressArrayList = new ArrayList<>();
        while (rs.next()) {
            ProgressDto progress = new ProgressDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
            progressArrayList.add(progress);
        }
        return progressArrayList;
    }


    public boolean DeleteProgress(String funderID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from progress where progress_Id = ?", funderID);

    }

    public ObservableList getAllProjectID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select progress_Id from progress");
        ObservableList<String> ProgressDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            ProgressDtoArrayList.add(rs.getString("progress_Id"));
        }
        return  ProgressDtoArrayList;

    }
}

