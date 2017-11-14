import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by Ryan on 10/31/2017.
 */
public class ManageStudentController extends MainController {

    public void SearchStudent(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("NewStudentSearch.fxml"));
        createStage(root4, actionEvent);
    }

    public void ManageNewStudent(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("NewStudentProfile.fxml"));
        createStage(root4, actionEvent);
    }

    public void ManageStudentList(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("StudentList.fxml"));
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
