package com.devoir;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.housing.*;

/**
 * @author Corentin Chupin & Bastien Sebire, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2017
 */
public class AddHousingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object user = request.getSession().getAttribute("user");
        if(user == null){
            this.terminate(request,response,"You have to be logged to add Housing");
            return;
        }

        // Retrieve the product entered
        String name=request.getParameter("name");
        String country=request.getParameter("country");
        int surface=new Integer(request.getParameter("surface"));
        String address=request.getParameter("address");
        int roomCount=new Integer(request.getParameter("roomCount"));
        HousingModel housing;

        if(request.getParameter("surfaceArea") != null){
            int surfaceArea=new Integer(request.getParameter("surfaceArea"));
            housing=new HouseModel(country, surface, roomCount, address, name, surfaceArea);
        } else {
            housing=new FlatModel(country, surface, roomCount, address, name);
        }

        // Insert house into DB
        try {
            int housing_id = HousingDBHandler.getDb().create(housing);
            UserDBHandler.getDb().associateHousing(housing_id, (UserModel)user);
        } catch (Exception e) {
            this.terminate(request,response,"Error while inserting housing ("+e+").");
            return;
        }

        // Everything went well
        this.terminate(request,response,"Housing has been added");

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
