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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;


/**
 *
 * @author Robin Yonge
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
    public void init() throws ServletException {

        // store category list in servlet context
        getServletContext().setAttribute("allUsers", userFacade.findAll());
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    /**
     * doGet primarily handles page routing and the fetching of information,
     * anything involving input will be handled by doPost
     **/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("raw userPath: "+request.getServletPath());
        
        Users currentUser;
        //this creates a session object for this connection if one does not already exist 
        HttpSession session = request.getSession();
        //if this session has a user attribute, get the associated Users entity
        //and initialize currentUser with it, for user later
        if (session.getAttribute("user") != null) {
            System.out.println("User attribute of current session is: "+session.getAttribute("user"));
            currentUser = userFacade.find(session.getAttribute("user"));
        }
        String userPath = request.getServletPath();
        System.out.println("Routing GET request for userPath: "+request.getServletPath());
        
        if (userPath.equals("/leaderboard")) {
            //get single user data for logged in user using: 
            //currentUser.getUsername();
            //currentUser.getScore(); 
            //get data for all logged in users using: 
            List<Users> allUsers = userFacade.findAll();
            Collections.sort(allUsers, new Comparator<Users>() {
                @Override public int compare(Users u1, Users u2) {
                    return u2.getScore() - u1.getScore();
                }
            });
            /**For debugging: make sure the list is ordered correctly
             * Iterator<Users> debuggo = allUsers.iterator();
             * while (debuggo.hasNext()) {
             *    System.out.println(debuggo.next());
             * }
             **/
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
    /**
     * doPost handles taking input and interacting with session beans, most of 
     * these methods will route to a view after executing. 
     **/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("raw userPath: "+request.getServletPath());
        
        String userPath = request.getServletPath();
        //hacky workarounds, for POST requests with incorrect paths???
        System.out.println("Routing POST request for userPath: "+request.getServletPath());
        //the current logged in user, not initialized yet
        Users currentUser;
        
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
            newUser.setScore(new Integer(0));
                        System.out.println("Creating user named:");
                        System.out.println("Username: "+newUser.getUsername()+" Password: "+newUser.getPassword());
                        System.out.println("there are "+userFacade.count()+" users so far");
            userFacade.create(newUser);
            //account created, redirect to login page
                        System.out.println("user path is "+userPath);
            userPath = "/loginPage";
                        System.out.println("user path is "+userPath);
                        
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
