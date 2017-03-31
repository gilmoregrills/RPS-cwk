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
public class playerChoices implements playerChoicesLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public char playerChoices(String playername){
        char playerChoice = '\u0000';
        System.out.println(playername + " make your choice: ");
        
        if (playerChoice == 'R')
            return 'R';
        else if (playerChoice == 'P')
            return 'P';
        else if (playerChoice == 'S')
            return 'S';
        
        return playerChoice;
    }
}
