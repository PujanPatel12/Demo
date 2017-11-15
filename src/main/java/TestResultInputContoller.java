import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

import static java.sql.Types.NULL;

/**
 * Created by Ryan on 11/3/2017.
 */
public class TestResultInputContoller extends MainController {


    public Button save;
    public ChoiceBox studentchoicebox;
    public ChoiceBox testchoicebox;
    public TextField studentscoretextfield;
    public TextArea notestextarea;
    public DatePicker testdatepicker;

    public void initialize() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select testID,testName from Test");
        int testID;
        String testName;

        while(resultSet.next()){
            testID = resultSet.getInt("testID");
            testName = resultSet.getString("testName");
            testchoicebox.getItems().addAll(testID + "," + testName);

        }
        resultSet.close();
        statement.close();


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
    public void SaveButtonPressed(ActionEvent actionEvent) throws SQLException {
        int testid = testchoicebox.getSelectionModel().getSelectedIndex() + 1;
        int studentid = studentchoicebox.getSelectionModel().getSelectedIndex() +1;

        Connection connection = DBHelper.getINSTANCE().getConnection();

        String sql = "INSERT INTO Test_Result(studentID,testID,testDate,studentScore,finalScore, resultNotes) values(?,?,?,?,?,?) ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,studentid);
            preparedStatement.setInt(2,testid);
            preparedStatement.setDate(3, Date.valueOf(testdatepicker.getValue()));
            preparedStatement.setInt(4, Integer.parseInt(studentscoretextfield.getText()));
            preparedStatement.setInt(5,NULL);
            preparedStatement.setString(6,notestextarea.getText());
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Added New Test Result");
            alert.setHeaderText("Added new Test Result");
            alert.setContentText("Successfully added New Test Result to Student : "+ studentid);
            alert.showAndWait();

        }
        connection.close();

    }

    public void ManageTestList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("TestList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goTestMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageTest.fxml"));
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
