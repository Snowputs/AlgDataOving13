package AStar;

public class ANode {
    int plass;
    double breddegradRad;
    double lengdegradRad;
    double cosBredde;
    AStarKant førsteKant;
    public FørANode før;
    int plassIHeap;

    public ANode(int plass, double breddegrad, double lengdegrad){
        this.plass = plass;
        this.breddegradRad = breddegrad;
        this.lengdegradRad = lengdegrad;
        this.cosBredde = Math.cos(breddegradRad);
    }
}
