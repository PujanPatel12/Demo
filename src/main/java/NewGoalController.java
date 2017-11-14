import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/2/2017.
 */
public class NewGoalController extends MainController {


    public TextArea goaldes;
    public ChoiceBox weapon;
    public DatePicker dateentered;
    public DatePicker datecompleted;
    public TextField goalName;
    public Button savebutton;
    //  public ComboBox Studentcombobox;
    public ChoiceBox StudentChoiceBox;


    public void initialize() throws SQLException {
        Connection connection = DBHelper.getConnection();
        //adds weapon to choicebox

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select weaponName FROM Weapon");
        String weaponName;
        while (resultSet.next()) {
            weaponName = resultSet.getString("weaponName");
            weapon.getItems().add(weaponName);
        }
        resultSet.close();

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
            StudentChoiceBox.getItems().addAll(id + " | " + lastname + ", " + studentname);
        }
        resultSet1.close();
        connection.close();
    }

    public void saveButtonpressed(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        // int studentid = Studentcombobox.getSelectionModel().getSelectedIndex() +1;
        int studentid = StudentChoiceBox.getSelectionModel().getSelectedIndex() + 1;
        int weapons = weapon.getSelectionModel().getSelectedIndex() + 1;
        Date comp;

        if(datecompleted.getValue() != null) {

            String sql = "INSERT INTO GOAL(studentID,weaponID,goalName,dateEntered,dateCompleted,goalDescrption) values(?,?,?,?,?,?)";


            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, studentid);
                preparedStatement.setInt(2, weapons);
                preparedStatement.setString(3, goalName.getText());
                preparedStatement.setDate(4, Date.valueOf(dateentered.getValue()));
                preparedStatement.setDate(5, Date.valueOf(datecompleted.getValue()));
                preparedStatement.setString(6, goaldes.getText());
                preparedStatement.execute();
                preparedStatement.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added new Goal");
                alert.setHeaderText("Added new Goal");
                alert.setContentText("Successfully Added new Goal for Student : " + studentid);
                alert.showAndWait();


            }
        }

        if(datecompleted.getValue() == null){
            String sql = "INSERT INTO GOAL(studentID,weaponID,goalName,dateEntered,goalDescrption) values(?,?,?,?,?)";


            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, studentid);
                preparedStatement.setInt(2, weapons);
                preparedStatement.setString(3, goalName.getText());
                preparedStatement.setDate(4, Date.valueOf(dateentered.getValue()));
                preparedStatement.setString(5, goaldes.getText());
                preparedStatement.execute();
                preparedStatement.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added new Goal");
                alert.setHeaderText("Added new Goal");
                alert.setContentText("Successfully Added new Goal for Student : " + studentid);
                alert.showAndWait();


            }

        }



    }





    public void ManageGoalList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("GoalList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goGoalMenu(ActionEvent actionEvent) throws IOException
    {
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
