import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class WeightedQuickUnionUF {
	private int[] id;
	private int count; // number of equivalence classes
	// everything the same as quick-union, as well as
	private int[] sz; // to count number of objects in the tree rooted at i

	public WeightedQuickUnionUF(int N){
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i=0; i<N;i++){
			id[i]=i;
			sz[i]=1;
		}
	}
	
	public int count(){
		return count;
	}
	
	public int find(int p){
		// move p up the tree until it reaches the top, and return that final value
		while (p!=id[p]){
			p=id[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q){
		return find(p)==find(q);
	}
	
	public void union(int p,int q){
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP==rootQ) return;
		// here's where the magic happens...
		if (sz[rootP] < sz[rootQ]) 	{id[rootP]=rootQ; sz[rootQ] += sz[rootP];}
		else 						{id[rootQ]=rootP; sz[rootP] += sz[rootQ];}
		count--; // one less equivalence class after the merger...
	}
	
    public static void main(String[] args) throws IOException {
//    	String filename = ;
    	System.setIn(new FileInputStream("/home/willg/Code/algs4/Excercise1/input2.txt")); 
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
//        	System.out.println(StdIn.readInt());
//        	StdIn.readChar();
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        	
        }
//        StdOut.println(uf.count() + " components");
        for (int i=0; i < uf.id.length; i++) {
        	StdOut.println(uf.id[i]);
        }
        System.out.println(Arrays.toString(uf.id));
        System.out.println(Arrays.toString(uf.id).replaceAll(",","").replace("[","").replace("]", ""));
    }

	
	
}
