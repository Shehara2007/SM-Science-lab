package lk.ijse.sciencelab.Dto;


public class GroupDto {
    private String groupId;
    private String groupName;
    private String progress;
    private String member;
    private String researchProgress;
    private String scientistId;

    public GroupDto() {
    }

    public GroupDto(String groupId, String groupName, String progress, String member, String researchProgress, String scientistId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.progress = progress;
        this.member = member;
        this.researchProgress = researchProgress;
        this.scientistId = scientistId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getResearchProgress() {
        return researchProgress;
    }

    public void setResearchProgress(String researchProgress) {
        this.researchProgress = researchProgress;
    }

    public String getScientistId() {
        return scientistId;
    }

    public void setScientistId(String scientistId) {
        this.scientistId = scientistId;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", progress='" + progress + '\'' +
                ", member='" + member + '\'' +
                ", researchProgress='" + researchProgress + '\'' +
                ", scientistId='" + scientistId + '\'' +
                '}';
    }
}