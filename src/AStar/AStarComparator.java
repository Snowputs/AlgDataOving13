package AStar;

import java.util.Comparator;

public class AStarComparator implements Comparator<ANode> {
    public int compare(ANode a, ANode b){
        return a.før.totalVekt() - b.før.totalVekt();
    }
}
