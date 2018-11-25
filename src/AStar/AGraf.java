package AStar;

import java.io.*;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AGraf {
    int antNoder;
    int antKanter;
    public int noderSjekket = 0;
    public ANode[] noder;
    PriorityQueue<ANode> kø = new PriorityQueue<>(new AStarComparator());

    public void lagGraf(BufferedReader noder, BufferedReader kanter) throws IOException {
        StringTokenizer n = new StringTokenizer(noder.readLine());
        StringTokenizer k = new StringTokenizer(kanter.readLine());
        antNoder = Integer.parseInt(n.nextToken());
        antKanter = Integer.parseInt(k.nextToken());
        this.noder = new ANode[antNoder];
        for (int i = 0; i < antNoder; i++) {
            n = new StringTokenizer(noder.readLine());
            this.noder[i] = new ANode(Integer.parseInt(n.nextToken()),
                    Math.toRadians(Double.parseDouble(n.nextToken())),
                    Math.toRadians(Double.parseDouble(n.nextToken())));
        }
        for (int i = 0; i < antKanter; i++) {
            k = new StringTokenizer(kanter.readLine());
            int fraNode = Integer.parseInt(k.nextToken());
            int tilNode = Integer.parseInt(k.nextToken());
            int vekt = Integer.parseInt(k.nextToken());
            AStarKant vk = new AStarKant(this.noder[tilNode], this.noder[fraNode].førsteKant, vekt);
            this.noder[fraNode].førsteKant = vk;
        }
    }

    public void initForgj(ANode start, ANode slutt) {
        for (int i = 0; i < antNoder; i++) {
            noder[i].før = new FørANode();
        }
        start.før.tidFraStartNode = 0;
        start.før.estimerTidTilSluttNode(start, slutt);
        kø.add(start);
    }

    public void kortesteVeimellom(ANode a, ANode b) {
        initForgj(a, b);
        while (!kø.isEmpty()) {
            ANode n = kø.poll();
            noderSjekket++;
            if (n.plass == b.plass) {
                return;
            }
            for (AStarKant k = n.førsteKant; k != null; k = k.nesteKant) {
                FørANode f = k.tilNode.før;
                if (f.tidFraStartNode > (n.før.tidFraStartNode + k.kjøretid)) {
                    if (!kø.remove(k.tilNode)) {
                        k.tilNode.før.estimerTidTilSluttNode(k.tilNode, b);     //Legger til estimat for noden for å putte den i kø
                    }
                    f.tidFraStartNode = n.før.tidFraStartNode + k.kjøretid;
                    f.forgjenger = n;
                    kø.add(k.tilNode);
                }
            }
        }
    }

    public void printKoordinater(ANode a, ANode b) {

        String res = "";
        while (true) {
            res = Math.toDegrees(b.breddegradRad) + ", " + Math.toDegrees(b.lengdegradRad) + "\n" + res;
            if (b.før.forgjenger.equals(a)) {
                res = Math.toDegrees(b.før.forgjenger.breddegradRad) + ", " + Math.toDegrees(b.før.forgjenger.lengdegradRad) + "\n" + res;
                break;
            }
            b = b.før.forgjenger;
        }

        try {
            FileWriter fw = new FileWriter("src/data/Astar koordinater.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(res);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String veien(ANode a, ANode b) {
        String res = "";
        FørANode f = b.før;
        while (true) {
            res = b.plass + "\n" + res;
            if (b.før.forgjenger.equals(a)) {
                res = b.før.forgjenger.plass + "\n" + res;
                break;
            }

            b = b.før.forgjenger;
        }
        return res;
    }
}

class Testprogram {
    public static void main(String[] args) {
        AGraf aGraf = new AGraf();
        File aNodes = new File("src/data/noder.txt");
        File aEdges = new File("src/data/kanter.txt");
        try {
            BufferedReader n = new BufferedReader(new FileReader(aNodes));
            BufferedReader k = new BufferedReader(new FileReader(aEdges));
            aGraf.lagGraf(n, k);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

            Scanner aReader = new Scanner(System.in);

            System.out.println("\n\nNummer til ønsket FraNode: ");
            int startNode = aReader.nextInt();

            while (startNode >= aGraf.antNoder || startNode < 0) {
                System.out.println("Out of bounds, try again ");
                startNode = aReader.nextInt();
            }

            System.out.println("Nummer til ønsket TilNode: ");
            int sluttNode = aReader.nextInt();

            while (sluttNode >= aGraf.antNoder || sluttNode < 0) {
                System.out.println("Out of bounds, try again ");
                sluttNode = aReader.nextInt();
            }


            Date start = new Date();
            aGraf.kortesteVeimellom(aGraf.noder[startNode], aGraf.noder[sluttNode]);
            Date slutt = new Date();

            //System.out.println(g.veien(g.noder[startNode], g.noder[sluttNode]));   //Viser hvilke noder veien består av, fa start- til sluttnode.

            double tidBrukt = slutt.getTime() - start.getTime();
            System.out.println("Tid brukt: " + tidBrukt + " ms");
            System.out.println("Tid fra A til B: " + aGraf.noder[sluttNode].før.TimerMinutterOgSekunder());
            System.out.println("Antall noder sjekket: " + aGraf.noderSjekket);

            aGraf.printKoordinater(aGraf.noder[startNode], aGraf.noder[sluttNode]);              //Printer koordinatene til nodene fra start til slutt i filen "rute koordinater.txt", som kan brukes i https://mapmakerapp.com/


    }
}
