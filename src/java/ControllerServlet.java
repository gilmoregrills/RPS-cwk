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
                           "/playGame",
                           "/create",
                           "/login",
                           "/loginPage"})
public class ControllerServlet extends HttpServlet {

    
    @EJB
    private UsersFacade userFacade;
    
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
        
        HttpSession session = request.getSession();
        Users currentUser;
        String userPath = request.getServletPath();
        System.out.println("userPath : "+request.getServletPath());
        
        if (userPath.equals("/leaderboard")) {
            if (session.getAttribute("user") != null) {
                currentUser = userFacade.find(session.getAttribute("user"));
                session.setAttribute("score", currentUser.getScore());
            } else {
                
            }

        } else if (userPath.equals("/startGame")) {
            //return start game page/form/list of logged in users

        } else if (userPath.equals("/playGame")) {
            //return the game view (depending on how we implement it)

        } else if (userPath.equals("/createAccount")) {
            System.out.println("accessed createAccount");
            //return the create account view
        } else if (userPath.equals("/loginPage")) {
            
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        System.out.println("userPath : "+request.getServletPath());
        //the current logged in user, not initialized yet
        Users currentUser;
        
        //hacky workarounds, for POST requests with incorrect paths???
        if (userPath.equals("/createAccount")) {
            userPath = "/create";
        }
        if (userPath.equals("/loginPage")) {
            userPath = "/login";
        }
        
        //this creates a session object for this connection if one does not already exist 
        HttpSession session = request.getSession();
        //if this session has a user attribute, get the associated Users entity
        //and initialize currentUser with it, for user later
        if (session.getAttribute("user") != null) {
            System.out.println("User attribute of current session is: "+session.getAttribute("user"));
            currentUser = userFacade.find(session.getAttribute("user"));
        }
        if (userPath.equals("/login")) {
            Users returningUser = userFacade.find(request.getParameter("username"));
            System.out.println("Logging in with:");
            System.out.println("Username: "+returningUser.getUsername()+" Password: "+returningUser.getPassword());
            session.setAttribute("user", returningUser.getUsername());
            session.setAttribute("score", returningUser.getScore());
            System.out.println("User attribute of current session is: "+session.getAttribute("user"));
            //once logged in, redirect to startGame page
            userPath = "/startGame";
            
        } else if (userPath.equals("/create")) {
            String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            Users newUser = new Users(userName);
            newUser.setPassword(passWord);
            System.out.println("Creating user named:");
            System.out.println("Username: "+newUser.getUsername()+" Password: "+newUser.getPassword());
            System.out.println("there are "+userFacade.count()+" users so far");
            userFacade.create(newUser);
            //account created, redirect to login page
            userPath = "/loginPage";
            
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
