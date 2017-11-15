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

/**
 * Created by Ryan on 11/14/2017.
 */
public class UpdateTournamentResultController extends MainController {

    public ChoiceBox studentchoicebox;
    public ChoiceBox eventnamechoicebox;
    public TextField indicatortextfield;
    public TextField finalplacementtextfield;
    public Button UpdateTournamentButton;
    public Text idtext;

    public void  initialize() throws SQLException {
        idtext.setVisible(false);
        student();
        tournamenteventname();
    }

    private void tournamenteventname() throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT DISTINCT tournamentEventID,tournmentEventName from Tournament_Event");
        int id;
        String name;

        while (resultSet1.next()) {

            name = resultSet1.getString("tournmentEventName");
            eventnamechoicebox.getItems().addAll(name);
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

            studentchoicebox.getItems().addAll(id);
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

    public void updatepressed(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String t  = idtext.getText();
        int id = Integer.parseInt(t);
        int studentid = studentchoicebox.getSelectionModel().getSelectedIndex()+1;
        int te = eventnamechoicebox.getSelectionModel().getSelectedIndex()+1;
        int ind = Integer.parseInt(indicatortextfield.getText());
        int finalplace = Integer.parseInt(finalplacementtextfield.getText());

        String sql = "UPDATE RealFinalFinal.dbo.Event_Result SET studentID =?, tournamentEventID =?, indicator =?, finalPlacement =? where eventResultID="+id;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,studentid);
            preparedStatement.setInt(2,te);
            preparedStatement.setInt(3,ind);
            preparedStatement.setInt(4,finalplace);
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully Updated");
            alert.setHeaderText("Updated Tournament Result");
            alert.setContentText("Successfully updated Tournament Event Result for Student  : " + studentid);
            alert.showAndWait();


        }

    }
}
