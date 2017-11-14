import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/13/2017.
 */
public class AddStudentToClassController extends MainController {

    public ChoiceBox studentnameidchoicebox;
    public TextField sectionnumbertextfield;
    public ChoiceBox coursechoicebox;
    public DatePicker dateaddeddatepicker;
    public ChoiceBox rosterstatuschoicebox;


    public void initialize() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT studentID,Stu_firstName,Stu_lastName from Student");
        String studentname;
        String lastname;
        int id;

        while (resultSet1.next()) {
            id = resultSet1.getInt("studentID");
            studentname = resultSet1.getString("Stu_firstName");
            lastname = resultSet1.getString("Stu_lastName");
            //  Studentcombobox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            studentnameidchoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
        }
        resultSet1.close();
        statement1.close();



        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("Select DISTINCT classID, Course.courseName , class.sectionNumber  FROM Class INNER  JOIN  Course ON Class.courseID = Course.courseID");
        String coursename;
        int courseid;
        int sectionnumber;
        while(resultSet2.next()){
        courseid = resultSet2.getInt("classID");
        coursename = resultSet2.getString("courseName");
        sectionnumber = resultSet2.getInt("sectionNumber");
        coursechoicebox.getItems().addAll(courseid + "| "+ coursename + " | " + sectionnumber);
        }
        resultSet2.close();
        statement2.close();


        Statement statement3 = connection.createStatement();
        ResultSet resultSet3 = statement3.executeQuery("Select * from Class_Roster_Status");
        String statusdescription;
        while(resultSet3.next()){
            statusdescription =resultSet3.getString("Ros_statDescription");
            rosterstatuschoicebox.getItems().addAll(statusdescription);

        }
        resultSet3.close();
        statement3.close();

    }


    public void goStudentRoster(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("StudentRosterReport.fxml"));
        createStage(root4, actionEvent);
    }

    public void goClassMenu(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("ManageClass.fxml"));
        createStage(root4, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }

    public void savebuttonpressed(ActionEvent actionEvent)  throws SQLException{
        save();

    }


    public void save() throws SQLException{
        int classid = coursechoicebox.getSelectionModel().getSelectedIndex()+1;
        int status = rosterstatuschoicebox.getSelectionModel().getSelectedIndex()+1;
        int studentid = studentnameidchoicebox.getSelectionModel().getSelectedIndex()+1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "Insert  INTO Class_Roster(classID,studentID,Ros_statusCode,dateAdded) values (?,?,?,?) ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,classid);
            preparedStatement.setInt(2,studentid);
            preparedStatement.setInt(3,status);
            preparedStatement.setDate(4, Date.valueOf(dateaddeddatepicker.getValue()));
            preparedStatement.execute();
            preparedStatement.close();

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successfully added Student");
        alert.setHeaderText("Added Student to Classed");
        alert.setContentText("Successfully Added Student : " + studentid + " INTO Class :" + classid);
        alert.showAndWait();

    }
}
