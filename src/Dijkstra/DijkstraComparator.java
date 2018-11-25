package Dijkstra;

import java.util.Comparator;

public class DijkstraComparator implements Comparator<DNode> {
    public int compare(DNode a, DNode b){
        return a.før.totalvekt - b.før.totalvekt;
    }
}
