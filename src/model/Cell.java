package model;

import javafx.scene.control.Button;

public class Cell extends Button {
    private boolean isChoosed;
    private boolean isBlue;
    private int x,y;

    


    public Cell(int x, int y) {
        super(" ");
        this.x = x;
        this.y = y;

        isBlue=false;
        isChoosed=false;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }
}
