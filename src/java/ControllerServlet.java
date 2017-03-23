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
    //right now these aren't connected to the pages (any links just directly 
    //display the .jsp file)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();

        //if index page is requested the user should be logged out
        if (userPath.equals("/index")) {
            //return index page & log out the user

        } else if (userPath.equals("/leaderboard")) {
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
    //creation) as well as the startGame requests
    //I am unsure as to how we're planning to implement the actual gameplay 
    //elements, maybe in their own method? 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
     
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}