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
                inputPosition(2,1,3,1);
            }
            else if (inputString.startsWith("go"))
            {
                inputGo();
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

    private static void inputPosition(int positionDepX, int positionDepY, int positionX, int positionY){
        System.out.println("bestmove " + transformPosCol(positionDepY) + positionDepX + transformPosCol(positionY) + positionX);
    }

    private static String transformPosCol(int positionCol){
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

    private static void inputGo(){}

    private static void inputPrint(){}

}
