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
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ryan on 11/14/2017.
 */
public class TournamentResultListController extends MainController {

    public TableColumn studentID;
    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn tournamentnamecol;
    public TableColumn tournamenteventname;
    public TableColumn incidatorcol;
    public TableColumn finalplacementcol;
    public TableColumn eventsizecol;
    public Button CreateTournamentButton;
    public TableView TournamentResultList;


    public void initialize() throws SQLException {
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        studentID.setCellValueFactory(new PropertyValueFactory<TournamentResult,Integer>("studentID"));
        studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<TournamentResult,String>("Stu_firstName"));
        studentlastnamecol.setCellValueFactory(new PropertyValueFactory<TournamentResult,String>("Stu_lastName"));
        tournamentnamecol.setCellValueFactory(new PropertyValueFactory<TournamentResult,String>("tournamentName"));
        tournamenteventname.setCellValueFactory(new PropertyValueFactory<TournamentResult,String>("tournmentEventName"));
        incidatorcol.setCellValueFactory(new PropertyValueFactory<TournamentResult,Integer>("indicator"));
        finalplacementcol.setCellValueFactory(new PropertyValueFactory<TournamentResult,Integer>("finalPlacement"));
        eventsizecol.setCellValueFactory(new PropertyValueFactory<TournamentResult,Integer>("eventSize"));
        String sql ="select Event_Result.eventResultID, Student.studentID,Stu_firstName,Stu_lastName,tournamentName,tournmentEventName,Event_Result.indicator,Event_Result.finalPlacement,Tournament_Event.eventSize from Student INNER JOIN Event_Result on Student.studentID = Event_Result.studentID INNER JOIN Tournament_Event on Event_Result.tournamentEventID = Tournament_Event.tournamentEventID INNER JOIN Tournament on Tournament_Event.tournamentID = Tournament.tournamentID";

        ResultSetHandler<List<TournamentResult>> T = new BeanListHandler<TournamentResult>(TournamentResult.class);
        List<TournamentResult> tournamentResultList = queryRunner.query(sql,T);
        TournamentResultList.setItems(FXCollections.observableArrayList(tournamentResultList));



    }

    public void ManageNewTournamentResult(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("NewTournamentResult.fxml"));
        createStage(root4, actionEvent);
    }

    public void UpdateNewTournamentResult(ActionEvent actionEvent) throws IOException {
        //Parent root4= FXMLLoader.load(getClass().getResource("NewTournamentResult.fxml"));
        TournamentResult tournamentResult = (TournamentResult) TournamentResultList.getSelectionModel().getSelectedItem();
        int trid = TournamentResultList.getSelectionModel().getSelectedIndex()+1;

        if(tournamentResult != null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateTournamentResult.fxml"));
            Parent root4 = fxmlLoader.load();
            UpdateTournamentResultController tournamentResultController = fxmlLoader.getController();

            tournamentResultController.studentchoicebox.setValue(tournamentResult.getStudentID());
            tournamentResultController.eventnamechoicebox.setValue(tournamentResult.getTournmentEventName());
            tournamentResultController.finalplacementtextfield.setText(String.valueOf(tournamentResult.getFinalPlacement()));
            tournamentResultController.indicatortextfield.setText(String.valueOf(tournamentResult.getIndicator()));
            tournamentResultController.idtext.setText(String.valueOf(trid));

            createStage(root4, actionEvent);

        }

    }

    public void goTournamentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("ManageTournament.fxml"));
        createStage(root4, actionEvent);
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
