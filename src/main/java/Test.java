import java.sql.Date;

public class Test {
    private int testID;
    private int testResultID;
    private int weaponID;
    private int highestTestScore;
    private String testDescription;
    private int studentID;
    private Date testDate;
    private int studentScore;
    private float finalScore;
    private String resultNotes;
    private String testName;
    private String weaponName;
    private String Stu_firstName;
    private String Stu_lastName;

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getTestResultID() {
        return testResultID;
    }

    public void setTestResultID(int testResultID) {
        this.testResultID = testResultID;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    public int getHighestTestScore() {
        return highestTestScore;
    }

    public void setHighestTestScore(int highestTestScore) {
        this.highestTestScore = highestTestScore;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public int getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(int studentScore) {
        this.studentScore = studentScore;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    public String getResultNotes() {
        return resultNotes;
    }

    public void setResultNotes(String resultNotes) {
        this.resultNotes = resultNotes;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
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

    @Override
    public String toString() {
        return "Test{" +
                "testID=" + testID +
                ", testResultID=" + testResultID +
                ", weaponID=" + weaponID +
                ", highestTestScore=" + highestTestScore +
                ", testDescription='" + testDescription + '\'' +
                ", studentID=" + studentID +
                ", testDate=" + testDate +
                ", studentScore=" + studentScore +
                ", finalScore=" + finalScore +
                ", resultNotes='" + resultNotes + '\'' +
                ", testName='" + testName + '\'' +
                ", weaponName='" + weaponName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                '}';
    }
}
