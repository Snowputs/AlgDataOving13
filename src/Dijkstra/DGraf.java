package Dijkstra;

import java.io.*;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DGraf {
    public int antNoder;
    int antKanter;
    public int noderSjekket = 0;
    public DNode[] noder;
    PriorityQueue<DNode> kø = new PriorityQueue<>(new DijkstraComparator());
    //Heap heap;

    public void lagGraf (BufferedReader noder, BufferedReader kanter) throws IOException {
        StringTokenizer n = new StringTokenizer(noder.readLine());
        StringTokenizer k = new StringTokenizer(kanter.readLine());
        antNoder = Integer.parseInt(n.nextToken());
        antKanter = Integer.parseInt(k.nextToken());
        this.noder = new DNode[antNoder];
        for (int i = 0; i< antNoder; i++){
            n = new StringTokenizer(noder.readLine());
            this.noder[i] = new DNode(Integer.parseInt(n.nextToken()), Double.parseDouble(n.nextToken()), Double.parseDouble(n.nextToken()));
        }
        for (int i = 0; i < antKanter; i++){
            k = new StringTokenizer(kanter.readLine());
            int fraNode = Integer.parseInt(k.nextToken());
            int tilNode = Integer.parseInt(k.nextToken());
            int vekt = Integer.parseInt(k.nextToken());
            DijkstraKant vk = new DijkstraKant(this.noder[tilNode], this.noder[fraNode].førsteKant, vekt);
            this.noder[fraNode].førsteKant = vk;
        }
    }

    public void initForgj(DNode start){
        for (int i = 0; i < antNoder; i++){
            noder[i].før = new FørDNode();
        }
        start.før.totalvekt = 0;
        kø.add(start);
    }

    public void kortesteVeimellom (DNode a, DNode b){
        initForgj(a);
        while(!kø.isEmpty()){
            DNode n = kø.poll();
            noderSjekket++;
            if (n.plass == b.plass){
                return;
            }
            for (DijkstraKant k = n.førsteKant; k != null; k = k.nesteKant){
                FørDNode f = k.tilNode.før;
                if (f.totalvekt > (n.før.totalvekt + k.kjøretid)){
                    kø.remove(k.tilNode);
                    f.totalvekt = n.før.totalvekt + k.kjøretid;
                    f.forgjenger = n;
                    kø.add(k.tilNode);
                }
            }
        }
    }

    public void printKoordinater(DNode a, DNode b){

        String res = "";
        while (true) {
            res = b.breddegrad + ", " + b.lengdegrad + "\n" + res;
            if (b.før.forgjenger.equals(a)){
                res = b.før.forgjenger.breddegrad + ", " + b.før.forgjenger.lengdegrad + "\n" + res;
                break;
            }
            b = b.før.forgjenger;
        }

        try{
            FileWriter fw = new FileWriter("src/data/Dijkstra koordinater.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(res);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String veien (DNode a, DNode b){
        String res = "";
        FørDNode f = b.før;
        while (true){
            res = b.plass + "\n" + res;
            if (b.før.forgjenger.equals(a)){
                res = b.før.forgjenger.plass + "\n" + res;
                break;
            }
//            if (b.før.forgjenger == null) {
//                break;
//            }
            b = b.før.forgjenger;
        }
        return res;
    }
}


class Testprogram {
    public static void main(String[] args) {
        DGraf g = new DGraf();
        File noder = new File("src/data/noder.txt");
        File kanter = new File("src/data/kanter.txt");
        try {
            BufferedReader n = new BufferedReader(new FileReader(noder));
            BufferedReader k = new BufferedReader(new FileReader(kanter));
            g.lagGraf(n, k);
        }
        catch (Exception e) { e.printStackTrace(); }

            Scanner reader = new Scanner(System.in);

            System.out.println("\n\nSkriv nummeret til ønsket FraNode: ");
            int startNode = reader.nextInt();

            while (startNode >= g.antNoder || startNode < 0) {
                System.out.println("Out of bounds, try again ");
                startNode = reader.nextInt();
            }

            System.out.println("Skriv nummeret til ønsket TilNode: ");
            int sluttNode = reader.nextInt();

            while (sluttNode >= g.antNoder || sluttNode < 0) {
                System.out.println("Out of bounds, try again ");
                sluttNode = reader.nextInt();
            }


            Date start = new Date();
            g.kortesteVeimellom(g.noder[startNode], g.noder[sluttNode]);
            Date slutt = new Date();

            //System.out.println(g.veien(g.noder[startNode], g.noder[sluttNode]));   //Viser hvilke noder veien består av, fa start- til sluttnode.

            double tidBrukt = slutt.getTime() - start.getTime();
            System.out.println("Tid brukt: " + tidBrukt + " ms");
            System.out.println("Tid fra A til B: " + g.noder[sluttNode].før.TimerMinutterOgSekunder());
            System.out.println("Antall noder sjekket: " + g.noderSjekket);

            g.printKoordinater(g.noder[startNode], g.noder[sluttNode]);              //Printer koordinatene til nodene fra start til slutt i filen "rute koordinater.txt", som kan brukes i https://mapmakerapp.com/

    }
}

