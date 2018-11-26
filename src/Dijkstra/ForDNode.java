package Dijkstra;

import java.util.concurrent.TimeUnit;

public class ForDNode {
    int totalvekt;
    DNode forgjenger;
    boolean aldriVærtIKø = true;
    static int uendelig = 1000000000;

    public ForDNode(){
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
