package lk.ijse.sciencelab.Dto;


public class ProjectDto {
    private String projectId;
    private String startDate;
    private String endDate;
    private double fundingAmount;
    private String title;
    private String description;

    public ProjectDto() {
    }

    public ProjectDto(String projectId, String startDate, String endDate, double fundingAmount, String title, String description) {
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fundingAmount = fundingAmount;
        this.title = title;
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(double fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "projectId='" + projectId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", fundingAmount=" + fundingAmount +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}