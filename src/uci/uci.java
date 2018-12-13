package uci;

import java.util.*;

import agent.Agent;
import environment.Environment;
import environment.Move;

public class uci {

    static String engineName = "On va vous cogner sec v1";
    static Agent agent;
    static Environment environment;

    public static void uciCom(){
    	agent=new Agent();
    	environment=new Environment();
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
                inputGo();
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
        int[] result = {transformColToPos(input.charAt(0)),(int)input.charAt(1),transformColToPos(input.charAt(3)),(int)input.charAt(4)};
        executeAction(environment, transformColToPos(input.charAt(0)),(int)input.charAt(1),transformColToPos(input.charAt(3)),(int)input.charAt(4));
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

    private static void inputGo(){
    	agent.observ(environment.getChessBoard());
    	Move move=agent.getBestMove(2);
    	executeAction(environment,move.getPiece().getPosX(),move.getPiece().getPosY(),move.getDest()[0],move.getDest()[1]);
        System.out.println("bestmove " + transformPosToCol(move.getPiece().getPosX())+ move.getPiece().getPosY()+transformPosToCol(move.getDest()[0])+move.getDest()[1]);
    }

    private static void inputPrint(){}

    private static void executeAction(Environment environment,int positionDepartX , int positionDepartY , int positionDestX, int positionDestY) {
    	environment.getChessBoard()[positionDestX][positionDestY].setActualPieces(environment.getChessBoard()[positionDepartX][positionDepartY].getActualPieces());
    	environment.getChessBoard()[positionDestX][positionDestY].setActualPieces(null);
    }
}
