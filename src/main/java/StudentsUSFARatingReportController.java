import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
 * Created by Ryan on 11/10/2017.
 */
public class StudentsUSFARatingReportController extends MainController {

    public TableColumn studentidcol;
    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn weaponNamecol;
    public TableColumn USfarankingcol;
    public TableView reportlist;
    public ChoiceBox weaponchoicebox;



    public void initialize() throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT  DISTINCT weaponID,weaponName from Weapon");
        String weaponName;

        while (resultSet1.next()) {

            weaponName= resultSet1.getString("weaponName");
            //    lastname = resultSet1.getString("Stu_lastName");
            //     studentidchoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            weaponchoicebox.getItems().addAll(weaponName);
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

    public void generatereport() throws SQLException {

        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        int weaponID = weaponchoicebox.getSelectionModel().getSelectedIndex()+1;
        QueryRunner queryRunner = new QueryRunner(ds);
        String sql ="select student.studentID,student.Stu_lastName,student.Stu_firstName,Competitive_Weapon.USFARatingCode,Competitive_Weapon.weaponID,weaponName, USFARatingDescription from Student INNER JOIN Competitive_Weapon on Student.studentID = Competitive_Weapon.studentID INNER JOIN  Weapon on Competitive_Weapon.weaponID = Weapon.weaponID INNER JOIN USFA_Rating on Competitive_Weapon.USFARatingCode = USFA_Rating.USFARatingCode WHERE weapon.weaponID =" + weaponID;
        studentidcol.setCellValueFactory(new PropertyValueFactory<StudentUSFARating,Integer>("studentID"));
        studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<StudentUSFARating,String>("Stu_firstName"));
        studentlastnamecol.setCellValueFactory(new PropertyValueFactory<StudentUSFARating,String>("Stu_lastName"));
        weaponNamecol.setCellValueFactory(new PropertyValueFactory<StudentUSFARating,String>("weaponName"));
        USfarankingcol.setCellValueFactory(new PropertyValueFactory<StudentUSFARating,String>("USFARatingDescription"));

        ResultSetHandler<List<StudentUSFARating>> S = new BeanListHandler<StudentUSFARating>(StudentUSFARating.class);
        List<StudentUSFARating> studentUSFARatingList = queryRunner.query(sql,S);
        reportlist.setItems(FXCollections.observableArrayList(studentUSFARatingList));
    }


    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generatereport();
    }
}
