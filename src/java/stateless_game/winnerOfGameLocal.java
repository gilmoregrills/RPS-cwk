/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless_game;

import javax.ejb.Local;

/**
 *
 * @author Aatish
 */
@Local
public interface winnerOfGameLocal {
    
    String winnerOfGame(String playername1, String playername2, String player1Choice, String player2Choice);
}
