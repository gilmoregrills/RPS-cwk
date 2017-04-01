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
import session.GameState;
import stateless_game.winnerOfGame;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


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
                           "/loginPage",
                           "/requestGame",
                           "/makeMove"})
public class ControllerServlet extends HttpServlet {

    
    @EJB
    UsersFacade userFacade;
    
    @EJB
    AvailableUsers availableUsers;
    
    @EJB 
    GameState games;
    
    @EJB
    winnerOfGame rps;
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
        Users currentUser = null;
        
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
            availableUsers.addLoggedIn(returningUser);
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
                        
        } else if (userPath.equals("/requestGame")) {
            Users player1 = currentUser;
            Users player2 = userFacade.find(request.getParameter("opponent"));
            if (games.findGame(player1, player2)) {
                userPath = "playGame";
                session.setAttribute("playerNum", "2");
            } else {
                games.createGame(player1, player2);
                //System.out.println("ur opponent is: "+games.playerGames.get(player1).getUsername());
                session.setAttribute("playerNum", "1");
                //something that makes the player wait for a response???
                //if there's no response kick them back to /startGame
            }
            System.out.println("requesting game between: "+player2.getUsername()+" and "+player1.getUsername());
            int waitTime = 0;
            //if the request hasn't been accepted, wait 10 seconds, if player
            //waits 60 seconds the invite times out so redirect to startGame pg            
            /*
            while(!games.requestAccepted(player1, player2) || waitTime != 10) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    waitTime += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            */
            //check if a request to play with the key of the requested user
            //already exists, if not then create one!
            /*
            if (games.requestAccepted(player1, player2)) {
                userPath = "/playGame";            
                session.setAttribute("opponent", player2.getUsername());
            } else {
                userPath = "/startGame";
            }
            */
            userPath = "/playGame";
            
        } else if (userPath.equals("/makeMove")) {
            Users player1 = currentUser;
            Users player2 = userFacade.find(session.getAttribute("opponent"));
            String gameName = player1.getUsername()+player2.getUsername();
            System.out.println(request.getParameter("move"));
            String playerMove = request.getParameter("move");
            String playerNumber = session.getAttribute("playerNum").toString();
            games.makeMove(player1.getUsername(), gameName, playerMove);
            int waitTime = 0;
            //give other player 60 seconds to make their move
            while(!games.movesMade(gameName) && waitTime == 60) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    waitTime += 10;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //timeout or somethint???
            //if (waitTime == 60) {
            //    userPath = "/startGame";
                //redirect to the startGame page somehow, timeout effectively
            //}
            String opponentMove = games.getOpponentMove(player1.getUsername(), gameName);
            if (playerNumber.equals("1")) { //if player 1, calculate the winner
                String outcome = rps.winnerOfGame(player1.getUsername(), player2.getUsername(), playerMove, opponentMove);
                //I never actually set the right value in the game outcome hashmap! 
            } else { //WORKAROUND - if player 2 wait for a few seconds
                while(games.getOutcome(gameName) == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        //potentially add timeout here too
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }   
                }
            }
            //because that means both players have made their decision, we pass
            //this state information to the game playing script and it tells us
            //the username of the winner! Winner should potentially be stored in the singleton too
            
            String outcome = games.getOutcome(gameName);
            
            if (outcome.equals(player1.getUsername())) {
                userPath = "/winPage";
                player1.setScore(currentUser.getScore() + 2);
            } else if (outcome.equals("draw")) {
                userPath = "/drawPage";
                player1.setScore(currentUser.getScore() + 1);
            } else { //it was a loss
                userPath = "lossPage";
            }

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
