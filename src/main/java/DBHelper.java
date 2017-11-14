/**
 * Created by pujan on 9/30/17.
 */

//Test Upload by Pujan1
// Testttttt

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DBHelper {
    private static SQLServerDataSource ds;
    private static DBHelper INSTANCE = new DBHelper();
    private DBHelper(){


    }

    public static DBHelper getINSTANCE() {

        return INSTANCE;
    }


    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    public void init() throws SQLException{
        ds = new SQLServerDataSource();


        ds.setURL("jdbc:sqlserver://10.211.55.3;databaseName=test2");
        ds.setDatabaseName("test2");
        ds.setUser("pujan");
       ds.setPassword("548817");
/*
        ds.setURL("jdbc:sqlserver://172.26.54.39:1433;databaseName=Semifinal2");
        ds.setDatabaseName("Semifinal2");
        ds.setUser("ryan");
        ds.setPassword("ryan");
*/
        ds.getConnection();

    }


    public void close() throws SQLException{
        if(ds !=null ){
        ds.getConnection().close();
        }
    }


}
