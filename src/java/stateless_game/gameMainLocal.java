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
public interface gameMainLocal {
    void gameMain(String[] args) ;
}
