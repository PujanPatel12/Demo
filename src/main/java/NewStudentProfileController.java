import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;


public class NewStudentProfileController extends MainController{

    public TextField Student_sexTextField;
    public Button Save;
    public DatePicker dateofbirth;
    public TextField addresstextfield;
    public TextField zipcodetextfield;
    @FXML
    private TextField lastnametextfield;
    @FXML
    private TextField firstnametextfield;
    @FXML
    private ChoiceBox communicationmediumchoicebox;
    @FXML
    private ChoiceBox StudentStatusChoicebox;
    @FXML
    private TextField StudentCityTextfield;
    @FXML
    private ChoiceBox statechoicebox;
    @FXML
    private ChoiceBox Countrychoicebox;
    @FXML
    private TextField StudentPhoneNumber;
    @FXML
    private ChoiceBox WeaponChoiceBox;
    @FXML
    private TextField Student_notesTextField;

    public void initialize() throws SQLException {

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
        Parent root= FXMLLoader.load(getClass().getResource("StudentList.fxml"));
        createStage(root, actionEvent);
    }

    public void goStudentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root= FXMLLoader.load(getClass().getResource("ManageStudent.fxml"));
        createStage(root, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }

    @Override
    public void createStage(Parent root, ActionEvent actionEvent) {
        super.createStage(root, actionEvent);
    }

    public void savebuttonpressed(ActionEvent actionEvent) throws SQLException {

        int communication = communicationmediumchoicebox.getSelectionModel().getSelectedIndex() +1;
        int state = statechoicebox.getSelectionModel().getSelectedIndex() +1;
        int country = Countrychoicebox.getSelectionModel().getSelectedIndex() +1;
        int studentstatus = StudentStatusChoicebox.getSelectionModel().getSelectedIndex() +1;
        int studentid;
        //int weapon;
        int zipcode = Integer.parseInt(zipcodetextfield.getText());
        int num; // primary key




            Connection connection = DBHelper.getINSTANCE().getConnection();

            String sql = "INSERT INTO STUDENT(communicationID,Stu_statusCode,Stu_firstName,Stu_lastName,Stu_sex,Stu_dateOfBirth,Stu_address,Stu_city,Stu_zipcode,stateID,countryID,Stu_phoneNumber,Stu_notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){

                preparedStatement.setInt(1,communication);
                preparedStatement.setInt(2,studentstatus);
                preparedStatement.setString(3, String.valueOf(firstnametextfield.getText()));
                preparedStatement.setString(4, String.valueOf(lastnametextfield.getText()));
                preparedStatement.setString(5, String.valueOf(Student_sexTextField.getText()));
                preparedStatement.setDate(6, Date.valueOf(dateofbirth.getValue()));
                preparedStatement.setString(7, String.valueOf(addresstextfield.getText()));
                preparedStatement.setString(8, String.valueOf(StudentCityTextfield.getText()));
                preparedStatement.setInt(9, zipcode);
                preparedStatement.setInt(10,state);
                preparedStatement.setInt(11,country);
                preparedStatement.setString(12, String.valueOf(StudentPhoneNumber.getText()));
                preparedStatement.setString(13, String.valueOf(Student_notesTextField.getText()));
                preparedStatement.execute();



                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added New Student");
                alert.setHeaderText("Added new Student");
                alert.setContentText("Successfully added new student");
                alert.showAndWait();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    num = resultSet.getInt(1);
                    System.out.println(num);

                }

                preparedStatement.close();

            }
        connection.close();



    }
}

