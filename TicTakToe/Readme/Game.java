package TicTakToe.Readme;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Board board;
    Deque<Player> players;

    Game() {
        initialiseGame();
    }

    private void initialiseGame() {
        players = new LinkedList<>();
        PlayingPiece playingPieceX = new PlayingPieceX();
        PlayingPiece playingPieceO = new PlayingPieceO();

        Player player1 = new Player(12L, "Raghu", playingPieceX);
        Player player2 = new Player(12L, "Raghu", playingPieceO);

        players.add(player1);
        players.add(player2);

        board = new Board(3, 3);
    }

    public String startGame() {

        boolean noWinner = true;
        while (noWinner) {
            Player pl = players.removeFirst();

            if (board.isBoardFilled()) {
                noWinner=false;
                continue;
            }

            System.out.println("Hi Player "+pl.name +" , it's your turn now. Please enter row and col");
            Scanner inputScn= new Scanner(System.in);
            String s = inputScn.nextLine();
            String value[] = s.split(",");
            int inputRow = Integer.parseInt(value[0]);
            int inputCol = Integer.parseInt(value[1]);
            Boolean isPieceAddedSuccesfull = board.addPiece(inputRow,inputCol,pl.playingPiece);
            if(!isPieceAddedSuccesfull){
                players.addFirst(pl);
                continue;
            }

            players.addLast(pl);
            boolean isWinner = isThereWinner(inputRow,inputCol,pl.playingPiece.p);
            if(isWinner){
                return pl.name;
            }

        }
        return "TIE ";
    }

    public boolean isThereWinner(Integer row,Integer col, PieceType p){
        // Logic for row, col and diagnol;
        return true;
    }

}
