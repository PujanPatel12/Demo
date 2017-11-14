import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 10/28/2017.
 */

public class NewCourseController extends MainController {

    @FXML
    public TextField CourseNameTextfield;
    @FXML
    public TextField CourseDescription_textfield;
    @FXML
    public Button Save;
    @FXML
    public ChoiceBox<String> Cour_Status_Choicebox;



    public void initialize() throws SQLException {

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


// HAVE TO FIX COURSE STATUS DROP DOWN BUT INSERT WORKS, I DO NOT KNOW HOW TO GET VALUE FROM CHOICEBOX BUT IT INSERTS THE
    //VALUE "1" FOR COURSE STATUS WHEN WE MAKE IT
    public void save_Button_Pressed(ActionEvent actionEvent) throws SQLException {
        int coursestatus = Cour_Status_Choicebox.getSelectionModel().getSelectedIndex() + 1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "Insert into Course (Cour_statusCode, courseName, courseDescription) VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,coursestatus);
            preparedStatement.setString(2,CourseNameTextfield.getText());
            preparedStatement.setString(3,CourseDescription_textfield.getText());
            preparedStatement.execute();
            preparedStatement.close();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("New Course");
        alert.setHeaderText("Added new Course");
        alert.setContentText("Successfully added new Course");
        alert.showAndWait();


    }

    public void ManageCourseList(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("CourseList.fxml"));
        createStage(root4, actionEvent);
    }

    public void goCourseMenu(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("ManageCourse.fxml"));
        createStage(root4, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }


}

