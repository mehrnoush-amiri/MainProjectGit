package controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Cell;

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
    private Hyperlink newGameBTN;

    @FXML
    private VBox gameStage;

    @FXML
    private Hyperlink player1BTN;



    private boolean isBlue;

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Cell[][] cells=new Cell[8][8];

        for (int i=0 ; i<8 ; i++){
            HBox hBox = new HBox (  );
            hBox.setAlignment (Pos.CENTER);
            for (int j=0 ; j<8 ; j++){
                Cell cell = new Cell (i,j);
                cell.setPrefHeight (400);
                cell.setPrefWidth (400);
                cells[i][j]=cell;
                hBox.getChildren ().add (cell);
            }
            gameStage.getChildren ().add (hBox);
        }

        newGameBTN.setOnAction(event -> {
            setBlue(true);

        });

    }



    //isturn for player
    private boolean isTurn(Cell[][] cells, boolean isBlue){
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (cells[i][j].isChoosed() && cells[i][j].isBlue()==isBlue){

                    if (isTurnColRight(cells,isBlue,i,j)){
                        return true;
                    }
                    if (isTurnColLeft(cells,isBlue,i,j)){
                        return true;
                    }
                    if (isTurnRowDown(cells,isBlue,i,j)){
                        return true;
                    }
                    if (isTurnRowUp(cells,isBlue,i,j)){
                        return true;
                    }
                    if (isTurnbothDownRight(cells,isBlue,i,j)){
                        return true;
                    }
                    if (isTurnbothUpLeft(cells,isBlue,i,j)){
                        return true;
                    }
                    if (isTurnbothDownLeft(cells,isBlue,i,j)) {
                        return true;
                    }
                    if (isTurnbothUpRight(cells,isBlue,i,j)) {
                        return true;
                    }


                }
            }
        }
        return false;
    }
    //check 8 side of a button
    private boolean isTurnColRight(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (j+1<8)
                if (!cells[i][j+1].isChoosed()){
                    return false;
                }
            if (j+1<8)
                if (cells[i][j+1].isChoosed() && cells[i][j+1].isBlue()!=isBlue) {
                    int changej=j+1;
                    while (true){
                        changej++;
                        if (changej>7){
                            return false;
                        }
                        if (cells[i][changej].isBlue()==isBlue && cells[i][changej].isChoosed()){
                            return false;
                        }
                        if (!cells[i][changej].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnColLeft(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (j-1>-1)
                if (!cells[i][j-1].isChoosed()){
                    return false;
                }
            if (j-1>-1)
                if (cells[i][j-1].isChoosed() && cells[i][j-1].isBlue()!=isBlue) {
                    int changej=j-1;
                    while (true){
                        changej--;
                        if (changej<0){
                            return false;
                        }
                        if (cells[i][changej].isBlue()==isBlue &&cells[i][changej].isChoosed()){
                            return false;
                        }
                        if (!cells[i][changej].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnRowDown(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if(i+1<8)
                if (!cells[i+1][j].isChoosed()){
                    return false;
                }
            if(i+1<8)
                if (cells[i+1][j].isChoosed() && cells[i+1][j].isBlue()!=isBlue) {
                    int changei=i+1;
                    while (true){
                        changei++;
                        if (changei>7){
                            return false;
                        }
                        if (cells[changei][j].isBlue()==isBlue &&cells[changei][j].isChoosed()){
                            return false;
                        }
                        if (!cells[changei][j].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnRowUp(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (i-1>-1)
                if (!cells[i-1][j].isChoosed()){
                    return false;
                }
            if (i-1>-1)
                if (cells[i-1][j].isChoosed() && cells[i-1][j].isBlue()!=isBlue) {
                    int changei=i-1;
                    while (true){
                        changei--;
                        if (changei<0){
                            return false;
                        }
                        if (cells[changei][j].isBlue()==isBlue && cells[changei][j].isChoosed()){
                            return false;
                        }
                        if (!cells[changei][j].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnbothUpLeft(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (i-1>-1 && j-1>-1)
                if (!cells[i-1][j-1].isChoosed()){
                    return false;
                }
            if (i-1>-1 && j-1>-1)
                if (cells[i-1][j-1].isChoosed() && cells[i-1][j-1].isBlue()!=isBlue) {
                    int changei=i-1;
                    int changej=j-1;
                    while (true){
                        changei--;
                        changej--;
                        if (changei<0 || changej<0){
                            return false;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            return false;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnbothUpRight(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (i-1>-1 && j+1<8)
                if (!cells[i-1][j+1].isChoosed()){
                    return false;
                }
            if (i-1>-1 && j+1<8)
                if (cells[i-1][j+1].isChoosed() && cells[i-1][j+1].isBlue()!=isBlue) {
                    int changei=i-1;
                    int changej=j+1;
                    while (true){
                        changei--;
                        changej++;
                        if (changei<0 || changej>7){
                            return false;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            return false;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnbothDownLeft(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (i+1<8 && j-1>-1)
                if (!cells[i+1][j-1].isChoosed()){
                    return false;
                }
            if (i+1<8 && j-1>-1)
                if (cells[i+1][j-1].isChoosed() && cells[i+1][j-1].isBlue()!=isBlue) {
                    int changei=i+1;
                    int changej=j-1;
                    while (true){
                        changei++;
                        changej--;
                        if (changei>7 || changej<0){
                            return false;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            return false;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

    private boolean isTurnbothDownRight(Cell[][] cells,boolean isBlue,int i,int j){
        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {
            if (i+1<8 && j+1<8)
                if (!cells[i+1][j+1].isChoosed()){
                    return false;
                }
            if (i+1<8 && j+1<8)
                if (cells[i+1][j+1].isChoosed() && cells[i+1][j+1].isBlue()!=isBlue) {
                    int changei=i+1;
                    int changej=j+1;
                    while (true){
                        changei++;
                        changej++;
                        if (changei>7 || changej>7){
                            return false;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            return false;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            return true;
                        }
                    }
                }
        }

        return false;
    }

}
