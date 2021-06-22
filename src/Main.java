import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader ( this.getClass ().getResource ("view/GamePage.fxml") );
        loader.load ();
        primaryStage.setTitle ("Reversi Game");
        primaryStage.setScene (new Scene (loader.getRoot ()));
        primaryStage.show ();

        // primaryStage.setFullScreen (true);
       // primaryStage.setFullScreenExitKeyCombination (KeyCombination.NO_MATCH);




    }

}