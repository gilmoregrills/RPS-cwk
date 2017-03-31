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
public class introMessage implements introMessageLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void introMessage(String playername){
        System.out.println("Welcome, " + playername + ", to Rock, Paper, Scissors. Are you ready to begin?");
        System.out.println("Please select option 'Yes' or 'No'");
    } 
}
