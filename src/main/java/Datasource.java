import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.SQLException;

public class Datasource {
    private static SQLServerDataSource ds;
    private static  Datasource INSTANCE = new Datasource();
    private Datasource(){

    }

    public static Datasource getINSTANCE(){
        return  INSTANCE;
    }

    public  static SQLServerDataSource datasource() throws SQLException{
        ds = new SQLServerDataSource();
/*
       ds.setURL("jdbc:sqlserver://10.211.55.3:1433;databaseName=test2");
        ds.setDatabaseName("test2");
        ds.setUser("pujan");
        ds.setPassword("548817");
*/
        ds.setURL("jdbc:sqlserver://172.26.54.39:1433;databaseName=RealFinalFInal");
        ds.setDatabaseName("RealFinalFinal");
        ds.setUser("ryan");
        ds.setPassword("ryan");


        return ds;
    }


}
