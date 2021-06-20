import com.sun.javafx.tk.ScreenConfigurationAccessor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader ( this.getClass ().getResource ("view/GamePage.fxml") );
        loader.load ();
        primaryStage.setTitle ("Reversi Game");
        primaryStage.setScene (new Scene (loader.getRoot ()));
        primaryStage.show ();


    }

}