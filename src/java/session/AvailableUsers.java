/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Singleton;
import java.util.ArrayList;
import entity.Users;
import javax.ejb.LocalBean;
/**
 *
 * @author Robin Yonge
 */

@Singleton
@LocalBean
public class AvailableUsers {
    
    ArrayList<Users> loggedIn = new ArrayList<Users>();
    
    public void addLoggedIn(Users user) {
        loggedIn.add(user);
    }
    public boolean userAvailable(Users user) {
        if (loggedIn.contains(user)) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<Users> getAvailable() {
        return loggedIn;
    }
}
