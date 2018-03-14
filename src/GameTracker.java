import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;

public class GameTracker  {
	private int score = 0;
	private ArrayList<String> leaderboard = new ArrayList<String>();
	private String name;
	private int lives = 3;
	private int width;
	private int height;
	public static int stage = 1;


	public GameTracker(){
		setName();
		addToLeaderboard();
		sortScore();
		System.out.println(leaderboard);
	}
	
	public void setName(){
		name = JOptionPane.showInputDialog("Enter your name:: ");
	}
	
	public String getName(){
		return name;
	}
	
	public int getLives() {
		return lives;
	}

	public void killLife() {
		lives -= 1;
	}
	
	public void setScore(Ship ship){
		score += ship.getVariation() * 100;
	}
	
	public int getScore(){
		return score;
	}
	
	public void addToLeaderboard(){
		leaderboard.add(getScore(), getName());
	}
	
	public void sortScore(){
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

	public void draw(Graphics2D g2){
		for( int i= 0; i <= lives; i++)
		{
			g2.drawImage(Ship.playerShip , -width/2, -height/2, width, height, null);
		}
	}

	

	public ArrayList<String> getLeaderboard(){
		return leaderboard;
	}
	
	public static void main(String[] args){
		GameTracker testing = new GameTracker();
	}

}
