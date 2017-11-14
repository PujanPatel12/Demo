import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Pujan on 11/13/2017.
 */
public class UpdateIncidentReportController extends MainController {

    public ChoiceBox studentchoicebox;
    public ChoiceBox incidentchoiebox;
    public DatePicker incidentdatepicker;
    public Text incidentrepid;


    public void initialize() throws SQLException {

        studentidc();
        incidentrepid.setVisible(false);


    }
    public void studentidc() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT studentID,Stu_firstName,Stu_lastName from Student");
        String studentname;
        String lastname;
        int id;

        while (resultSet1.next()) {
            id = resultSet1.getInt("studentID");
           // studentname = resultSet1.getString("Stu_firstName");
            //lastname = resultSet1.getString("Stu_lastName");
            //  Studentcombobox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            studentchoicebox.getItems().addAll(id);
        }
        resultSet1.close();
        statement1.close();


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT incidentType FROM Incident_Type");
        String in;
        while(resultSet.next()){
            in = resultSet.getString("incidentType");
            incidentchoiebox.getItems().addAll(in);
        }
        resultSet.close();
        statement.close();
        connection.close();




    }

    public void ManageIncidentReportList(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("IncidentReportList.fxml"));
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
        updatevalues();
    }

    public void updatevalues() throws SQLException {
        String i = incidentrepid.getText();
        int id = Integer.parseInt(i);
        int studentid = studentchoicebox.getSelectionModel().getSelectedIndex()+1;
        int inc = incidentchoiebox.getSelectionModel().getSelectedIndex()+1;
        Date date = Date.valueOf(incidentdatepicker.getValue());
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql ="UPDATE Incident_Report set IncidentTypeID =?, studentID =?, incidentDate=? WHERE incidentReportID ="+ id;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,inc);
            preparedStatement.setInt(2,studentid);
            preparedStatement.setDate(3, date);
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updated Incident Report");
            alert.setHeaderText("Successful");
            alert.setContentText("Successfully Updated Incident Report");
            alert.showAndWait();
        }
    }
}
