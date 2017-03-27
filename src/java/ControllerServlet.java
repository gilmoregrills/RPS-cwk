/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ejb.EJB;
//import session.UserFacade;


/**
 *
 * @author gilmoregrills
 */
//a urlPattern for each page, basically 
@WebServlet(name="ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"/index", 
                           "/leaderboard", 
                           "/startGame", 
                           "/createAccount", 
                           "/playGame"})
public class ControllerServlet extends HttpServlet {

    
    //@EJB
    //private UserFacade userFacade;
    
    //public void init() throws ServletException {
        
    //}
    
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    //doGet primarily handles page requests, as you can kinda see from the
    //if/else blocks below 
    //All of the pages aside from the index page should be here
    //the index page can just be accessed from the root url and
    //linked to on pages with "./"
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
        
        if (userPath.equals("/leaderboard")) {
            //return leaderboard page

        } else if (userPath.equals("/startGame")) {
            //return start game page/form/list of logged in users

        } else if (userPath.equals("/playGame")) {
            //return the game view (depending on how we implement it)

        } else if (userPath.equals("/createAccount")) {
            //return the create account view
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    //doPost is I guess what we'll use to take all account detail input (login &
    //creation)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
        //this will create a session if one does not
        //exist, or return the existing session for the
        //user any time a POST request is made (on login
        //etc)
        HttpSession session = request.getSession();
        //might also need to explicitly assign a user attr
        //to the session
        //User user = (User) session.getAttribute("user");
        
        if (userPath.equals("/")) {
            // handle login stuff

        } else if (userPath.equals("/")) {
            //handle account creation (including adding new user to the db)

        } else if (userPath.equals("/")) {
            //handle starting a game/joining a game
            //(if game exists between those users && user = one of the users,
            //then join game, if not: create new game)
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

}
