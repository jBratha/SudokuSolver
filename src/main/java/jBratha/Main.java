package jBratha;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jBratha.controller.Controller;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/window.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        stage.setScene(new Scene(root));
//        controller.setUpKeyListeners();
        stage.setTitle("Sudoku Solver");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
