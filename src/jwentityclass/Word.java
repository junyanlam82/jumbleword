/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwentityclass;

import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Rays
 */
public class Word {
    
    private String content;
    private String sentences;
    private int wordCount;
    private int level;
    private int score = 0;
    //private boolean taken = false;
    
    public Word(String content, String sentences) {
        this.content = content;
        this.sentences = sentences;
        this.wordCount = content.length();
        if(wordCount <5){
            this.level = 1;
        }
        else if(wordCount<7){
            this.level = 2;
        }
        else{
            this.level = 3;
        }
         //used to check when this word stack is being used
    }

    public String shuffle() {
        String shuffledWord = this.content; // start with original
        int wordSize = shuffledWord.length();
        int shuffleCount = 10; // let us randomly shuffle letters 10 times
        for(int i=0;i<shuffleCount;i++) {
            //swap letters in two indexes
            int position1 = ThreadLocalRandom.current().nextInt(0, wordSize);
            int position2 = ThreadLocalRandom.current().nextInt(0, wordSize);
            shuffledWord = swapCharacters(shuffledWord,position1,position2);
        }
        
        return shuffledWord;
    }
    
    private String swapCharacters(String shuffledWord, int position1, int position2) {
        char[] charArray = shuffledWord.toCharArray();
        // Replace with a "swap" function, if desired:
        char temp = charArray[position1];
        charArray[position1] = charArray[position2];
        charArray[position2] = temp;
        return new String(charArray);
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public String getContent() {
        return content;
    }

    public int getLevel() {
        return level;
    }

     public int getScore() {
        return score;
    }
     
    public void setContent(String content) {
        this.content = content;
    }

    public String getSentences() {
        return sentences;
    }

    public int getWordCount() {
        return wordCount;
    }
    
    @SuppressWarnings("empty-statement")
    public boolean checkEquals(String userEnter){
        
        boolean result = userEnter.equals(content); 
        if(result){
            score = 2*level;
        }
        else{
            ;
        }
        return result;
        
    }
    @Override
    public String toString() {
        return /*content.length() +*/ "Word{" + "content=" + content + ", level=" + level + ", wordcount =" + content.length() + '}';
    }

}
