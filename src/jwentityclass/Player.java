/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwentityclass;

/**
 *
 * @author Asus
 */
import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable{
    private static int nextPlayerID = 5000;
    private String name;
    private int playerID;
    private String password;
    Scanner player = new Scanner(System.in);

    public Player(int playerID) {
        this.playerID = playerID;
//        setPassword();
        
  }
  
    public Player(String name) {
        this.name = name;
        this.playerID = nextPlayerID;
        nextPlayerID++;
  }

   public Player(int playerID, String name) {
        this.name = name;
        this.playerID = playerID;
        nextPlayerID++;
  }
  
   
//    private void setPassword(){
//        System.out.printf("Set your personal password : ");
//        String temp = player.nextLine();
//        System.out.println("Re-type your password : ");
//        String temp2 = player.nextLine();
//        
//        while(checkPasswordEqual(temp,temp2)&&temp.length()<=8){
//            System.out.println("Your password should only have 8 characters and in alphanumeric form.");
//        }
//        
//        this.password = temp;
//    }
//    public boolean checkPasswordEqual(String temp1 , String temp2){
//        
//        if(checkAlphaNumeric(temp1)&&checkAlphaNumeric(temp2))
//            return temp1.equals(temp2);
//        else{
//            System.out.println("Your password is not matched!");
//            return false;
//        }
//    }
//    public boolean checkAlphaNumeric(String check){
//        String pattern= "^[a-zA-Z0-9]*$";
//        return check.matches(pattern);
//    }
    public String getName() {
        return name;
  }
    public void setName(String name) {
        this.name = name;
  }
    public int getPlayerID() {
        return playerID;
    }
    public void setPlayerID(int pLayerID) {
        this.playerID = pLayerID;
    }
    public static int getNextPlayerID() {
        return nextPlayerID;
    }

  public static void setNextPlayerID(int nextPlayerID) {
    Player.nextPlayerID = nextPlayerID;
  }
  
  public String getPassword() {
    return password;
 }

  public void setPassword(String password) {
    this.password = password;
  }

//  public int hashCode() {
//    int hash = 3;
//    return hash;
//  }

  public String toString(){
      return this.name + "," + this.playerID ;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Player other = (Player) obj;
    if (this.playerID != other.playerID) {
      return false;
    }
    return true;
  }

}