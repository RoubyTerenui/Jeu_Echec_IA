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



}
