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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by PUJAN on 11/10/2017.
 */
public class OneStudentTournamentResultsController extends MainController {

    public TableColumn studentlastcol;
    public TableColumn studentfirstcol;
    public TableColumn tournamentnamecol;
    public TableColumn eventnamecol;
    public TableColumn eventratingcol;
    public TableColumn finalplacementcol;
    public TableColumn eventsizecol;
    public ChoiceBox studentidchoicebox;
    public TextField studentlastnametextfield;
    public TextField studentfirstnametextfield;
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

    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generatelist();
    }


    public void generatelist() throws SQLException {
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        String studentfn = studentfirstnametextfield.getText();
        String studentln = studentlastnametextfield.getText();
        int studentid = studentidchoicebox.getSelectionModel().getSelectedIndex()+1;
        String sql ="select Student.studentID,Student.Stu_firstName,Student.Stu_lastName,Tournament.tournamentName,Event_Rating.eventRatingName,Tournament_Event.tournamentEventName,Event_Result.finalPlacement,Tournament_Event.eventSize FROM Student INNER JOIN Event_Result ON Student.studentID = Event_Result.studentID INNER JOIN  Tournament_Event ON Event_Result.tournamentEventID = Tournament_Event.tournamentEventID INNER JOIN  Tournament ON Tournament_Event.tournamentID = Tournament.tournamentID INNER JOIN Event_Rating ON Tournament_Event.eventRatingName = Event_Rating.eventRatingName Where Student.studentID="+ studentid +" OR  Student.Stu_firstName ='"+  studentfn + "'" + "OR Student.Stu_lastName ="+ "'"+studentln+ "'" + "ORDER BY Student.studentID";
        tournamentnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,String>("tournamentName"));
        eventnamecol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,String>("tournamentEventName"));
        studentfirstcol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,String>("Stu_firstName"));
        studentlastcol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,String>("Stu_lastName"));
        finalplacementcol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,Integer>("finalPlacement"));
        eventsizecol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,Integer>("eventSize"));
        eventratingcol.setCellValueFactory(new PropertyValueFactory<OneStudentTournamentResult,String>("eventRatingName"));
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<OneStudentTournamentResult>> T = new BeanListHandler<OneStudentTournamentResult>(OneStudentTournamentResult.class);
        List<OneStudentTournamentResult> oneStudentTournamentResults = queryRunner.query(sql,T);
        reportlist.setItems(FXCollections.observableArrayList(oneStudentTournamentResults));



    }
}
