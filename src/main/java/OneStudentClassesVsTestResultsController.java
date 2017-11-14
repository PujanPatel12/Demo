import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Created by PUJAN on 11/10/2017.
 */
public class OneStudentClassesVsTestResultsController extends MainController {

    public TableView reportlist;
    public TableColumn firstnamecol;
    public TableColumn lastnamecol;
    public TableColumn coursenamecol;
    public TableColumn sectionnumbercol;
    public TableColumn classstartdatecol;
    public TableColumn classenddatecol;
    public TableColumn testnamecol;
    public TableColumn testdatecol;
    public TableColumn studentScorecol;
    public TableColumn highestscorecol;
    public ChoiceBox studentidchoicebox;
    public TextField studentlntextfield;
    public TextField studentfntextfield;

    public void initialize() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT studentID,Stu_firstName,Stu_lastName from Student");
        String studentname;
        String lastname;
        int id;

        while (resultSet1.next()) {
            id = resultSet1.getInt("studentID");
            // studentname = resultSet1.getString("Stu_firstName");
            //    lastname = resultSet1.getString("Stu_lastName");
            //     studentidchoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            studentidchoicebox.getItems().addAll(id);
        }
        resultSet1.close();
        statement1.close();

    }

    public void generate() throws SQLException{
        int studentid = studentidchoicebox.getSelectionModel().getSelectedIndex()+1;
        String studentfn = studentfntextfield.getText();
        String studentln = studentlntextfield.getText();
        String sql ="select Student.studentID,Stu_lastName,Stu_firstName,courseName,sectionNumber,classStartDate,classEndDate,testName,testDate,studentScore,Test_Result.finalScore from Student INNER JOIN Test_Result on Student.studentID = Test_Result.studentID INNER JOIN Class_Roster on Student.studentID = Class_Roster.studentID INNER JOIN Class on Class_Roster.classID = Class.classID INNER JOIN Course on Class.courseID = Course.courseID INNER JOIN Test on Test_Result.testID = Test.testID WHERE Student.studentID="+studentid + " OR Student.Stu_firstName ='"+ studentfn + "'" + "OR Student.Stu_lastName ="+ "'"+studentln+ "'" + "ORDER BY Student.studentID";
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<OneStuClassvstest>> O = new BeanListHandler<OneStuClassvstest>(OneStuClassvstest.class);
        List<OneStuClassvstest> oneStuClassvstests = queryRunner.query(sql,O);
        firstnamecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,String>("Stu_firstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,String>("Stu_lastName"));
        coursenamecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,String>("courseName"));
        sectionnumbercol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,Integer>("sectionNumber"));
        classstartdatecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,Date>("classStartDate"));
        classenddatecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,Date>("classEndDate"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,String>("testName"));
        testdatecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,Date>("testDate"));
        studentScorecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,Integer>("studentScore"));
        highestscorecol.setCellValueFactory(new PropertyValueFactory<OneStuClassvstest,Integer>("finalScore"));
        reportlist.setItems(FXCollections.observableArrayList(oneStuClassvstests));
    }


    public void ManageReports(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageReports.fxml"));
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

    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generate();
    }
}
