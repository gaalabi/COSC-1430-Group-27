
public class GameState {
	private boolean playerWin;
	private boolean aiWin;
	
	public boolean getPlayerWin(){ return playerWin; }
	public boolean getAiWin(){ return aiWin; }
	
	public GameState(){
		playerWin = false;
		aiWin = false;
	}
	
	public void setWinStatus(int[][] playerBoard, int[][] aiBoard){
		int row = playerBoard.length;
		int col = playerBoard[0].length;
		playerWin = true;
		aiWin = true;
		for(int r=0; r<row; r++){
			for(int c=0; c<col; c++){
				if(playerBoard[r][c] == 1 || playerBoard[r][c] == 2 || playerBoard[r][c] == 3 || playerBoard[r][c] == 4 || playerBoard[r][c] == 5){
					aiWin = false;
				}
				if(aiBoard[r][c] == 1 || aiBoard[r][c] == 2 || aiBoard[r][c] == 3 || aiBoard[r][c] == 4 || aiBoard[r][c] == 5){
					playerWin = false;
				}
			}
		}
	}
}
