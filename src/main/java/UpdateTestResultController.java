import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateTestResultController extends MainController {

    public ChoiceBox studentchoicebox;
    public ChoiceBox testchoicebox;
    public TextField studentscoretextfield;
    public TextArea notestextarea;
    public DatePicker testdatepicker;
    public Button UpdateButton;
    public TextField studentidtextfield;
    public Text testidtext;


    public void initialize() throws SQLException {

        Connection connection = DBHelper.getINSTANCE().getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select testID,testName from Test");
        int testID;
        String testName;

        while(resultSet.next()){
        //    testID = resultSet.getInt("testID");
            testName = resultSet.getString("testName");
            testchoicebox.getItems().addAll( testName);

        }
        resultSet.close();
        statement.close();


        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT studentID,Stu_firstName,Stu_lastName from Student");
        String studentname;
        String Lastname;
        int id;

        while (resultSet1.next()) {
            id = resultSet1.getInt("studentID");

            studentchoicebox.getItems().addAll(id);
        }
        resultSet1.close();
        statement1.close();





        connection.close();
    }




    public void ManageTestList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("TestList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goTestResultMenu(ActionEvent actionEvent) throws IOException {
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

    public void UpdateTestResult(ActionEvent actionEvent) {
    }
}

