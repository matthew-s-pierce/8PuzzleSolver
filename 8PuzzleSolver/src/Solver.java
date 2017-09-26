import java.util.LinkedList;
/*
 * Class that implements a best-first search on an implicit graph representing the potential states of
 * the board.
 */
public class Solver {
	SearchNode goalNode;
	
	public Solver(SearchNode initial){
		MinPriorityQueue<SearchNode> minPQ = new MinPriorityQueue<SearchNode>(1);
		minPQ.insert(initial);	
		
		while(!minPQ.isEmpty()){
			SearchNode selectedNode = minPQ.removeMin();
			//check if we reached the goal state
			if(selectedNode.getBoard().isGoal()){
				goalNode = selectedNode;
				StdOut.println("We found the solution!");
				break;
			}
			
			LinkedList<Board> neighbors = (LinkedList<Board>) selectedNode.getBoard().neighbors();
			//put the neighbors into the PQ (unless they match the selected node)
			while(!neighbors.isEmpty()){
				Board n1 = neighbors.pop();
				if((selectedNode.getPrev()==null) ||
						(!n1.isEqualTo(selectedNode.getPrev().getBoard()))){
					SearchNode s1 = new SearchNode(n1);
					s1.setMoves(selectedNode.getMoves()+1);
					s1.setPrev(selectedNode);
					minPQ.insert(s1);
				}
				else{
					continue;
				}
			}
		}

		
	}
		
	public int moves(){
		return goalNode.getMoves();
	}
		
	public Iterable<Board> solution(){
		if(goalNode == null){
			return null;
		}
		
		SearchNode currentNode = goalNode;
		LinkedList<Board> solutionList = new LinkedList<Board>();
		
		while(currentNode.getPrev()!=null){
			solutionList.addFirst(currentNode.getBoard());
			currentNode = currentNode.getPrev();
		}
		return solutionList;
	}
	
}
