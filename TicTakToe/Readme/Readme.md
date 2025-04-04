Piece(can be extended to other symbols)
    -Cross
    -Circle

Player
    -id
    -name
    -Symbol Chosen(X, O)
    functions()
        -markTheSymbol(id,row,col)
        -chooseSymbol()

Board
    -List<Player> playerLst;
    -rows
    -columns
    -game=[[rows][colums]] - List<List<Integer>> board
    -setGame()-Initialise all with 0s
    -setSymbol(id,rowNo,colNo)
    -checkValidStep(rowNo,colNo)
    -isWinner(id,rowNo,colNo)

    