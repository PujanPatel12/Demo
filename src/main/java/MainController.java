import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by pujan on 9/30/17.
 */


public class MainController {


    public void ManageStudentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageStudent.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageInstructorMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageInstructor.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageGuardianMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageGuardian.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageCourseMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageCourse.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageClassMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageClass.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageTournamentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageTournament.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageGoalMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageGoal.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageTestMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageTest.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageIncidentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageIncident.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageReports(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageReports.fxml"));
        createStage(root8, actionEvent);
    }

    public void goHome(ActionEvent actionEvent) throws IOException
    {
        Parent root9 = FXMLLoader.load(getClass().getResource("mainscreen.fxml"));
        createStage(root9, actionEvent);
    }

    public void LogOut(ActionEvent actionEvent) throws IOException
    {
        Parent root9 = FXMLLoader.load(getClass().getResource("Login.fxml"));
        createStage(root9, actionEvent);
    }

    public void createStage(Parent root, ActionEvent actionEvent)
    {
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



}
