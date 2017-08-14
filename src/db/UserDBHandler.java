package com.devoir;

import com.devoir.UserModel;
import java.sql.SQLException;
import javax.servlet.ServletException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Set;

/**
 * @author Corentin Chupin & Bastien Sebire, Universit&eacute; de Caen Basse-Normandie, France.
 * @since January, 2017
 */
public class UserDBHandler {

    /** The unique instance of class HibernateUserDB (null if none). */
    private static HibernateUserDB db;

    /**
     * Returns the instance of HibernateUserDB.
     * @throws SQLException if any problem occurs for accessing the database
     */
    public static HibernateUserDB getDb () throws SQLException, ServletException {
        if (UserDBHandler.db==null) {
            UserDBHandler.initialize();
        }
        return UserDBHandler.db;
    }

    public void destroy () {
        if (this.db!=null) {
            this.db.destroy();
        }
    }

    // Helper methods =====================================================================

    /**
     * Initializes the connection to the database and the instance of SQLHousingDB.
     * For each of these objects, nothing occurs if it is already initialized.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    private static void initialize () throws SQLException, ServletException {
      // déplacer dans le dbhandler
        Configuration configuration = null;
        ServiceRegistry registry = null;
        try {
            configuration = new Configuration().configure();
            registry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        } catch (Throwable e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
        UserDBHandler.db=new HibernateUserDB(configuration.buildSessionFactory(registry));
    }

}
