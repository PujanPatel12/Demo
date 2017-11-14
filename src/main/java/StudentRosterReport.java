import java.sql.Date;

public class StudentRosterReport {
    private int studentID;
    private String Stu_firstName;
    private String Stu_lastName;
    private Date classStartDate;
    private Date classEndDate;
    private int Ros_statusCode;
    private String Ros_statDescription;
    private String className;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStu_firstName() {
        return Stu_firstName;
    }

    public void setStu_firstName(String stu_firstName) {
        Stu_firstName = stu_firstName;
    }

    public String getStu_lastName() {
        return Stu_lastName;
    }

    public void setStu_lastName(String stu_lastName) {
        Stu_lastName = stu_lastName;
    }

    public Date getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
    }

    public Date getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public int getRos_statusCode() {
        return Ros_statusCode;
    }

    public void setRos_statusCode(int ros_statusCode) {
        Ros_statusCode = ros_statusCode;
    }

    public String getRos_statDescription() {
        return Ros_statDescription;
    }

    public void setRos_statDescription(String ros_statDescription) {
        Ros_statDescription = ros_statDescription;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "StudentRosterReport{" +
                "studentID=" + studentID +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", classStartDate=" + classStartDate +
                ", classEndDate=" + classEndDate +
                ", Ros_statusCode=" + Ros_statusCode +
                ", Ros_statDescription='" + Ros_statDescription + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
