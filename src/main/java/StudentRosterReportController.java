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
 * Created by Ryan on 11/10/2017.
 */
public class StudentRosterReportController extends MainController {

    public TableColumn studentidcol;
    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn startdatecol;
    public TableColumn enddatecol;
    public TableColumn classstatuscol;
    public TableView reportlist;
    public ChoiceBox courseid_choicebox;
    public TextField sectionnumbertextfield;

    public void initialize() throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT Course.courseID,Course.courseName from Course");
        String courseName;
        int id;

        while (resultSet1.next()) {
            id = resultSet1.getInt("courseID");
            courseName= resultSet1.getString("courseName");
            //    lastname = resultSet1.getString("Stu_lastName");
            //     studentidchoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
           courseid_choicebox.getItems().addAll(id + "" + courseName);
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

    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generatereport();
    }

    public void generatereport() throws SQLException {
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        int courseID = courseid_choicebox.getSelectionModel().getSelectedIndex()+1;
        int sectionNumber = Integer.parseInt(sectionnumbertextfield.getText());

            String sql = "select Course.courseID,Class.sectionNumber, Student.studentID,Student.Stu_firstName,Student.Stu_lastName,classEndDate,classStartDate,Class_Roster_Status.Ros_statDescription,Course.courseID,Class.sectionNumber FROM Class INNER JOIN Class_Roster ON Class.classID = Class_Roster.classID INNER JOIN  Student ON Class_Roster.studentID = Student.studentID INNER JOIN Class_Roster_Status ON Class_Roster.Ros_statusCode = Class_Roster_Status.Ros_statusCode INNER JOIN  Course ON Class.courseID = Course.courseID Where Course.courseID =" + courseID + "OR Class.sectionNumber =" + sectionNumber;
            studentidcol.setCellValueFactory(new PropertyValueFactory<StudentRosterReport, Integer>("studentID"));
            studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<StudentRosterReport, String>("Stu_firstName"));
            studentlastnamecol.setCellValueFactory(new PropertyValueFactory<StudentRosterReport, String>("Stu_lastName"));
            startdatecol.setCellValueFactory(new PropertyValueFactory<StudentRosterReport, Date>("classStartDate"));
            enddatecol.setCellValueFactory(new PropertyValueFactory<StudentRosterReport, Date>("classEndDate"));
            classstatuscol.setCellValueFactory(new PropertyValueFactory<StudentRosterReport, String>("Ros_statDescription"));
            ResultSetHandler<List<StudentRosterReport>> S = new BeanListHandler<StudentRosterReport>(StudentRosterReport.class);
            List<StudentRosterReport> studentRosterReports = queryRunner.query(sql, S);
            reportlist.setItems(FXCollections.observableArrayList(studentRosterReports));




    }
}
