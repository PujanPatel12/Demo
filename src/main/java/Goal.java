import java.sql.Date;

public class Goal {

    private int goalID;
    private int studentID;
    private String Stu_firstName;
    private String Stu_lastName;
    private String goalName;
    private int weaponID;
    private Date dateEntered;
    private Date dateCompleted;
    private String goalDescrption;
    private String weaponName;

    @Override
    public String toString() {
        return "Goal{" +
                "goalID=" + goalID +
                ", studentID=" + studentID +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", goalName='" + goalName + '\'' +
                ", weaponID=" + weaponID +
                ", dateEntered=" + dateEntered +
                ", dateCompleted=" + dateCompleted +
                ", goalDescrption='" + goalDescrption + '\'' +
                ", weaponName='" + weaponName + '\'' +
                '}';
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getGoalDescrption() {
        return goalDescrption;
    }

    public void setGoalDescrption(String goalDescrption) {
        this.goalDescrption = goalDescrption;
    }


    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

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

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
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
