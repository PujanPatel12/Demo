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
public class OneStudentPrivatelessonsVsTourneyResultsController extends MainController {

    public TableView reportlist;
    public TableColumn studentlncol;
    public TableColumn studentfncol;
    public TableColumn privatelessonnamecol;
    public TableColumn privatelessondatecol;
    public TableColumn tournamentnamecol;
    public TableColumn tournamentdatecol;
    public TableColumn tournamenteventnamecol;
    public TableColumn finalplacementcol;
    public TableColumn eventsizecol;
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

    public void generatelist() throws SQLException{

        int studentid = studentidchoicebox.getSelectionModel().getSelectedIndex()+1;
        String studentfn = studentfntextfield.getText();
        String studentln = studentlntextfield.getText();
        String sql = "select Student.studentID,Stu_firstName,Stu_lastName,tournamentEventName,tournamentName,Tournament.startDate,privateLessonName,privateLessonDate,Event_Result.finalPlacement,Tournament_Event.eventSize FROM STUDENT INNER JOIN Event_Result ON Student.studentID = Event_Result.studentID INNER JOIN Tournament_Event ON Event_Result.tournamentEventID = Tournament_Event.tournamentEventID INNER JOIN  Tournament ON Tournament_Event.tournamentID = Tournament.tournamentID INNER JOIN Private_Lesson ON Student.studentID = Private_Lesson.studentID WHERE Student.studentID="+studentid + " OR Student.Stu_firstName ='"+ studentfn + "'" + "OR Student.Stu_lastName ="+ "'"+studentln+ "'" + "ORDER BY Student.studentID";
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<OneStudentPrivateLesson>> O = new BeanListHandler<OneStudentPrivateLesson>(OneStudentPrivateLesson.class);
        List<OneStudentPrivateLesson> oneStudentPrivateLessons = queryRunner.query(sql,O);
        studentfncol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,String>("Stu_firstName"));
        studentlncol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,String>("Stu_lastName"));
        tournamentnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,String>("tournamentName"));
        eventsizecol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,Integer>("eventSize"));
        finalplacementcol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,Integer>("finalPlacement"));
        privatelessondatecol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,Date>("privateLessonDate"));
        tournamentdatecol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,Date>("startDate"));
        privatelessonnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,String>("privateLessonName"));
        tournamenteventnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentPrivateLesson,String>("tournamentEventName"));
        reportlist.setItems(FXCollections.observableArrayList(oneStudentPrivateLessons));


    }

    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generatelist();
    }

}
