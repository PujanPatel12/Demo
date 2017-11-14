import java.sql.Date;

public class Class {
    private int classID;
    private int courseID;
    private int Cla_statCode;
    private int sectionNumber;
    private Date classStartDate;
    private Date classEndDate;
    private String courseName;
    private String Cla_statDescription;






    @Override
    public String toString() {
        return "Class{" +
                "classID=" + classID +
                ", courseID=" + courseID +
                ", Cla_statCode=" + Cla_statCode +
                ", sectionNumber=" + sectionNumber +
                ", classStartDate=" + classStartDate +
                ", classEndDate=" + classEndDate +
                ", Cla_statDescription='" + Cla_statDescription + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public String getCla_statDescription() {
        return Cla_statDescription;
    }

    public void setCla_statDescription(String cla_statDescription) {
        Cla_statDescription = cla_statDescription;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }




    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCla_statCode() {
        return Cla_statCode;
    }

    public void setCla_statCode(int cla_statCode) {
        Cla_statCode = cla_statCode;
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


}
