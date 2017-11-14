import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class NewTournamentDataController {

    @FXML
    private TextField tournamentName;
    @FXML
    private TextField tournamenttype;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button Save;

    public void save_Button_Pressed(ActionEvent actionEvent) throws  SQLException {

        Connection connection = DBHelper.getINSTANCE().getConnection();
        String sql = "Insert into Tournament(tournamentName, startDate, endDate, tournamentType) VALUES (?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,tournamentName.getText());
            preparedStatement.setDate(2, Date.valueOf(startDate.getValue()));
            preparedStatement.setDate(3, Date.valueOf(endDate.getValue()));
            preparedStatement.setString(4,tournamenttype.getText());
            preparedStatement.execute();
            connection.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New tournament");
            alert.setHeaderText("Added new Tournament");
            alert.setContentText("Successfully Added new Tournament");
            alert.showAndWait();
        }

    }

    // Something here doesn't work file choser pops up something wrong with Dates
    public void importButtonPressed(ActionEvent actionEvent) throws SQLException, FileNotFoundException, ParseException {

        int linecounter = 0;
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showOpenDialog(stage);
        Scanner importfile = new Scanner(file);
        while (importfile.hasNext()){
            String line =importfile.nextLine();
            // the line below changes the  column delimiter so change this before we submit
            String[] lines = line.split(",");
            Tournament tournament = new Tournament();
            Connection connection = DBHelper.getINSTANCE().getConnection();
            if(linecounter > 0){
                tournament.setTournamentName(lines[1]);

                tournament.setImportstartDate(lines[2]);

                tournament.setImportendDate(lines[3]);
                tournament.setTournamentType(lines[4]);

                String sql = "Insert into Tournament(tournamentName, startDate, endDate, tournamentType) VALUES (?,?,?,?)";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setString(1,lines[1]);
                    preparedStatement.setDate(2, Date.valueOf(lines[2]));
                    preparedStatement.setDate(3, Date.valueOf(lines[3]));
                    preparedStatement.setString(4,lines[4]);
                    preparedStatement.execute();
                    preparedStatement.close();

                }

            }
            linecounter++;
            connection.close();
        }

    }

    public void ManageTournamentList(ActionEvent actionEvent) throws IOException
    {
        Parent root= FXMLLoader.load(getClass().getResource("TournamentList.fxml"));
        createStage(root, actionEvent);
    }

    public void goTournamentMenu(ActionEvent actionEvent) throws IOException
    {
        Parent root= FXMLLoader.load(getClass().getResource("ManageTournament.fxml"));
        createStage(root, actionEvent);
    }

    public void goHome(ActionEvent actionEvent) throws IOException
    {
        Parent root9 = FXMLLoader.load(getClass().getResource("mainscreen.fxml"));
        createStage(root9, actionEvent);
    }

    public void createStage(Parent root, ActionEvent actionEvent)
    {
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


}
