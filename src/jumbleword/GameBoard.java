/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumbleword;

import implementation.StoryQueue;
import jwentityclass.Word;
import jwentityclass.Player;
import jwentityclass.Story;
import implementation.DataList;
import implementation.SortedScoreList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import jwentityclass.Score;
import jwinterface.SortedListInterface;

/**
 *
 * @author Asus
 */
public class GameBoard {

    DataList<Player> getPlayer = new DataList<>();
    DataList<Word> getData = new DataList<>();
    StoryQueue<Story> getStory = new StoryQueue<>();
    SortedListInterface<Score> rankingBoard = new SortedScoreList<Score>();
    private Story currentStory = null;
    private int totalMarks = 0;
    Scanner gameBoard = new Scanner(System.in);

    public GameBoard() {

        readScoreHistory();
        initializeWord();
        displayMenu();

    }

    public void displayMenu() {
        Scanner menu = new Scanner(System.in);
        int selection = 0;
        while (selection != 3) {
            System.out.println("Jumble Word");
            System.out.println("************");
            System.out.println("1.New Game");
            System.out.println("2.Show ranking list");
            System.out.println("3.Exit");

            System.out.print("Enter your selection : ");
            selection = menu.nextInt();

            while (selection < 1 && selection > 3) {
                System.out.println("Oops! Invalid input ya !");
                System.out.println("Enter your selection : ");
                selection = menu.nextInt();
            }

            if (selection == 1) {
                startGame();
            } else if (selection == 2) {
                System.out.println("");
                System.out.printf("Name\t|ID\t| Total Score\n");
                System.out.println("***********************************************");
                if (rankingBoard.isEmpty()) {
                    System.out.println("<< There's no ranking owh ! Go play game ba.>>");
                    System.out.println("");
                } else {
                    System.out.println(rankingBoard.toString());
                    System.out.println("");
                }
                
            } else
            ;
        }
        
        writeScoreHistory(rankingBoard);

    }

    public void startGame() {

        // TODO code application logic here
        System.out.println("");
        System.out.print("Player name : ");
        String playerName = gameBoard.nextLine();
        
        while(!checkAlpha(playerName)){
            System.out.println("Type your name in alphabet only ya! ");
            System.out.print("Player Name: ");
            playerName = gameBoard.nextLine();
        }
        Player newPlayer = new Player(playerName);

        boolean repeatGame = true;

        while (repeatGame) {
            loadGame(playerName);
            if (playAgain()) {
                repeatGame = true;
            } else {
                repeatGame = false;
            }
        }

        if (gameOver()) {
            System.out.println("");
            System.out.println("Your total scored marks :" + totalMarks);
            rankingBoard.add(new Score(totalMarks, newPlayer));
        } else
            ;

        
        totalMarks = 0;
    }

    public void loadGame(String player) {
        Scanner loadGame = new Scanner(System.in);
        System.out.println("");
        System.out.println("Level Selection");
        System.out.println("****************");
        System.out.println("1.Easy");
        System.out.println("2.Medium");
        System.out.println("3.Hard");
        System.out.print("[Choose your level]: ");
        int level = loadGame.nextInt();
        System.out.println("");
        System.out.println("*******************************************************");
        System.out.println("Player name : " + player + "\t|\t" + "Current level : " + level);
        System.out.println("*******************************************************");
        initializeGame(level);
        while (!getStory.isEmpty()) {
            currentStory = getStory.dequeue();
            currentStory.generateStory();
            totalMarks += currentStory.getMarksCollected();

        }
        getStory.clear();
    }
    
    public boolean checkAlpha(String check){
        String pattern= "^[a-zA-Z]*$";
        return check.matches(pattern);
    }
    
    public void readScoreHistory(){
     try {
            File myQuestion = new File("score.txt");
            Scanner myReader = new Scanner(myQuestion);
            while(myReader.hasNextLine()){
                String playerName = null;
                String playerID_string = null;
                String marks_string = null;
                String data = myReader.nextLine();
                String[] arrSplit = data.split(",");
                //for(int i=0;i<arrSplit.length;i++){
                    playerName = arrSplit[0];
                    playerID_string = arrSplit[1];
                    marks_string = arrSplit[2];
                    int playerID = Integer.parseInt(playerID_string);
                    int marks = Integer.parseInt(marks_string);
                    //sentences = arrSplit[i];
                 
                //}
               rankingBoard.add(new Score(marks,new Player(playerID,playerName)));
               
             }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void writeScoreHistory(SortedListInterface<Score> entry){
         try{    
           FileWriter fw=new FileWriter("score.txt");    
           fw.write(entry.toString());    
           fw.close();    
          }catch(Exception e){System.out.println(e);} 
    }
    public void initializeGame(int levelChoice) {
        Story easyLevel = new Story(1, getData);
        Story mediumLevel = new Story(2, getData);
        Story hardLevel = new Story(3, getData);

        switch (levelChoice) {
            case 1:
                getStory.enqueue(easyLevel);
                break;
            case 2:
                getStory.enqueue(mediumLevel);
                getStory.enqueue(easyLevel);
                break;
            case 3:
                getStory.enqueue(hardLevel);
                getStory.enqueue(mediumLevel);
                getStory.enqueue(easyLevel);
                break;
            default:
                break;
        }
    }

    public boolean gameOver() {
        System.out.println("Game Over !");
        //System.out.println("Your total marks is : " + totalMarks);
        return true;
    }

    public boolean playAgain() {
        Scanner playAgain = new Scanner(System.in);
        System.out.println("Play Again ? [Y/N]");
        char pAgain = playAgain.next().charAt(0);
        char response = Character.toUpperCase(pAgain);
        while (response != 'Y' && response != 'N') {
            System.out.println("Check your input owh ! try again !");
            pAgain = playAgain.next().charAt(0);
        }

        return response == 'Y';

    }

    public void initializeWord() {
        try {
            File myQuestion = new File("question.txt");
            Scanner myReader = new Scanner(myQuestion);
            while (myReader.hasNextLine()) {
                String word = null;
                String sentences = null;
                String data = myReader.nextLine();
                String[] arrSplit = data.split("@");
                //for(int i=0;i<arrSplit.length;i++){
                word = arrSplit[0];
                sentences = arrSplit[1];
                //sentences = arrSplit[i];
                //}
                getData.add(new Word(word, sentences));

            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void displayAll(DataList<Word> entry) {
        System.out.println(entry.toString());
    }

}
