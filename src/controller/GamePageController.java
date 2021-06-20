package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {


    @FXML
    private TableColumn<?, ?> blueTAB;

    @FXML
    private TableColumn<?, ?> redTAB;

    @FXML
    private Label blueLBL;

    @FXML
    private Label redLBL;

    @FXML
    private TextField blueField;

    @FXML
    private TextField redField;

    @FXML
    private Hyperlink hyperlink;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
