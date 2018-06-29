package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class round {
	
	public static int timecount=30;
	public static int timer;
	private int turnno=1;
	static Movements move;
	public static boolean gameover=false;
	private static int playerTurn=1;
	public static void nextround() {
		if (playerTurn==1) {
			playerTurn=2;
			Main.turnlabel.setText("Black player is now moving");
		}
		else 
		{
			playerTurn=1;
			Main.turnlabel.setText("White player is now moving");
		}
		timecount=30;
		Main.timerLabel.setText("You have "+round.timecount+" seconds left");

	}
	round(Movements move, int timer){
		this.move=move;
		timecount=timer;
		this.timer=timer;
		@SuppressWarnings("deprecation")
			Timeline tick = TimelineBuilder.create().keyFrames(new KeyFrame(new Duration(1000),new EventHandler<ActionEvent>() {
			                  public void handle(ActionEvent t) {
			                	  if (!gameover) {
			                	  timecount--;
			                	  Main.timerLabel.setText("You have "+round.timecount+" seconds left");
			                	  if (timecount==0) {
			                		  gameover=true;
			                		  Main.turnlabel.setText("Good game!");
			                		  		if (playerTurn==1) {
			                		  			Main.log.insertText(Main.log.getLength(), "Black team won by timeout!");
			                		  		}
			                		  		else {
			                		  			System.out.println("THE END whitek won");
			                		  			Main.log.insertText(Main.log.getLength(), "White team won by timeout!");
			                		  		}
			                		  	
			                	  	}
			                		  
			                	 }
			                	  else {
			                		  Main.timerLabel.setText("Game over! Thanks for playing!");
			                	  }
			                 }	
			        	}
			        )
			     ).cycleCount(Timeline.INDEFINITE)
			    .build();
		tick.play();
	}
	public static int getPlayerTurn() {
		return playerTurn;
	}
	public static void forceEndTurn() {
		move.forceEndTurn();
	}
	
	
}
