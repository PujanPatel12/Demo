import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ryan on 11/2/2017.
 */
public class GuardianListController extends MainController {


    public TableColumn<Guardian,String> guarlastname;
    public TableColumn<Guardian,String> guarfirstname;
    public TableColumn<Guardian,String> guaraddresss;
    public TableColumn<Guardian,String> guarphonenumber;
    public TableColumn<Guardian,String> relationship;
    public TableView guardiantable;
    public TableColumn<Guardian,String>  guarcity;
    public TableColumn<Guardian,String>  state;
    public TableColumn<Guardian,String>  zipcode;
    public TableColumn<Guardian,String>  Country;
    public TableColumn<Guardian,String>  status;
    public TableColumn Notes;


    public void initialize() throws SQLException{
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        guarfirstname.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_firstName"));
        guarlastname.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_lastName"));
        guaraddresss.setCellValueFactory(new PropertyValueFactory<Guardian,String >("Guar_address"));
        guarphonenumber.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_phoneNumber"));
        relationship.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_relatDescription"));
        guarcity.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_city"));
        state.setCellValueFactory(new PropertyValueFactory<Guardian,String>("stateName"));
        Country.setCellValueFactory(new PropertyValueFactory<Guardian,String>("countryName"));
        zipcode.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_zipcode"));
        status.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_statDescription"));
        Notes.setCellValueFactory(new PropertyValueFactory<Guardian,String>("Guar_notes"));

        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Guardian>>g = new BeanListHandler<Guardian>(Guardian.class);
        List<Guardian> guardians = queryRunner.query("SELECT Guar_zipcode,Guar_address,Guar_city,Guar_firstName,Guar_lastName,Guar_notes,Guar_phonenumber,Guar_relatDescription,State_Province.stateName,Country.countryName,Guardian_Status.Guar_statDescription FROM Guardian INNER JOIN Guardian_Status ON Guardian.statusCode = Guardian_Status.Guar_statusCode INNER JOIN  State_Province ON Guardian.stateID = State_Province.stateID INNER JOIN  Country ON Guardian.countryID = Country.countryID INNER JOIN  Guardian_Relationship ON Guardian.relationshipCode = Guardian_Relationship.relationshipCode ", g);
        guardiantable.setItems(FXCollections.observableArrayList(guardians));
        guardiantable.getSelectionModel().getSelectedIndex();

    }

    public void UpdateGuardian(ActionEvent actionEvent) throws IOException {


        Guardian guardian = (Guardian) guardiantable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateGuardian.fxml"));
        int guardianvalue = guardiantable.getSelectionModel().getSelectedIndex()+1;
        if(guardian != null){
            //Parent root4= FXMLLoader.load(getClass().getResource("UpdateGuardian.fxml"));
            Parent root = fxmlLoader.load();
            UpdateGuardianController updateGuardianController = fxmlLoader.getController();
            updateGuardianController.zipcodetextfield.setText(guardian.getGuar_zipcode());
            updateGuardianController.StudentPhoneNumber.setText(guardian.getGuar_phoneNumber());
            updateGuardianController.relationshipchoicebox.setValue(guardian.getGuar_relatDescription());
            updateGuardianController.notestextfield.setText(guardian.getGuar_notes());
            updateGuardianController.lastnametextfield.setText(guardian.getGuar_lastName());
            updateGuardianController.firstnametextfield.setText(guardian.getGuar_firstName());
            updateGuardianController.statechoicebox.setValue(guardian.getStateName());
            updateGuardianController.Countrychoicebox.setValue(guardian.getCountryName());
            updateGuardianController.addresstextfield.setText(guardian.getGuar_address());
            updateGuardianController.citytextfield.setText(guardian.getGuar_city());
            updateGuardianController.statuschoicebox.setValue(guardian.getGuar_statDescription());
            updateGuardianController.guardianidtext.setText(String.valueOf(guardianvalue));
            createStage(root,actionEvent);

        }

       ;
    }

    public void ManageGuardianProfile(ActionEvent actionEvent) throws IOException, SQLException {
        Parent root4= FXMLLoader.load(getClass().getResource("GuardianProfile.fxml"));
        createStage(root4, actionEvent);
        initialize();
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

    public void deletebuttonpressed(ActionEvent actionEvent) throws SQLException {
        int guardianid = guardiantable.getSelectionModel().getSelectedIndex()+1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "Update Guardian SET statusCode =1 where guardianID ="+guardianid;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            preparedStatement.close();
            initialize();
        }

        connection.close();
    }
}
