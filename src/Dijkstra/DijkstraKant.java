package Dijkstra;

class DijkstraKant {
    DijkstraKant nesteKant;
    DNode tilNode;
    int kjøretid;
    public DijkstraKant(DNode n, DijkstraKant nst, int kjøretid){
        nesteKant = nst;
        tilNode = n;
        this.kjøretid = kjøretid;
    }
}