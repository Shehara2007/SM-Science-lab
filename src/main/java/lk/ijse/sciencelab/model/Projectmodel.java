package lk.ijse.sciencelab.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Projectmodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select project_id from project order by project_id DESC limit 1");
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

    public boolean save(ProjectDto project) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into project values (?,?,?,?,?,?)",project.getProjectId(),project.getStartDate(),project.getEndDate(),project.getFundingAmount(),project.getTitle(),project.getDescription());
    }

    public boolean update(ProjectDto project) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update project set start_Date = ?,end_Date = ?,funding_Amount = ?,title = ?,description = ? where project_id = ?",project.getStartDate(),project.getEndDate(),project.getFundingAmount(),project.getTitle(),project.getDescription(),project.getProjectId());
    }

    public ArrayList<ProjectDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from project");
        ArrayList<ProjectDto> projectArrayList = new ArrayList<>();
        while (rs.next()) {
            ProjectDto project = new ProjectDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            projectArrayList.add(project);
        }
        return projectArrayList;
    }

    public boolean DeleteProject(String projectID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from project where project_id = ?", projectID);
    }

    public ObservableList getAllProjectID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select project_Id from project");
        ObservableList<String> supplierDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            supplierDtoArrayList.add(rs.getString("project_Id"));
        }
        return supplierDtoArrayList;

    }
    }


