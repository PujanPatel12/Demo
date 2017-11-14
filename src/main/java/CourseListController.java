import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ryan on 10/28/2017.
 */
public class CourseListController extends MainController {

    @FXML
    private Button UpdateButton;
    @FXML
    private TableView<Course> Course;
    @FXML
    private TableColumn<Course, String> CourseName;
    @FXML
    private TableColumn<Course, String> CourseDescription;
    @FXML
    private TableColumn<Course_Status, Integer> CourseStatus;


    public void initialize() throws SQLException {

        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();

        CourseName.setCellValueFactory(new PropertyValueFactory<Course,String>("courseName"));
        CourseDescription.setCellValueFactory(new PropertyValueFactory<Course,String>("courseDescription"));
        CourseStatus.setCellValueFactory(new PropertyValueFactory<Course_Status,Integer>("Cour_statDescription"));


            int idx = 0;
            if (Course.getSelectionModel().getSelectedIndex() > -1) {
                idx = Course.getSelectionModel().getSelectedIndex();
            }
            QueryRunner queryRunner = new QueryRunner(ds);

            ResultSetHandler<List<Course>> C = new BeanListHandler<Course>(Course.class);
            List<Course> courses = queryRunner.query("select courseID,courseName,courseDescription,Course_Status.Cour_statDescription FROM Course INNER JOIN Course_Status ON Course.Cour_statusCode = Course_Status.Cour_statusCode", C);
            Course.setItems(FXCollections.observableArrayList(courses));
            Course.getSelectionModel().select(idx);


    }


//    @Override
//    public void ManageNewCourse(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateCourse.fxml"));
//        Course course = Course.getSelectionModel().getSelectedItem();
//        UpdateCourseController updateCourseController = fxmlLoader.getController();
//        updateCourseController.CourseNameTextfield.setText(course.getCourseName());
//        updateCourseController.CourseDescription_textfield.setText(course.getCourseDescription());
//
//        super.ManageNewCourse(actionEvent);
//    }

    public void UpdateCourse(ActionEvent actionEvent) throws IOException, SQLException {
        Course course = Course.getSelectionModel().getSelectedItem();
        int coursevalue = Course.getSelectionModel().getSelectedIndex()+1;
        if(course != null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateCourse.fxml"));
            Parent root = fxmlLoader.load();
            UpdateCourseController updateCourseController = fxmlLoader.getController();
            updateCourseController.CourseDescription_textfield.setText(course.getCourseDescription());
            updateCourseController.CourseNameTextfield.setText(course.getCourseName());
            updateCourseController.Cour_Status_Choicebox.setValue(course.getCour_statDescription());
            updateCourseController.courseidtextfield.setText(String.valueOf(coursevalue));
            createStage(root,actionEvent);
            initialize();


        }
    }


    public void ManageNewCourse(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("NewCourse.fxml"));
        createStage(root4, actionEvent);
    }

    public void goCourseMenu(ActionEvent actionEvent) throws IOException {

        Parent root4= FXMLLoader.load(getClass().getResource("ManageCourse.fxml"));
        createStage(root4, actionEvent);
    }

    @Override
    public void goHome(ActionEvent actionEvent) throws IOException {
        super.goHome(actionEvent);
    }


    public void deletebuttonpressed(ActionEvent actionEvent) throws SQLException {
        int courseid =  Course.getSelectionModel().getSelectedIndex() +1;
        Connection connection = DBHelper.getINSTANCE().getConnection();
        String SQL =  "UPDATE Course SET Cour_statusCode = '1' WHERE courseID="+ courseid;
        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL)){
            preparedStatement.execute();
           // System.out.println("Changed Student ID:" + courseid + " to Inactive");
            initialize();
            preparedStatement.close();

        }
        connection.close();
    }
}

