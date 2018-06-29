package application;



import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tile extends StackPane {
	private Movements move;
	boolean odd;
	private int x;
	private int y;
	private Piece piece;

	private boolean hasPiece = false;
	Background bg;
		
	Tile(boolean oddtile, int x, int y, Movements move){
		this.move=move;
		this.x=x;
		this.y=y;
		odd=oddtile;
		if (oddtile) {
			SetOddRegularColor();
			this.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event)->this.OddHover());
			this.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event)->this.SetOddRegularColor());
		}
		else {
			SetEvenRegularColor();
			this.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event)->this.EvenHover());
			this.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event)->this.SetEvenRegularColor());
		}
		setPrefSize(100,100);
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event)->move.selectTile(this));
	}
	public void SetOddRegularColor() {
		setColor(Color.DARKRED);
	}
	
	public void SetEvenRegularColor() {
		setColor(Color.BURLYWOOD);
	}
	private void setColor(Color color) {
		BackgroundFill bgFill = new BackgroundFill(color, new CornerRadii(0), new Insets(5));
		Background bg = new Background(bgFill);
		setBackground(bg);
		
	}
	public void EvenHover() {
		setColor(Color.rgb(237, 199, 150));
	}
	public void OddHover() {
		setColor(Color.rgb(164, 25, 25));
	}
	public boolean getHasPiece() {
		return hasPiece;
	}
	public void setHasPiece() {
		hasPiece=!hasPiece;
	}
	public Tile getSelectedTile() {
		return this;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public  void putPiece (Piece addpiece) {
		piece=addpiece;
	}
	public  Piece getPiece () {
		return piece;
	}
}
