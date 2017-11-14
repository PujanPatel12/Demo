import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by Ryan on 10/31/2017.
 */
public class ManageGuardianController extends MainController{

    public void ManageNewGuardian(ActionEvent actionEvent) throws IOException {
        Parent root4= FXMLLoader.load(getClass().getResource("GuardianProfile.fxml"));
        createStage(root4, actionEvent);
    }

    public void ManageGuardianList(ActionEvent actionEvent) throws IOException {
        Parent root4= FXMLLoader.load(getClass().getResource("GuardianList.fxml"));
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
