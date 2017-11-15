import java.sql.Date;

public class TournamentResult {
    private int eventResultID;
    private int studentID;
    private int tournamentEventID;
    private int indicator;
    private int finalPlacement;
    private int tournamentID;
    private String tournamentName;
    private Date startDate;
    private Date endDate;
    private String tournamentType;
    private String Stu_lastName;
    private String Stu_firstName;
    private int eventSize;
    private String tournmentEventName;

    public int getEventResultID() {
        return eventResultID;
    }

    public void setEventResultID(int eventResultID) {
        this.eventResultID = eventResultID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTournamentEventID() {
        return tournamentEventID;
    }

    public void setTournamentEventID(int tournamentEventID) {
        this.tournamentEventID = tournamentEventID;
    }

    public int getIndicator() {
        return indicator;
    }

    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }

    public int getFinalPlacement() {
        return finalPlacement;
    }

    public void setFinalPlacement(int finalPlacement) {
        this.finalPlacement = finalPlacement;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(String tournamentType) {
        this.tournamentType = tournamentType;
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

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public String getTournmentEventName() {
        return tournmentEventName;
    }

    public void setTournmentEventName(String tournmentEventName) {
        this.tournmentEventName = tournmentEventName;
    }

    @Override
    public String toString() {
        return "TournamentResult{" +
                "eventResultID=" + eventResultID +
                ", studentID=" + studentID +
                ", tournamentEventID=" + tournamentEventID +
                ", indicator=" + indicator +
                ", finalPlacement=" + finalPlacement +
                ", tournamentID=" + tournamentID +
                ", tournamentName='" + tournamentName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tournamentType='" + tournamentType + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", eventSize=" + eventSize +
                ", tournmentEventName='" + tournmentEventName + '\'' +
                '}';
    }
}
