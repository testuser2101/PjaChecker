package application;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Board extends GridPane {
	private Movements move;

	public StackPane garbage = new StackPane();
	public  Tile[][] boardTiles = new Tile[8][8];
	static Piece[] pieceList = new Piece[24];
	public GridPane startBoard(Movements move, int time){
		round round=new round(move,time);
		int pieceId=0;
		GridPane drawnBoard = new GridPane();
		for (int i=0; i<8;i++) {
			for (int j=0;j<8;j++) {
				boolean isEven = ((i+j)%2==0);
				Tile square = new Tile(isEven,i,j,move);
				
				
				boardTiles[i][j] = square;
				if (j<3&&isEven) {
					pieceList[pieceId] = new Piece(1,pieceId++,i,j,move);
					square.getChildren().add(pieceList[pieceId-1]);
					square.putPiece(pieceList[pieceId-1]);
					square.setHasPiece();
					
				}
				else 
					if (j>4&&isEven){
						pieceList[pieceId] = new Piece(2,pieceId++,i,j,move);
						square.getChildren().add(pieceList[pieceId-1]);
						square.putPiece(pieceList[pieceId-1]);
						square.setHasPiece();
					}
				drawnBoard.add(square, i, j);
			}
		}
		return drawnBoard;
		
	
	}
	
	public  Tile[][] getBoard(){
		return boardTiles;
	}
	public void putToGarbage(Piece thrash) {
		Pane dupa = new Pane();
		garbage.getChildren().add(thrash);
		garbage.getChildren().add(dupa);
	}
	
}
