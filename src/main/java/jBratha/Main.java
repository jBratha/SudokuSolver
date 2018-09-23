package jBratha;


import jBratha.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/window.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("sudoku Solver");
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
