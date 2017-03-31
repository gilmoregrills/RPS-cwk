/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Singleton;
import entity.Users;
import java.util.HashMap;
import javax.ejb.LocalBean;
/**
 *
 * @author Robin Yonge
 */
@Singleton
@LocalBean
public class GameState {
    
    /**
     * String is a concat of both player names like "gameStarter+gameJoiner"
     * String[] will be the move each player makes [gameStarterMove, gameJoinerMove]
     */
    public HashMap<String, String[]> playerMoves = new HashMap<String, String[]>();
    
    /**
     * key Users is the user starting the game
     * value Users is the user they want to play with
     */
    public HashMap<Users, Users> playerGames = new HashMap<Users, Users>();
    
    /**
     * key String is an identical concat of both player names as in playerMoves
     * value String is the outcome of the game, either a username or "draw"
     */
    public HashMap<String, String> gameOutcomes = new HashMap<String, String>();
    
    public String getOutcome(String gameName) {
        return gameOutcomes.get(gameName);
    }
    
    /**
     * get the moves for an existing game
     */
    public String getOpponentMove(String playerName, String gameName) {
        String[] gameMoves = playerMoves.get(gameName);
        if (playerGames.containsKey(playerName)) {
            return gameMoves[1];
        } else {
            return gameMoves[0];
        }
    }
    
    public boolean movesMade(String gameName) {
        String[] moves = playerMoves.get(gameName);
        if (moves[0] != null && moves[1] != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * send a player's move
     */
    public void makeMove(String playerName, String gameName, String move) {
        if (playerGames.containsKey(playerName)) {
            String[] moves = playerMoves.get(gameName);
            moves[0] = move;
            playerMoves.put(gameName, moves);
        } else {
            String[] moves = playerMoves.get(gameName);
            moves[1] = move;
            playerMoves.put(gameName, moves);
        }
    }
    
    /**
     * is the user you're searching for already looking to play a game against you? 
     */
    public boolean findGame(Users currentUser, Users opponentUser) {
        if (playerGames.containsValue(currentUser) && playerGames.containsKey(opponentUser)) {
            //if there is a game request that fits your request, create entry in moves
            String gameName = currentUser.getUsername()+opponentUser.getUsername();
            playerMoves.put(gameName, new String[2]);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * create a game in the playerGames hash map
     * create somewhere for moves to go
     */
    public void createGame(Users currentUser, Users opponentUser) {
        playerGames.put(currentUser, opponentUser);
    }
    /**
     * check if request has been accepted
     */
    public boolean requestAccepted(Users currentUser, Users opponentUser) {
        String gameName = currentUser.getUsername()+opponentUser.getUsername();
        if (playerMoves.containsKey(gameName)) {
            return true;
        } else {
            return false;
        }
    }
}
