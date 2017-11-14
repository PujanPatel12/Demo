import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by Ryan on 11/10/2017.
 */
public class ManageReportsController extends MainController {

    public void OneStudentGoalReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("OneStudentGoalReport.fxml"));
        createStage(root8, actionEvent);
    }

    public void OneStudentTournamentResultsReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("OneStudentTournamentResults.fxml"));
        createStage(root8, actionEvent);
    }

    public void OneStudentTestResultsReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("OneStudentTestResults.fxml"));
        createStage(root8, actionEvent);
    }

    public void StudentRosterReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("StudentRosterReport.fxml"));
        createStage(root8, actionEvent);
    }

    public void StudentsUSFARatingReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("StudentsUSFARatingReport.fxml"));
        createStage(root8, actionEvent);
    }

    public void StudentsTestResultsPerWeaponReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("StudentsWithUnsatisfactoryTestsPerWeapon.fxml"));
        createStage(root8, actionEvent);
    }

    public void OneStudentPrivateLessonsVsGoalsReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("OneStudentPrivateLessonsVsGoals.fxml"));
        createStage(root8, actionEvent);
    }

    public void OneStudentPrivateLessonsVsTournamentResultsReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("OneStudentPrivateLessonsVsTourneyResults.fxml"));
        createStage(root8, actionEvent);
    }

    public void OneStudentClassesVsTestResultsReport(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("OneStudentClassesVsTestResults.fxml"));
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
}
