package jwentityclass;

import implementation.DataList;
import implementation.WordStack;
import java.util.Scanner;

public class Story {
	private int marksCollected=0;
        private WordStack<Word> getWord = new WordStack<>();
        
        public Story(int chosenLevel,DataList<Word> entry) {
            entry.shuffle();
            wordSelection(chosenLevel,entry);
        }
        
        private void wordSelection(int level,DataList<Word> select){ 
            Word temp;
            for(int i=0;i<select.getLength();i++){
               temp = select.getEntry(i+1);
               if(temp.getLevel()== level ){
                  getWord.push(temp);
               }
            }
        }
        
        public void generateStory(){
            Scanner display = new Scanner(System.in);
            
            int count=0;
            
            while(!getWord.isEmpty()&&count<3){
            int temp=0;
            Word current = getWord.pop();
            System.out.println("");
            System.out.println("["+current.shuffle()+"]" + " " + current.getSentences());
            System.out.print("Guess your answer : ");
            String answer = display.nextLine();
            
            while(!current.checkEquals(answer)&&temp<3){
                System.out.println("");
                System.out.print("Oh o... It's not correct~" + "You left " + (2-temp) + " times "+  "try again : ");
                answer = display.nextLine();
                temp+=1;
                
            }
            
                if(temp<3){
                    System.out.println("");
                    System.out.println("**************************");
                    System.out.println("Amazing! You scored " + current.getScore() + ".");
                    System.out.println("**************************");
                    this.marksCollected+=current.getScore();
                }
                else{
                    current.setScore(0);
                    System.out.println("");
                    System.out.println("***********************************************");
                    System.out.println("Nevermind! Keep trying ! You get " + current.getScore() + ".");
                    System.out.println("***********************************************");
                }
                count+=1;
            }
            
        }

	public int getMarksCollected() {
            return this.marksCollected;
	}

	public void setMarksCollected(int marksCollected) {
		this.marksCollected = marksCollected;
	}
        
	

}