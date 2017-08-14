package com.devoir;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class for SQL storage of offers in a database.
 * @author Bastien Sebire and Corentin Chupin, Universit&eacute; de Caen Basse-Normandie, France.
 * @since November, 2016
 */
public class SQLOfferDB {

    /** The name for the SQL table where to store offers. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createOfferStatement;

    /** A prepared statement for retrieval of one offer. */
    private PreparedStatement retrieveOfferStatement;

    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store housings
     * @throws SQLException if a database access error occursroot cause
     */
    public SQLOfferDB (Connection link, String table) throws SQLException {
        this.link=link;
        this.table=table;
        String query=null;
        query="INSERT INTO `"+this.table+"`(user_id, housing_id, dateStart, dateEnd, country) VALUES(?,?,?,?,?)";
        this.createOfferStatement=this.link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        query="SELECT * FROM `"+this.table+"` WHERE id=?";
        this.retrieveOfferStatement=this.link.prepareStatement(query);
    }

    //@Override
    public void addOffer (OfferModel offer) throws SQLException {
        this.create(offer);
    }

    //@Override
    public List<OfferModel> getAll () throws SQLException {
        return this.retrieveAll();
    }

    // Methods

    /**
     * Resets the link to the database.
     * This method can be used in case the connection breaks down.
     * @param link An active link to the database
     */
    public void setLink (Connection link) {
        this.link=link;
    }

    /**
     * Creates the necessary table in the database. Nothing occurs if the table already exists.
     * @throws SQLException if a database access error occurs
     */
    public void createTables () throws SQLException {
        String query="CREATE TABLE IF NOT EXISTS `"+this.table+"` (";
        query+="`id` INT(100) NOT NULL AUTO_INCREMENT, ";
        query+="`user_id` VARCHAR(100) NOT NULL, ";
        query+="`housing_id` int(100) NOT NULL, ";
        query+="`dateStart` VARCHAR(255) , ";
        query+="`dateEnd` VARCHAR(255) , ";
        query+="`country` VARCHAR(255) NOT NULL, ";
        query+="PRIMARY KEY (`id`) ";
        query+=")";
        Statement statement=this.link.createStatement();
        statement.execute(query);
        System.out.println(query);
    }

    /**
     * Stores a new offer in the database.
     * @param offer The offer to store
     * @throws SQLException if a database access error occurs
     */
    public int create (OfferModel offer) throws SQLException {
        this.createOfferStatement.setString(1,offer.user_mail);
        this.createOfferStatement.setInt(2,offer.housing_id);
        this.createOfferStatement.setString(3,offer.getDateStart());
        this.createOfferStatement.setString(4,offer.getDateEnd());
        this.createOfferStatement.setString(5,offer.getCountry());
        this.createOfferStatement.execute();

        ResultSet rs = this.createOfferStatement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Retrieves all the offers in the database.
     * @return A list of all offers in the database
     * @throws SQLException if a database access error occurs
     */
    public List<OfferModel> retrieveAll () throws SQLException {
        String query="SELECT * FROM `"+this.table+"`";
        ResultSet rs=null;
        Statement statement=this.link.createStatement();
        rs=statement.executeQuery(query);
        List<OfferModel> res=new ArrayList<OfferModel>();
        while (rs.next()) {
          OfferModel offer;
          offer = new OfferModel(rs.getInt("id"), rs.getString("user_id"), rs.getInt("housing_id"), rs.getString("dateStart"),rs.getString("dateEnd"),rs.getString("country"));
          res.add(offer);
        }
        return res;
    }

    /**
     * Retrieves offers linked to one user in the database.
     * @return A list of offers in the database
     * @throws SQLException if a database access error occurs
     */
    public List<OfferModel> retrieveByUserId(String user_mail) throws SQLException {
        String query="SELECT * FROM `"+this.table+"` WHERE user_id ='"+user_mail+"'";
        System.out.println(query);
        ResultSet rs=null;
        Statement statement=this.link.createStatement();
        rs=statement.executeQuery(query);
        List<OfferModel> res=new ArrayList<OfferModel>();
        while (rs.next()) {
          OfferModel offer;
          offer = new OfferModel(rs.getInt("id"), rs.getString("user_id"), rs.getInt("housing_id"), rs.getString("dateStart"),rs.getString("dateEnd"),rs.getString("country"));
          res.add(offer);
        }
        return res;
    }

    /**
     * Retrieves an offer in the database.
     * @param id id of the housing
     * @return A HousingWithIdModel, or null if none with the given adress exists in the database
     * @throws SQLException if a database access error occurs
     */
    public OfferModel retrieve (int id) throws SQLException {
        this.retrieveOfferStatement.setInt(1, id);
        ResultSet rs=this.retrieveOfferStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }

        OfferModel offer;
        offer = new OfferModel(rs.getInt("id"), rs.getString("user_id"), rs.getInt("housing_id"), rs.getString("dateStart"),rs.getString("dateEnd"),rs.getString("country"));

        return offer;
    }

    /**
     * Drops the table from the database. Nothing occurs if the table does not exist.
     * @throws SQLException if a database access error occurs
     */
    public void deleteTables () throws SQLException {
        String query="DROP TABLE IF EXISTS `"+this.table+"`";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Deletes a product. Nothing occurs in case the product does not exist in the database.
     * @param offer
     * @throws SQLException if a database access error occurs
     */
    public void delete (OfferModel offer) throws SQLException {
        String query="DELETE FROM `"+this.table+"` WHERE id=\""+offer.getId()+"\"";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

}
