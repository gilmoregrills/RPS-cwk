/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.*;
import java.util.List;

/**
 *
 * @author gilmoregrills
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    
    @PersistenceContext(unitName = "RockPaperScissorsPU")
    private EntityManager ecm;
    
    @Override
    protected EntityManager getEntityManager() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RockPaperScissorsPU");
    EntityManager ecm = emf.createEntityManager(); 
    return ecm;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public List<Users> getUsers() {
        return findAll();
    }
    
    @Override
    public void create(Users user) {
        getEntityManager().persist(user);
    }
    
}
