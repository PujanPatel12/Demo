import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/14/2017.
 */
public class NewTournamentResultController extends MainController {

    public ChoiceBox studentchoicebox;
    public ChoiceBox tournamenteventnamechoicebox;
    public TextField indicatortextfield;
    public TextField finalplacementtextfield;
    public TextField eventsizetextfield;


    public void initialize() throws SQLException{
        student();
        tournamenteventname();


    }

    private void tournamenteventname() throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT DISTINCT tournamentEventID,tournamentEventName from Tournament_Event");
        int id;
        String name;

        while (resultSet1.next()) {
            id = resultSet1.getInt("tournamentEventID");
            name = resultSet1.getString("tournamentEventName");
            tournamenteventnamechoicebox.getItems().addAll(id + " | " + name);
        }
        resultSet1.close();
        statement1.close();

    }

    private void student() throws SQLException {
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
            studentchoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
        }
        resultSet1.close();
        statement1.close();


        connection.close();
    }

    public void ManageTournamentResultList(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("TournamentResultList.fxml"));
        createStage(root4, actionEvent);
    }

    public void goTournamentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("ManageTournament.fxml"));
        createStage(root4, actionEvent);
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

        int student = studentchoicebox.getSelectionModel().getSelectedIndex()+1;
        int te = tournamenteventnamechoicebox.getSelectionModel().getSelectedIndex()+1;
        int ind = Integer.parseInt(indicatortextfield.getText());
        int  finalplacement = Integer.parseInt(finalplacementtextfield.getText());

        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "Insert into Event_Result(studentID, tournamentEventID, indicator, finalPlacement)  VALUES  (?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,student);
            preparedStatement.setInt(2,te);
            preparedStatement.setInt(3,ind);
            preparedStatement.setInt(4,finalplacement);
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Added new Tournament Result");
            alert.setHeaderText("Added new Tournament Result");
            alert.setContentText("Successfully added new Tournament Result for Student : " + student);
            alert.showAndWait();
        }

        connection.close();

    }
}
