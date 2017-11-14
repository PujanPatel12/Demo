import java.sql.Date;

public class Onestudentonegoaloneprivate {
    private int studentID;
    private String Stu_lastName;
    private String Stu_firstName;
    private int privateLessonID;
    private String privateLessonName;
    private Date privateLessonDate;
    private String goalName;
    private Date dateEntered;
    private Date dateCompleted;

    @Override
    public String toString() {
        return "Onestudentonegoaloneprivate{" +
                "studentID=" + studentID +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", privateLessonID=" + privateLessonID +
                ", privateLessonName='" + privateLessonName + '\'' +
                ", privateLessonDate=" + privateLessonDate +
                ", goalName='" + goalName + '\'' +
                ", dateEntered=" + dateEntered +
                ", dateCompleted=" + dateCompleted +
                '}';
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public int getPrivateLessonID() {
        return privateLessonID;
    }

    public void setPrivateLessonID(int privateLessonID) {
        this.privateLessonID = privateLessonID;
    }

    public String getPrivateLessonName() {
        return privateLessonName;
    }

    public void setPrivateLessonName(String privateLessonName) {
        this.privateLessonName = privateLessonName;
    }

    public Date getPrivateLessonDate() {
        return privateLessonDate;
    }

    public void setPrivateLessonDate(Date privateLessonDate) {
        this.privateLessonDate = privateLessonDate;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
