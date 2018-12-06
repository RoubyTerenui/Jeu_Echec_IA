package main;

import agent.Agent;
import environment.Environment;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Environment environment = new Environment();
		Agent agent = new Agent();
		agent.observ(environment.getChessBoard());
		System.out.println(agent.getBestMove());

	}

}
