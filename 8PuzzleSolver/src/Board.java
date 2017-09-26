import java.util.LinkedList;

/*
 * Class to represent an 8-puzzle board
 */
public class Board{
	int[][] board;
	
	public Board(int[][] blocks) {
		board = new int[blocks.length][blocks.length];
		for(int row=0; row <blocks.length; row++){
			for(int col=0; col<blocks.length; col++){
				board[row][col] = blocks[row][col];
			}
		}
	}
	
	public int dimension(){
		return (int) board.length;
	}

	/*
	 * Measures the manhattan distance between a state and the goal state (i.e. a measure of
	 * how 'far' we are , in moves, from the goal state)
	 */
	public int manhattan(){
	int manhattan = 0;
		for(int row=0; row<this.dimension(); row++){
			for(int col=0; col<this.dimension(); col++){
				if(board[row][col]!=0){
					if(board[row][col]!=(row*this.dimension())+(col)+1){
						int wrongNum = board[row][col];
						double div = (double) wrongNum/((double)this.dimension());
						int actualRow = (int) Math.ceil(div);
						actualRow = actualRow - 1;
						int actualCol;
						if(wrongNum % this.dimension()==0){
							actualCol = this.dimension()-1;
						}else{
							actualCol = (wrongNum % this.dimension())-1;
						}
						int manhattanPartial = Math.abs(actualRow-row)+Math.abs(actualCol-col);
						manhattan = manhattan + manhattanPartial;
					}
				}
			}
		}
		return manhattan;
	}
	
	public int hamming(){
		int hamming = 0;
		int counter = 1;
		for(int row=0; row<this.dimension(); row++){
			for(int col=0; col<this.dimension(); col++){
				if(board[row][col]!=0){
					if(board[row][col]!=counter){
						hamming++;
					}
				}
				counter++;
			}
		}
		return hamming;
	}
	
	/*
	 * Checks if the board is solved
	 */
	public boolean isGoal(){
		int counter = 1;
		for(int row=0; row<this.dimension(); row++){
			for(int col=0; col<this.dimension(); col++){
				if((row == this.dimension()-1) && (col == this.dimension()-1)){
					counter = 0;
				}
				if(board[row][col]!=counter){
					return false;
				}
				counter++;
			}
		}
		return true;
	}
	
	public boolean isEqualTo(Board b){
		if(b == null){
			return false;
		}
		for(int row=0; row < this.dimension(); row++){
			for(int col=0; col < this.dimension(); col++){
				if(board[row][col]!=b.board[row][col]){
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Gets the possible board states that could be reached from the current board state
	 */
	public Iterable<Board> neighbors(){
		LinkedList<Board> neighbors = new LinkedList<Board>();
		int blankRow = -1;
		int blankCol = -1;
		for(int row=0; row<this.dimension(); row++){
			for(int col=0; col<this.dimension(); col++){
				if(board[row][col]==0){
					blankRow = row;
					blankCol = col;
				}
			}
		}
		int[][] newBlocks = copyBlocks();
		
		//now that we have the blank space, determine what moves are possible
		if(blankRow>0){	//can move a piece above down
			newBlocks[blankRow][blankCol] = board[blankRow-1][blankCol];
			newBlocks[blankRow-1][blankCol] = 0;
			neighbors.add(new Board(newBlocks));
			//now return blocks to its original form so we don't need to copy
			newBlocks[blankRow-1][blankCol] = newBlocks[blankRow][blankCol];
			newBlocks[blankRow][blankCol] = 0;
		}
		//can we move a piece up?
		if(blankRow<this.dimension()-1){	//can move a piece up
			newBlocks[blankRow][blankCol] = board[blankRow+1][blankCol];
			newBlocks[blankRow+1][blankCol] = 0;
			neighbors.add(new Board(newBlocks));
			//now return blocks to its original form so we don't need to copy
			newBlocks[blankRow+1][blankCol] = newBlocks[blankRow][blankCol];
			newBlocks[blankRow][blankCol] = 0;
		}
		//can we move a piece left?
		if(blankCol<this.dimension()-1){
			newBlocks[blankRow][blankCol] = board[blankRow][blankCol+1];
			newBlocks[blankRow][blankCol+1] = 0;
			neighbors.add(new Board(newBlocks));
			//now return blocks to its original form so we don't need to copy
			newBlocks[blankRow][blankCol+1] = newBlocks[blankRow][blankCol];
			newBlocks[blankRow][blankCol] = 0;
		}
		//can we move a piece right?
		if(blankCol>0){	
			newBlocks[blankRow][blankCol] = board[blankRow][blankCol-1];
			newBlocks[blankRow][blankCol-1] = 0;
			neighbors.add(new Board(newBlocks));
			//now return blocks to its original form so we don't need to copy
			newBlocks[blankRow][blankCol-1] = newBlocks[blankRow][blankCol];
			newBlocks[blankRow][blankCol] = 0;
		}
		return neighbors;
	}

	public String toString(){
		String s="";
		for(int row=0; row<this.dimension(); row++){
			s = s + "\n";
			for(int col=0; col<this.dimension(); col++){
				s = s + board[row][col]+ " "; 
			}
		}
		
		return s;
		
	}
	
	private int[][] copyBlocks(){
		int[][] copiedBoard = new int[this.dimension()][this.dimension()];
		for(int row=0; row<this.dimension(); row++){
			for(int col=0; col<this.dimension(); col++){
				copiedBoard[row][col] = board[row][col];
			}
		}
		return copiedBoard;
	}
}
