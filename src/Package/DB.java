package Package;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://library.c4flwkue0p9r.ap-south-1.rds.amazonaws.com:3306/Library?autoReconnect=true&useSSL=false",
                    "Smitesh98","Smitesh98");
        }catch(Exception e){System.out.println(e);}
        return con;
    }

}
