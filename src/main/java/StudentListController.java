import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
 *
 *
 */
public class StudentListController extends MainController {


    public TableColumn<Student,String> lastnamecol;
    public TableColumn<Student,String> firstnamecol;
    public TableColumn<Student,Date> DOBcol;
    public TableColumn<Student,String> sexcol;
    public TableColumn<Student,String> Addresscol;
    public TableColumn<Student,String> phonenumbercol;
    public TableColumn<Student,String> weaponcol;
    public TableView studenttable;
    public TableColumn<Student,String> zipcodecol;
    public TableColumn<Student,String> statecol;
    public TableColumn<Student,String> studentstatuscol;
    public Button Delete;
    public TableColumn<Student,String> communicationmedium;
    public TableColumn<Student,String> studentnotes;
    public TableColumn<Student,Integer> studentIDcol;

    public void initialize() throws SQLException{
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        lastnamecol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_lastName"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_firstName"));
        DOBcol.setCellValueFactory(new PropertyValueFactory<Student,Date>("Stu_dateOfBirth"));
        sexcol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_sex"));
        Addresscol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_address"));
        phonenumbercol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_phoneNumber"));
        weaponcol.setCellValueFactory(new PropertyValueFactory<Student,String>("CountryName"));
        statecol.setCellValueFactory(new PropertyValueFactory<Student,String>("stateName"));
        zipcodecol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_zipcode"));
        studentstatuscol.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_StatDescription"));
        communicationmedium.setCellValueFactory(new PropertyValueFactory<Student,String>("communicationType"));
        studentnotes.setCellValueFactory(new PropertyValueFactory<Student,String>("Stu_notes"));
        studentIDcol.setCellValueFactory(new PropertyValueFactory<Student,Integer>("studentID"));


        int selection = 0;
        if(studenttable.getSelectionModel().getSelectedIndex() > -1){
            selection = studenttable.getSelectionModel().getSelectedIndex();
        }

        QueryRunner queryRunner = new QueryRunner(ds);

        ResultSetHandler<List<Student>> S = new BeanListHandler<Student>(Student.class);

        List<Student> students = queryRunner.query("select  DISTINCT  Student.studentID, Stu_firstName,Stu_lastName,Stu_address,Stu_dateOfBirth,Stu_zipcode,stateName,Stu_phoneNumber,Stu_notes,Stu_sex,Country.countryName,Communication_Medium.communicationType ,Student_Status.Stu_StatDescription FROM Student INNER JOIN State_Province ON Student.stateID = State_Province.stateID INNER JOIN   Country on Student.countryID = Country.countryID JOIN Student_Status ON Student.Stu_statusCode = Student_Status.Stu_statusCode INNER JOIN Communication_Medium ON Student.communicationID = Communication_Medium.communicationID ORDER BY Student.studentID;", S);

        studenttable.setItems(FXCollections.observableArrayList(students));

       // studenttable.getSelectionModel().getSelectedIndex();


    }


    public void ManageNewStudent(ActionEvent actionEvent) throws IOException, SQLException {
        Parent root8 = FXMLLoader.load(getClass().getResource("NewStudentProfile.fxml"));
        createStage(root8, actionEvent);
        initialize();
    }
/*
    public void UpdateStudent(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("UpdateStudentProfile.fxml"));
        createStage(root8, actionEvent);
    }
    */

    public void goStudentMenu(ActionEvent actionEvent) throws IOException
    {
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

    public void deleteButtonPressed(ActionEvent actionEvent) throws SQLException{
        int studentid =  studenttable.getSelectionModel().getSelectedIndex() +1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String SQL =  "UPDATE Student SET Stu_statusCode = '1' WHERE studentID="+ studentid;
        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL)){
            preparedStatement.execute();
         //   System.out.println("Changed Student ID:" + studentid + " to Inactive");
            initialize();
            preparedStatement.close();

        }
        connection.close();


    }

    public void updatedatebuttonPressed(ActionEvent actionEvent) throws IOException {
        Student student = (Student) studenttable.getSelectionModel().getSelectedItem();
        int studentvalue = studenttable.getSelectionModel().getSelectedIndex()+1;
        if(student !=null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateStudentProfile.fxml"));
            Parent root = fxmlLoader.load();
            UpdateStudentProfileController updateStudentProfileController = fxmlLoader.getController();
            updateStudentProfileController.firstnametextfield.setText(student.getStu_firstName());
            updateStudentProfileController.lastnametextfield.setText(student.getStu_lastName());
            updateStudentProfileController.addresstextfield.setText(student.getStu_address());
            updateStudentProfileController.Countrychoicebox.setValue(student.getCountryName());
            updateStudentProfileController.dateofbirth.setValue(student.getStu_dateOfBirth().toLocalDate());
            updateStudentProfileController.statechoicebox.setValue(student.getStateName());
            updateStudentProfileController.Student_sexTextField.setText(student.getStu_sex());
            updateStudentProfileController.StudentPhoneNumber.setText(student.getStu_phoneNumber());
            updateStudentProfileController.zipcodetextfield.setText(student.getStu_zipcode());
            updateStudentProfileController.communicationmediumchoicebox.setValue(student.getCommunicationType());
            updateStudentProfileController.StudentCityTextfield.setText(student.getStu_city());
            updateStudentProfileController.Student_notesTextField.setText(student.getStu_notes());
            updateStudentProfileController.StudentStatusChoicebox.setValue(student.getStu_StatDescription());
            updateStudentProfileController.studentidtext.setText(String.valueOf(studentvalue));
            createStage(root,actionEvent);

        }

    }
}
