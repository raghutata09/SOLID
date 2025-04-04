package TicTakToe.Readme;

import java.util.List;

public class Board {

    int rows;
    int columns;
    PlayingPiece[][] board;
    Board(int rows,int col){
        this.rows=rows;
        this.columns = col;
        this.board= new PlayingPiece[rows][col];
    }

    public boolean addPiece(int row, int col,PlayingPiece playingPiece){
        if(board[row][col]!=null){
            return false;
        }
        board[row][col]=playingPiece;
        return true;
    }

    public boolean getFreeCells(int id, int row, int col,PlayingPiece playingPiece){
        if(board[row][col]!=null){
            return false;
        }
        board[row][col]=playingPiece;
        return true;
    }

    public boolean isBoardFilled(){
        for(int i=0;i<rows;i++){
            for(int c=0;c<columns;c++){
                if(board[i][c]==null){
                    return false;
                }
            }
        }
        return true;
    }




}
