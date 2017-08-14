package com.devoir;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    // TODO : doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve the product entered
        String mail=request.getParameter("mail");
        String password=request.getParameter("password");

        // Insert user into DB
        try {
            UserModel user = UserDBHandler.getDb().login(mail, password);

            if(user != null){
              this.terminate(request,response,"You have been logged as " + user.getFirstName() + " " + user.getLastName());
              request.getSession().setAttribute("user", user);
            } else
              this.terminate(request,response,"There is a problem with your credentials");
        } catch (Exception e) {
            this.terminate(request,response,"Error while login user ("+e+").");
            return;
        }
    }

    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    protected void terminate (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/home?message="+message));
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
