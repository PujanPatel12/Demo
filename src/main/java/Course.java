public class Course {
    private int courseID;
    private int Cour_statusCode;
    private String courseName;
    private String courseDescription;
    private String  Cour_statDescription;

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", Cour_statusCode=" + Cour_statusCode +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", Cour_statDescription='" + Cour_statDescription + '\'' +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCour_statusCode() {
        return Cour_statusCode;
    }

    public void setCour_statusCode(int cour_statusCode) {
        Cour_statusCode = cour_statusCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCour_statDescription() {
        return Cour_statDescription;
    }

    public void setCour_statDescription(String cour_statDescription) {
        Cour_statDescription = cour_statDescription;
    }
}
