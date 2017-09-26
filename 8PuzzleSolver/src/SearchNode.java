/*
 * Class which represents a node in the Search tree. Contains a reference to a particular board setup
 * as well as the board setup that came before it (so that we can produce a linked list)
 */
public class SearchNode implements Comparable<SearchNode>{
	private SearchNode prev;
	private Board value;
	private int moves;
	
	public SearchNode(Board val){
		value = val;
	}
	
	public Board getBoard(){
		return value;
	}
	
	public void setMoves(int x){
		moves = x;
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void setPrev(SearchNode node){
		prev = node;
	}
	
	public SearchNode getPrev(){
		return prev;
	}

	/*
	 * Compare To method determines absolute 'value' of a search node based on 
	 * the number of moves needed to attain the board state
	 */
	@Override
	public int compareTo(SearchNode node) {
		Board b1 = this.getBoard();
		Board b2 = node.getBoard();
		int p1 = b1.hamming() + this.moves;
		int p2 = b2.hamming() + node.moves;
		if(p1 < p2){
			return -1;
		}else if(p1==p2){
			return 0;
		}else{
			return 1;
		}
		
	}
	
	@Override
	public String toString(){
		return getBoard().toString()+" priority:"+(this.getBoard().hamming()+this.moves);
	}
}
