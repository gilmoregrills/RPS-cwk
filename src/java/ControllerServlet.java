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
import session.AvailableUsers;
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
    UsersFacade userFacade;
    AvailableUsers available;
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
        
        String userPath = request.getServletPath();
        System.out.println("Routing GET request for userPath: "+request.getServletPath());
        Users currentUser;
        //this creates a session object for this connection if one does not already exist 
        HttpSession session = request.getSession();
        //if this session has a user attribute, get the associated Users entity
        //and initialize currentUser with it, for user later
        if (session.getAttribute("user") != null) {
            System.out.println("User attribute of current session is: "+session.getAttribute("user"));
            currentUser = userFacade.find(session.getAttribute("user"));
        }

        if (userPath.equals("/leaderboard")) {
            //get single user data for logged in user using: 
            //currentUser.getUsername();
            //currentUser.getScore(); 
            //below code gets all users from the DB and sorts them into allUsers
            List<Users> allUsers = userFacade.findAll();
            Collections.sort(allUsers, new Comparator<Users>() {
                @Override public int compare(Users u1, Users u2) {
                    return u2.getScore() - u1.getScore();
                }
            });
            session.setAttribute("all", allUsers);
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
        
        String userPath = request.getServletPath();
        System.out.println("Routing POST request for userPath: "+request.getServletPath());
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
            session.setAttribute("user", returningUser.getUsername());
            session.setAttribute("score", returningUser.getScore());
            available.addLoggedIn(returningUser);
            //once logged in, redirect to startGame page
            userPath = "/startGame";
            
        } else if (userPath.equals("/create")) {
            String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            Users newUser = new Users(userName);
            newUser.setPassword(passWord);
            newUser.setScore(new Integer(0));
            userFacade.create(newUser);
            //account created, redirect to login page
            userPath = "/loginPage";
                        
        } else if (userPath.equals("requestGame")) {
            //check if a request to play with the key of the requested user
            //already exists, if not then create one!
            userPath = "/playGame";
            
        } else if (userPath.equals("makeMove")) {
            //send the move choice to the right String[] array in GameState.playerMoves
            //if the first index is null, move goes there and we wait?
            //if the first index contains a value, the other player has already 
            //made their move and our move goes in the second index
            //because that means both players have made their decision, we pass
            //this state information to the game playing script and it tells us
            //the username of the winner! Winner should potentially be stored in the singleton too
            
            //String outcome; <- the winner of the game, could be based on
            //an if (winner.equals(currentUser)) or something so that the outcome
            //is different for each player
            //if (outcome.equals(win)) { 
            //    userPath = "/winPage";
            //    currentUser.setScore(currentUser.getScore()+2);
            //} else if (outcome.equals(draw)) {
            //    userPath = "/drawPage";
            //    currentUser.setScore(currentUser.getScore()+1);
            //} else {
            //    userPath = "/lossPage";
            //}
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
