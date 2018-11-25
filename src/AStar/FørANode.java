package AStar;

import java.util.concurrent.TimeUnit;

public class FørANode {
    int tidFraStartNode;
    int estTilSluttNode;
    ANode forgjenger;
    static int uendelig = 1000000000;

    public int finn_dist() {return tidFraStartNode;}
    public ANode finn_forgj() {return forgjenger;}
    FørANode(){
        tidFraStartNode = uendelig;
    }
    int totalVekt() {return tidFraStartNode + estTilSluttNode;}

    public void estimerTidTilSluttNode(ANode start, ANode slutt) {
        double sinBredde = Math.sin((start.breddegradRad - slutt.breddegradRad)/2.0);
        double sinLengde = Math.sin((start.lengdegradRad - slutt.lengdegradRad)/2.0);
        int estimo = 41701091;   //2*6371/110*3600*100 (2r / høyeste fartsgrense * antall sekunder i time * 100 (for hundredels sekunder :))
        estTilSluttNode = (int) (estimo * Math.asin(Math.sqrt(sinBredde * sinBredde + start.cosBredde * slutt.cosBredde * sinLengde * sinLengde)));
    }

    public String TimerMinutterOgSekunder () {
        int millisekunder = tidFraStartNode * 10;
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
