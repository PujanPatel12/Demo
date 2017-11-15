import java.sql.Date;

public class OneStuClassvstest {
    private String Stu_lastName;
    private String Stu_firstName;
    private String courseName;
    private int sectionNumber;
    private Date classStartDate;
    private Date classEndDate;
    private String testName;
    private Date testDate;
    private int studentScore;
    private int finalScore;
    private int highestTestScore;

    @Override
    public String toString() {
        return "OneStuClassvstest{" +
                "Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", sectionNumber=" + sectionNumber +
                ", classStartDate=" + classStartDate +
                ", classEndDate=" + classEndDate +
                ", testName='" + testName + '\'' +
                ", testDate=" + testDate +
                ", studentScore=" + studentScore +
                ", finalScore=" + finalScore +
                ", highestTestScore=" + highestTestScore +
                '}';
    }

    public int getHighestTestScore() {
        return highestTestScore;
    }

    public void setHighestTestScore(int highestTestScore) {
        this.highestTestScore = highestTestScore;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

}
