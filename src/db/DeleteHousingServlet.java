package com.devoir;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.housing.*;

public class DeleteHousingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve the product entered
        int id=new Integer(request.getParameter("id"));

        // Insert user into DB
        try {
            HousingWithIdModel housing = HousingDBHandler.getDb().retrieve(id);
            if(housing == null){
              this.terminate(request,response,"Housing doesn't exists");
              return;
            } else {
              this.terminate(request,response,"Housing has been deleted : "+housing);
              HousingDBHandler.getDb().delete(housing);
            }
        } catch (Exception e) {
            this.terminate(request,response,"Error while inserting user ("+e+").");
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
