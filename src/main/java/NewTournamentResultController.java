import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by Ryan on 11/14/2017.
 */
public class NewTournamentResultController extends MainController {

    public void ManageTournamentResultList(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("TournamentResultList.fxml"));
        createStage(root4, actionEvent);
    }

    public void goTournamentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root4= FXMLLoader.load(getClass().getResource("ManageTournament.fxml"));
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
