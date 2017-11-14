public class Guardian {
    private int guardianID;
    private int relationshipCode;
    private int statusCode;
    private String Guar_firstName;
    private String Guar_lastName;
    private String Guar_address;
    private  String Guar_zipcode;
    private String Guar_city;
    private int stateID;
    private int countryID;
    private String stateName;
    private String countryName;
    private String Guar_phoneNumber;
    private String Guar_notes;
    private String Guar_relatDescription;
    private  String Guar_statDescription;
    private int studentID;

    @Override
    public String toString() {
        return "Guardian{" +
                "guardianID=" + guardianID +
                ", relationshipCode=" + relationshipCode +
                ", statusCode=" + statusCode +
                ", Guar_firstName='" + Guar_firstName + '\'' +
                ", Guar_lastName='" + Guar_lastName + '\'' +
                ", Guar_address='" + Guar_address + '\'' +
                ", Guar_zipcode='" + Guar_zipcode + '\'' +
                ", Guar_city='" + Guar_city + '\'' +
                ", stateID=" + stateID +
                ", countryID=" + countryID +
                ", stateName='" + stateName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", Guar_phoneNumber='" + Guar_phoneNumber + '\'' +
                ", Guar_notes='" + Guar_notes + '\'' +
                ", Guar_relatDescription='" + Guar_relatDescription + '\'' +
                ", Guar_statDescription='" + Guar_statDescription + '\'' +
                ", studentID=" + studentID +
                '}';
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getGuardianID() {
        return guardianID;
    }

    public void setGuardianID(int guardianID) {
        this.guardianID = guardianID;
    }

    public int getRelationshipCode() {
        return relationshipCode;
    }

    public void setRelationshipCode(int relationshipCode) {
        this.relationshipCode = relationshipCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getGuar_firstName() {
        return Guar_firstName;
    }

    public void setGuar_firstName(String guar_firstName) {
        Guar_firstName = guar_firstName;
    }

    public String getGuar_lastName() {
        return Guar_lastName;
    }

    public void setGuar_lastName(String guar_lastName) {
        Guar_lastName = guar_lastName;
    }

    public String getGuar_address() {
        return Guar_address;
    }

    public void setGuar_address(String guar_address) {
        Guar_address = guar_address;
    }

    public String getGuar_zipcode() {
        return Guar_zipcode;
    }

    public void setGuar_zipcode(String guar_zipcode) {
        Guar_zipcode = guar_zipcode;
    }

    public String getGuar_city() {
        return Guar_city;
    }

    public void setGuar_city(String guar_city) {
        Guar_city = guar_city;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getGuar_phoneNumber() {
        return Guar_phoneNumber;
    }

    public void setGuar_phoneNumber(String guar_phoneNumber) {
        Guar_phoneNumber = guar_phoneNumber;
    }

    public String getGuar_notes() {
        return Guar_notes;
    }

    public void setGuar_notes(String guar_notes) {
        Guar_notes = guar_notes;
    }

    public String getGuar_relatDescription() {
        return Guar_relatDescription;
    }

    public void setGuar_relatDescription(String guar_relatDescription) {
        Guar_relatDescription = guar_relatDescription;
    }

    public String getGuar_statDescription() {
        return Guar_statDescription;
    }

    public void setGuar_statDescription(String guar_statDescription) {
        Guar_statDescription = guar_statDescription;
    }
}
