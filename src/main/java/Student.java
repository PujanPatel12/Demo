import java.sql.Date;

/**
 * Created by pujan on 9/30/17.
 */

public class Student {
    private int StudentID;
    private int communicationID;
    private int Stu_statusCode;
    private String Stu_firstName;
    private String Stu_lastName;
    private String Stu_sex;
    private Date Stu_dateOfBirth;
    private String Stu_address;
    private String Stu_city;
    private String Stu_zipcode;
    private int stateID;
    private int countryID;
    private String Stu_phoneNumber;
    private String Stu_notes;
    private String weaponName;
    private String stateName;
    private String  Stu_StatDescription;
    private String countryName;

    private String communicationType;

    public String getStu_notes() {
        return Stu_notes;
    }



    @Override
    public String toString() {
        return "Student{" +
                "StudentID=" + StudentID +
                ", communicationID=" + communicationID +
                ", Stu_statusCode=" + Stu_statusCode +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_sex='" + Stu_sex + '\'' +
                ", Stu_dateOfBirth=" + Stu_dateOfBirth +
                ", Stu_address='" + Stu_address + '\'' +
                ", Stu_city='" + Stu_city + '\'' +
                ", Stu_zipcode='" + Stu_zipcode + '\'' +
                ", stateID=" + stateID +
                ", countryID=" + countryID +
                ", Stu_phoneNumber='" + Stu_phoneNumber + '\'' +
                ", Stu_notes='" + Stu_notes + '\'' +
                ", weaponName='" + weaponName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", Stu_StatDescription='" + Stu_StatDescription + '\'' +
                ", countryName='" + countryName + '\'' +
                ", communicationType='" + communicationType + '\'' +
                '}';
    }

    public void setStu_notes(String stu_notes) {
        Stu_notes = stu_notes;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public String getStu_StatDescription() {
        return Stu_StatDescription;
    }

    public void setStu_StatDescription(String stu_StatDescription) {
        Stu_StatDescription = stu_StatDescription;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public int getCommunicationID() {
        return communicationID;
    }

    public void setCommunicationID(int communicationID) {
        this.communicationID = communicationID;
    }

    public int getStu_statusCode() {
        return Stu_statusCode;
    }

    public void setStu_statusCode(int stu_statusCode) {
        Stu_statusCode = stu_statusCode;
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

    public String getStu_sex() {
        return Stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        Stu_sex = stu_sex;
    }

    public Date getStu_dateOfBirth() {
        return Stu_dateOfBirth;
    }

    public void setStu_dateOfBirth(Date stu_dateOfBirth) {
        Stu_dateOfBirth = stu_dateOfBirth;
    }

    public String getStu_address() {
        return Stu_address;
    }

    public void setStu_address(String stu_address) {
        Stu_address = stu_address;
    }

    public String getStu_city() {
        return Stu_city;
    }

    public void setStu_city(String stu_city) {
        Stu_city = stu_city;
    }

    public String getStu_zipcode() {
        return Stu_zipcode;
    }

    public void setStu_zipcode(String stu_zipcode) {
        Stu_zipcode = stu_zipcode;
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

    public String getStu_phoneNumber() {
        return Stu_phoneNumber;
    }

    public void setStu_phoneNumber(String stu_phoneNumber) {
        Stu_phoneNumber = stu_phoneNumber;
    }



    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
