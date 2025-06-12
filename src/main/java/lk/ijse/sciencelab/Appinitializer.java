package lk.ijse.sciencelab;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appinitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(Appinitializer.class.getResource("/view/Funder.fxml"));
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene scene=new Scene(parent);

//        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ScienceLab!");
        stage.setScene(scene);
        stage.show();


     /*   Parent parent=FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene=new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Supermarket App");*/
    }
}
