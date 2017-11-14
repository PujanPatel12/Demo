import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

public class UpdateCourseController extends MainController {

    public TextField CourseNameTextfield;
    public TextField CourseDescription_textfield;
    public ChoiceBox Cour_Status_Choicebox;
    public Button update;
    public Text courseidtextfield;


    public void initialize() throws SQLException{
        courseidtextfield.setVisible(false);
        Connection connection = DBHelper.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select Cour_statusCode,Cour_statDescription FROM Course_Status");
        int course_status_code;
        String course_status_Description;
        while (resultSet.next()) {
            course_status_code = resultSet.getInt("Cour_statusCode");
            course_status_Description= resultSet.getString("Cour_statDescription");

            Cour_Status_Choicebox.getItems().add(course_status_Description);
        }

        resultSet.close();
    }



    public void ManageCourseList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("CourseList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goCourseMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageCourse.fxml"));
        createStage(root8, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }

    @Override
    public void createStage(Parent root, ActionEvent actionEvent) {
        super.createStage(root, actionEvent);
    }

    public void updatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        int coursestatus = Cour_Status_Choicebox.getSelectionModel().getSelectedIndex()+1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String courses = courseidtextfield.getText();
        int courseid = Integer.parseInt(courses);
        String sql = "UPDATE Course Set Cour_statusCode =?, courseName =?, courseDescription = ? WHERE courseID= " + courseid;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,coursestatus);
            preparedStatement.setString(2,CourseNameTextfield.getText());
            preparedStatement.setString(3,CourseDescription_textfield.getText());
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Course");
            alert.setHeaderText("update");
            alert.setContentText("Update Successful on Course ID  :" + courseid);
            alert.showAndWait();


        }



    }
}
