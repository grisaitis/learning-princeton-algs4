import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class PriorityQueue<Key> implements Iterable<Key> {
	// max-oriented, 
	private int N; // size of priority queue
	private Key[] pq;
	private Comparator<Key> comparator;
	
	public PriorityQueue(Key[] keys) {
		N = keys.length;
		pq = (Key[]) new Object[keys.length+1];
		for (int i=0;i<N;i++) pq[i+1]=keys[i];
		for (int k=N/2;k>=1; k--) sink(k);
//		assert isMaxHeap();
	}
	public PriorityQueue(int capacity, Comparator<Key> comparator){
		N=0;
		this.comparator = comparator;
		pq = (Key[]) new Object[capacity+1];
	}
	public PriorityQueue(int capacity) {
		N=0;
		pq = (Key[]) new Object[capacity+1];
	}
	public PriorityQueue(){
		this(1); // call own constructor for capacity of 1
	}
	private boolean isMaxHeap(){
		return true;
	}
	private boolean less(int i, int j){
		if (comparator == null){
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) <0;
		}
		else {
			return comparator.compare(pq[i],pq[j]) < 0;
		}
	}
	private void exch(int i, int j){
		Key old_i = pq[i];
		pq[i]=pq[j];
		pq[j] = old_i;
	}
	private void swim(int k){
		while (k > 1 && less(k/2,k)){
			exch(k/2,k);
			k = k/2;
		}
	}
	private void sink(int k){
		/*
		 * recursive implementation...
		int j = 2*k; // position of first child, if it exists
		if (j <= N) { // if this node has a child 
			// then look into exchanging it...
			// if the first child is not the only child *and* if it's less than the other child,
			if (j<N && less(j,j+1)) {
				j++; // then focus on the second child
			}
			exch(k,j);
			sink(j);
		}
		else return;
		*/
		while (2*k <= N){
			int j = 2*k;
			if (j<N && less(j,j+1)) j++;
			if (!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	private void resize(int new_size){
		assert new_size > N; 
		Key new_pq[] = (Key[]) new Object[new_size];
		for (int i=1; i<=N; i++){
			new_pq[i] = pq[i];
		}
		pq = new_pq;
	}
	private void insert(Key v){
		// check if current array size is too small for one more item
		// if so, then grow it. else do nothing. 
		// add it to the end
		if (pq.length - 1 <= N){ // if we don't have room for more
			/* 
			 * e.g. length = 5, fitting up to N=4. pq can support N < length
			 * when i add something, check if length can support more. i.e., if (N+1<length)
			 * equivalently, check if it can't support more, i.e. if !(N+1<length) = !(N < length-1) = (N > length-1) 
			*/
			resize(pq.length*2); // double size
		}
		pq[++N] = v;
		swim(N);
	}
	private Key delMax(){
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		Key max = pq[1];
		exch(1,N);
		pq[N--] = null; 
		sink(1);
		return max;
	}
	private boolean isEmpty(){
		return N==0;
	}
	private Key max(){
		return pq[1];
	}
	private int size(){
		return N;
	}
    public Iterator<Key> iterator() { return new HeapIterator(); }
    private class HeapIterator implements Iterator<Key> {
    	// create a new pq
        private PriorityQueue<Key> copy;
        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator(){
        	if (comparator == null) copy = new PriorityQueue<Key>(size());
        	else                    copy = new PriorityQueue<Key>(size(), comparator);
        	for (int i=1;i<=N;i++) copy.insert(pq[i]);
        }
        public boolean hasNext() {return (!copy.isEmpty());}
        public void remove(){ throw new UnsupportedOperationException(); }
        public Key next(){
        	// throw exception if empty
        	if (!hasNext()) throw new NoSuchElementException();
        	return copy.delMax();
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create new PQ (initially empty)
		PriorityQueue<int> pq = new PriorityQueue<int>();
		// get values from first line of input
//		String line = StdIn.readLine().trim();
		
		while (StdIn.hasNextLine()){
			// add those to PQ, in order of input
			int item = StdIn.readInt();
			pq.insert(item);
		}
		while (!pq.isEmpty()){
			System.out.print(pq.delMax().)
		}
		// create PQ from this array, inserting the items "in order" in the PQ
		// insert them in the given order
		// read in additional values
		// insert those
		// 
		
	}

}
