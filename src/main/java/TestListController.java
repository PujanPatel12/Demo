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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ryan on 11/3/2017.
 */
public class TestListController extends MainController {


    public TableColumn<Test,Integer> testIDcol;
    public TableColumn<Test,String> testnameCol;
    public TableColumn<Test,Date> testDatecol;
    public TableColumn<Test,String> weaponnamecol;
    public TableColumn<Test,Integer> highestscorecol;
    public TableColumn<Test,String> testDescription;
    public TableView testtable;
    public TableColumn firstnamecol1;
    public TableColumn firstnamecol;
    public TableColumn lastnamecol;
    public TableColumn studentidcol;
    public TableColumn studentscore;
    public TableColumn testnotescol;

    public void initialize() throws SQLException {
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        testIDcol.setCellValueFactory(new PropertyValueFactory<Test,Integer>("testID"));
        testnameCol.setCellValueFactory(new PropertyValueFactory<Test,String>("testName"));
        testDatecol.setCellValueFactory(new PropertyValueFactory<Test,Date>("testDate"));
  //      weaponnamecol.setCellValueFactory(new PropertyValueFactory<Test,String>("weaponName"));
//        testDescription.setCellValueFactory(new PropertyValueFactory<Test,String>("testDescription"));
        highestscorecol.setCellValueFactory(new PropertyValueFactory<Test,Integer>("highestTestScore"));
        studentidcol.setCellValueFactory(new PropertyValueFactory<Test,Integer>("studentID"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<Test,Integer>("Stu_firstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<Test,Integer>("Stu_firstName"));
        studentscore.setCellValueFactory(new PropertyValueFactory<Test,Integer>("studentScore"));
        testnotescol.setCellValueFactory(new PropertyValueFactory<Test,Integer>("resultNotes"));
        int selection = 0;
        if(testtable.getSelectionModel().getSelectedIndex() > -1){
            selection = testtable.getSelectionModel().getSelectedIndex();
        }

        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Test>> T =  new BeanListHandler<Test>(Test.class);
        List<Test> tests = queryRunner.query("select  Student.studentID,Student.stu_firstName, Student.stu_lastName, Test.testID,Test.testName,Weapon.weaponName,Test_Result.testDate,Test.testDescription,Test.highestTestScore,Test_Result.studentScore,resultNotes from Test  INNER JOIN  Weapon ON Test.weaponID = Weapon.weaponID INNER JOIN Test_Result ON Test.testID = Test_Result.testID INNER JOIN Student ON Test_Result.studentID = Student.studentID", T);
        testtable.setItems(FXCollections.observableArrayList(tests));
        testtable.getSelectionModel().getSelectedIndex();

    }




    public void ManageTestResultInput(ActionEvent actionEvent) throws IOException
    {
        Parent root8 = FXMLLoader.load(getClass().getResource("TestResultInput.fxml"));
        createStage(root8, actionEvent);
    }

    public void goTestMenu(ActionEvent actionEvent) throws IOException
    {
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

    public void updatebuttonpressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateTestResult.fxml"));
        Test test = (Test) testtable.getSelectionModel().getSelectedItem();
        int testid = testtable.getSelectionModel().getSelectedIndex() + 1;
        if (test != null) {
            Parent root = fxmlLoader.load();
            UpdateTestResultController updateTestResultController = fxmlLoader.getController();
            updateTestResultController.notestextarea.setText(test.getResultNotes());
            updateTestResultController.studentchoicebox.setValue(test.getStudentID());
            updateTestResultController.studentscoretextfield.setText(String.valueOf(test.getStudentScore()));
            updateTestResultController.testchoicebox.setValue(test.getTestName());
            updateTestResultController.testdatepicker.setValue(test.getTestDate().toLocalDate());
            updateTestResultController.testidtext.setText(String.valueOf(testid));
            createStage(root,actionEvent);



        }

    }
    }

