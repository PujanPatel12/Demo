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
public class StudentsWithUnsatisfactoryTestsPerWeaponController extends MainController {

    public TableColumn studentlastnamecol;
    public TableColumn studentfirstnamecol;
    public TableColumn testnamecol;
    public TableColumn testdatecol;
    public TableColumn studentscorecol;
    public TableColumn highestposscorecol;
    public TextField studentscoretextfield;
    public ChoiceBox weaponChoicebox;
    public TableView reportlist;



    public void initialize() throws SQLException {
        Connection connection = DBHelper.getINSTANCE().getConnection();
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT weaponID,weaponName from Weapon");
        String weaponName;

        while (resultSet1.next()) {

            weaponName= resultSet1.getString("weaponName");
            //    lastname = resultSet1.getString("Stu_lastName");
            //     studentidchoicebox.getItems().addAll(id + " | " + lastname + ", " + studentname);
            weaponChoicebox.getItems().addAll(weaponName);
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

    public void generate() throws SQLException {
        int weaponid = weaponChoicebox.getSelectionModel().getSelectedIndex()+1;
        int studentScore = Integer.parseInt(studentscoretextfield.getText());
        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        String sql ="SELECT Test.weaponID,weaponName,Stu_firstName,Stu_lastName,studentScore,highestTestScore,testName,testDate FROM Student INNER JOIN Test_Result ON Student.studentID = Test_Result.studentID INNER JOIN  TEST ON Test_Result.testID = Test.testID  INNER JOIN  Weapon on Test.weaponID = Weapon.weaponID WHERE Test.weaponID =" +weaponid + "and   Test_Result.studentScore <" + studentScore;
        QueryRunner queryRunner = new QueryRunner(ds);
        studentfirstnamecol.setCellValueFactory(new PropertyValueFactory<StudentWithUnsatifiactoryTestPerWeapon,String>("Stu_firstName"));
        studentlastnamecol.setCellValueFactory(new PropertyValueFactory<StudentWithUnsatifiactoryTestPerWeapon,String>("Stu_lastName"));
//        testnamecol.setCellValueFactory(new PropertyValueFactory<StudentWithUnsatifiactoryTestPerWeapon,String>("testName"));
        studentscorecol.setCellValueFactory(new PropertyValueFactory<StudentWithUnsatifiactoryTestPerWeapon,Integer>("studentScore"));
        highestposscorecol.setCellValueFactory(new PropertyValueFactory<StudentWithUnsatifiactoryTestPerWeapon,Integer>("highestTestScore"));
        testdatecol.setCellValueFactory(new PropertyValueFactory<StudentWithUnsatifiactoryTestPerWeapon,Date>("testDate"));
        ResultSetHandler<List<StudentWithUnsatifiactoryTestPerWeapon>> S = new BeanListHandler<StudentWithUnsatifiactoryTestPerWeapon>(StudentWithUnsatifiactoryTestPerWeapon.class);
        List<StudentWithUnsatifiactoryTestPerWeapon> studentWithUnsatifiactoryTestPerWeapons = queryRunner.query(sql,S);
        reportlist.setItems(FXCollections.observableArrayList(studentWithUnsatifiactoryTestPerWeapons));

        }

    public void generatebuttonpressed(ActionEvent actionEvent) throws SQLException {
        reportlist.refresh();
        generate();
    }
}
