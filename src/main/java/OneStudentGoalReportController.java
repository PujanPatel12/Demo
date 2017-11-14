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
public class OneStudentGoalReportController extends MainController {

    public TableView report_tableview;
    public TableColumn<OneStudentGoalReport, String> studentlncol;
    public TableColumn<OneStudentGoalReport, String> studentfncol;
    public TableColumn<OneStudentGoalReport, String> goalname;
    public TableColumn<OneStudentGoalReport,Date> goaldateeneteredcol;
    public TableColumn<OneStudentGoalReport,Date> goaldatecompletedcol;
    public TableColumn<OneStudentGoalReport, String> weaponnamecol;
    public TextField studentfirstnametextfield;
    public TextField studentlastnametextfield;
    public ChoiceBox studentidchoicebox;


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
           // lastname = resultSet1.getString("Stu_lastName");
            //  Studentcombobox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            studentidchoicebox.getItems().addAll(id);
        }
        resultSet1.close();
        statement1.close();



    }




    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner((ds));
        report_tableview.refresh();

        int studentid = studentidchoicebox.getSelectionModel().getSelectedIndex()+1;
        String studentfn = studentfirstnametextfield.getText();
        String studentln = studentlastnametextfield.getText();

        String sql = "select Student.studentID, Stu_lastName,Student.Stu_firstName,Goal.goalName,Weapon.weaponName, goal.dateCompleted,goal.dateEntered from Student INNER JOIN Goal on goal.studentID = student.studentID INNER JOIN Weapon ON GOAL.weaponID = Weapon.weaponID WHERE Student.studentID= "+studentid + " OR  Student.Stu_firstName ='"+  studentfn + "'" + "OR Student.Stu_lastName ="+ "'"+studentln+"'";
        ResultSetHandler<List<OneStudentGoalReport>> O = new BeanListHandler<OneStudentGoalReport>(OneStudentGoalReport.class);
        List<OneStudentGoalReport> oneStudentGoalReports = queryRunner.query(sql,O);
        studentfncol.setCellValueFactory(new PropertyValueFactory<OneStudentGoalReport,String>(("Stu_firstName")));
        studentlncol.setCellValueFactory(new PropertyValueFactory<OneStudentGoalReport,String>("Stu_lastName"));
        goalname.setCellValueFactory(new PropertyValueFactory<OneStudentGoalReport,String>("goalName"));
        weaponnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentGoalReport,String>("weaponName"));
        goaldatecompletedcol.setCellValueFactory(new PropertyValueFactory<OneStudentGoalReport,Date>("dateCompleted"));
        goaldateeneteredcol.setCellValueFactory(new PropertyValueFactory<OneStudentGoalReport,Date>("dateEntered"));
        report_tableview.setItems(FXCollections.observableArrayList(oneStudentGoalReports));






    }
    /*
    public void aftergenerate() throws SQLException{



    }
    */



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


}
