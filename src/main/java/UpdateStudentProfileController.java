import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateStudentProfileController extends MainController {

    public TextField firstnametextfield;
    public TextField lastnametextfield;
    public TextField addresstextfield;
    public TextField StudentCityTextfield;
    public TextField zipcodetextfield;
    public ChoiceBox statechoicebox;
    public ChoiceBox Countrychoicebox;
    public TextField StudentPhoneNumber;
    public ChoiceBox WeaponChoiceBox;
    public TextField Student_sexTextField;
    public TextField Student_notesTextField;
    public ChoiceBox StudentStatusChoicebox;
    public ChoiceBox communicationmediumchoicebox;
    public DatePicker dateofbirth;
    public AnchorPane studentAddressTextField;
    public Text studentidtext;
    public Button UpdateButton;


    public void initialize()throws SQLException{

        studentidtext.setVisible(false);


        Connection connection = DBHelper.getConnection();
        //adds weapon to choicebox
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select weaponName FROM Weapon");
        String weaponName;
        while (resultSet.next()) {
            weaponName= resultSet.getString("weaponName");
            WeaponChoiceBox.getItems().add(weaponName);
        }
        resultSet.close();

        //adds state to choicebox
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("Select stateName FROM State_Province");
        String state;
        while(resultSet1.next()){
            state = resultSet1.getString("stateName");
            statechoicebox.getItems().addAll(state);
        }

        //Adds country to choicebox
        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("Select countryName from Country");
        String country;
        while(resultSet2.next()){
            country = resultSet2.getString("countryName");
            Countrychoicebox.getItems().addAll(country);
        }

        //adds student status choice box
        Statement statement3 = connection.createStatement();
        ResultSet resultSet3 = statement3.executeQuery("Select Stu_StatDescription from Student_Status");
        String student_status;
        while(resultSet3.next()){
            student_status = resultSet3.getString("Stu_StatDescription");
            StudentStatusChoicebox.getItems().addAll(student_status);
        }

        //Adds communication medium to choice box

        Statement statement4 = connection.createStatement();
        ResultSet resultSet4 = statement4.executeQuery("Select communicationType from Communication_Medium");
        String communication;
        while(resultSet4.next()){
            communication = resultSet4.getString("communicationType");
            communicationmediumchoicebox.getItems().addAll(communication);
        }
        connection.close();

    }


    public void ManageStudentList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("StudentList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goStudentMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageStudent.fxml"));
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


    public void updateButtonPressed(ActionEvent actionEvent) throws SQLException, IOException {
        int communication = communicationmediumchoicebox.getSelectionModel().getSelectedIndex() +1;
        int state = statechoicebox.getSelectionModel().getSelectedIndex() +1;
        int country = Countrychoicebox.getSelectionModel().getSelectedIndex() +1;
        int studentstatus = StudentStatusChoicebox.getSelectionModel().getSelectedIndex() +1;
        String studentid = studentidtext.getText();
        int studid = Integer.parseInt(studentid);
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "UPDATE Student SET communicationID =?, Stu_statusCode =?, Stu_firstName =?, Stu_lastName =?, Stu_sex =?, Stu_dateOfBirth =?, Stu_address=?, Stu_city =?, Stu_zipcode =?, stateID =?, countryID =?, Stu_phoneNumber =?, Stu_notes =? WHERE studentID="+ studid;
        try(PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setInt(1,communication);
            preparedStatement.setInt(2,studentstatus);
            preparedStatement.setString(3,firstnametextfield.getText());
            preparedStatement.setString(4,lastnametextfield.getText());
            preparedStatement.setString(5,Student_sexTextField.getText());
            preparedStatement.setDate(6, Date.valueOf(dateofbirth.getValue()));
            preparedStatement.setString(7,addresstextfield.getText());
            preparedStatement.setString(8,StudentCityTextfield.getText());
            preparedStatement.setString(9,zipcodetextfield.getText());
            preparedStatement.setInt(10,state);
            preparedStatement.setInt(11,country);
            preparedStatement.setString(12,StudentPhoneNumber.getText());
            preparedStatement.setString(13,Student_notesTextField.getText());
            preparedStatement.execute();
            preparedStatement.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Student");
            alert.setHeaderText("update");
            alert.setContentText("Update Successful on :" + studid);
            alert.showAndWait();
        }
    }
}
