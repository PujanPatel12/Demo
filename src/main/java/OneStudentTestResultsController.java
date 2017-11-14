import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
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
public class OneStudentTestResultsController extends MainController {

    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn testnamecol;
    public TableColumn testdatecol;
    public TableColumn studentscorecol;
    public TableColumn highestscorecol;
    public ChoiceBox studentidchoicebox;
    public TextField studentlastnametextfield;
    public TextField studentfirstnametextfield;
    public Button generatebuttonpressed;
    public TableView reportlist;


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

    public void generateList() throws SQLException {
        reportlist.refresh();


        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);


        int studentid = studentidchoicebox.getSelectionModel().getSelectedIndex()+1;
        String studentfn = studentfirstnametextfield.getText();
        String studentln = studentlastnametextfield.getText();
        String sql = "select Student.Stu_lastName,Student.Stu_firstName,testName,highestTestScore,testDate,studentScore FROM Student INNER JOIN Test_Result ON Student.studentID = Test_Result.studentID INNER JOIN  Test ON Test_Result.testID = Test.testID WHERE Student.studentID= "+studentid + " OR  Student.Stu_firstName ='"+  studentfn + "'" + "OR Student.Stu_lastName ="+ "'"+studentln+ "'" + "ORDER BY Student.studentID";
        ResultSetHandler<List<OneStudentTestResult>> L = new BeanListHandler<OneStudentTestResult>(OneStudentTestResult.class);
        List<OneStudentTestResult> oneStudentTestResults = queryRunner.query(sql,L);
        studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentTestResult,String>("Stu_firstName"));
        studentlastnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentTestResult,String>("Stu_lastName"));
        testdatecol.setCellValueFactory(new PropertyValueFactory<OneStudentTestResult,Date>("testDate"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentTestResult,String>("testName"));
        studentscorecol.setCellValueFactory(new PropertyValueFactory<OneStudentTestResult,Integer>("studentScore"));
        highestscorecol.setCellValueFactory(new PropertyValueFactory<OneStudentTestResult,Integer>("highestTestScore"));
        reportlist.setItems(FXCollections.observableArrayList(oneStudentTestResults));





    }


    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generateList();







    }
}
