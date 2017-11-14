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
import java.util.Date;
import java.util.List;

/**
 * Created by Ryan on 11/13/2017.
 */
public class IncidentReportListController extends MainController {

    public TableView incidentlist;
    public TableColumn studentidcol;
    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn incidentnamecol;
    public TableColumn incidentdatecol;
    public TableColumn incidentdescriptioncol;

    public void initialize() throws SQLException{
        initial();
    }

    public void initial() throws SQLException{
        String sql ="select incidentReportID,Student.studentID,Incident_Type.incidentTypeID,incidentDate,Stu_firstName,Stu_lastName,incidentTypeDescription,incidentType from Incident_Report INNER JOIN Incident_Type ON Incident_Report.incidentTypeID = Incident_Type.incidentTypeID INNER JOIN  Student ON Incident_Report.studentID = Student.studentID";
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<IncidentReport>> I = new BeanListHandler<IncidentReport>(IncidentReport.class);
        List<IncidentReport> incidentReports = queryRunner.query(sql,I);
        studentidcol.setCellValueFactory(new PropertyValueFactory<IncidentReport,Integer>("studentID"));
        studentlastnamecol.setCellValueFactory(new PropertyValueFactory<IncidentReport,String>("Stu_lastName"));
        studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<IncidentReport,String>("Stu_firstName"));
        incidentdatecol.setCellValueFactory(new PropertyValueFactory<IncidentReport,Date>("incidentDate"));
        incidentnamecol.setCellValueFactory(new PropertyValueFactory<IncidentReport,String>("incidentType"));
        incidentdescriptioncol.setCellValueFactory(new PropertyValueFactory<IncidentReport,String>("incidentTypeDescription"));
        incidentlist.setItems(FXCollections.observableArrayList(incidentReports));


    }

    public void ManageNewIncidentReport(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("NewIncidentReport.fxml"));
        createStage(root8, actionEvent);
    }

    public void ManageUpdateIncidentReport(ActionEvent actionEvent) throws IOException {
       // Parent root8 = FXMLLoader.load(getClass().getResource("UpdateIncidentReport.fxml"));
        IncidentReport incidentReport = (IncidentReport) incidentlist.getSelectionModel().getSelectedItem();
        int inicidentrepid = incidentlist.getSelectionModel().getSelectedIndex()+1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateIncidentReport.fxml"));
        if(incidentReport != null){
            Parent root8 = fxmlLoader.load();
            UpdateIncidentReportController updateIncidentReportController = fxmlLoader.getController();
            updateIncidentReportController.studentchoicebox.setValue(incidentReport.getStudentID());
            updateIncidentReportController.incidentchoiebox.setValue(incidentReport.getIncidentType());
            updateIncidentReportController.incidentdatepicker.setValue(incidentReport.getIncidentDate().toLocalDate());
            updateIncidentReportController.incidentrepid.setText(String.valueOf(inicidentrepid));
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
