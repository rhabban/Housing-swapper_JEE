package com.devoir;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Corentin Chupin & Bastien Sebire, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2017
 */
public class AddOfferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object _user = request.getSession().getAttribute("user");
        if(_user == null){
            this.terminate(request,response,"You have to be logged to add Housing");
            return;
        }

        // Retrieve the offer entered
        String from=request.getParameter("from");
        String to=request.getParameter("to");
        String country=request.getParameter("country");

        int housing_id=Integer.parseInt(request.getParameter("housing_id"));

        UserModel user = (UserModel)_user;

        OfferModel offer = new OfferModel(-1, user.getMail(), housing_id, from, to, country);

        // Insert offer into DB
        try {
            OfferDBHandler.getDb().create(offer);
        } catch (Exception e) {
            this.terminate(request,response,"Error while inserting offer ("+e+").");
            return;
        }

        // Everything went well
        this.terminate(request,response,"Offer has been posted");

    }

    /**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     */
    protected void terminate (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/my_offers?message="+message));
    }

    @Override
    public void destroy () {
        try {
            OfferDBHandler.close();
        } catch (SQLException e) {
            this.log("Erreur lors de la cl&ocirc;ture de la connexion SQL ("+e+").");
       }
    }

}
