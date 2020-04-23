package s21380.panels.games.score;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreFileProcessor {
    String filePatch;
    File file;
    Integer bestScore;
    List<Integer> fileIntegerList;

    private static ScoreFileProcessor instance;

    public static ScoreFileProcessor getInstance(){
        if(instance==null){
            instance = new ScoreFileProcessor("resources/ScoreTab");
        }
        return instance;
    }

    private ScoreFileProcessor(String filePatch) {
        this.filePatch = filePatch;
         file = new File(filePatch);
        initFileProcesor();
    }

    private void initFileProcesor(){
        fileIntegerList = new ArrayList<>();
        Scanner myReader = null;

        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("ScoreFile read error - file not found");
        }

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            for (String temp: data.split(";")
                 ) {
                fileIntegerList.add(Integer.parseInt(temp));
            }
        }
        myReader.close();
    }


    public List<Integer> getFileInList(){
    return fileIntegerList;
    }

    public String getBestScore(){
        Integer tempBestScore=0;
        if (fileIntegerList!=null && fileIntegerList.size()>0){
            for (Integer tempInt : fileIntegerList) {
                if (tempBestScore<=tempInt){
                    tempBestScore=tempInt;
                }
            }
        }
        return tempBestScore.toString();
    }

    public void addScore(Integer newScore){
        fileIntegerList.add(newScore);
    }

    public void saveScoreFile(){
        Writer writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Integer tempInt : fileIntegerList
             ) {
            try {
                writer.write(tempInt.toString()+";");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
