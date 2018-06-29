package application;

import javafx.geometry.Insets;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;

public class Piece extends StackPane{
	private Movements move;
	int id;
	private int team;
	private int piecex;
	private int piecey;
	private Ellipse pieceEllipse = new Ellipse(17.5,17.5, 35, 35);
	Piece(int teamNo,int id, int piecex, int piecey, Movements move){
		this.move=move;
		id=id;
		this.piecex=piecex;
		this.piecey=piecey;
		team=teamNo;
		if (team==1) {
			pieceEllipse.setFill(Color.WHITE);
			pieceEllipse.setStroke(Color.BLACK);
		}
		else {
			pieceEllipse.setFill(Color.rgb(155,155,155));
			pieceEllipse.setStroke(Color.BLACK);
		}
		pieceEllipse.setStrokeWidth(5);
		getChildren().add(pieceEllipse);
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event)->move.selectPiece(this));
		
	}
	public void setTeam(int teamNo) {
		team=teamNo;
	}

	public Ellipse getPieceShape() {
		return pieceEllipse;
	}
	public int getX() {
		return piecex;
	}
	public int getY() {
		return piecey;
	}
	public void setX(int x) {
		this.piecex=x;
	}
	public void setY(int y) {
		this.piecey=y;
	}
	public int getTeam() {
		return team;
	}
}

