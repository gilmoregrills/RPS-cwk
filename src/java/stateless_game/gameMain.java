/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless_game;

import javax.ejb.Stateless;

/**
 *
 * @author Aatish
 */
@Stateless
public class gameMain implements gameMainLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void gameMain(String[] args){
        
        String playername1 = "Robin";
        String playername2 = "Ariana";
        
        //TESTING ONLY
        //int winGame = 2;
        //int drawGame = 1;
        //int loseGame = 0;
        
        String playername1ToBegin;
        String playername2ToBegin;        
        char player1ToBegin = 'Y';
        char player2ToBegin = 'Y';
        boolean playername1Ready = false;
        boolean playername2Ready = false;
        
        String gameWinner = "";
        
        introMessage(playername1);
        // Has Player 1 clicked on 'Start game' button?
        playername1Ready = readyToBegin(player1ToBegin);
            
        introMessage(playername2);
        // Has player 1 clicked on 'Start game' button?
        playername2Ready = readyToBegin(player2ToBegin);
        
        if (playername1Ready && playername2Ready){
            System.out.println("Let's play Rock, Paper, Scissors!");
        
            char player1Choice = playerChoices(playername1);
            char player2Choice = playerChoices(playername2);
            
            //TESTING ONLY
            //char player1Choice = 'S';
            //char player2Choice = 'P';
            
            gameWinner = winnerOfGame(playername1, playername2, player1Choice, player2Choice);
            
            //TEST OUTPUT - final returned value is name of winner, or "draw"
            System.out.println(gameWinner);
        }
    }
}
