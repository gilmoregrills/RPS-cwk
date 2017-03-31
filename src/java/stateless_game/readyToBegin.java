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
public class readyToBegin implements readyToBeginLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean readyToBegin(char playernameIsReady){       
        if (playernameIsReady == 'Y')
            return true;
        
        return false;
    }
}
