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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class GoalListController extends MainController {

    public TableColumn studentfirstname;
    public TableColumn studentlastname;
    public TableColumn goalname;
    public TableColumn dateentered;
    public TableColumn datecompleted;
    public TableColumn Goal_Description;
    public TableView goaltableview;
    public Button deletebutton;
    public TableColumn weaponNamecol;
    public TableColumn studentIDcol;

    public void initialize() throws SQLException{
        studentfirstname.setCellValueFactory(new PropertyValueFactory<Goal,String>("Stu_firstName"));
        studentlastname.setCellValueFactory(new PropertyValueFactory<Goal,String>("Stu_lastName"));
        goalname.setCellValueFactory(new PropertyValueFactory<Goal,String>("goalName"));
        dateentered.setCellValueFactory(new PropertyValueFactory<Goal,Date>("dateEntered"));
        datecompleted.setCellValueFactory(new PropertyValueFactory<Goal,Date>("dateCompleted"));
        Goal_Description.setCellValueFactory(new PropertyValueFactory<Goal,String>("goalDescrption"));
        weaponNamecol.setCellValueFactory(new PropertyValueFactory<Goal,String>("weaponName"));
        studentIDcol.setCellValueFactory(new PropertyValueFactory<Goal,String>("studentID"));

        SQLServerDataSource ds = Datasource.getINSTANCE().datasource();
        QueryRunner queryRunner = new QueryRunner(ds);
        ResultSetHandler<List<Goal>> g = new BeanListHandler<Goal>(Goal.class);
        String sql = "SELECT Stu_firstName,Stu_lastName,goalName,Goal.dateEntered,dateCompleted,Goal.goalDescrption,Weapon.weaponName,Student.studentID FROM STUDENT INNER JOIN GOAL ON STUDENT.studentID = Goal.studentID  INNER JOIN Weapon  on goal.weaponID = weapon.weaponID";
        List<Goal> goals = queryRunner.query(sql,g);
        goaltableview.setItems(FXCollections.observableArrayList(goals));
        goaltableview.getSelectionModel().getSelectedIndex();
        int idx = 0;
        if(goaltableview.getSelectionModel().getSelectedIndex() > -1){
            idx = goaltableview.getSelectionModel().getSelectedIndex();
        }

    }

    public void UpdateGoal(ActionEvent actionEvent) throws IOException {

       Goal goal = (Goal) goaltableview.getSelectionModel().getSelectedItem();
       int goalid = goaltableview.getSelectionModel().getSelectedIndex()+1;
       if(goal !=null){
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateGoal.fxml"));
           Parent root4 = fxmlLoader.load();
           UpdateGoalController updateGoalController = fxmlLoader.getController();
           updateGoalController.datecompleted.setValue(goal.getDateCompleted().toLocalDate());
           updateGoalController.dateentered.setValue(goal.getDateEntered().toLocalDate());
           updateGoalController.goaldes.setText(goal.getGoalDescrption());
           updateGoalController.goalName.setText(goal.getGoalName());
           updateGoalController.studentidtextfield.setText(String.valueOf(goal.getStudentID()));
           updateGoalController.weapon.setValue(goal.getWeaponName());
           updateGoalController.goalidtext.setText(String.valueOf(goalid));
           createStage(root4, actionEvent);

       }

    }

    public void ManageNewGoal(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("NewGoal.fxml"));
        createStage(root8, actionEvent);
    }

    public void goGoalMenu(ActionEvent actionEvent) throws IOException {
        Parent root8 = FXMLLoader.load(getClass().getResource("ManageGoal.fxml"));
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

    public void deletebuttonpressed(ActionEvent actionEvent) {
        deletebutton.setText("FUCK THIS CLASS");
    }
}
