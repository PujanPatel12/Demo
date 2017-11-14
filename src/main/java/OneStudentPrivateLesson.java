import java.sql.Date;

public class OneStudentPrivateLesson {
    private int studentID;
    private String Stu_lastName;
    private String Stu_firstName;
    private int privateLessonID;
    private String privateLessonName;
    private Date privateLessonDate;
    private String tournamentName;
    private Date tournamentDate;

    @Override
    public String toString() {
        return "OneStudentPrivateLesson{" +
                "studentID=" + studentID +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", privateLessonID=" + privateLessonID +
                ", privateLessonName='" + privateLessonName + '\'' +
                ", privateLessonDate=" + privateLessonDate +
                ", tournamentName='" + tournamentName + '\'' +
                ", tournamentDate=" + tournamentDate +
                ", startDate=" + startDate +
                ", tournamentEventName='" + tournamentEventName + '\'' +
                ", eventSize=" + eventSize +
                ", finalPlacement=" + finalPlacement +
                '}';
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private Date startDate;
    private String tournamentEventName;
    private int eventSize;
    private int finalPlacement;

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

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Date getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(Date tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

    public String getTournamentEventName() {
        return tournamentEventName;
    }

    public void setTournamentEventName(String tournamentEventName) {
        this.tournamentEventName = tournamentEventName;
    }

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public int getFinalPlacement() {
        return finalPlacement;
    }

    public void setFinalPlacement(int finalPlacement) {
        this.finalPlacement = finalPlacement;
    }

}
