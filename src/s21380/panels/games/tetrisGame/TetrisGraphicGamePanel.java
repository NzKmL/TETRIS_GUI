package s21380.panels.games.tetrisGame;

import s21380.panels.games.GraphicGamePanel;
import s21380.panels.games.score.ScoreFileProcessor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class TetrisGraphicGamePanel extends GraphicGamePanel implements KeyListener {

    private int blockSize;
    private int boardWidth, boardHeight;
    private TetrisElementColor[][] board;// [row][col]
    private TetrisElementColor[][] tempBoard;
    private boolean isNotGameOver = false;
    private int tempTempo;
    private int TEMPO = 3;
    private Block currentBlock;
    boolean isOK=true;
    Thread thread;


    public TetrisGraphicGamePanel(int width, int hight, int boardWidth, int boardHeight) {
        super(width, hight);
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.blockSize = (hight-1)/(boardHeight) ;

        this.setPreferredSize(new Dimension(width,hight));
        this.tempTempo =TEMPO; //row down per secound
        this.gameScore = 0;
        this.level=1;

        board = new TetrisElementColor[boardHeight][boardWidth];
        tempBoard = new TetrisElementColor[boardHeight][boardWidth];
      ;
        //startGame();

    }

    public void startGame(){
        if(thread!=null && thread.isAlive()) {
           gameOver();
        }
            TEMPO=3;
            gameScore=0;
            tempTempo=TEMPO;
            board = new TetrisElementColor[boardHeight][boardWidth];
            tempBoard = new TetrisElementColor[boardHeight][boardWidth];
            currentBlock = new Block(0, boardWidth);
            isNotGameOver=true;
             isOK=true;

        if(thread ==null) {
            thread = new Thread(() -> {

                currentBlock = new Block(0, boardWidth);
                while (isNotGameOver) {
                    moveBlock();

                    try {
                        Thread.sleep(getDelay());
                    } catch (InterruptedException e) {
                        System.err.println("Game thread error");
                    }

                }
                gameOver();
                thread.interrupt();
                thread = null;
            });
        }
        if(!thread.isAlive()) {
            thread.start();
        }
    }

    public void gameOver() {
        System.out.println("Save score to file;");

        if (gameScore>0){
            System.out.println("add score: " +gameScore);
            ScoreFileProcessor.getInstance().addScore(gameScore);
        }

        ScoreFileProcessor.getInstance().saveScoreFile();
    }



    private void moveBlock() {

        fullRowsOperations();

        isOK=   addElementToTempBoard(currentBlock);

        if (isOK){
            for (int j=0; j< currentBlock.getShapeBoard()[currentBlock.getShapeBoard().length-1].length; j++) {
                for(int i=currentBlock.getShapeBoard().length-1; i>=0;i--)
                    if(currentBlock.getShapeBoard()[i][j]!=null) {
                        if((currentBlock.getCurrentRow()+i+1<boardHeight)&&((tempBoard[currentBlock.getCurrentRow()+i+1][currentBlock.getCurrentColumn()+j] !=null)||(currentBlock.getCurrentRow() + currentBlock.getShapeBoard().length >= boardHeight))){
                            isOK=false;

                        }
                        break;
                    }
            }
        }
        if(isOK){
            currentBlock.incrementRow();
        }else {
            fullRowsOperations();
            if (currentBlock.getCurrentRow() == 0) {
                isNotGameOver = false;
            } else {
                board = tempBoard;
                currentBlock = new Block(0, boardWidth);
            }
        }
    }

    private void fullRowsOperations() {
        int combo =0;
        for(int i=0; i<boardHeight; i++){
            boolean isfull = isRowFull(board[i]);

            if(isfull){
                for(int j=0; j<board[i].length; j++){
                    board[i][j]=null;
                    combo++;
                }
                for(int k=i; k>1; k--){
                    board[k] = board[k-1];
                }
                board[0] = new TetrisElementColor[boardWidth];
                scoreOperation(combo);
            }

        }


    }

    private void scoreOperation(int combo) {
        gameScore += 10 + level*5 + (combo-1)*5;

        level = gameScore/200;
        ++TEMPO;
        tempTempo=TEMPO;
        System.out.println(level);
    }

    private long getDelay() {
        return 1000/ tempTempo;

    }

    private boolean isRowFull(TetrisElementColor[] row){
        //return false if any element of board in the line is not null;
        // return true if a line is full of elements

       if(Arrays.stream(row).filter(tetrisElementColor -> tetrisElementColor==null).count()>0)
            return false;
        return true;
    }

    private boolean addElementToTempBoard(Block block) {
        if (currentBlock.getCurrentRow()+currentBlock.getShapeBoard().length>boardHeight){
            return false;
        }
        tempBoard = copyBoard(board);


        for(int i=0; i< block.getShapeBoard().length; i++){
            for(int j=0; j<block.getShapeBoard()[i].length; j++){
                if(block.getShapeBoard()[i][j]!=null)
                tempBoard[block.getCurrentRow()+i][block.getCurrentColumn()+j] = block.getShapeBoard()[i][j];

            }

        }
        return true;
    }

    private TetrisElementColor[][] copyBoard(TetrisElementColor[][] board){
        TetrisElementColor[][] tempboard = new TetrisElementColor[board.length][];
        for(int i=0; i<board.length; i++){
            tempboard[i] = new TetrisElementColor[board[i].length];
                for(int j=0; j<board[i].length; j++){
                 tempboard[i][j] = board[i][j];
                }
        }
        return tempboard;
    }

    @Override

    public void paintComponent(Graphics g) {

            super.paintComponent(g);
            int zeroPoint = blockSize/2;

            for(int row = 0; row < tempBoard.length; row++)
                for(int col = 0; col < tempBoard[row].length; col++)
                    if(tempBoard[row][col] != null) {
                        g.setColor(tempBoard[row][col].getColor());
                        g.fillRect((width - blockSize * boardWidth) / 2 + col * blockSize, zeroPoint + blockSize * row, blockSize, blockSize);

                    }

            g.setColor(Color.BLACK); //line color
            for(int i = 0; i <= boardHeight; i++) {
                g.drawLine((width  - blockSize * boardWidth) / 2, i * blockSize + zeroPoint,
                        (width  + blockSize * boardWidth) / 2, i * blockSize+zeroPoint);
                if(i<=boardWidth)
                    g.drawLine((width  - blockSize * boardWidth) / 2 + i * blockSize  , zeroPoint,
                            (width  - blockSize * boardWidth) / 2 + i * blockSize, boardHeight * blockSize+zeroPoint);

            }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isNotGameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                currentBlock.moveLeft();
                addElementToTempBoard(currentBlock);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                currentBlock.moveRight();
                addElementToTempBoard(currentBlock);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                tempTempo = TEMPO * 3;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                currentBlock.rotation();
            }
            addElementToTempBoard(currentBlock);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            tempTempo =TEMPO;}
        }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}
