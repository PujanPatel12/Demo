import java.sql.Date;

public class Instructor {
    private int instructorID;
    private int Ins_statusCode;
    private String Ins_firstName;
    private String Ins_lastName;
    private String Ins_sex;
    private Date Ins_dateOfBirth;
    private String Ins_address;
    private String Ins_city;
    private int Ins_zipcode;
    private int stateID;
    private int CountryID;
    private String Ins_phoneNumber;
    private String Ins_statDescription;
    private String stateName;
    private String countryName;

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorID=" + instructorID +
                ", Ins_statusCode=" + Ins_statusCode +
                ", Ins_firstName='" + Ins_firstName + '\'' +
                ", Ins_lastName='" + Ins_lastName + '\'' +
                ", Ins_sex='" + Ins_sex + '\'' +
                ", Ins_dateOfBirth=" + Ins_dateOfBirth +
                ", Ins_address='" + Ins_address + '\'' +
                ", Ins_city='" + Ins_city + '\'' +
                ", Ins_zipcode=" + Ins_zipcode +
                ", stateID=" + stateID +
                ", CountryID=" + CountryID +
                ", Ins_phoneNumber='" + Ins_phoneNumber + '\'' +
                ", Ins_statDescription='" + Ins_statDescription + '\'' +
                ", stateName='" + stateName + '\'' +
                ", countryName='" + countryName + '\'' +
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

    public String getIns_statDescription() {
        return Ins_statDescription;
    }

    public void setIns_statDescription(String ins_statDescription) {
        Ins_statDescription = ins_statDescription;
    }


    public String getIns_phoneNumber() {
        return Ins_phoneNumber;
    }

    public void setIns_phoneNumber(String ins_phoneNumber) {
        Ins_phoneNumber = ins_phoneNumber;
    }


    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getIns_statusCode() {
        return Ins_statusCode;
    }

    public void setIns_statusCode(int ins_statusCode) {
        Ins_statusCode = ins_statusCode;
    }

    public String getIns_firstName() {
        return Ins_firstName;
    }

    public void setIns_firstName(String ins_firstName) {
        Ins_firstName = ins_firstName;
    }

    public String getIns_lastName() {
        return Ins_lastName;
    }

    public void setIns_lastName(String ins_lastName) {
        Ins_lastName = ins_lastName;
    }

    public String getIns_sex() {
        return Ins_sex;
    }

    public void setIns_sex(String ins_sex) {
        Ins_sex = ins_sex;
    }

    public Date getIns_dateOfBirth() {
        return Ins_dateOfBirth;
    }

    public void setIns_dateOfBirth(Date ins_dateOfBirth) {
        Ins_dateOfBirth = ins_dateOfBirth;
    }

    public String getIns_address() {
        return Ins_address;
    }

    public void setIns_address(String ins_address) {
        Ins_address = ins_address;
    }

    public String getIns_city() {
        return Ins_city;
    }

    public void setIns_city(String ins_city) {
        Ins_city = ins_city;
    }

    public int getIns_zipcode() {
        return Ins_zipcode;
    }

    public void setIns_zipcode(int ins_zipcode) {
        Ins_zipcode = ins_zipcode;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }


}
