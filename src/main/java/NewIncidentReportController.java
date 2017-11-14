import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/13/2017.
 */
public class NewIncidentReportController extends MainController {

    public ChoiceBox studentinfochoicebox;
    public ChoiceBox incidentnamechoicebox;
    public DatePicker incidentdatepicker;

    public void initialize() throws SQLException{

        studentidc();


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
            studentname = resultSet1.getString("Stu_firstName");
            lastname = resultSet1.getString("Stu_lastName");
            //  Studentcombobox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            studentinfochoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
        }
        resultSet1.close();
        statement1.close();


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT incidentType FROM Incident_Type");
        String in;
        while(resultSet.next()){
            in = resultSet.getString("incidentType");
            incidentnamechoicebox.getItems().addAll(in);
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

    public void savebuttonpressed(ActionEvent actionEvent) throws SQLException {
        save();
    }

    public void save() throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        int student = studentinfochoicebox.getSelectionModel().getSelectedIndex()+1;
        int incident = incidentnamechoicebox.getSelectionModel().getSelectedIndex()+1;
        Date incidentdate = Date.valueOf(incidentdatepicker.getValue());
        String sql ="Insert into Incident_Report(incidentTypeID,studentID,incidentDate) values (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,incident);
            preparedStatement.setInt(2,student);
            preparedStatement.setDate(3,incidentdate);
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Added new Incident Report");
            alert.setHeaderText("Added new Incident Report");
            alert.setContentText("Added Incident for Student : " + student);
            alert.showAndWait();
        }
    }
}
