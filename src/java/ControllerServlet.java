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
import session.UsersFacade;
import entity.Users;


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
        System.out.println(userPath);
        
        if (userPath.equals("/leaderboard")) {
            //return leaderboard page

        } else if (userPath.equals("/startGame")) {
            //return start game page/form/list of logged in users

        } else if (userPath.equals("/playGame")) {
            //return the game view (depending on how we implement it)

        } else if (userPath.equals("/createAccount")) {
            System.out.println("accessed createAccount");
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
        System.out.println("we got a POST request!!");
      
        UsersFacade userface = new UsersFacade();
        
        String userPath = request.getServletPath();
        System.out.println("userPath pre-if: "+request.getServletPath());
        userPath = "/create";
        //this will create a session if one does not
        //exist, or return the existing session for the
        //user any time a POST request is made (on login
        //etc)
        //HttpSession session = request.getSession();
        //here's the user object thawt will be attached to the
        //session on login, get it here if it exists so as to
        //be able to use it below
        //User user = (User) session.getAttribute("user");
        
        if (userPath.equals("/login")) {
            //handle login stuff here
            //find the user entity that matches the login deets
            //session.setAttribute("user", the-user-id);
            
        } else if (userPath.equals("/create")) {
            System.out.println(request.getParameter("username")+request.getParameter("password"));
            String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            Users newUser = new Users(userName);
            newUser.setPassword(passWord);
            userface.create(newUser);
            System.out.println("pre-forward: "+userPath);
            userPath = "/leaderboard";
            System.out.println("post-forward: "+userPath); //should be login page in future
            
            //handle account creation (including adding new user to the db)
            //create the user entity in the database using
            //the constructor commented
        } else if (userPath.equals("./")) {
            //handle request to start a game (probs just pass
            //data to the game-handling code)
            
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
