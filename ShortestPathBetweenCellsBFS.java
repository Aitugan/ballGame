
import java.util.LinkedList;

public class ShortestPathBetweenCellsBFS {
    public static class Hex  {
        int x;
        int y;
        int dist;  	//distance
        Hex prev;  //parent cell in the path

        Hex(int x, int y, int dist, Hex prev) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        
        @Override
        public String toString(){
        	return "(" + x + "," + y + ")";
        }
    }
    
    //BFS, Time O(n^2), Space O(n^2)
    public static int[][] shortestPath(int[][] matrix, int[] start, int[] end) {
		int sx = start[0], sy = start[1];
		int dx = end[0], dy = end[1];
		
    	//if start or end value is 0, return
	   if (matrix[sx][sy] == 1 || matrix[dx][dy] == 1)
		   return null;
	   
	    int m = matrix.length;
	    int n = matrix[0].length;	    
        Hex[][] cells = new Hex[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0 || matrix[i][j] == 2) {
                    cells[i][j] = new Hex(i, j, Integer.MAX_VALUE, null);
                }
            }
        }
       
        LinkedList<Hex> queue = new LinkedList<>();       
        Hex src = cells[sx][sy];
        src.dist = 0;
        queue.add(src);
        Hex dest = null;
        Hex p;
        while ((p = queue.poll()) != null) {
        	//find destination 
            if (p.x == dx && p.y == dy) { 
                dest = p;
                break;
            }
            // moving up
            update(cells, queue, p.x - 1, p.y, p);
            // moving down
            update(cells, queue, p.x + 1, p.y, p);
            // moving left
            update(cells, queue, p.x, p.y - 1, p);
            //moving right
            update(cells, queue, p.x, p.y + 1, p);
        }
        if (dest == null) {
            int[][] arr1 = new int[0][0];
            return arr1;
        } else {
            LinkedList<Hex> path = new LinkedList<>();
            p = dest;
            do {
                path.addFirst(p);
            } while ((p = p.prev) != null);
            int[][] arr = new int[path.size()][2];
            for (int i = 0; i < path.size(); i++) {
                arr[i][0]=path.get(i).y;
                arr[i][1]=path.get(i).x;
            }
            return arr;
        }
    }




    //function to update hex visiting status
    static void update(Hex[][] hex, LinkedList<Hex> queue, int x, int y, Hex parent) { 
        if (x < 0 || x >= hex.length || y < 0 || y >= hex[0].length || hex[x][y] == null) {
            return;
        }
        
        int dist = parent.dist + 1;
        Hex p = hex[x][y];
        if (dist < p.dist) {
            p.dist = dist;
            p.prev = parent;
            queue.add(p);
        }
    }
}