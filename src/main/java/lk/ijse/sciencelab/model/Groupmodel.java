package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lk.ijse.sciencelab.Dto.GroupDto;
import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Groupmodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select group_Id from research_group order by group_Id DESC limit 1");
        char tableCharactor = 'G';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format("G%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharactor + "001";
    }

    public static ObservableList getAllGroupID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select group_Id from research_group");
        ObservableList<String> GroupDtoDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            GroupDtoDtoArrayList.add(rs.getString("group_Id"));
        }
        return GroupDtoDtoArrayList;
    }

    public boolean save(GroupDto group) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into research_group values (?,?,?,?,?,?)", group.getGroupId(), group.getGroupName(), group.getProgress(), group.getMember(), group.getResearchProgress(), group.getScientistId());
    }

    public boolean update(GroupDto group) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update research_group set group_Name = ?,progress = ?,member = ?,research_progress = ?, scientist_Id = ? where group_Id = ?", group.getGroupName(), group.getProgress(), group.getMember(), group.getResearchProgress(),group.getScientistId(), group.getGroupId() );
    }

    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from research_group");
        ArrayList<GroupDto> groupArrayList = new ArrayList<>();
        while (rs.next()) {
            GroupDto group = new GroupDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            groupArrayList.add(group);
        }
        return  groupArrayList;
    }


    public boolean DeleteGroup(String groupID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from research_group where group_Id = ?", groupID);

    }

    public ObservableList getAllProjectID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select scientist_Id from scientist");
        ObservableList<String> groupArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            groupArrayList.add(rs.getString("scientist_Id"));
        }
        return  groupArrayList;

    }

    public ObservableList getgroupIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select group_Id from research_group");
        ObservableList<String> equipmentArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            equipmentArrayList.add(rs.getString("group_Id"));
        }
        return  equipmentArrayList;

    }
    }


