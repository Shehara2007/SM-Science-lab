package lk.ijse.sciencelab.Dto;


public class ScientistDto {
    private String scientistId;
    private String contact;
    private String scientistName;
    private String employee;
    private String specialization;

    public ScientistDto() {
    }

    public ScientistDto(String scientistId, String contact, String scientistName, String employee, String specialization) {
        this.scientistId = scientistId;
        this.contact = contact;
        this.scientistName = scientistName;
        this.employee = employee;
        this.specialization = specialization;
    }

    public String getScientistId() {
        return scientistId;
    }

    public void setScientistId(String scientistId) {
        this.scientistId = scientistId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getScientistName() {
        return scientistName;
    }

    public void setScientistName(String scientistName) {
        this.scientistName = scientistName;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "ScientistDto{" +
                "scientistId='" + scientistId + '\'' +
                ", contact='" + contact + '\'' +
                ", scientistName='" + scientistName + '\'' +
                ", employee='" + employee + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}