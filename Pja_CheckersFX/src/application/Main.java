package application;
	


import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.layout.*;


public class Main extends Application {
	public static TextArea log = new TextArea();


	public static Label whiteLabel=new Label("White: 0");
	public static Label blackLabel=new Label("Black: 0");//= new Label("Black:/n"+scoeBlack);
	public static Label turnlabel;// = new Label ("now moving :/n"+getText);
	public static Label timerLabel;

	
	public Board board = new Board();
	public Movements move = new Movements(board);
	public void start(Stage primaryStage) {
		SetTimer(new Stage());
		Pane logbox = new Pane();
		GridPane scoreBox = new GridPane();
		StackPane whiteScore = new StackPane();
		StackPane blackScore= new StackPane();
		StackPane turn = new StackPane();	
		StackPane timer = new StackPane();
		timerLabel=new Label("You have "+round.timecount+" seconds left");
		StackPane buttonPane = new StackPane();
		Button button = new Button("EndTurn");
		log.setWrapText(true);
		log.setEditable(false);
		logbox.getChildren().add(log);
		logbox.setPrefSize(300,450);
		logbox.setMaxSize(300,450);
		logbox.setBackground(new Background (new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(5))));
		log.setMaxSize(300,450);
		log.setPrefSize(300, 450);
		//logbox.getChildren().add(log);
		whiteScore.setPrefSize(150,100);
		blackScore.setPrefSize(150,100);
		whiteScore.getChildren().add(whiteLabel);
		blackScore.getChildren().add(blackLabel);
		turn.setPrefSize(0,50);
		turnlabel = new Label ("White player is now moving");
		turnlabel.setAlignment(Pos.CENTER);		
		turn.getChildren().add(turnlabel);
		BorderPane app = new BorderPane();
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (Movements.afterkill)
				round.forceEndTurn();
			}
			
		});
		buttonPane.setPrefSize(0,100);
		buttonPane.getChildren().add(button);
		FlowPane flow = new FlowPane(Orientation.VERTICAL);
		flow.setMaxSize(300,800);
		flow.setPrefSize(300, 800);
		scoreBox.add(whiteScore,0,0);
		scoreBox.add(blackScore, 1, 0);
		timer.setPrefHeight(100);
		timer.getChildren().add(timerLabel);
		flow.getChildren().add(scoreBox);
		flow.getChildren().add(turn);
		flow.getChildren().add(timer);
		flow.getChildren().add(buttonPane);
		flow.getChildren().add(logbox); 	



		app.setRight(board.startBoard(move,30));
		app.setCenter(flow);
		Scene scene = new Scene(app);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void SetTimer(Stage stage) {
		GridPane settings = new GridPane();
		
		Scene scene=new Scene(settings,400,300);
		stage.setScene(scene);
		stage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
