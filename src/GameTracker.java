//A+ Computer Science  -  www.apluscompsci.com
//Names - Amber, Akash, Arjun, Connor, Nihal, Narendhar, Sonia
//Date - 3/24/18
//Class - AP Comp Sci Period 7
//Lab  - Galaga
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Tracks almost all game properties within one mostly-static class
// Provides a standardized way of storing and accessing game-specific details and info
public class GameTracker  {
	// Various instance methods, declared public/private based
	// on external needs
    public static boolean shouldClose=true;
	public static int Score = 0;
	private static ArrayList<String> leaderboard = new ArrayList<String>();
	private static String name;
	public static int stage = 0;
	private static int DEFAULT_LIVES=2;
	private static int lives = DEFAULT_LIVES;
	public static boolean showStage=false;
	public static int showStageCounter=0;
	public static boolean paused=false;
	public static int pausedCount=0;
	public static String finalString;

	//Getter and setter methods to set scores and names.
	public static void setName(){
		name = JOptionPane.showInputDialog("Enter your name:: ");
	}
	public static String getName(){
		return name;
	}
	public static int getLives() {
		return lives;
	}

	//Subtracts lives when the player ship is hit.
	public static void killLife() {
		lives -= 1;
	}
	public static void setScore(Ship ship){
		Score += ship.getVariation() * 100;
	}
	public static int getScore(){
		return Score;
	}

	// Unused
	// Proof-of-concept leaderboard system
	// Didn't get used due to server-side nature of the worldwide leaderboard
	public static void addToLeaderboard(){
		String leaderLine = getName() + ":: " + getScore();
		leaderboard.add(leaderLine);
	}

	// Unused
	// Proof-of-concept leaderboard sorting algorithm
	// Didn't get used for same reasons as addToLeaderboard()
	public static void sortScore(){
		for(int i = 0; i < leaderboard.size() - 1; i++){
			String temporary = leaderboard.get(i);
			int thisNumber = Integer.parseInt(temporary.substring(0, temporary.indexOf(" ")));

			String temporary2 = leaderboard.get(i+1);
			int nextNumber = Integer.parseInt(temporary2.substring(0, temporary2.indexOf(" ")));
			
			if(thisNumber < nextNumber){
				leaderboard.set(i, temporary2);
				leaderboard.set(i+1, temporary);
			}
		}
	}

	// USED FOR DEBUGGING
//	public static String printLeaderboard(){
//		finalString = "";
//
//		if(leaderboard.size() < 10)
//			looper = leaderboard.size();
//		else
//			looper = 10;
//
//		for(int i = 0; i < looper; i++){
//			String line = (i+1) + ". " + leaderboard.get(i) + "\n";
//			finalString += line;
//		}
//
//		System.out.println(finalString);
//
//		return finalString;
//	}

	//This method draws the lives on the gui and sets the location of the lives on the screen.
	public static void drawLives(Graphics2D g2) {
		for( int i= 0; i < lives; i++)
		{
			g2.drawImage(Ship.playerShip , 10+(Ship.DEFAULT_WIDTH+10)*i, GameGUI.canvasHeight-Ship.DEFAULT_WIDTH-10, Ship.DEFAULT_WIDTH, Ship.DEFAULT_HEIGHT, null);
		}
	}

	
	// Returns the leaderboard as an arrayList (UNUSED)
	public static ArrayList<String> getLeaderboard(){
		return leaderboard;
	}

	// Resets various game properties
	public static void reset() {
		Score=0;
		lives=DEFAULT_LIVES;
		stage=0;
	}

	// Add a life to the life tracker
    public static void addLife() {
        lives++;
    }
}
