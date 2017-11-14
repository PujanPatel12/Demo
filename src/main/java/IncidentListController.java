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
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ryan on 11/13/2017.
 */
public class IncidentListController extends MainController {

    public TableView incidentlist;
    public TableColumn<Incident,String> namecol;
    public TableColumn<Incident,String> desccol;

    public void initialize() throws SQLException{
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        namecol.setCellValueFactory(new PropertyValueFactory<Incident,String>("incidentType"));
        desccol.setCellValueFactory(new PropertyValueFactory<Incident,String>("incidentTypeDescription"));
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Incident>> I = new BeanListHandler<Incident>(Incident.class);
        String sql = "Select incidentTypeID, incidentType,IncidentTypeDescription from Incident_Type";
        List<Incident> incidents = queryRunner.query(sql,I);

        incidentlist.setItems(FXCollections.observableArrayList(incidents));
    }



    public void ManageNewIncident(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("NewIncident.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageUpdateIncident(ActionEvent actionEvent) throws IOException {
        Incident incident = (Incident) incidentlist.getSelectionModel().getSelectedItem();
        int incidentid = incidentlist.getSelectionModel().getSelectedIndex()+1;
        //Parent root8 = FXMLLoader.load(getClass().getResource("UpdateIncident.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateIncident.fxml"));
        if(incident != null){
            Parent root8 = fxmlLoader.load();
            UpdateIncidentController updateIncidentController = fxmlLoader.getController();
            updateIncidentController.inccidentdesta.setText(incident.getIncidentTypeDescription());
            updateIncidentController.incidentnamettf.setText(incident.getIncidentType());
            updateIncidentController.incidentIDtext.setText(String.valueOf(incidentid));

            createStage(root8, actionEvent);
        }


    }

    public void goIncidentMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageIncident.fxml"));
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
