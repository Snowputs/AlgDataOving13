package AStar;

public class AStarKant {
    AStarKant nesteKant;
    ANode tilNode;
    int kjøretid;
    public AStarKant(ANode n, AStarKant nst, int kjøretid){
        nesteKant = nst;
        tilNode = n;
        this.kjøretid = kjøretid;
    }
}
