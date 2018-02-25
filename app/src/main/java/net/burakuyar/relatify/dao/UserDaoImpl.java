package net.burakuyar.relatify.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.burakuyar.relatify.AppMain;
import net.burakuyar.relatify.model.Person;
import net.burakuyar.relatify.model.Relative;
import net.burakuyar.relatify.model.User;
import org.apache.commons.logging.Log;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by burak on 2/25/2018.
 */
@Repository
public class UserDaoImpl implements UserDao{
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private DataSource dataSource;

    static String PERSON_FORMAT =
            "INSERT INTO person VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') ON CONFLICT (hash_id) DO UPDATE SET live_status = excluded.live_status";
    static String USER_FORMAT =
            "INSERT INTO owner VALUES (%d, '%s') ON CONFLICT DO NOTHING";
    static String RELATIVE_FORMAT =
            "INSERT INTO user_relative VALUES (%d, %d, '%s') ON CONFLICT DO NOTHING";
    static String EMAIL_FORMAT =
            "INSERT INTO user_email VALUES (%d, '%s') ON CONFLICT (user_id, email) DO UPDATE SET last_send_date = NOW()";
    @Override
    public int save(User user) {
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(insertPersonSQL(user.getUser()));
            stmt.executeUpdate(insertUserSQL(user));
            stmt.executeUpdate(insertEmailSQL(user));
            for (Relative relative : user.getRelatives()){
                stmt.executeUpdate(insertPersonSQL(relative.getPerson()));
                stmt.executeUpdate(insertRelativeSQL(user, relative));
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return 0;
        }
        return user.hashCode();
    }

    private String insertPersonSQL(Person p){
        return String.format(PERSON_FORMAT, p.hashCode(), p.getGender(), p.getName(),
                p.getSurname(), p.getFatherName(), p.getMotherName(), p.getBirthPlaceAndDate(),
                p.getAddress(), p.getSerieNo(), p.getMaritalStatus(), p.getLiveStatus());
    }

    private String insertUserSQL(User user){
        return String.format(USER_FORMAT, user.hashCode(), user.getPdfJSON());
    }

    private String insertEmailSQL(User user){
        return String.format(EMAIL_FORMAT, user.hashCode(), user.getEmail());
    }

    private String insertRelativeSQL(User user, Relative relative){
        return String.format(RELATIVE_FORMAT, user.hashCode(), relative.getPerson().hashCode(), relative.getRelation());
    }
}
