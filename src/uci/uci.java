package uci;

import java.util.*;

public class uci {

    static String engineName = "On va vous cogner sec v1";

    public static void uciCom(){
        Scanner input = new Scanner(System.in);
        while(true){
            String inputString = input.nextLine();
            if("uci".equals(inputString)){
                inputUCI();
            }
            else if ("isready".equals(inputString))
            {
                inputIsReady();
            }
            else if (inputString.startsWith("position"))
            {
                inputPosition(inputString);
            }
            else if (inputString.startsWith("go"))
            {
                inputGo(2,1,3,1);
            }
            else if (inputString.equals("quit"))
            {
                inputQuit();
            }
        }
    }

    private static void inputUCI() {
        System.out.println("id name " + engineName);
        System.out.println("id author ValTereKarKev");

        //options go here
        System.out.println("uciok");
    }

    private static void inputIsReady(){
        System.out.println("isready");
    }

    private static void inputQuit() {
        System.exit(0);
    }

    private static void inputPosition(String inputString){
        String output = "";
        Boolean blackPlayler = true;
        int size = inputString.length();
        if(inputString.contains("startpos ")){
            if(inputString.contains("moves ")){
                output = inputString.substring(size - 4, size);
            }
        }
        String[] BlackList = output.split(" ");
        if(BlackList.length / 2 != 0){
            blackPlayler = false;
        }
    }

    private static String transformPosToCol(int positionCol){
        switch(positionCol){
            case 1: return "a";
            case 2: return "b";
            case 3: return "c";
            case 4: return "d";
            case 5: return "e";
            case 6: return "f";
            case 7: return "g";
            case 8: return "h";
        }
        return "";
    }

    private static int transformColToPos(char positionCol){
        switch(positionCol){
            case 'a': return 1;
            case 'b': return 2;
            case 'c': return 3;
            case 'd': return 4;
            case 'e': return 5;
            case 'f': return 6;
            case 'g': return 7;
            case 'h': return 8;
        }
        return 0;
    }

    private static void inputGo(int positionDepX, int positionDepY, int positionX, int positionY){
        System.out.println("bestmove " + transformPosToCol(positionDepY) + positionDepX + transformPosToCol(positionY) + positionX);
    }

    private static String getTimer(String inputString){
        String output = "";
        int size = inputString.length();
        if(inputString.contains("go ")){
            output = inputString.substring(size - 2, 3);
        }
        return output;
    }
}
