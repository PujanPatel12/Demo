import java.sql.Date;

public class OneStudentGoalReport {
    private int studentID;
    private String Stu_firstName;
    private String Stu_lastName;
    private Date dateEntered;
    private Date dateCompleted;
    private int goalID;
    private int weaponID;
    private String weaponName;
    private String goalName;

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

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    @Override
    public String toString() {
        return "OneStudentGoalReport{" +
                "studentID=" + studentID +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", dateEntered=" + dateEntered +
                ", dateCompleted=" + dateCompleted +
                ", goalID=" + goalID +
                ", weaponID=" + weaponID +
                ", weaponName='" + weaponName + '\'' +
                ", goalName='" + goalName + '\'' +
                '}';
    }
}
