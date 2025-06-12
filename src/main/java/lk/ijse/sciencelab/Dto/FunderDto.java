package lk.ijse.sciencelab.Dto;


public class FunderDto {
    private String funderId;
    private String funderName;
    private double amount;
    private String project;
    private String organization;

    public FunderDto() {
    }

    public FunderDto(String funderId, String funderName, double amount, String project, String organization) {
        this.funderId = funderId;
        this.funderName = funderName;
        this.amount = amount;
        this.project = project;
        this.organization = organization;
    }

    public String getFunderId() {
        return funderId;
    }

    public void setFunderId(String funderId) {
        this.funderId = funderId;
    }

    public String getFunderName() {
        return funderName;
    }

    public void setFunderName(String funderName) {
        this.funderName = funderName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "FunderDto{" +
                "funderId='" + funderId + '\'' +
                ", funderName='" + funderName + '\'' +
                ", amount=" + amount +
                ", project='" + project + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}