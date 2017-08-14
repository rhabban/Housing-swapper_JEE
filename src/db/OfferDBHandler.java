package com.devoir;

import com.housing.*;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;



/**
 * @author Corentin Chupin & Bastien Sebire, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2017
 */
public class OfferDBHandler {

    /** The unique link to the database (null if none active). */
    private static Connection link;

    /** The unique instance of class SQLProductsDB (null if none). */
    private static SQLOfferDB db;

    /**
     * Returns the instance of SQLOfferDB.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public static SQLOfferDB getDb () throws NamingException, SQLException {
        if (OfferDBHandler.db==null) {
            OfferDBHandler.initialize();
        }
        return OfferDBHandler.db;
    }

    /**
     * Releases the connection to the database.
     * @throws SQLException if any problem occurs while closing the connection
     */
    public static void close () throws SQLException {
        if (OfferDBHandler.link!=null) {
            OfferDBHandler.link.close();
        }
    }

    // Helper methods =====================================================================

    /**
     * Initializes the connection to the database and the instance of SQLOfferDB.
     * For each of these objects, nothing occurs if it is already initialized.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    private static void initialize () throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        String host=initialContext.doLookup("java:comp/env/host");
        String database=initialContext.doLookup("java:comp/env/database");
        String username=initialContext.doLookup("java:comp/env/username");
        String password=initialContext.doLookup("java:comp/env/password");
        String table=initialContext.doLookup("java:comp/env/offerTable");
        OfferDBHandler.db=new SQLOfferDB(OfferDBHandler.getLink(host,database,username,password),table);
        OfferDBHandler.db.createTables();
    }

    /**
     * Returns the link to the database, which is active.
     * @param host The hostname for the DBMS
     * @paam database The name for the database to use in the DBMS
     * @param username The username for connecting to the database
     * @param password The password for connecting to the database
     * @return An active link to the database
     * @throws SQLException if no active link can be established
     */
    private static Connection getLink (String host, String database, String username, String password) throws SQLException {
        if (OfferDBHandler.link==null) {
            MysqlDataSource ds=new MysqlDataSource();
            ds.setServerName(host);
            ds.setDatabaseName(database);
            OfferDBHandler.link=ds.getConnection(username,password);
        }
        if (!OfferDBHandler.link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return OfferDBHandler.link;
    }

    // Action methods =====================================================================
    public static OfferModel retrieve(int id){
        try{
          OfferDBHandler.initialize();
          OfferModel offer = db.retrieve(id);

          UserModel user = UserDBHandler.getDb().retrieve(offer.getUser().getMail());
          HousingWithIdModel housing = HousingDBHandler.getDb().retrieve(offer.getHousing().getId());

          offer.setUser(user);
          offer.setHousing(housing);

          return offer;
        } catch (SQLException e) {
            System.out.println("Exception: "+e);
        } catch (ServletException e){
            System.out.println("Exception: "+e);
        } catch (NamingException e){
            System.out.println("Exception: "+e);
        }

        return null;
    }

    public static List<OfferModel> retrieveAll(){
        try{
          OfferDBHandler.initialize();
          List<OfferModel> offers = db.retrieveAll();
          System.out.println("offers chargés");
          for(OfferModel offer : offers){
              System.out.println(offer);
              UserModel user = UserDBHandler.getDb().retrieve(offer.user_mail);
              HousingWithIdModel housing = HousingDBHandler.getDb().retrieve(offer.housing_id);

              offer.setUser(user);
              offer.setHousing(housing);
          }
          return offers;
        } catch (SQLException e) {
            System.out.println("Exception: "+e);
        } catch (ServletException e){
            System.out.println("Exception: "+e);
        } catch (NamingException e){
          System.out.println("Exception: "+e);
        }

        return null;
    }

    public static List<OfferModel> retrieveByUserId(String user_mail){
      try{
        OfferDBHandler.initialize();
        List<OfferModel> offers = db.retrieveByUserId(user_mail);
        System.out.println("offers chargés");
        for(OfferModel offer : offers){
            System.out.println(offer);
            UserModel user = UserDBHandler.getDb().retrieve(offer.user_mail);
            HousingWithIdModel housing = HousingDBHandler.getDb().retrieve(offer.housing_id);

            offer.setUser(user);
            offer.setHousing(housing);
        }
        return offers;
      } catch (SQLException e) {
          System.out.println("Exception: "+e);
      } catch (ServletException e){
          System.out.println("Exception: "+e);
      } catch (NamingException e){
        System.out.println("Exception: "+e);
      }

      return null;
    }
}
