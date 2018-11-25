package Dijkstra;

import java.util.concurrent.TimeUnit;

public class FørDNode {
    int totalvekt;
    DNode forgjenger;
    boolean aldriVærtIKø = true;
    static int uendelig = 1000000000;

    public int finn_dist() {return totalvekt;}
    public DNode finn_forgj() {return forgjenger;}
    public FørDNode(){
        totalvekt = uendelig;
    }

    public String TimerMinutterOgSekunder () {
        int millisekunder = totalvekt * 10;
        String tid = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisekunder),
                TimeUnit.MILLISECONDS.toMinutes(millisekunder) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisekunder)),
                TimeUnit.MILLISECONDS.toSeconds(millisekunder) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisekunder))
        );
        return tid;
    }
}
