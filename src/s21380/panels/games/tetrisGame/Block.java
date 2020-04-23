package s21380.panels.games.tetrisGame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Block {

    private Integer currentRow;
    private int currentColumn;
    private int boardWidth;
    private TetrisElementColor shapeBoard[][];
    private Set<TetrisElementColor[][]> blockTypeSet ;

    public Block(int startRow, int boardWidth){
        this.currentRow =startRow;
        this.currentColumn = boardWidth/2;
        this.boardWidth = boardWidth;

        shapeBoard = new TetrisElementColor[1][1];
        TetrisElementColor element = TetrisElementColor.generateTetrisElementColor();
        shapeBoard = new TetrisElementColor[][]{{element, element},{element, null}};

        blockTypeSet = new HashSet<TetrisElementColor[][]>();

        blockTypeSet.add( new TetrisElementColor[][]{{element, element},{element, null}});
        blockTypeSet.add( new TetrisElementColor[][]{{element},{element},{element},{element}});
        blockTypeSet.add( new TetrisElementColor[][]{{element, element},{element, element}});
        blockTypeSet.add(new TetrisElementColor[][]{{element, element},{element, null},{element, null}});
        blockTypeSet.add(new TetrisElementColor[][]{{element, element},{null, element},{null, element}});
        blockTypeSet.add(new TetrisElementColor[][]{{element, null},{element, element},{element, null}});
        shapeBoard = getRandomElement();
    }

    private  TetrisElementColor[][] getRandomElement(){
        //return random color from blocktypeset
        return (TetrisElementColor[][]) blockTypeSet.toArray()[new Random().nextInt(blockTypeSet.size())];
    }
    public void incrementRow(){
        currentRow++;
}

    public void moveLeft() {
        if(currentColumn !=0)
        currentColumn--;
    }

    public void moveRight() {
        if(currentColumn+shapeBoard[0].length !=boardWidth)
        currentColumn++;
    }

    public Integer getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(Integer currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public TetrisElementColor[][] getShapeBoard() {
        return shapeBoard;
    }

    public void setShapeBoard(TetrisElementColor[][] shapeBoard) {
        this.shapeBoard = shapeBoard;
    }

    public void rotation(){
        // Rotacja w lewo - pierwszy wiersz staje się ostatnią kolumną bez zmiany kolejności elementów
        int oldRowNumb;//[x][]
        int oldColNumb;//[][x]

        oldColNumb=shapeBoard[0].length;
        oldRowNumb = shapeBoard.length;

        TetrisElementColor newshapeBoard[][] = new TetrisElementColor[oldColNumb][oldRowNumb];

        for (int i =0; i<oldColNumb; i++){
            for(int j=0; j<oldRowNumb; j++){
                newshapeBoard[i][oldRowNumb-1-j] =shapeBoard[j][i];
            }
        }

        shapeBoard = newshapeBoard;
    }
}
