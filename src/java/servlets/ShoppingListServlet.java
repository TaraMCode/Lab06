/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 854950
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loggingout = request.getParameter("logout"); // gets the query string from shoppingList.jsp
        if (loggingout != null) { // if the logout button is clicked
            HttpSession session = request.getSession(); // get the session
            session.invalidate(); // invalidate the session
        }

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response); // always write this at the end of the doGet
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null && action.equals("register")) { // do this three more times - add, delete etc
            String usernameString = request.getParameter("username");
            // doPost() first validates that user name and password are not empty. 
            if (usernameString == null || usernameString.equals("")) {
                request.setAttribute("username", usernameString); // reloads username to boxes
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("username_attribute", usernameString);
                // response.sendRedirect("/WEB-INF/shoppingList.jsp"); // Can't redirect to a JSP, can only use forward
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        }
        //getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
