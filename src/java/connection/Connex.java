package connection;
import java.sql.*;

public class Connex
{
    String DB_base = "jdbc:oracle:thin:@localhost:1521:orcl";
    Connection connection = null;

    // Encapsulation
    public void setConnection(Connection connection){this.connection = connection;}
    public Connection getConnection(){return this.connection;}

    // Constructeur
    public Connex(){}

    public Connection DBconnect(){
        try{
            // user Oracle and mdp
            String username = "star";
            String mdp = "star";

            //etape 1: charger la classe de driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            //etape 2: creer l'objet de connexion
            setConnection(DriverManager.getConnection(DB_base,username,mdp));
            getConnection().setAutoCommit(false);
        }
        catch(Exception e){ 
            System.out.println(e); 
        }
        return this.connection;
    }

     public void coupeConnection(){
        try{
           this.connection.close();
        }
        catch(Exception e){ 
            System.out.println(e); 
        }
    }

}