/**
 * Created by pujan on 10/12/17.
 */
public class Communication_Medium {
    private  int communicationID;
    private String communicationType;



    public int getCommunicationID() {
        return communicationID;
    }

    public void setCommunicationID(int communicationID) {
        this.communicationID = communicationID;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    @Override
    public String toString() {
        return "Communication_Medium{" +
                "communicationID=" + communicationID +
                ", communicationType='" + communicationType + '\'' +
                '}';
    }
}
