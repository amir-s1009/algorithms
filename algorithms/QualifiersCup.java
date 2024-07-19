import java.util.ArrayList;
import java.util.Scanner;

public class QualifiersCup {

    public static void main(String[] args) {
        QualifiersCup qualifiersCup = new QualifiersCup();
        qualifiersCup.init();
        qualifiersCup.respondQueries();
        //qualifiersCup.info();
    }
    private Game[] games;
    private String[] queries;
    private ArrayList<Rank> ranks;
    private ArrayList<Rank> ranking;
    public Node champion;


    private String getChampion(){
        int max = 0;
        String champion = "";
        for(Rank rank: ranks)
            if(rank.rank > max){
                max = rank.rank;
                champion = rank.team;
            }
        return champion;
    }
    private int indexOfTeam(String team){
        int i;
        for(i = 0; i < ranks.size(); i++)
            if(ranks.get(i).team.equals(team))
                break;
        if(i < ranks.size())
            return i;
        else
            return -1;
    }

    //lookup method returns the nearest node to the root
    private Node lookup(Node root, String team){
        if(root == null)
            return null;
        else if(root.getTeam().equals(team))
            return root;
        else {
            Node leftRes = lookup(root.getLeft(), team);
            if(leftRes == null)
                return lookup(root.getRight(), team);
            else
                return leftRes;
        }
    }

    private void respondQueries(){
        for(String query : queries){
            Node node1;
            Node node2;
            String teamA;
            String teamB;
            //extracting the teams:
            teamA = query.substring(0, query.indexOf(' '));
            teamB = query.substring(query.indexOf(' ')+1, query.length());
            node1 = lookup(champion, teamA);
            node2 = lookup(champion, teamB);
            //reaching the deepest teams in tree:
            while (node1.getRight() != null)
                node1 = node1.getRight();
            while (node2.getRight() != null)
                node2 = node2.getRight();
            int counter = 1;

            while(node1.getParent() != node2.getParent()){
                    node1 = node1.getParent();
                    node2 = node2.getParent();
                    counter++;
            }
            System.out.println(counter);

        }
    }

    public void printTree(Node node){
        if(node != null) {
            printTree(node.getLeft());
            System.out.println(node.getTeam());
            printTree(node.getRight());
        }
    }

    public void info(){
        System.out.println("champion is: "+"\""+champion.getTeam()+"\"");
        System.out.println("");
        System.out.println("rankings:");
        printRanking();
        System.out.println("");
        System.out.println("games:");
        printGames();
        System.out.println("");
        System.out.println("in-order walk of tree is as below:");
        printTree(champion);
    }

    private void printRanking(){
        for (Rank rank : ranking)
            System.out.println(rank.team+","+rank.rank);
    }
    private void printGames(){
        for(Game game: games)
            System.out.println("[winner: "+game.winner()+", loser: "+game.loser()+"]");
    }
    private void initRanks(){
        for (Game game : games) {
            int indexOfTeam = indexOfTeam(game.winner());
            if (indexOfTeam >= 0)
                ranks.get(indexOfTeam).rank++;
            else
                ranks.add(new Rank(game.winner(), 1));

            indexOfTeam = indexOfTeam(game.loser());
            if (indexOfTeam < 0)
                ranks.add(new Rank(game.loser(), 0));
        }
        champion = new Node(getChampion());

        ranking = new ArrayList<>();
        for (Rank rank : ranks) ranking.add(new Rank(rank.team, rank.rank));

        initTree(champion);
    }
    public void init(){
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();

        int index = firstLine.indexOf(' ');
        int k = Integer.parseInt(firstLine.substring(0, index));
        int n = Integer.parseInt(firstLine.substring(index+1, firstLine.length()));

        String[] results = new String[((int) Math.pow(2, k)) - 1];
        queries = new String[n];
        games = new Game[results.length];

        for(int i = 0; i < results.length; i++)
            results[i] = scanner.nextLine();
        for(int i = 0; i < queries.length; i++)
            queries[i] = scanner.nextLine();

        for(int i = 0; i < results.length; i++){
            String teamA;
            String teamB;
            String winner;
            String loser;
            int scoreA;
            int scoreB;
            teamA = results[i].substring(0, results[i].indexOf(' '));
            results[i] = results[i].replace(teamA, "");
            results[i] = results[i].replaceFirst(" ", "");
            //System.out.println(results[i]);
            if(!results[i].contains("("))
                scoreA = Integer.parseInt(results[i].substring(0, results[i].indexOf(' ')));
            else
                scoreA = Integer.parseInt(results[i].substring(0, results[i].indexOf('(')));
            results[i] = results[i].replaceFirst(String.valueOf(scoreA), "");
            //System.out.println(results[i]);
            if(results[i].charAt(0) == '('){
                int j = 1;
                String penaltyA = "";
                while(results[i].charAt(j) != ')')
                    penaltyA += results[i].charAt(j++);
                scoreA += Integer.parseInt(penaltyA);
                results[i] = results[i].substring(2+penaltyA.length(), results[i].length());
            }
            results[i] = results[i].replaceFirst(" ", "");
            //System.out.println(results[i]);
            results[i] = results[i].replace("-", "");
            //System.out.println(results[i]);
            results[i] = results[i].replaceFirst(" ", "");
            //System.out.println(results[i]);

            if(!results[i].contains("("))
                scoreB = Integer.parseInt(results[i].substring(0, results[i].indexOf(' ')));
            else
                scoreB = Integer.parseInt(results[i].substring(0, results[i].indexOf('(')));
            results[i] = results[i].replaceFirst(String.valueOf(scoreB), "");
            //System.out.println(results[i]);
            if(results[i].charAt(0) == '('){
                int j = 1;
                String penaltyB = "";
                while(results[i].charAt(j) != ')')
                    penaltyB += results[i].charAt(j++);
                scoreB += Integer.parseInt(penaltyB);
                results[i] = results[i].substring(2+penaltyB.length(), results[i].length());
            }
            teamB = results[i].substring(1, results[i].length());

            if(scoreA > scoreB) {
                winner = teamA;
                loser = teamB;
            }
            else{
                winner = teamB;
                loser = teamA;
            }
            games[i] = new Game(winner, loser, false);
        }
        ranks = new ArrayList<>();
        initRanks();
    }

    private boolean isLeaf(String team){
        return ranks.get(indexOfTeam(team)).rank == 0;
    }

    private void initTree(Node node){
        if(!isLeaf(node.getTeam())){
            Game[] games = getGamesByWinner(node.getTeam()).toArray(new Game[0]);
            for(Game game: games){
                //searching for correct sub-node:
                if(((ranks.get(indexOfTeam(game.winner())).rank - ranks.get(indexOfTeam(game.loser())).rank) == 1) && !game.used()){
                    game.use();
                    node.setLeft(new Node(game.loser()));
                    node.getLeft().setParent(node);
                    node.setRight(new Node(game.winner()));
                    node.getRight().setParent(node);
                    //decrementing winner rank:
                    ranks.get(indexOfTeam(game.winner())).rank--;
                    //now recurse on sub-nodes:
                    initTree(node.getLeft());
                    initTree(node.getRight());
                    break;
                }
            }
        }
    }

    private ArrayList<Game> getGamesByWinner(String winner){
        ArrayList<Game> query = new ArrayList<>();
        for(Game game: games)
            if(game.winner().equals(winner))
                query.add(game);
        return query;
    }
}
class Game{
    Game(String winner, String loser, boolean used){
        this.winner = winner;
        this.loser = loser;
        this.used = used;
    }
    private final String winner;
    private final String loser;
    private boolean used;

    String winner(){return winner;}
    String loser(){return loser;}
    boolean used(){return used;}
    void use(){this.used = true;}
}

class Node {
    Node(String team){
        this.team = team;
    }
    private final String team;
    private Node parent;
    private Node left;
    private Node right;

    public String getTeam() {
        return team;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class Rank{
    Rank(){

    }
    Rank(String team, int rank){
        this.team = team;
        this.rank = rank;
    }
    String team;
    int rank;
}