import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Pujan
 */
public class NewClassController extends MainController {

    @FXML
    private Button SaveButton;
    @FXML
    private ChoiceBox Course_Choice_Box;
    @FXML
    private TextField sectionumbertextfield;
    @FXML
    private ChoiceBox Class_Status;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker EnddatePicker;


    public void initialize() throws SQLException{
        // adds course drop down
        Connection connection = DBHelper.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select courseName FROM Course");
        String courseName;
        //Fills weapon Drop down list
        while (resultSet.next()) {
            courseName = resultSet.getString("courseName");
            Course_Choice_Box.getItems().add(courseName);
        }
        connection.close();
        // adds class status drop down

        Connection connection1 = DBHelper.getINSTANCE().getConnection();
        Statement statement1 = connection1.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT Cla_statDescription from Class_Status");
        String class_status;
        while(resultSet1.next()){
            class_status = resultSet1.getString("Cla_statDescription");
            Class_Status.getItems().addAll(class_status);
        }
        connection1.close();

    }

    public void SaveButtonPressed(ActionEvent actionEvent) throws SQLException {
        int coursenumber = Course_Choice_Box.getSelectionModel().getSelectedIndex() + 1;
        int Cla_statCode = Class_Status.getSelectionModel().getSelectedIndex() + 1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        Statement statement1 = connection.createStatement();
        String sql  = "Insert into Class(courseID,Cla_statCode,sectionNumber,classStartDate,classEndDate) VALUES (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            //preparedStatement.setString(1, String.valueOf((Integer)Course_Choice_Box.getSelectionModel().getSelectedIndex()));
            //preparedStatement.setString(2, String.valueOf(Class_Status.getSelectionModel().getSelectedIndex()));
            preparedStatement.setInt(1,coursenumber);
            preparedStatement.setInt(2,Cla_statCode);
            preparedStatement.setString(3,sectionumbertextfield.getText());
            preparedStatement.setDate(4, Date.valueOf(startDatePicker.getValue()));
            preparedStatement.setDate(5, Date.valueOf(EnddatePicker.getValue()));
            preparedStatement.execute();

        }
        connection.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Added new Class");
        alert.setHeaderText("Added new Class");
        alert.setContentText("Successfully added new Class");
        alert.showAndWait();
    }


    public void ManageClassList(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("ClassList.fxml"));
        createStage(root4, actionEvent);
    }

    public void goClassMenu(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("ManageClass.fxml"));
        createStage(root4, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }


}
