import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Pujan on 11/7/2017.
 */
public class UpdateIncidentController extends MainController {

    public TextField incidentnamettf;
    public TextArea inccidentdesta;
    public Text incidentIDtext;

    public void initialize(){
        incidentIDtext.setVisible(false);
    }

    public void ManageIncidentList(ActionEvent actionEvent) throws IOException
    {
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

    public void updatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        update();
    }

    public void update() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String i = incidentIDtext.getText();
        int incidentid = Integer.parseInt(i);
        String sql = "UPDATE Incident_Type set IncidentType =?, IncidentTypeDescription =? WHERE incidentTypeID =" + incidentid;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,incidentnamettf.getText());
            preparedStatement.setString(2,inccidentdesta.getText());
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Incident ");
            alert.setHeaderText("Succuessfully Updated");
            alert.setContentText("Successfully Updated Incident");
            alert.showAndWait();
        }
        connection.close();


    }
}
