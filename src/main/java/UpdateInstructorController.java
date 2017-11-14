import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateInstructorController extends  MainController {

    public TextField firstnametextfield;
    public TextField lastnametextfield;
    public ChoiceBox insstatuschoicebox;
    public TextField gendertextfield;
    public TextField addresstextfield;
    public TextField citytextfield;
    public TextField zipcodetextfield;
    public ChoiceBox countrychoicebox;
    public TextField phonenumbertextfield;
    public ChoiceBox statechoicebox;
    public DatePicker dateofBirthpicker;

    public Text instructorIDText;
    public int countryid;
    public Text countryidtext;
    public Text stateidtext;


    public void initialize() throws SQLException, ParseException {

        String id = instructorIDText.getText();
        instructorIDText.setVisible(false);



//        instructorid = Integer.parseInt(InstructorIDtextfield.getId());
        Connection connection = DBHelper.getINSTANCE().getConnection();
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();


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



        connection.close();

    }

    public void UpdateInstructor(ActionEvent actionEvent) throws IOException , SQLException{
        int statuscode = insstatuschoicebox.getSelectionModel().getSelectedIndex()+1;
        int countrycode = countrychoicebox.getSelectionModel().getSelectedIndex()+1;
        int statecode = statechoicebox.getSelectionModel().getSelectedIndex()+1;
        String instructorid = instructorIDText.getText();
        int instructorvalue = Integer.parseInt(instructorid);
       Connection connection = DBHelper.getINSTANCE().getConnection();
       String sql = "UPDATE Instructor SET Ins_firstName = ?, Ins_lastName=?, Ins_statusCode =?, Ins_sex =?, Ins_dateOfBirth =?, Ins_address =?, Ins_city =?, Ins_zipcode =?, stateID =?, countryID = ?, Ins_phoneNumber =? where instructorID =" + instructorvalue;
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setString(1, firstnametextfield.getText());
           preparedStatement.setString(2, lastnametextfield.getText());
           preparedStatement.setInt(3,statuscode);
           preparedStatement.setString(4, gendertextfield.getText());
           preparedStatement.setDate(5, Date.valueOf(dateofBirthpicker.getValue()));
           preparedStatement.setString(6,addresstextfield.getText());
           preparedStatement.setString(7,citytextfield.getText());
           preparedStatement.setString(8,zipcodetextfield.getText());
           preparedStatement.setInt(9,statecode);
           preparedStatement.setInt(10,countrycode);
           preparedStatement.setString(11,phonenumbertextfield.getText());
           preparedStatement.execute();
           preparedStatement.close();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Update Instructor");
           alert.setHeaderText("update");
           alert.setContentText("Update Successful on :" + instructorvalue);
           alert.showAndWait();

       }
       connection.close();




    }

    public void ManageInstructorList(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("InstructorList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goInstructorMenu(ActionEvent actionEvent) throws IOException {
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
