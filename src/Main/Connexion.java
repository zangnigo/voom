
package Main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;



/**
 *
 * @author hp
 */
public class Connexion {
    
    private static String serveurname = "localhost";
    private static String username = "root";
    private static String dbname = "vroom";
    private static Integer portnumber = 3306;
    private static String password = "";
    
    public static Connection getConnection()
    {
        Connection cnx = null;
        
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(serveurname);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
    
        try{
             cnx = datasource.getConnection();
        } catch(SQLException ex){
          //  Logger.getLogger(" Get Connection -> " + Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        return cnx;
    }
    
}