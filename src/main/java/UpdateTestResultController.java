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
        testidtext.setVisible(false);

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

    public void UpdateTestResult(ActionEvent actionEvent) throws SQLException {

        int studentid = studentchoicebox.getSelectionModel().getSelectedIndex()+1;
        int testid = testchoicebox.getSelectionModel().getSelectedIndex()+1;
        int testscore = Integer.parseInt(studentscoretextfield.getText());
        Date dateoftest = Date.valueOf(testdatepicker.getValue());
        String notes = notestextarea.getText();
        String tid = testidtext.getText();
        int testidd = Integer.parseInt(tid);

        Connection connection = DBHelper.getINSTANCE().getConnection();

        String sql ="Update Test_Result set studentID =?, testID=?, testDate=?, studentScore =?, resultNotes=? where testResultID ="+ testidd;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,studentid);
            preparedStatement.setInt(2,testid);
            preparedStatement.setDate(3,dateoftest);
            preparedStatement.setInt(4,testscore);
            preparedStatement.setString(5,notes);
            preparedStatement.execute();
            preparedStatement.close();;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updated Test Result");
            alert.setHeaderText("Successfully Updated Test Result");
            alert.setContentText("Successfully Updated Test Result for Student ID:" + studentid);
            alert.showAndWait();
        }

        connection.close();


    }
}

