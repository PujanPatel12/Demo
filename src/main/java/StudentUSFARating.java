public class StudentUSFARating {
    private int studentID;
    private int USFARatingCode;
    private String weaponName;
    private int weaponID;
    private String Stu_lastName;
    private String Stu_firstName;
    private String USFARatingDescription;

    public String getUSFARatingDescription() {
        return USFARatingDescription;
    }

    public void setUSFARatingDescription(String USFARatingDescription) {
        this.USFARatingDescription = USFARatingDescription;
    }

    @Override
    public String toString() {
        return "StudentUSFARating{" +
                "studentID=" + studentID +
                ", USFARatingCode=" + USFARatingCode +
                ", weaponName='" + weaponName + '\'' +
                ", weaponID=" + weaponID +
                ", Stu_lastName='" + Stu_lastName + '\'' +
                ", Stu_firstName='" + Stu_firstName + '\'' +
                ", USFARatingDescription='" + USFARatingDescription + '\'' +
                '}';
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getUSFARatingCode() {
        return USFARatingCode;
    }

    public void setUSFARatingCode(int USFARatingCode) {
        this.USFARatingCode = USFARatingCode;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
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

}
