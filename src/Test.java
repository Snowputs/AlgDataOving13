import AStar.AGraf;
import Dijkstra.DGraf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        DGraf dGraf = new DGraf();
        AGraf aGraf = new AGraf();
        File noder = new File("src/data/noder.txt");
        File kanter = new File("src/data/kanter.txt");
        try {
            BufferedReader n = new BufferedReader(new FileReader(noder));
            BufferedReader k = new BufferedReader(new FileReader(kanter));
            dGraf.lagGraf(n, k);
            BufferedReader v = new BufferedReader(new FileReader(noder));
            BufferedReader l = new BufferedReader(new FileReader(kanter));
            aGraf.lagGraf(v, l);
        }
        catch (Exception e) { e.printStackTrace(); }

        Scanner reader = new Scanner(System.in);

        System.out.println("\n\nSkriv nummeret til ønsket FraNode: ");
        int startNode = reader.nextInt();

        while (startNode >= dGraf.antNoder || startNode < 0) {
            System.out.println("Out of bounds, try again ");
            startNode = reader.nextInt();
        }

        System.out.println("Skriv nummeret til ønsket TilNode: ");
        int sluttNode = reader.nextInt();

        while (sluttNode >= dGraf.antNoder || sluttNode < 0) {
            System.out.println("Out of bounds, try again ");
            sluttNode = reader.nextInt();
        }


        Date start = new Date();
        dGraf.kortesteVeimellom(dGraf.noder[startNode], dGraf.noder[sluttNode]);
        Date slutt = new Date();

        double tidBrukt = slutt.getTime() - start.getTime();
        System.out.println("Dijkstra: ");
        System.out.println("Tid brukt: " + tidBrukt + " ms");
        System.out.println("Tid fra A til B: " + dGraf.noder[sluttNode].før.TimerMinutterOgSekunder());
        System.out.println("Antall noder sjekket: " + dGraf.noderSjekket);

        dGraf.printKoordinater(dGraf.noder[startNode], dGraf.noder[sluttNode]);

        System.out.println("\n");


        start = new Date();
        aGraf.kortesteVeimellom(aGraf.noder[startNode], aGraf.noder[sluttNode]);
        slutt = new Date();

        tidBrukt = slutt.getTime() - start.getTime();
        System.out.println("A*:");
        System.out.println("Tid brukt: " + tidBrukt + " ms");
        System.out.println("Tid fra A til B: " + aGraf.noder[sluttNode].før.TimerMinutterOgSekunder());
        System.out.println("Antall noder sjekket: " + aGraf.noderSjekket);

        aGraf.printKoordinater(aGraf.noder[startNode], aGraf.noder[sluttNode]);

    }
}
