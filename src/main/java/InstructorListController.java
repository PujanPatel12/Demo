import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pujan
 */
public class InstructorListController extends MainController {


    public TableColumn<String,Instructor> inslastnamecol;
    public TableColumn<String,Instructor>  insfirstnamecol;
    public TableColumn<String,Instructor>  insgendercol;
    public TableColumn<String,Instructor>  insaddresscol;
    public TableColumn<String,Instructor>  inscity;
    public TableColumn<String,Instructor> statecol;
    public TableColumn<String,Instructor> countrycol;
    public TableColumn<String,Instructor> statuscol;
    public TableColumn<Date,Instructor> dateofBirth;


    public TableView InsTable;
    public TableColumn<String,Instructor> phonenumbercol;
    public TableColumn zipcodecol;
    private int instructorid;

    public void initialize()throws SQLException{
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        inslastnamecol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_lastName"));
        insfirstnamecol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_firstName"));
        insgendercol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_sex"));
        insaddresscol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_address"));
        inscity.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_city"));
        statecol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("stateName"));
       countrycol.setCellValueFactory(new  PropertyValueFactory<String,Instructor>("countryName"));
       statuscol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_statDescription"));
      phonenumbercol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_phoneNumber"));
        dateofBirth.setCellValueFactory(new PropertyValueFactory<Date,Instructor>("Ins_dateOfBirth"));
        zipcodecol.setCellValueFactory(new PropertyValueFactory<String,Instructor>("Ins_zipcode"));


        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Instructor>> I = new BeanListHandler<Instructor>(Instructor.class);
        List<Instructor> instructors = queryRunner.query("Select Ins_firstName,Ins_lastName,Ins_sex,Ins_address,Ins_dateOfBirth,Ins_address,Ins_city, stateName,countryName,Instructor_Status.Ins_statDescription,Ins_zipcode,Ins_phoneNumber from State_Province inner join Instructor on Instructor.stateID = State_Province.StateID INNER JOIN COUNTRY ON Instructor.countryID = Country.countryID INNER JOIN Instructor_Status ON Instructor.Ins_statusCode = Instructor_Status.Ins_statusCode",I);
        InsTable.setItems(FXCollections.observableArrayList(instructors));
        InsTable.getSelectionModel().getSelectedIndex();


        int idx = 0;
        if(InsTable.getSelectionModel().getSelectedIndex() > -1){
            idx = InsTable.getSelectionModel().getSelectedIndex();
        }
    }


    public void UpdateInstructor(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("UpdateInstructor.fxml"));
        createStage(root4, actionEvent);
    }

    public void ManageInstructorProfile(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("InstructorProfile.fxml"));

        createStage(root8, actionEvent);
    }

    public void goInstructorMenu(ActionEvent actionEvent) throws IOException
    {

        //Parent root8 = FXMLLoader.load(getClass().getResource("ManageInstructor.fxml"));
        //createStage(root8, actionEvent);


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

    public void updatepressed(ActionEvent actionEvent) throws IOException, SQLException {

        Instructor instructor = (Instructor) InsTable.getSelectionModel().getSelectedItem();
        int value = InsTable.getSelectionModel().getSelectedIndex() +1;

        if(instructor != null){
            System.out.println("You select an instructor");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateInstructor.fxml"));
            Parent root = fxmlLoader.load();



            UpdateInstructorController updateInstructorController = fxmlLoader.getController();
            updateInstructorController.firstnametextfield.setText(instructor.getIns_firstName());
            updateInstructorController.lastnametextfield.setText(instructor.getIns_lastName());
            updateInstructorController.addresstextfield.setText(instructor.getIns_address());
            updateInstructorController.citytextfield.setText(instructor.getIns_city());
            updateInstructorController.insstatuschoicebox.setValue(instructor.getIns_statDescription());
            updateInstructorController.statechoicebox.setValue(instructor.getStateName());
         //   updateInstructorController.countryidtext.setText(String.valueOf(instructor.getCountryName()));
            updateInstructorController.countrychoicebox.setValue(instructor.getCountryName());
            updateInstructorController.zipcodetextfield.setText(String.valueOf(instructor.getIns_zipcode()));
            updateInstructorController.gendertextfield.setText(instructor.getIns_sex());
            updateInstructorController.phonenumbertextfield.setText(instructor.getIns_phoneNumber());
            updateInstructorController.instructorIDText.setText(String.valueOf(value));
            updateInstructorController.dateofBirthpicker.setValue(instructor.getIns_dateOfBirth().toLocalDate());
            createStage(root,actionEvent);


        }
        initialize();

    }

    public void deletebuttonpressed(ActionEvent actionEvent) throws SQLException{
        int instructorid = InsTable.getSelectionModel().getSelectedIndex() + 1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String SQL =  "UPDATE Instructor SET Ins_statusCode= '1' WHERE instructorID="+ instructorid;
        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL)){
            preparedStatement.execute();
          //  System.out.println("Changed Instructor ID:" + instructorid + " to Inactive");
            initialize();
            preparedStatement.close();

        }
        connection.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successfully made Instructor Inactive");
        alert.setHeaderText("Successfully made Instructor Inactive");
        alert.setContentText("Successfully made Instructor Inactive");
        alert.showAndWait();
        initialize();


    }
}
