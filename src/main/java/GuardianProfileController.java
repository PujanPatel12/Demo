import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/2/2017.
 */
public class GuardianProfileController extends MainController{

    public Button save;
    public ChoiceBox statuschoicebox;
    public ChoiceBox relationshipchoicebox;
    public TextField firstnametextfield;
    public TextField lastnametextfield;
    public TextField addresstextfield;
    public TextField zipcodetextfield;
    public TextField citytextfield;
    public ChoiceBox statechoicebox;
    public ChoiceBox Countrychoicebox;
    public TextField StudentPhoneNumber;
    public TextField notestextfield;

    public void initialize() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();

        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("Select countryName from Country");
        String country;
        while(resultSet2.next()){
            country = resultSet2.getString("countryName");
            Countrychoicebox.getItems().addAll(country);
        }
        //fills state
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("Select stateName FROM State_Province");
        String state;
        while(resultSet1.next()){
            state = resultSet1.getString("stateName");
            statechoicebox.getItems().addAll(state);
        }

        // fills guardian choicebox
        Statement statement4 = connection.createStatement();
        ResultSet resultSet4 = statement4.executeQuery("SELECT Guar_relatDescription from Guardian_Relationship");
        String relationship;
        while(resultSet4.next()){
            relationship = resultSet4.getString("Guar_relatDescription");
            relationshipchoicebox.getItems().addAll(relationship);
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Guar_statDescription from Guardian_Status");
        String status;
        while ((resultSet.next())){
            status = resultSet.getString("Guar_statDescription");
            statuschoicebox.getItems().addAll(status);
        }




    }


    public void saveButtonPressed(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        int country = Countrychoicebox.getSelectionModel().getSelectedIndex() +1;
        int state = statechoicebox.getSelectionModel().getSelectedIndex()+1;
        int status = statuschoicebox.getSelectionModel().getSelectedIndex()+1;
        int relationship = relationshipchoicebox.getSelectionModel().getSelectedIndex()+1;

        String sql = "INSERT INTO GUARDIAN(relationshipCode, statusCode,Guar_firstName,Guar_lastName,Guar_address,Guar_city,Guar_zipcode,stateID,countryID,Guar_phonenumber,Guar_notes) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,relationship);
            preparedStatement.setInt(2,status);
            preparedStatement.setString(3,firstnametextfield.getText());
            preparedStatement.setString(4,lastnametextfield.getText());
            preparedStatement.setString(5,addresstextfield.getText());
            preparedStatement.setString(6,citytextfield.getText());
            preparedStatement.setString(7,zipcodetextfield.getText());
            preparedStatement.setInt(8,state);
            preparedStatement.setInt(9,country);
            preparedStatement.setString(10,StudentPhoneNumber.getText());
            preparedStatement.setString(11,notestextfield.getText());
            preparedStatement.execute();
            preparedStatement.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully made a new Guardian");
            alert.setHeaderText("Successfully Made a new Guardian");
            alert.setContentText("Successfully made a new Guardian");
            alert.showAndWait();


        }
        connection.close();


    }

    public void ManageGuardianList(ActionEvent actionEvent) throws IOException {
        Parent root4= FXMLLoader.load(getClass().getResource("GuardianList.fxml"));
        createStage(root4, actionEvent);
    }

    public void goGuardianMenu(ActionEvent actionEvent) throws IOException {
        Parent root4= FXMLLoader.load(getClass().getResource("ManageGuardian.fxml"));
        createStage(root4, actionEvent);
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
