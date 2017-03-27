/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gilmoregrills
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "RockPaperScissorsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
/*notes:
AbstractFacade has -
create() method
find() method
findAll

so I think on creation it'll be a case 
of instantiating an entity of the correct type
and then calling create(entity)

on login I guess we'll use find? Can't find
by ID though we'd have to match username to id

for the leaderboard I assume it'll be findAll
then iterate through them to print the list 
*/
