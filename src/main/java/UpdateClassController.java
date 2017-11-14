import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateClassController extends MainController {

    public TextField sectionumbertextfield;
    public ChoiceBox Course_Choice_Box;
    public ChoiceBox Class_Status;
    public DatePicker startDatePicker;
    public DatePicker EnddatePicker;
    public Button UpdateButton;
    public Text courseIDtext;



    public void initialize() throws SQLException {
        courseIDtext.setVisible(false);
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

    public void UpdateClass(ActionEvent actionEvent) throws IOException, SQLException {
        /*
        Parent root8 = FXMLLoader.load(getClass().getResource("UpdateClass.fxml"));
        createStage(root8, actionEvent);
        */

        Connection connection = DBHelper.getINSTANCE().getConnection();
        int class_stats=Class_Status.getSelectionModel().getSelectedIndex()+1;
        String classes = courseIDtext.getText();
        int classid = Integer.parseInt(classes);
        String sql = "UPDATE CLASS SET  Cla_statCode =?, classStartDate =?, classEndDate =? where classID ="+classid;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,class_stats);
           // preparedStatement.setString(2,sectionumbertextfield.getText());
            preparedStatement.setDate(2, Date.valueOf(startDatePicker.getValue()));
            preparedStatement.setDate(3, Date.valueOf(EnddatePicker.getValue()));
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Class");
            alert.setHeaderText("Update Status");
            alert.setContentText("Update Successful on :" + classid);
            alert.showAndWait();

        }
    }

    public void ManageClassList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ClassList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goClassMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageClass.fxml"));
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
