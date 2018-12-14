package uci;

import java.util.*;

import agent.Agent;
import environment.Environment;
import environment.Move;
import pieces.Pawn;

public class uci {

    static String engineName = "ProjectEquipeValTer";
    static Agent agent;
    static Environment environment;

    public uci() {
    	super();

    }
    public static void uciCom(){
        agent=new Agent();
    	environment=new Environment();
    	agent.observ(environment.getChessBoard());
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
        Boolean blackPlayer = true;
        int size = inputString.length();
        if(inputString.contains("startpos ")){
            if(inputString.contains("moves ")){
                System.out.println(size);
                output = inputString.substring(size - 4, size);
                System.out.println(output);
                String[] BlackList = output.split(" ");
                 System.out.println(BlackList.length);
                if(BlackList.length % 2 != 0){
                    blackPlayer = false;
                    agent.setAiOwner( blackPlayer );
                   
                }

                int[] result = {transformColToPos(output.charAt(0)),Character.getNumericValue(output.charAt(1)),transformColToPos(output.charAt(2)),Character.getNumericValue(output.charAt(3))};
                System.out.println(result[0]+" "+result[1]+ " "+ result[2]+ " "+result[3]);
                executeAction(environment, result[1]-1,result[0]-1,result[3]-1,result[2]-1);
                agent.observ(environment.getChessBoard());
        
            }
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

    private static void inputGo(){
        System.out.println("info inputGOFunction");
        Move move;        
        if(agent.getPossibleMoves(environment.getChessBoard(),agent.getAiOwner()).size()<10){
            move=agent.getBestMove(4);
        }
        else{
            move=agent.getBestMove(2);
        }
        int originX=move.getPiece().getPosX()+1;
        int destX=move.getDest()[0]+1 ;
        System.out.println("bestmove " +transformPosToCol(move.getPiece().getPosY()+1)+ originX + transformPosToCol(move.getDest()[1]+1)+destX);
    	executeAction(environment,move.getPiece().getPosX(),move.getPiece().getPosY(),move.getDest()[0],move.getDest()[1]);
        agent.observ(environment.getChessBoard());
    }

    private static void inputPrint(){}

    private static void executeAction(Environment environment,int positionDepartX , int positionDepartY , int positionDestX, int positionDestY) {
        int[] dest={positionDestX,positionDestY};
        Move move=new Move(environment.getChessBoard()[positionDepartX][positionDepartY].getActualPieces(),dest);
        if(move.getPiece().getClass()== Pawn.class){
            ((Pawn)environment.getChessBoard()[positionDepartX][positionDepartY].getActualPieces()).setInitialPosition(false);
            ((Pawn) move.getPiece()).setInitialPosition(false);
        }
        environment.setChessBoard(agent.fakeMove(environment.getChessBoard(), move));
    }
}
