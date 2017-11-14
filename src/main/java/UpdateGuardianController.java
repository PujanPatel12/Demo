import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Ryan on 11/7/2017.
 */
public class UpdateGuardianController extends MainController {

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
    public Text guardianidtext;

    public void initialize() throws SQLException {
        guardianidtext.setVisible(false);
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
    public void UpdateGuardian(ActionEvent actionEvent) throws IOException, SQLException {
       /*
        Parent root8 = FXMLLoader.load(getClass().getResource("UpdateGuardian.fxml"));
        createStage(root8, actionEvent);
        */
        int country = Countrychoicebox.getSelectionModel().getSelectedIndex() +1;
        int state = statechoicebox.getSelectionModel().getSelectedIndex()+1;
        int status = statuschoicebox.getSelectionModel().getSelectedIndex()+1;
        int relationship = relationshipchoicebox.getSelectionModel().getSelectedIndex()+1;

        String g = guardianidtext.getText();
        int guardianID = Integer.parseInt(g);
       Connection connection = DBHelper.getINSTANCE().getConnection();
       String sql = "UPDATE Guardian SET relationshipCode =?, statusCode =?, Guar_firstName =?, Guar_lastName =?, Guar_address =?, Guar_city = ?, Guar_zipcode =?, stateID =?, countryID =?, Guar_phonenumber =?, Guar_notes =? WHERE guardianID ="+ guardianID;
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
           alert.setTitle("Update A Guardian");
           alert.setHeaderText("Guardian Update status");
           alert.setContentText("Update Successful on :" + guardianID);
           alert.showAndWait();

       }
       connection.close();
    }

    public void ManageGuardianList(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("GuardianList.fxml"));
        createStage(root8, actionEvent);
    }

    public void goGuardianMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageGuardian.fxml"));

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
