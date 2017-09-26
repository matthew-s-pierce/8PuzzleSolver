/*
 * Class that implements a minimum priority queue
 */

@SuppressWarnings("unchecked")
public class MinPriorityQueue <Key extends Comparable>{
	private Key[] heap;
	private int N;
	
	public MinPriorityQueue(int capacity){
		heap = (Key[]) new Comparable[capacity+1];
		N = 0;
	}
	
	public void sink(int k){
		while(2*k <= N){
			int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(heap, k, j);
            k = j;
		}
	}
	
	public void swim(int k){
		while(k>1 && greater(k/2,k)){
			exch(heap,k,k/2);
			k = k/2;
		}
	}
	
	public void insert(Key key){
        if (N == heap.length - 1) resize(2 * heap.length);
		N++;
		heap[N] = key;
		swim(N);
	}
	
	public Key removeMin(){
		Key k = heap[1];
		exch(heap, 1,N);
		N--;
		sink(1);
		heap[N+1] = null;
		if ((N > 0) && (N == (heap.length - 1) / 4)) resize(heap.length  / 2);
		return k;
	}
	
	public boolean greater(int i, int j){
		if(heap[i].compareTo(heap[j])>0){
			return true;
		}
		return false;
	}
	
	public void exch(Key[] array, int a, int b){
		Key temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }
    
    public boolean isEmpty(){
    	return N < 1;
    }
    
    public void printPQ(){
    	for(int i =1; i<N+1; i++){
    		StdOut.println(heap[i].toString());
    	}
    }
}
