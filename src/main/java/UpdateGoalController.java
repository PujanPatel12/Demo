import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateGoalController extends MainController {

    public TextField studentidtextfield;
    public Label goalidtext;
    public Button UpdateButton;
    public TextField goalName;
    public DatePicker datecompleted;
    public DatePicker dateentered;
    public ChoiceBox weapon;
    public TextArea goaldes;

    public void initialize() throws SQLException {
        goalidtext.setVisible(false);
        Connection connection = DBHelper.getINSTANCE().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select weaponName FROM Weapon");
        String weaponName;
        while (resultSet.next()) {
            weaponName = resultSet.getString("weaponName");
            weapon.getItems().add(weaponName);
        }
        resultSet.close();

    }

    public void UpdateGoal(ActionEvent actionEvent) throws IOException, SQLException {
        String goalyid = goalidtext.getText();
        int goalID = Integer.parseInt(goalyid);
        Connection connection = DBHelper.getINSTANCE().getConnection();
        int weaponid = weapon.getSelectionModel().getSelectedIndex()+1;
        String sql = "UPDATE GOAL SET StudentID =?, weaponID =?, goalName =?, dateEntered =?, dateCompleted =?, goalDescrption =? WHERE goalID ="+goalID;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, Integer.parseInt(studentidtextfield.getText()));
            preparedStatement.setInt(2,weaponid);
            preparedStatement.setString(3,goalName.getText());
            preparedStatement.setDate(4, Date.valueOf(dateentered.getValue()));
            preparedStatement.setDate(5, Date.valueOf(datecompleted.getValue()));
            preparedStatement.setString(6,goaldes.getText());

            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Goal");
            alert.setHeaderText("Update Goal");
            alert.setContentText("Update Successful on Goal :" + goalID);
            alert.showAndWait();


        }
        connection.close();
    }

    public void ManageGoalList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("GoalList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goGoalMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageGoal.fxml"));
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
