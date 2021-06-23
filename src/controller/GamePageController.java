package controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Cell;
import java.net.URL;
import java.util.*;

public class GamePageController implements Initializable {


    @FXML
    private SplitPane split;

    @FXML
    private VBox vboxStage;

    @FXML
    private AnchorPane anchorPaneView;

    @FXML
    private ListView<Integer> listViewBlue;

    @FXML
    private ListView<Integer> listViewRed;

    @FXML
    private Label blueLBL;

    @FXML
    private Label redLBL;

    @FXML
    private TextField blueField;

    @FXML
    private TextField redField;

    @FXML
    private Button newGameBTN;

    @FXML
    private Button player1BTN;

    @FXML
    private Button player2BTN;

    @FXML
    private Label listViewBlueBTN;

    @FXML
    private Label listViewRedBTN;




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
///build cells in vbox
        VBox gameStage = new VBox (  );
        vboxStage.getChildren ().add (gameStage);
        for (int i=0 ; i<8 ; i++){
            HBox hBox = new HBox (  );

            hBox.setAlignment (Pos.CENTER);
            for (int j=0 ; j<8 ; j++){
                Cell cell = new Cell (i,j);
                cell.setPrefHeight (400);
                cell.setPrefWidth (400);
                cells[i][j]=cell;
                hBox.getChildren ().add (cell);
                hBox.heightProperty ().addListener (new ChangeListener<Number> ( ) {
                    @Override
                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                        cell.setPrefHeight (arg2.doubleValue ());
                    }
                });
                hBox.widthProperty ().addListener (new ChangeListener<Number> ( ) {
                    @Override
                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                        cell.setPrefWidth (arg2.doubleValue ());
                    }
                });
            }
            gameStage.heightProperty ().addListener (new ChangeListener<Number> ( ) {
                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                    hBox.setPrefHeight (arg2.doubleValue ());
                }
            });
            gameStage.widthProperty ().addListener (new ChangeListener<Number> ( ) {
                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                    hBox.setPrefWidth (arg2.doubleValue ());
                }
            });
            gameStage.getChildren ().add (hBox);
        }


        vboxStage.heightProperty ().addListener (new ChangeListener<Number> ( ) {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                gameStage.setPrefHeight (arg2.doubleValue ()/4);
            }
        });
        vboxStage.widthProperty ().addListener (new ChangeListener<Number> ( ) {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                gameStage.setPrefWidth (arg2.doubleValue ()/4);
            }
        });


//color the game stage
        split.setStyle("-fx-background-color: yellow");
        listViewBlue.setStyle("-fx-background-color: yellow");
        blueField.setStyle("-fx-background-color: skyblue");
        redField.setStyle("-fx-background-color: pink");
        listViewRed.setStyle("-fx-background-color: yellow");
        listViewBlueBTN.setStyle("-fx-background-color: skyblue");
        listViewRedBTN.setStyle("-fx-background-color: pink");


        //start new game
        newGameBTN.setOnAction(event -> {
            redField.setText ("2");
            blueField.setText ("2");

            setBlue(true);
            firstColor(cells);
            if (isTurn(cells, isBlue)) {
                setVisiable(cells, isBlue);
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Cell cell = cells[i][j];
                    final int fi = i;
                    final int fj = j;
                    cell.setOnAction(event1 -> {
                        //check if cell is suitable for being selected
                        if (!cell.isDisabled() && !cell.isChoosed()){
                            //check is turn blue
                            if (isBlue()){
                                cell.setChoosed(true);
                                cell.setBlue(true);
                                cell.setStyle("-fx-background-color: darkblue");
                                cells[fi][fj] = cell;
                                //change color of cells
                                betweenColorRowDown(fi, fj, isBlue(), cells);
                                betweenColorRowUp(fi, fj, isBlue(), cells);
                                betweenColorColLeft(fi, fj, isBlue(), cells);
                                betweenColorColRight(fi, fj, isBlue(), cells);
                                betweenColorDownRight(fi, fj, isBlue(), cells);
                                betweenColorDownLeft(fi, fj, isBlue(), cells);
                                betweenColorUpLeft(fi, fj, isBlue(), cells);
                                betweenColorUpRight(fi, fj, isBlue(), cells);
                                changeColor(cells);
                                int blue=0,red=0;
                                    for (int l=0;l<8;l++){
                                        for (int k=0;k<8;k++){
                                            if (cells[l][k].isChoosed() && cells[l][k].isBlue() ){
                                                blue++;
                                               blueField.setText(String.valueOf(blue));
                                            }
                                            if (cells[l][k].isChoosed() && !cells[l][k].isBlue() ){
                                               red++;
                                               redField.setText(String.valueOf(red));
                                            }

                                        }
                                    }
                                //check turn
                                if (isTurn(cells, false)) {
                                    setBlue(false);
                                    setVisiable(cells, isBlue());
                                } else {
                                    setBlue(true);
                                    setVisiable(cells, isBlue());
                                }
                            }
                            //check is turn red
                            else {
                                cell.setChoosed(true);
                                cell.setBlue(false);
                                cell.setStyle("-fx-background-color: red");
                                cells[fi][fj] = cell;
                                //change color in turn red
                                betweenColorRowDown(fi, fj, isBlue(), cells);
                                betweenColorRowUp(fi, fj, isBlue(), cells);
                                betweenColorColLeft(fi, fj, isBlue(), cells);
                                betweenColorColRight(fi, fj, isBlue(), cells);
                                betweenColorDownRight(fi, fj, isBlue(), cells);
                                betweenColorDownLeft(fi, fj, isBlue(), cells);
                                betweenColorUpLeft(fi, fj, isBlue(), cells);
                                betweenColorUpRight(fi, fj, isBlue(), cells);
                                changeColor(cells);
                                int blue=0,red=0;
                                for (int l=0;l<8;l++){
                                    for (int k=0;k<8;k++){
                                        if (cells[l][k].isChoosed() && cells[l][k].isBlue() ){
                                            blue++;
                                            blueField.setText(String.valueOf(blue));
                                        }
                                        if (cells[l][k].isChoosed() && !cells[l][k].isBlue() ){
                                            red++;
                                            redField.setText(String.valueOf(red));
                                        }

                                    }
                                }
                                //check turn for blue
                                if (isTurn(cells, true)) {
                                    setBlue(true);
                                    setVisiable(cells, isBlue());
                                } else {
                                    setBlue(false);
                                    setVisiable(cells, isBlue());

                                }
                            }
                        }


                        //check if is end of game
                        if (!isTurn(cells, true) && !isTurn(cells, false)){
                            int blue=0,red=0;
                                    for (int l=0;l<8;l++){
                                        for (int k=0;k<8;k++){
                                            if (cells[l][k].isChoosed() && cells[l][k].isBlue() ){
                                                blue++;

                                            }
                                            if (cells[l][k].isChoosed() && !cells[l][k].isBlue() ){
                                                red++;

                                            }

                                        }
                                    }
                            listViewBlue.getItems ().add (0,blue);
                            listViewRed.getItems ().add (0,red);

                        }


                    });
                }
            }
        });


        //play as player one with computer
        player1BTN.setOnAction(event -> {
            blueField.setText ("2");
            redField.setText ("2");
            setBlue(true);

            firstColor(cells);
            if (isTurn(cells,isBlue)){
                setVisiable(cells,isBlue);
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Cell cell = cells[i][j];
                    final int fi = i;
                    final int fj = j;
                    cell.setOnAction(event1 -> {

                        if (!cell.isDisabled() && !cell.isChoosed()){

                            if (isBlue()){
                                cell.setChoosed(true);
                                cell.setBlue(true);
                                cell.setStyle("-fx-background-color: darkblue");
                                cells[fi][fj] = cell;
                                //change color of cells
                                betweenColorRowDown(fi, fj, isBlue(), cells);
                                betweenColorRowUp(fi, fj, isBlue(), cells);
                                betweenColorColLeft(fi, fj, isBlue(), cells);
                                betweenColorColRight(fi, fj, isBlue(), cells);
                                betweenColorDownRight(fi, fj, isBlue(), cells);
                                betweenColorDownLeft(fi, fj, isBlue(), cells);
                                betweenColorUpLeft(fi, fj, isBlue(), cells);
                                betweenColorUpRight(fi, fj, isBlue(), cells);
                                changeColor(cells);
                                int blue=0,red=0;
                                    for (int l=0;l<8;l++){
                                        for (int k=0;k<8;k++){
                                            if (cells[l][k].isChoosed() && cells[l][k].isBlue() ){
                                                blue++;
                                                blueField.setText(String.valueOf(blue));
                                            }
                                            if (cells[l][k].isChoosed() && !cells[l][k].isBlue() ){
                                                red++;
                                                redField.setText(String.valueOf(red));
                                            }

                                        }
                                    }
                                //check turn
                                if (isTurn(cells, false)){
                                    setBlue(false);
                                    setVisiable(cells, isBlue());
                                    ArrayList<Cell> systemPlayerCells = new ArrayList<>();
                                    //find appropriate cells for system
                                    for (int l = 0; l < 8; l++) {
                                        for (int k = 0; k < 8; k++) {
                                            if (!cells[l][k].isChoosed() && !cells[l][k].isDisabled()) {
                                                systemPlayerCells.add(cells[l][k]);
                                            }
                                        }
                                    }

                                    if (systemPlayerCells.size() > 0) {
                                        Random rand = new Random();

                                        int randNum=0;
                                        if(systemPlayerCells.size()>1)
                                            randNum = rand.nextInt(systemPlayerCells.size() - 1);
                                        //change certain cell
                                        cells[systemPlayerCells.get(randNum).getX()][systemPlayerCells.get(randNum).getY()] =
                                                systemPlayerCells.get(randNum);
                                        cells[systemPlayerCells.get(randNum).getX()][systemPlayerCells.get(randNum).getY()].setChoosed(true);
                                        cells[systemPlayerCells.get(randNum).getX()][systemPlayerCells.get(randNum).getY()].setBlue(false);
                                        cells[systemPlayerCells.get(randNum).getX()][systemPlayerCells.get(randNum).getY()].setStyle("-fx-background-color: red");
                                        //change is blue in 8 side
                                        betweenColorRowDown(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorRowUp(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorColLeft(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorColRight(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorDownRight(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorDownLeft(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorUpLeft(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);
                                        betweenColorUpRight(systemPlayerCells.get(randNum).getX(), systemPlayerCells.get(randNum).getY(), isBlue(), cells);

                                        //make timer to slow down changing color
                                        TimerTask task=new TimerTask() {
                                            @Override
                                            public void run() {
                                                changeColor(cells);
                                                if (isTurn(cells, true)) {
                                                    setBlue(true);
                                                    setVisiable(cells, isBlue());
                                                }
                                            }
                                        };
                                        Timer timer=new Timer();
                                        timer.schedule(task,2000);
                                        int blue1=0,red1=0;
                                    for (int l=0;l<8;l++){
                                        for (int k=0;k<8;k++){
                                            if (cells[l][k].isChoosed() && cells[l][k].isBlue() ){
                                                blue1++;
                                                blueField.setText(String.valueOf(blue1));
                                            }
                                            if (cells[l][k].isChoosed() && !cells[l][k].isBlue() ){
                                                red1++;
                                                redField.setText(String.valueOf(red1));
                                            }

                                        }
                                    }



                                    }
                                }else {
                                    setBlue(true);
                                    setVisiable(cells, isBlue());
                                }

                            }

                        }

                        if (!isTurn(cells, true) && !isTurn(cells, false)){
                            int blue=0,red=0;
                                    for (int l=0;l<8;l++){
                                        for (int k=0;k<8;k++){
                                            if (cells[l][k].isChoosed() && cells[l][k].isBlue() ){
                                                blue++;

                                            }
                                            if (cells[l][k].isChoosed() && !cells[l][k].isBlue() ){
                                                red++;

                                            }

                                        }
                                    }
                                    listViewBlue.getItems ().add (0,blue);
                                    listViewRed.getItems ().add (0,red);
                        }

                    });
                }
            }

        });


    }

    //page start game
    private void firstColor(Cell[][] cells){


        for (int i=0;i<cells.length;i++){
            for (int j=0;j< cells.length;j++){

                cells[i][j].setDisable(true);
                cells[i][j].setChoosed(false);
                cells[i][j].setStyle("-fx-background-color: silver");

            }
        }
        cells[3][3].setChoosed(true);
        cells[3][3].setBlue(true);
        cells[3][3].setDisable(false);
        cells[3][3].setStyle("-fx-background-color: darkblue");
        cells[4][4].setChoosed(true);
        cells[4][4].setBlue(true);
        cells[4][4].setDisable(false);
        cells[4][4].setStyle("-fx-background-color: darkblue");
        cells[3][4].setChoosed(true);
        cells[3][4].setBlue(false);
        cells[3][4].setDisable(false);
        cells[3][4].setStyle("-fx-background-color: red");
        cells[4][3].setBlue(false);
        cells[4][3].setDisable(false);
        cells[4][3].setStyle("-fx-background-color: red");
        cells[4][3].setChoosed(true);
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

    //check all cells isVisiable
    private void setVisiable(Cell[][] cells,boolean isBlue) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cells[i][j].isChoosed() && cells[i][j].isBlue()==isBlue) {
                    setVisColRight(cells,isBlue,i,j);

                    setVisColLeft(cells,isBlue,i,j);

                    setVisRowDown(cells,isBlue,i,j);

                    setVisRowup(cells,isBlue,i,j);

                    setVisbothDownRight(cells,isBlue,i,j);

                    setVisbothUpLeft(cells,isBlue,i,j);

                    setVisbothDownLeft(cells,isBlue,i,j);

                    setVisbothUpRight(cells,isBlue,i,j);

                }
            }
        }
    }
    //set visiable for each side
    private void setVisRowDown(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if(i+1<8)
                if (cells[i+1][j].isChoosed() && cells[i+1][j].isBlue()!=isBlue) {
                    int changei=i+1;
                    while (true){
                        changei++;
                        if (changei>7){
                            break;
                        }
                        if (cells[changei][j].isBlue()==isBlue &&cells[changei][j].isChoosed()){
                            break;
                        }
                        if (!cells[changei][j].isChoosed()){
                            cells[changei][j].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    private void setVisRowup(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (i-1>-1)
                if (cells[i-1][j].isChoosed() && cells[i-1][j].isBlue()!=isBlue) {
                    int changei=i-1;
                    while (true){
                        changei--;
                        if (changei<0){
                            break;
                        }
                        if (cells[changei][j].isBlue()==isBlue && cells[changei][j].isChoosed()){
                            break;
                        }
                        if (!cells[changei][j].isChoosed()){
                            cells[changei][j].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    private void setVisColLeft(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (j-1>-1)
                if (cells[i][j-1].isChoosed() && cells[i][j-1].isBlue()!=isBlue) {
                    int changej=j-1;
                    while (true){
                        changej--;
                        if (changej<0){
                            break;
                        }
                        if (cells[i][changej].isBlue()==isBlue &&cells[i][changej].isChoosed()){
                            break;
                        }
                        if (!cells[i][changej].isChoosed()){
                            cells[i][changej].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    private void setVisColRight(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (j+1<8)
                if (cells[i][j+1].isChoosed() && cells[i][j+1].isBlue()!=isBlue) {
                    int changej=j+1;
                    while (true){
                        changej++;
                        if (changej>7){
                            break;
                        }
                        if (cells[i][changej].isBlue()==isBlue && cells[i][changej].isChoosed()){
                            break;
                        }
                        if (!cells[i][changej].isChoosed()){
                            cells[i][changej].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    private void setVisbothUpLeft(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (i-1>-1 && j-1>-1)
                if (cells[i-1][j-1].isChoosed() && cells[i-1][j-1].isBlue()!=isBlue) {
                    int changei=i-1;
                    int changej=j-1;
                    while (true){
                        changei--;
                        changej--;
                        if (changei<0 || changej<0){
                            break;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            break;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            cells[changei][changej].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    private void setVisbothUpRight(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (i-1>-1 && j+1<8)
                if (cells[i-1][j+1].isChoosed() && cells[i-1][j+1].isBlue()!=isBlue) {
                    int changei=i-1;
                    int changej=j+1;
                    while (true){
                        changei--;
                        changej++;
                        if (changei<0 || changej>7){
                            break;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            break;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            cells[changei][changej].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    private void setVisbothDownLeft(Cell[][] cells,boolean isBlue,int i,int j){

        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (i+1<8 && j-1>-1)
                if (cells[i+1][j-1].isChoosed() && cells[i+1][j-1].isBlue()!=isBlue) {
                    int changei=i+1;
                    int changej=j-1;
                    while (true){
                        changei++;
                        changej--;
                        if (changei>7 || changej<0){
                            break;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            break;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            cells[changei][changej].setDisable(false);
                            break;
                        }
                    }
                }
        }
    }

    private void setVisbothDownRight(Cell[][] cells,boolean isBlue,int i,int j){
        if (cells[i][j].isChoosed() && cells[i][j].isBlue() == isBlue) {

            if (i+1<8 && j+1<8)
                if (cells[i+1][j+1].isChoosed() && cells[i+1][j+1].isBlue()!=isBlue) {
                    int changei=i+1;
                    int changej=j+1;
                    while (true){
                        changei++;
                        changej++;
                        if (changei>7 || changej>7){
                            break;
                        }
                        if (cells[changei][changej].isBlue()==isBlue && cells[changei][changej].isChoosed()){
                            break;
                        }
                        if (!cells[changei][changej].isChoosed()){
                            cells[changei][changej].setDisable(false);
                            break;
                        }
                    }
                }
        }

    }

    //change color of cells
    private void changeColor(Cell[][] cells){
        for (int i=0;i<cells.length;i++){
            for (int j=0;j< cells.length;j++){
                if (cells[i][j].isChoosed() && cells[i][j].isBlue()){
                    cells[i][j].setStyle("-fx-background-color: darkblue");
                    cells[i][j].setDisable(false);
                }
                if (cells[i][j].isChoosed() && !cells[i][j].isBlue()){
                    cells[i][j].setStyle("-fx-background-color: red");
                    cells[i][j].setDisable(false);
                }
                if (!cells[i][j].isChoosed()){
                    cells[i][j].setDisable(true);
                }
            }
        }
    }
    //change color boolean in 8 side
    private void betweenColorRowDown(int i,int j,boolean isBlue,Cell[][] cells){
        int index=-1;
        for (int l=i+1;l<8;l++){
            if (cells[l][j].isChoosed() ){
                if( cells[l][j].isBlue()==isBlue){
                    index=l;
                    break;
                }
            }else {
                break;
            }
        }
        if (index>-1){
            for (int l=i+1;l<index;l++){
                cells[l][j].setBlue(isBlue);
            }
        }
    }

    private void betweenColorRowUp(int i,int j,boolean isBlue,Cell[][] cells){
        int index=-1;
        for (int l=i-1;l>=0;l--){
            if (cells[l][j].isChoosed() ){
                if( cells[l][j].isBlue()==isBlue){
                    index=l;
                    break;
                }
            }else {
                break;
            }
        }
        if (index>-1){
            for (int l=i-1;l>index;l--){
                cells[l][j].setBlue(isBlue);
            }
        }
    }

    private void betweenColorColLeft(int i,int j,boolean isBlue,Cell[][] cells){
        int index=-1;
        for (int l=j-1;l>=0;l--){
            if (cells[i][l].isChoosed() ){
                if( cells[i][l].isBlue()==isBlue){
                    index=l;
                    break;
                }
            }else {
                break;
            }
        }
        if (index>-1){
            for (int l=j-1;l>index;l--){
                cells[i][l].setBlue(isBlue);
            }
        }
    }

    private void betweenColorColRight(int i,int j,boolean isBlue,Cell[][] cells){
        int index=-1;
        for (int l=j+1;l<8;l++){
            if (cells[i][l].isChoosed() ){
                if( cells[i][l].isBlue()==isBlue){
                    index=l;
                    break;
                }
            }else {
                break;
            }
        }
        if (index>-1){
            for (int l=j+1;l<index;l++){
                cells[i][l].setBlue(isBlue);
            }
        }
    }

    private void betweenColorDownRight(int i,int j,boolean isBlue,Cell[][] cells){
        int indexi=-1,indexj=-1;
        int changei=i,changej=j;

        for (int l=1;changei<7 && changej<7;l++){
            changei++;
            changej++;
            if (cells[changei][changej].isChoosed() ) {
                if (cells[changei][changej].isBlue() == isBlue) {
                    indexi = changei;
                    indexj = changej;
                    break;
                }
            }else {
                break;
            }
        }
        if (indexi!=-1  && indexj!=-1){
            while(i<changei && j<changej){
                i++;
                j++;
                cells[i][j].setBlue(isBlue);
            }

        }
    }

    private void betweenColorDownLeft(int i,int j,boolean isBlue,Cell[][] cells){
        int indexi=8,indexj=-1;
        int changei=i,changej=j;

        for (int l=1;changei<7 && changej>0;l++){
            changei++;
            changej--;
            if (cells[changei][changej].isChoosed()) {
                if (cells[changei][changej].isBlue() == isBlue) {
                    indexi = changei;
                    indexj = changej;
                    break;
                }
            }else {
                break;
            }
        }
        if (indexi!=8  && indexj!=-1){
            while(i<changei && j>changej){
                i++;
                j--;
                cells[i][j].setBlue(isBlue);
            }

        }
    }

    private void betweenColorUpRight(int i,int j,boolean isBlue,Cell[][] cells){
        int indexi=-1,indexj=8;
        int changei=i,changej=j;

        for (int l=1;changei>0 && changej<7;l++){
            changei--;
            changej++;
            if (cells[changei][changej].isChoosed() ){
                if( cells[changei][changej].isBlue()==isBlue){
                    indexi=changei;
                    indexj=changej;
                    break;
                }
            }else {
                break;
            }
        }
        if (indexi>-1  && indexj<8){
            while(i>changei && j<changej){
                i--;
                j++;
                cells[i][j].setBlue(isBlue);
            }

        }
    }

    private void betweenColorUpLeft(int i,int j,boolean isBlue,Cell[][] cells){
        int indexi=8,indexj=8;
        int changei=i,changej=j;

        for (int l=1;changei>0 && changej>0;l++){
            changei--;
            changej--;
            if (cells[changei][changej].isChoosed() ){
                if( cells[changei][changej].isBlue()==isBlue){
                    indexi=changei;
                    indexj=changej;
                    break;
                }
            }else {
                break;
            }
        }
        if (indexi!=8  && indexj!=8){
            while(i>changei && j>changej){
                i--;
                j--;
                cells[i][j].setBlue(isBlue);
            }

        }
    }



}
