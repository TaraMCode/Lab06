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
        
        String action = request.getParameter("logout"); // gets the query string from login.jsp
        if (action != null) { // if the logout button is clicked
            HttpSession session = request.getSession(); // get the session
            session.invalidate(); // invalidate the session
            request.setAttribute("message", "You have successfully logged out."); // return a message informing the user they've logged in
        }
        
        HttpSession session = request.getSession();
                      
         getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response); // always write this at the end of the doGet
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usernameString = request.getParameter("person_username");

        // doPost() first validates that user name and password are not empty. 
        if (usernameString == null || usernameString.equals("")) {
            // response.sendRedirect("Hello");
            request.setAttribute("message", "Invalid login.");
            request.setAttribute("username_attribute", usernameString); // reloads username to boxes
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

       getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}