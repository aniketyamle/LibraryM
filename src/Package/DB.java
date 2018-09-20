package Package;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://mysql.stackcp.com:51414/library-36395493","library-36395493","24p9m3r43i");
        }catch(Exception e){System.out.println(e);}
        return con;
    }

}
