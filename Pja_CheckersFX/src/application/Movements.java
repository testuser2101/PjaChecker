package application;

import javafx.scene.paint.Color;

public class Movements {

	private int scorewhite=0;
	private int scoreblack=0;
	private String log= "Game start!/n";
	public static boolean afterkill=false;
	private Tile	PieceTile;
	private  Piece selectedPiece;
	private  Tile currentTile;
	private boolean isSelected= false;
	private Board board;
	boolean buglock;
	boolean hasMultiKillLeftUp=false;
	boolean hasMultiKillLeftDown=false;
	boolean hasMultiKillRightUp=false;
	boolean hasMultiKillRightDown=false;
	Movements(Board board){
		this.board=board;
	}
	
	public void selectPiece(Piece piece) {
		if (!round.gameover) {
			
			
			if (!afterkill) {
				if (round.getPlayerTurn()==piece.getTeam()) {
					isSelected=true;
					if (selectedPiece!=null) {
						selectedPiece.getPieceShape().setStroke(Color.BLACK);
					}
					selectedPiece=piece;
					selectedPiece.getPieceShape().setStroke(Color.YELLOW);
					PieceTile = board.getBoard()[selectedPiece.getX()][selectedPiece.getY()];
					System.out.println(selectedPiece.getParent());
				}
			}
	
		}	
	}
	public  void selectTile(Tile tile) {
		if (!round.gameover) {
			currentTile = tile;
			System.out.println(currentTile.getHasPiece());
			
			if (!afterkill) {
				if (selectedPiece!=null) {
					if	(!currentTile.getHasPiece()) {
						if((currentTile.getX()!=selectedPiece.getX())||(currentTile.getY()!=selectedPiece.getY())) 
						{
								tryMove();
						}
					}
				}
			}
			else {
				if	(!currentTile.getHasPiece())
				multiKill();
			}
		}
	}
	private void moveRegular() {
		if (round.getPlayerTurn()==1) {
//			System.out.println("White piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
//		(currentTile.getX()+1)+"-"+(currentTile.getY()+1));
			Main.log.insertText(Main.log.getLength(),"White piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
					(currentTile.getX()+1)+"-"+(currentTile.getY()+1)+"\n");
		}
		else {
	//		System.out.println("Black piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
//					(currentTile.getX()+1)+"-"+(currentTile.getY()+1));
			Main.log.insertText(Main.log.getLength(),"Black piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
					(currentTile.getX()+1)+"-"+(currentTile.getY()+1)+"\n");
			
		}
		PieceTile.setHasPiece();
		currentTile.setHasPiece();
		selectedPiece.getPieceShape().setStroke(Color.BLACK);
		currentTile.getChildren().add(selectedPiece);
		currentTile.putPiece(selectedPiece);
		selectedPiece.setX(currentTile.getX());
		selectedPiece.setY(currentTile.getY());
		PieceTile.putPiece(null);
		selectedPiece = null;
		PieceTile = null;
		currentTile = null;
		round.nextround();
	}
	public  void tryMove() {
		
		if (currentTile.getX()==selectedPiece.getX()+1||currentTile.getX()==selectedPiece.getX()-1) {
			if (currentTile.getY()==selectedPiece.getY()+1||currentTile.getY()==selectedPiece.getY()-1)
			moveRegular();
		}
		else {
			tryKill();
		}
	}
	private boolean checkIfKill(int dir) {
		switch (dir) {
			case 1:
				
				if (board.boardTiles[PieceTile.getX()+1][PieceTile.getY()-1].getHasPiece()&&
						board.boardTiles[PieceTile.getX()+1][PieceTile.getY()-1].getPiece().getTeam()!=round.getPlayerTurn()	) {
					return true;
					
				}
				break;
			case 2:
				if (board.boardTiles[PieceTile.getX()+1][PieceTile.getY()+1].getHasPiece()&&
						board.boardTiles[PieceTile.getX()+1][PieceTile.getY()+1].getPiece().getTeam()!=round.getPlayerTurn()) {
					return true;
				}
				break;
			case 3:
				
				if (board.boardTiles[PieceTile.getX()-1][PieceTile.getY()-1].getHasPiece()&&
						board.boardTiles[PieceTile.getX()-1][PieceTile.getY()-1].getPiece().getTeam()!=round.getPlayerTurn()) {
					return true;
				}
				break;
			case 4:
				if (board.boardTiles[PieceTile.getX()-1][PieceTile.getY()+1].getHasPiece()&&
						board.boardTiles[PieceTile.getX()-1][PieceTile.getY()+1].getPiece().getTeam()!=round.getPlayerTurn()) {
					return true;
				}
				break;
		}
			return false;
	}
	private void tryKill() {
		// LEFTUP
		
		if (currentTile.getX()==selectedPiece.getX()-2&&currentTile.getY()==selectedPiece.getY()-2) {
			if(checkIfKill(3)) {

				kill(board.boardTiles[selectedPiece.getX()-1][selectedPiece.getY()-1].getPiece());
			}
		}
		else
			//LOWER LEFT
		if (currentTile.getX()==selectedPiece.getX()-2&&currentTile.getY()==selectedPiece.getY()+2) {
			if(checkIfKill(4)) {

				kill(board.boardTiles[selectedPiece.getX()-1][selectedPiece.getY()+1].getPiece());
				
			}
		}
		else
			// RIGHT UP
		if (currentTile.getX()==selectedPiece.getX()+2&&currentTile.getY()==selectedPiece.getY()-2) {
			if(checkIfKill(1)) {

				kill(board.boardTiles[selectedPiece.getX()+1][selectedPiece.getY()-1].getPiece());
			}
		}
		else {
			// RIGHT down
			if (currentTile.getX()==selectedPiece.getX()+2&&currentTile.getY()==selectedPiece.getY()+2) {
				if(checkIfKill(2)) {

					kill(board.boardTiles[PieceTile.getX()+1][PieceTile.getY()+1].getPiece());	
					
				}
			}
		}
	}
		
	
	private void kill(Piece piece) {
		

		if (round.getPlayerTurn()==1) {
			scorewhite++;
			Main.whiteLabel.setText("White: "+scorewhite);

			Main.log.insertText(Main.log.getLength(), "White piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
					(currentTile.getX()+1)+"-"+(currentTile.getY()+1)+"\n");
			Main.log.insertText(Main.log.getLength(), "Black piece at position "+(piece.getX()+1)+"-"+(piece.getY()+1)+" is killed.\n");
			Main.log.insertText(Main.log.getLength(), "White player scores!\n");
//			System.out.println("White piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
//			(currentTile.getX()+1)+"-"+(currentTile.getY()+1));
//			System.out.println("White piece at position "+(piece.getX()+1)+"-"+(piece.getY()+1)+" is killed.");
//			System.out.println("White player scores!");

		}
		else {
			scoreblack++;
			Main.blackLabel.setText("Black: "+scoreblack);
			Main.log.insertText(Main.log.getLength(), "Black piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
					(currentTile.getX()+1)+"-"+(currentTile.getY()+1)+"\n");
			Main.log.insertText(Main.log.getLength(), "White piece at position "+(piece.getX()+1)+"-"+(piece.getY()+1)+" is killed.\n");
			Main.log.insertText(Main.log.getLength(), "Black player scores!\n");
	
//			System.out.println("Black piece moves from "+(selectedPiece.getX()+1)+"-"+(selectedPiece.getY()+1)+" to "+
//			(currentTile.getX()+1)+"-"+(currentTile.getY()+1));
//			System.out.println("White piece at position "+(piece.getX()+1)+"-"+(piece.getY()+1)+" is killed.");
//			System.out.println("Black Player Scores!");
		}
		
		PieceTile.setHasPiece();
		currentTile.setHasPiece();
	
		selectedPiece.setX(currentTile.getX());
		selectedPiece.setY(currentTile.getY());
		PieceTile.putPiece(null);
		
		currentTile.getChildren().add(selectedPiece);
		board.boardTiles[piece.getX()][piece.getY()].setHasPiece();
		board.putToGarbage(piece);
		
		currentTile.putPiece(selectedPiece);
		PieceTile=currentTile;
		checkPoints();
	//	System.out.println(currentTile.getPiece());
//		selectedPiece.getPieceShape().setStroke(Color.BLACK);
	//	selectedPiece = null;
	//	PieceTile = null;
	//	currentTile = null;
		checkIfHasDoubleKill();
	}
	private void checkIfHasDoubleKill() {

		boolean moved=false;
		boolean leftup=true;
		boolean leftdown=true;
		boolean rightup=true;
		boolean rightdown=true;


		afterkill=false;
		if (selectedPiece.getX()<2) {
			leftdown=false;
			leftup=false;
		}
		if (selectedPiece.getX()>5) {
			rightup=false;
			rightdown=false;
		}
		if (selectedPiece.getY()<2) {
			leftup=false;
			rightup=false;
		}
		if (selectedPiece.getY()>5) {
			leftdown=false;
			rightdown=false;
		}

		if (leftup) {
			if (!board.boardTiles[currentTile.getX()-2][currentTile.getY()-2].getHasPiece()) {
				if(checkIfKill(3)) {
					hasMultiKillLeftUp=true;
					afterkill=true;
				}
			}
		}

		if (leftdown) {
			if (!board.boardTiles[currentTile.getX()-2][currentTile.getY()+2].getHasPiece()) {
				if(checkIfKill(4)) {
					hasMultiKillLeftDown=true;
					afterkill=true;
				}
			}
		}

		if (rightup) {
			if (!board.boardTiles[currentTile.getX()+2][currentTile.getY()-2].getHasPiece()) { 
				if(checkIfKill(1)) {
					hasMultiKillRightUp=true;
					afterkill=true;
				}
			}
		}


		if (rightdown) {
			if (!board.boardTiles[currentTile.getX()+2][currentTile.getY()+2].getHasPiece()) {
				if(checkIfKill(2)) {
					hasMultiKillRightDown=true;
					afterkill=true;
				}
			}
		}

		if (!afterkill) {
			selectedPiece.getPieceShape().setStroke(Color.BLACK);
			selectedPiece = null;
			PieceTile = null;
			currentTile = null;
			round.nextround();
		}
	//	multiKill(hasMultiKillLeftUp,hasMultiKillLeftDown,hasMultiKillRightUp,hasMultiKillRightDown);
		
	}
	private void multiKill() {
			tryKill();
	}
	public void forceEndTurn() {
		if (round.getPlayerTurn()==1) {
			Main.log.insertText(Main.log.getLength(),"White player decided not to go for next kill\nWHAT A CHICKEN!");
		}
		else {
			Main.log.insertText(Main.log.getLength(),"Black player decided not to go for next kill\nWHAT A CHICKEN!");
		}
		afterkill=!afterkill;
		selectedPiece.getPieceShape().setStroke(Color.BLACK);
		selectedPiece = null;
		PieceTile = null;
		currentTile = null;
		round.nextround();
	}
	private void checkPoints() {
		if (scorewhite==12) {
			Main.log.insertText(Main.log.getLength(),"Game Over!\nWhite team won! Hooray!");
			round.gameover=true;
		}
		if (scoreblack==12) {
			Main.log.insertText(Main.log.getLength(),"Game Over!\nWhite team won! Hooray!");
			round.gameover=true;
		}
	}

}
