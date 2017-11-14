import java.sql.Date;

public class Tournament {
    private int tournamentID;
    private String tournamentName;
    private String importstartDate;
    private String importendDate;
    private Date startDate;
    private Date endDate;
    private String tournamentType;

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



    public String getImportstartDate() {
        return importstartDate;
    }

    public void setImportstartDate(String importstartDate) {
        this.importstartDate = importstartDate;
    }

    public String getImportendDate() {
        return importendDate;
    }

    public void setImportendDate(String importendDate) {
        this.importendDate = importendDate;
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



    public String getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(String tournamentType) {
        this.tournamentType = tournamentType;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentID=" + tournamentID +
                ", tournamentName='" + tournamentName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tournamentType='" + tournamentType + '\'' +
                '}';
    }





}
