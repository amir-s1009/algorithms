import java.util.Arrays;
public class Floyd {
    Floyd(int [][] distanceMatrix){
        this.cost = distanceMatrix;
        path = new int[cost.length][cost.length];
        init();
    }
    private int[][] cost;
    private int[][] path;

    private void init(){
        for(int i = 0; i < cost.length; i++)
            for(int j = 0; j < cost.length; j++)
                for(int k = 0; k < cost.length; k++)
                    if(cost[j][i]+cost[i][k] < cost[j][k]){
                        cost[j][k] = cost[j][i]+cost[i][k];
                        path[j][k] = i;
                    }
    }
    void info(int start, int end){
        System.out.println("the minimum cost from "+start+" to "+end+" is "+ "'"+cost[start][end]+"'"+".");
        System.out.println("the optimal path from "+start+" to "+end+" is:");
        printPath(start, end);
    }
    void shape(){
        System.out.println("the 'cost' matrix is as below:");
        for (int[] ints : cost) System.out.println(Arrays.toString(ints));
        System.out.println("the 'path' matrix is as below:");
        for (int[] ints : path) System.out.println(Arrays.toString(ints));
    }
    private void printCost(int start, int end){
        System.out.println(cost[start][end]);
    }
    private void printPath(int start, int end){
        if(path[start][end] == 0)
            System.out.println(String.valueOf(start)+"->"+String.valueOf(end));
        else{
            printPath(start, path[start][end]);
            printPath(path[start][end], end);
        }
    }

}
