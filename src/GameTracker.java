import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;

public class GameTracker  {
	private static int score = 0;
	private static ArrayList<String> leaderboard = new ArrayList<String>();
	private static String name;
	private static int lives = 3;
	private static int width;
	private static int height;
	public static int stage = 1;
	public static int Score = 0;


	public GameTracker(){
//		setName();
//		addToLeaderboard();
//		sortScore();
//		System.out.println(leaderboard);
	}
	
	public static void setName(){
		name = JOptionPane.showInputDialog("Enter your name:: ");
	}
	
	public static String getName(){
		return name;
	}
	
	public static int getLives() {
		return lives;
	}

	public static void killLife() {
		lives -= 1;
	}
	
	public static void setScore(Ship ship){
		score += ship.getVariation() * 100;
	}
	
	public static int getScore(){
		return score;
	}
	
	public static void addToLeaderboard(){
		leaderboard.add(getScore(), getName());
	}
	
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

	public static void draw(Graphics2D g2){
		for( int i= 0; i <= lives; i++)
		{
			g2.drawImage(Ship.playerShip , -width/2, -height/2, width, height, null);
		}
	}

	

	public static ArrayList<String> getLeaderboard(){
		return leaderboard;
	}
	
	//public static void main(String[] args){
		//GameTracker testing = new GameTracker();
	//}

}
