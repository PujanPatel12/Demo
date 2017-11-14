import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by pujan on 10/6/17.
 */


public class ClassRosterController {


    public TableView<Student> RosterTable;
    @FXML
    public TableColumn <Student, String> col_studentLastName;
    @FXML
    public TableColumn <Student, String> col_studentFirstname;
    @FXML
    public TableColumn<Student, Date> dateofBirth;


    public void initialize()throws SQLException{
        Connection connection = DBHelper.getINSTANCE().getConnection();
/*
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();

        ds.setURL("jdbc:sqlserver://10.211.55.3:1433;databaseName=test");
        ds.setDatabaseName("test");
        ds.setUser("pujan");
        ds.setPassword("pujan");
        ds.getConnection();

*/

        SQLServerDataSource datasource = Datasource.getINSTANCE().datasource();
        col_studentFirstname.setCellValueFactory(new PropertyValueFactory<Student, String>("Stu_firstName"));
        col_studentLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("Stu_lastName"));

        //this tests to make sure date is being grabbed which it is but its not showing up in javafx.. too many damn columns
        try {
            dateofBirth.setCellValueFactory(new PropertyValueFactory<Student, Date>("Stu_dateOfBirth"));
            System.out.println("Connection successfull");

        }catch (Error error)
        {
            System.out.println("Not connecting");
        }
        int idx = 0;
        if(RosterTable.getSelectionModel().getSelectedIndex() > -1){
            idx = RosterTable.getSelectionModel().getFocusedIndex();

        }
        QueryRunner queryRunner =  new QueryRunner(datasource);
        ResultSetHandler<List<Student>> l = new BeanListHandler<>(Student.class);
        List<Student> students = queryRunner.query("SELECT Stu_firstname, Stu_lastname, Stu_dateOfBirth FROM Student ORDER BY StudentID",l);
        RosterTable.setItems(FXCollections.observableArrayList(students));
        RosterTable.getSelectionModel().select(idx);




/*

        ObservableList students = null;
        try{
            students = FXCollections.observableArrayList(StudentHelper.getOurInstance().getStudents());
            System.out.println("Connection successful");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("connection failed");
        }
        RosterTable.setItems(students);
*/


    }






}
