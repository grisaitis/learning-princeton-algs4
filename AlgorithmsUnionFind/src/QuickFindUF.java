import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class QuickFindUF {
	private int[] id;
	
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i]=i;
	}

	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	public void union(int p, int q) {
		// change all entries whose id equals id[p] to id[q]
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++) 
			if (id[i] == pid) id[i] = qid;
	}
	
    public static void main(String[] args) throws IOException {
//    	String filename = ;
    	System.setIn(new FileInputStream("/home/willg/Code/algs4/Excercise1/input.txt")); 
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
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
