/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Singleton;
import entity.Users;
import java.util.HashMap;

/**
 *
 * @author rpr30
 */
@Singleton
public class GameState {
    
    /**
     * String is a concat of both player names like "gameStarter+gameJoiner"
     * String[] will be the move each player makes [gameStarterMove, gameJoinerMove]
     */
    HashMap<String, String[]> playerMoves = new HashMap<String, String[]>();
    
    /**
     * key Users is the user starting the game
     * value Users is the user they want to play with
     */
    HashMap<Users, Users> playerGames = new HashMap<Users, Users>();
    
    /**
     * key String is an identical concat of both player names as in playerMoves
     * value String is the outcome of the game, either a username or "draw"
     */
    HashMap<String, String> gameOutcomes = new HashMap<String, String>();
    
    /**
     * get the moves for an existing game
     */
    public String[] getMoves(String userNames) {
        return playerMoves.get(userNames);
    }
    
    /**
     * is the user you're searching for already looking to play a game against you? 
     * if yes you can go ahead
     * if no create an entry in playerGames
     */
    public void findOrCreateGame(Users currentUser, Users opponentUser) {
        if (!playerGames.containsKey(currentUser)) {
            playerGames.put(currentUser, opponentUser);
        }
    }
    
    /**
     * get the outcome of the game between the two users
     * 
     */
    public String getOutcome(String userNames) {
        return gameOutcomes.get(userNames);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
