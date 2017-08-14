package com.devoir;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserServlet extends HttpServlet {

    @Override
    // TODO : doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve the product entered
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String mail=request.getParameter("mail");
        String password=request.getParameter("password");
        UserModel user = new UserModel(firstName, lastName, mail, password);


        // Insert user into DB
        try {
            UserDBHandler.getDb().create(user);
        } catch (Exception e) {
            this.terminate(request,response,"Error while inserting user ("+e+").");
            return;
        }

        // Everything went well
        this.terminate(request,response,"User has been added");
    }

    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    protected void terminate (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/users?message="+message));
    }

    @Override
    public void destroy () {
        try {
            HousingDBHandler.close();
        } catch (SQLException e) {
            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
       }
    }
}
