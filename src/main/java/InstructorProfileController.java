import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/3/2017.
 */
public class InstructorProfileController extends MainController {


    public TextField firstnametextfield;
    public TextField lastnametextfield;
    public ChoiceBox insstatuschoicebox;
    public TextField gendertextfield;
    public TextField addresstextfield;
    public TextField citytextfield;
    public ChoiceBox statechoicebox;
    public ChoiceBox countrychoicebox;
    public TextField phonenumbertextfield;

    public Button save;
    public DatePicker dateofBirthpicker;
    public TextField zipcodetextfield;

    public void initialize() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();


        // fills instructor status
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select Ins_statDescription from Instructor_Status");
        String status;
        while ((resultSet.next())){
            status= resultSet.getString("Ins_statDescription");
            insstatuschoicebox.getItems().addAll(status);
        }

        //fills country
        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("Select countryName from Country");
        String country;
        while(resultSet2.next()){
            country = resultSet2.getString("countryName");
            countrychoicebox.getItems().addAll(country);
        }
        //fills state
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("Select stateName FROM State_Province");
        String state;
        while(resultSet1.next()){
            state = resultSet1.getString("stateName");
            statechoicebox.getItems().addAll(state);
        }
        connection.close();


    }


    public void saveButtonPressed(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        int statechoice = statechoicebox.getSelectionModel().getSelectedIndex() +1;
        int countrychoice = countrychoicebox.getSelectionModel().getSelectedIndex() +1;
        int statuschoice = insstatuschoicebox.getSelectionModel().getSelectedIndex() +1;

        String sql = "INSERT INTO INSTRUCTOR(Ins_statusCode,Ins_firstName,Ins_lastName,Ins_sex,Ins_dateOfBirth,Ins_address,Ins_city,Ins_zipcode,stateID,countryID,Ins_phoneNumber) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,statuschoice);
            preparedStatement.setString(2,firstnametextfield.getText());
            preparedStatement.setString(3,lastnametextfield.getText());
            preparedStatement.setString(4,gendertextfield.getText());
            preparedStatement.setDate(5, Date.valueOf(dateofBirthpicker.getValue()));
            preparedStatement.setString(6,addresstextfield.getText());
            preparedStatement.setString(7,citytextfield.getText());
            preparedStatement.setString(8,zipcodetextfield.getText());
            preparedStatement.setInt(9,statechoice);
            preparedStatement.setInt(10,countrychoice);
            preparedStatement.setString(11,phonenumbertextfield.getText());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            System.out.println("insert successful");
        }
        catch (Error error){
            System.out.println("Insert unsucessful");
        }

    }


    public void ManageInstructorList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("InstructorList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goInstructorMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageInstructor.fxml"));
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
