package Dijkstra;

public class DNode {
    int plass;
    double breddegrad;
    double lengdegrad;
    DijkstraKant førsteKant;
    public FørDNode før;
    int plassIHeap;

    public DNode(int plass, double breddegrad, double lengdegrad){
        this.plass = plass;
        this.breddegrad = breddegrad;
        this.lengdegrad = lengdegrad;
    }
}