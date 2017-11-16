public class OneStudentTournamentResult {
    private int studentID;
    private String Stu_firstName;
    private String Stu_lastName;
    private int eventSize;
    private String tournamentName;
    private String eventRatingName;
    private String finalPlacement;

    private String tournmentEventName;
    private String tournamentEventName;

    public String getTournamentEventName() {
        return tournamentEventName;
    }

    public void setTournamentEventName(String tournamentEventName) {
        this.tournamentEventName = tournamentEventName;
    }

    @Override
    public String toString() {
        return "OneStudentTournamentResult{" +
                "studentID=" + studentID +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", eventSize=" + eventSize +
                ", tournamentName='" + tournamentName + '\'' +
                ", eventRatingName='" + eventRatingName + '\'' +
                ", finalPlacement='" + finalPlacement + '\'' +
                ", tournmentEventName='" + tournmentEventName + '\'' +
                ", tournamentEventName='" + tournamentEventName + '\'' +
                '}';
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

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getEventRatingName() {
        return eventRatingName;
    }

    public void setEventRatingName(String eventRatingName) {
        this.eventRatingName = eventRatingName;
    }

    public String getFinalPlacement() {
        return finalPlacement;
    }

    public void setFinalPlacement(String finalPlacement) {
        this.finalPlacement = finalPlacement;
    }

    public String getTournmentEventName() {
        return tournmentEventName;
    }

    public void setTournmentEventName(String tournmentEventName) {
        this.tournmentEventName = tournmentEventName;
    }
}
