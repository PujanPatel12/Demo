import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by Ryan on 11/3/2017.
 */
public class ManageIncidentController extends MainController {

    public void ManageNewIncident(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("NewIncident.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageIncidentList(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("IncidentList.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageNewIncidentReport(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("NewIncidentReport.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageIncidentReportList(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("IncidentReportList.fxml"));
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
