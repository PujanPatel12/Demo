import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Course List, works 90% doesn't Grab course namep
 */
public class ClassListController extends MainController {

    @FXML
    private TableView<Class> ClassList;
    @FXML
    private TableColumn<Course, String> CourseName;
    @FXML
    private TableColumn<Class,Integer> SectionNumber;
    @FXML
    private TableColumn<Class,Date> startDate;
    @FXML
    private TableColumn<Class,Date> endDate;
    @FXML
    private TableColumn<Class, Integer> Classstatus;

    public void initialize() throws SQLException{



            SQLServerDataSource ds = Datasource.datasource();
            CourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
            SectionNumber.setCellValueFactory(new PropertyValueFactory<Class, Integer>("sectionNumber"));
            startDate.setCellValueFactory(new PropertyValueFactory<Class, Date>("classStartDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<Class, Date>("classEndDate"));
            Classstatus.setCellValueFactory(new PropertyValueFactory<Class, Integer>("Cla_statDescription"));

            int idx = 0;
            if (ClassList.getSelectionModel().getSelectedIndex() > -1) {
                idx = ClassList.getSelectionModel().getSelectedIndex();

            }

            QueryRunner queryRunner = new QueryRunner(ds);
            ResultSetHandler<List<Class>> C = new BeanListHandler<Class>(Class.class);



            List<Class> classes = queryRunner.query("SELECT Course.courseName, sectionNumber,classStartDate,classEndDate,Class_Status.Cla_statDescription FROM Class INNER JOIN Class_Status ON Class.Cla_statCode = Class_Status.Cla_statusCode INNER JOIN Course ON Class.courseID = Course.courseID ", C);

            ClassList.setItems(FXCollections.observableArrayList(classes));
            ClassList.getSelectionModel().getSelectedIndex();

    }


    public void UpdateClass(ActionEvent actionEvent) throws IOException {

       Class classes = ClassList.getSelectionModel().getSelectedItem();
       int classvalue = ClassList.getSelectionModel().getSelectedIndex()+1;
       if(classes != null){
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateClass.fxml"));
           Parent root = fxmlLoader.load();
           UpdateClassController updateClassController = fxmlLoader.getController();
           updateClassController.courseIDtext.setText(String.valueOf(classvalue));
           updateClassController.Class_Status.setValue(classes.getCla_statDescription());
            updateClassController.startDatePicker.setValue(classes.getClassStartDate().toLocalDate());
            updateClassController.EnddatePicker.setValue(classes.getClassEndDate().toLocalDate());
            updateClassController.sectionumbertextfield.setText(String.valueOf(classes.getSectionNumber()));
            updateClassController.Course_Choice_Box.setValue(classes.getCourseName());
           createStage(root,actionEvent);
       }
    }

    public void ManageNewClass(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("NewClass.fxml"));
        createStage(root4, actionEvent);
    }

    public void goClassMenu(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("ManageClass.fxml"));
        createStage(root4, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }


    public void deletebuttonpressed(ActionEvent actionEvent) throws SQLException {
        int classid =  ClassList.getSelectionModel().getSelectedIndex() +1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String SQL =  "UPDATE Class SET Cla_statCode = '1' WHERE classID="+ classid;
        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL)){
            preparedStatement.execute();
            //System.out.println("Changed CLass ID:" + classid + " to Inactive");
            initialize();
            preparedStatement.close();

        }
        connection.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Class Status");
        alert.setHeaderText("Successfully made Class Status inactive");
        alert.setContentText("Successfully made Class Inactive");
        alert.showAndWait();
    }
}
