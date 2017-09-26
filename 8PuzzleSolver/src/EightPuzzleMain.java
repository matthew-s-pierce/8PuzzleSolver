import java.util.LinkedList;

/*
 * Driver class that takes a text file representing an input state of the 8-puzzle board.
 * Calls the solver, which uses a best-first search heuristic on a priority queue storing board states
 * to find take the input state and turn it into the goal state (all pieces in ascending order)
 */

public class EightPuzzleMain {

	public static void main(String[] args) {
		// create initial board from file
		In in = new In("puzzle4x4-10.txt");
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				blocks[i][j] = in.readInt();
			}
		}
		Board initial = new Board(blocks);
		SearchNode initialNode = new SearchNode(initial);
		StdOut.println("Starting State:");
		StdOut.println(initialNode);
		initialNode.setPrev(null);
		initialNode.setMoves(0);
		
		Solver solver = new Solver(initialNode);
		LinkedList<Board> solution = (LinkedList<Board>) solver.solution();
		while(!solution.isEmpty()){
			StdOut.print(solution.pop().toString()+"\n");
		}
		StdOut.println("\nNumber of moves: "+ solver.moves());
		/* Uncomment for printing
		// print solution to standard output
		if (!solver.isSolvable()){
			StdOut.println("No solution possible");
		}else {
			StdOut.println("Minimum number of moves = " + solver.moves());
		}
		for (Board board : solver.solution())
			StdOut.println(board);
		}*/
	}
}
