import java.util.Arrays;

public class LCS {
    LCS(String[] s1, String[] s2){
        this.seq1 = s1;
        this.seq2 = s2;
        init();
    }
    private final String[] seq1;
    private final String[] seq2;
    private int[][] length;
    private String[][] path;

    private void init(){
        //init two tables:
        length = new int[seq1.length+1][seq2.length+1];
        path = new String[seq1.length][seq2.length];
        //init first row & col to 0:
        for(int i = 0; i < length.length; i++)
            length[i][0] = 0;
        for(int i = 0; i < length[0].length; i++)
            length[0][i] = 0;
        //fill in the tables:
        for(int row = 1; row < length.length; row++)
            for(int col = 1; col < length[0].length; col++) {
                if (seq1[row - 1].equals(seq2[col - 1])){
                    //left up is ++:
                    length[row][col] = length[row-1][col-1] + 1;
                    path[row-1][col-1] = "LU";
                }
                else{
                    //maximizing up and left:
                    if(length[row][col-1] > length[row-1][col]) {
                        length[row][col] = length[row][col-1];
                        path[row-1][col-1] = "L";
                    }
                    else{
                        length[row][col] = length[row-1][col];
                        path[row-1][col-1] = "U";
                    }
                }
            }
    }
    private void length(){
        for (int[] ints : length) System.out.println(Arrays.toString(ints));
    }
    private void path(){
        for (String[] strings : path) System.out.println(Arrays.toString(strings));
    }
    private void printLCS(int row, int col){
        if(row >= 0 && col >= 0){
            if(path[row][col].equals("LU")){
                printLCS(row-1, col-1);
                System.out.print(seq1[row]);
            }
            else if(path[row][col].equals("L"))
                printLCS(row, col-1);
            else
                printLCS(row-1, col);
        }
    }

    void info(){
        System.out.println("the length table is as below:");
        length();
        System.out.println("the path table is as below:");
        path();
        System.out.println("lcs length is "+"'"+length[seq1.length][seq2.length]+"'");
        System.out.print("lcs string is "+"'");
        printLCS(seq1.length-1, seq2.length-1);
        System.out.println("'");
    }
}