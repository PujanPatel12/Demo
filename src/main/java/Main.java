import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Round Rock Fencing Club Application");
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Round Rock Fencing Club Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) throws SQLException {
        DBHelper dbHelper = DBHelper.getINSTANCE();
        dbHelper.init();
        launch(args);
        dbHelper.close();
    }


    public void NewStudentButtonClicked(ActionEvent actionEvent) throws IOException{

    }


}
