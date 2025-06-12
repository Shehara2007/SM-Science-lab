package lk.ijse.sciencelab.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sciencelab.Dto.EmployeeDto;

import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employeemodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select employee_id from employee order by employee_id DESC limit 1");
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

    public boolean save(EmployeeDto employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into employee values (?,?,?,?,?,?)", employee.getEmployeeId(), employee.getRole(), employee.getEmployeeName(), employee.getContact(), employee.getGroupId(), employee.getEmail());
    }

    public boolean update(EmployeeDto employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update employee set role = ?,employee_Name = ?,contact = ?,group_Id = ?, email = ? where employee_Id = ?", employee.getRole(), employee.getEmployeeName(), employee.getContact(), employee.getGroupId(),employee.getEmail(), employee.getEmployeeId() );
    }

    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from employee");
        ArrayList<EmployeeDto> employeetArrayList = new ArrayList<>();
        while (rs.next()) {
            EmployeeDto employee = new EmployeeDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            employeetArrayList.add(employee);
        }
        return employeetArrayList;
    }


    public boolean DeleteEmployee(String employeeID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from employee where employee_Id = ?", employeeID);

    }

    public ObservableList getAllEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select employee_Id from employee");
        ObservableList<String> equipmentArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            equipmentArrayList.add(rs.getString("employee_Id"));
        }
        return  equipmentArrayList;

    }
    }


