import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ryan on 11/13/2017.
 */
public class NewIncidentController extends MainController {

    public Button savebuttonpressed;
    public TextField incidentname;
    public TextArea incidenttextarea;



    public void ManageIncidentList(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("IncidentList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goIncidentMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageIncident.fxml"));
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

    public void savepressed(ActionEvent actionEvent)  throws SQLException{
    getdata();

    }

    public void getdata() throws SQLException{
        String incidentName = incidentname.getText();
        String description = incidenttextarea.getText();
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "Insert into Incident_Type(incidentType,incidentTypeDescription) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,incidentName);
            preparedStatement.setString(2,description);
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Added new Incident");
            alert.setHeaderText("Added new Incident");
            alert.setContentText("Added" + incidentName + "Successfully");
            alert.showAndWait();
        }
        connection.close();

    }
}
