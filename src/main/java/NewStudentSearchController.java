import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by PUJAN on 10/6/2017.
 */
public class NewStudentSearchController extends MainController {

    public void ManageNewStudent(ActionEvent actionEvent) throws IOException {
        Parent root4= FXMLLoader.load(getClass().getResource("NewStudentProfile.fxml"));
        createStage(root4, actionEvent);
    }

    public void goStudentMenu(ActionEvent actionEvent) throws IOException {
        Parent root4= FXMLLoader.load(getClass().getResource("ManageStudent.fxml"));
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
