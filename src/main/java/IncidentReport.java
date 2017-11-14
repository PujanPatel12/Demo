import java.sql.Date;

public class IncidentReport {
    private int incidentActionID;
    private String actionName;
    private int incidentReportID;
    private int incidentTypeID;
    private int studentID;
    private Date incidentDate;
    private String incidentType;
    private String incidentTypeDescription;
    private String Stu_lastName;
    private String Stu_firstName;

    public int getIncidentActionID() {
        return incidentActionID;
    }

    public void setIncidentActionID(int incidentActionID) {
        this.incidentActionID = incidentActionID;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getIncidentReportID() {
        return incidentReportID;
    }

    public void setIncidentReportID(int incidentReportID) {
        this.incidentReportID = incidentReportID;
    }

    public int getIncidentTypeID() {
        return incidentTypeID;
    }

    public void setIncidentTypeID(int incidentTypeID) {
        this.incidentTypeID = incidentTypeID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIncidentTypeDescription() {
        return incidentTypeDescription;
    }

    public void setIncidentTypeDescription(String incidentTypeDescription) {
        this.incidentTypeDescription = incidentTypeDescription;
    }

    public String getStu_lastName() {
        return Stu_lastName;
    }

    public void setStu_lastName(String stu_lastName) {
        Stu_lastName = stu_lastName;
    }

    public String getStu_firstName() {
        return Stu_firstName;
    }

    public void setStu_firstName(String stu_firstName) {
        Stu_firstName = stu_firstName;
    }

    @Override
    public String toString() {
        return "IncidentReport{" +
                "incidentActionID=" + incidentActionID +
                ", actionName='" + actionName + '\'' +
                ", incidentReportID=" + incidentReportID +
                ", incidentTypeID=" + incidentTypeID +
                ", studentID=" + studentID +
                ", incidentDate=" + incidentDate +
                ", incidentType='" + incidentType + '\'' +
                ", incidentTypeDescription='" + incidentTypeDescription + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                '}';
    }
}
