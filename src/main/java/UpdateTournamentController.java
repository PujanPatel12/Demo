import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateTournamentController extends MainController {

    public TextField tournamentName;
    public TextField tournamenttype;
    public DatePicker startDate;
    public DatePicker endDate;
    public Button tournamentlistbutton;
    public Text tournamentidtext;

    public void UpdateTournament(ActionEvent actionEvent) throws IOException, SQLException {
        tournamentidtext.setVisible(false);
        String touryid = tournamentidtext.getText();
        int tournamentID = Integer.parseInt(touryid);
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "UPDATE Tournament Set tournamentName =?, startDate =?, endDate =?, tournamentType =? WHERE TournamentID ="+ tournamentID;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,tournamentName.getText());
            preparedStatement.setDate(2, Date.valueOf(startDate.getValue()));
            preparedStatement.setDate(3, Date.valueOf(endDate.getValue()));
            preparedStatement.setString(4,tournamenttype.getText());
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Tournament");
            alert.setHeaderText("Update Tournament");
            alert.setContentText("Update Successful on TournamentID :" + tournamentID);
            alert.showAndWait();



        }
        connection.close();

    }

    public void ManageTournamentList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("TournamentList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goTournamentMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageTournament.fxml"));
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
