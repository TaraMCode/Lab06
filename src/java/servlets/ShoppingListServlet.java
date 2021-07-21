/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
            throws ServletException, IOException { // The doGet() method is used for getting the information from server

        HttpSession session = request.getSession(); // get the session

        String loggingout = request.getParameter("logout"); // gets the query string from shoppingList.jsp
        if (loggingout != null) { // if the logout button is clicked

            session.invalidate(); // invalidate the session
        }
        String username = (String) session.getAttribute("username_attribute");

        if (username != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response); // always write this at the end of the doGet
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //  doPost() method is used for sending information to the server.

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        if (action != null && action.equals("register")) { // do this three more times - add, delete etc
            String usernameString = request.getParameter("username");

            // doPost() first validates that user name and password are not empty. 
            if (usernameString == null || usernameString.equals("")) {
                request.setAttribute("username", usernameString); // reloads username to boxes
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            } else {
                session.setAttribute("username_attribute", usernameString);
                ArrayList<String> list = new ArrayList<String>();
                session.setAttribute("shoppingListItem", list);
                // response.sendRedirect("/WEB-INF/shoppingList.jsp"); // Can't redirect to a JSP, can only use forward
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        }

        if (action != null && action.equals("Add")) {
            String addItemServlet = request.getParameter("item");

            ArrayList<String> list = (ArrayList<String>) session.getAttribute("shoppingListItem");
            //    request.setAttribute("itemIsAdded", true);
            list.add(addItemServlet);
            session.setAttribute("shoppingListItem", list);
            response.sendRedirect("ShoppingList");

        } else if (action != null && action.equals("Delete")) {
            String deleteItem = request.getParameter("items");
            ArrayList<String> list = (ArrayList<String>) session.getAttribute("shoppingListItem");
            list.remove(deleteItem);
            session.setAttribute("shoppingListItem", list);
            response.sendRedirect("ShoppingList");
        }
        //getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}