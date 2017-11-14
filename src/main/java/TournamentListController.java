import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * Created by Ryan on 10/28/2017.
 */
public class TournamentListController extends MainController{
    @FXML
    private TableView<Tournament> Tournament;
    @FXML
    private TableColumn<Tournament, String> TournamentName;
    @FXML
    private TableColumn<Tournament, String> TournamentType;
    @FXML
    private TableColumn<Tournament, Date> startDate;
    @FXML
    private TableColumn<Tournament, Date> endDate;


    public void initialize() throws SQLException{
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        TournamentName.setCellValueFactory(new PropertyValueFactory<Tournament,String>("tournamentName"));
        TournamentType.setCellValueFactory(new PropertyValueFactory<Tournament,String>("tournamentType"));
        startDate.setCellValueFactory(new PropertyValueFactory<Tournament,Date>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Tournament,Date>("endDate"));

        int select = 0;
        if(Tournament.getSelectionModel().getSelectedIndex() > -1){
            select = Tournament.getSelectionModel().getSelectedIndex();
        }
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Tournament>> t = new BeanListHandler<Tournament>(Tournament.class);
        List<Tournament> tournaments = queryRunner.query("Select * FROM Tournament" , t);
        Tournament.setItems(FXCollections.observableArrayList(tournaments));
        Tournament.getSelectionModel().select(select);



    }

    public void UpdateTournament(ActionEvent actionEvent) throws IOException {

        //Parent root4= FXMLLoader.load(getClass().getResource("UpdateTournament.fxml"));
        Tournament tournament = Tournament.getSelectionModel().getSelectedItem();
        int tournamentid = Tournament.getSelectionModel().getSelectedIndex()+1;
        if(tournament != null){


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateTournament.fxml"));
        Parent root4 = fxmlLoader.load();
        UpdateTournamentController updateTournamentController = fxmlLoader.getController();
        updateTournamentController.endDate.setValue(tournament.getEndDate().toLocalDate());
        updateTournamentController.startDate.setValue(tournament.getStartDate().toLocalDate());
        updateTournamentController.tournamentName.setText(tournament.getTournamentName());
        updateTournamentController.tournamenttype.setText(tournament.getTournamentType());
        updateTournamentController.tournamentidtext.setText(String.valueOf(tournamentid));
        createStage(root4, actionEvent);
    }
    }

    public void ManageNewTournament(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("NewTournamentData.fxml"));
        createStage(root4, actionEvent);
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

}
