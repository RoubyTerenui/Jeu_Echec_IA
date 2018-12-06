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
            else if (inputString.startsWith("setoption"))
            {
                inputSetOption(inputString);
            }
            else if ("isready".equals(inputString))
            {
                inputIsReady();
            }
            else if ("ucinewgame".equals(inputString))
            {
                inputUCINewGame();
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
            else if ("print".equals(inputString))
            {
                inputPrint();
            }
        }
    }

    private static void inputUCI() {
        System.out.println("id name " + engineName);
        System.out.println("id author ValTereKarKev");

        //options go here
        System.out.println("uciok");
    }

    private static void inputSetOption(String inputString){
        //TO DO setOption
    }

    private static void inputUCINewGame(){

    }

    private static void inputIsReady(){
        System.out.println("isready");
    }

    private static void inputQuit() {
        System.exit(0);
    }

    private static int[] inputPosition(String inputString){
        String input = "";
        int size = inputString.length();
        if(inputString.contains("startpos ")){
            if(inputString.contains("moves ")){
                input = inputString.substring(size - 2, size);
            }
        }
        int[] result = {transformColToPos(input.charAt(0)),(int)input.charAt(1)};
        return result;
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

    private static void inputPrint(){}

}
