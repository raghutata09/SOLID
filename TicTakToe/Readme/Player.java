package TicTakToe.Readme;

public class Player {
    Long id;
    String name;
    PlayingPiece playingPiece;

    Player(Long id, String name, PlayingPiece playingPiece){
        this.id=id;
        this.name=name;
        this.playingPiece=playingPiece;
    }

    public PlayingPiece getSymbol(){
        return this.playingPiece;
    }




}
