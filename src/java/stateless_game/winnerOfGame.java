/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless_game;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author Aatish
 */
@Singleton
@LocalBean
public class winnerOfGame implements winnerOfGameLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String winnerOfGame(String playername1, String playername2, String player1Choice, String player2Choice){
        if (player1Choice == "rock" && player2Choice == "rock"){
            System.out.println("Both players have chosen Rock - the game is a draw!");
            return "draw";
        }
        else if (player1Choice == "paper" && player2Choice == "paper"){
            System.out.println("Both players have chosen Paper - the game is a draw!");
            return "draw";
        }
        else if (player1Choice == "scissors" && player2Choice == "scissors"){
            System.out.println("Both players have chosen Scissors - the game is a draw!");
            return "draw";
        }
        else if (player1Choice == "rock" && player2Choice == "scissors"){
            System.out.println(playername1 + " chose Rock and " + playername2 + " chose Scissors - " + playername1 + " wins!");
            return playername1;
        }
        else if (player1Choice == "paper" && player2Choice == "rock"){
            System.out.println(playername1 + " chose Paper and " + playername2 + " chose Rock - " + playername1 + " wins!");
            return playername1;
        }
        else if (player1Choice == "scissors" && player2Choice == "paper"){
            System.out.println(playername1 + " chose Scissors and " + playername2 + " chose Paper - " + playername1 + " wins!");
            return playername1;
        }
        else if (player1Choice == "rock" && player2Choice == "paper"){
            System.out.println(playername1 + " chose Rock and " + playername2 + " chose Paper - " + playername2 + " wins!");
            return playername2;
        }
        else if (player1Choice == "paper" && player2Choice == "scissors"){
            System.out.println(playername1 + " chose Paper and " + playername2 + " chose Scissors - " + playername2 + " wins!");
            return playername2;
        }
        else if (player1Choice == "scissors" && player2Choice == "rock"){
            System.out.println(playername1 + " chose Scissors and " + playername2 + " chose Rock - " + playername2 + " wins!");
            return playername2;
        }
        
        return "";
    }
}
