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
public class OneStudentPrivateLessonsVsGoalsController extends MainController {

    public TableView reportlist;
    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn privatelessonnamecol;
    public TableColumn privatelessondatecol;
    public TableColumn goalshortnamecol;
    public TableColumn goalstartdatecol;
    public TableColumn goalenddatecol;
    public TextField studentlntextfield;
    public TextField studentfntextfield;
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

        String sql = "select Student.studentID,Stu_lastName,Stu_firstName,goalName,privateLessonName,privateLessonDate,goal.dateEntered,goal.dateCompleted from Student INNER JOIN Goal on Student.studentID = Goal.studentID INNER JOIN Private_Lesson on Student.studentID = Private_Lesson.studentID WHERE Student.studentID="+studentid + " OR  Student.Stu_firstName ='"+  studentfn + "'" + "OR Student.Stu_lastName ="+ "'"+studentln+ "'" + "ORDER BY Student.studentID";
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Onestudentonegoaloneprivate>> O = new BeanListHandler<Onestudentonegoaloneprivate>(Onestudentonegoaloneprivate.class);
        List<Onestudentonegoaloneprivate> onestudentonegoaloneprivates = queryRunner.query(sql,O);
        studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,String>("Stu_firstName"));
        studentlastnamecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,String>("Stu_lastName"));
        goalenddatecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,Date>("dateCompleted"));
        goalstartdatecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,Date>("dateEntered"));
        privatelessondatecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,Date>("privateLessonDate"));
        privatelessonnamecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,String>("privateLessonName"));
        goalshortnamecol.setCellValueFactory(new PropertyValueFactory<Onestudentonegoaloneprivate,String>("goalName"));

        reportlist.setItems(FXCollections.observableArrayList(onestudentonegoaloneprivates));
    }

    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException{
        reportlist.refresh();
        generatelist();
    }
}
