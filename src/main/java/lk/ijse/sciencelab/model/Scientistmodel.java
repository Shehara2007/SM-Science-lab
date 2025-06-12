package lk.ijse.sciencelab.model;
import lk.ijse.sciencelab.Dto.ScientistDto;

import lk.ijse.sciencelab.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Scientistmodel {

    public static String getText() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select scientist_id from scientist order by scientist_id DESC limit 1");
        if (rs.next()) {
            String lastId = rs.getString(1); // Example: "Sc002"
            System.out.println("Last ID from DB: " + lastId);

            if (lastId.length() > 2 && lastId.startsWith("Sc")) {
                String lastIdNumberString = lastId.substring(2); // get "002"
                System.out.println("Extracted number: " + lastIdNumberString);

                try {
                    int lastIdNumber = Integer.parseInt(lastIdNumberString);
                    int nextIdNumber = lastIdNumber + 1;
                    String nextIdString = String.format("Sc%03d", nextIdNumber);
                    System.out.println("Next ID: " + nextIdString);
                    return nextIdString;
                } catch (NumberFormatException e) {
                    System.err.println("Failed to parse number: " + lastIdNumberString);
                    throw e; // rethrow or handle
                }
            } else {
                System.err.println("Unexpected ID format: " + lastId);
            }
        }
        return "Sc001";
    }


    public boolean save(ScientistDto scientist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into scientist values (?,?,?,?,?)", scientist.getScientistId(), scientist.getScientistName(), scientist.getContact(), scientist.getEmployee(), scientist.getSpecialization());
    }

    public boolean update(ScientistDto scientist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update scientist set scientist_Name = ?,contact = ?,employee = ?,specialization = ? where scientist_Id = ?", scientist.getScientistName(), scientist.getContact(), scientist.getEmployee(), scientist.getSpecialization(),scientist.getScientistId() );
    }

    public ArrayList<ScientistDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from scientist");
        ArrayList<ScientistDto> scientistArrayList = new ArrayList<>();
        while (rs.next()) {
            ScientistDto scientist = new ScientistDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            scientistArrayList.add(scientist);
        }
        return scientistArrayList;
    }


    public boolean DeleteScientist(String scientistID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from scientist where scientist_Id = ?", scientistID);

    }
}

