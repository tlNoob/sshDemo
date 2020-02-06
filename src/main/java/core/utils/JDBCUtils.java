package core.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtils {
    private static  final  String JDBC_DRIVER="com.mysql.jdbc.Driver";
    private static  final  String JDBC_URL="jdbc:mysql://localhost:3306/tldemo";

    private static  final  String USER="root";
    private static  final  String PASSWORD="root";

    private static   Connection  connection=null;
    public  static Connection getConnection()  {
        try{
            if(connection==null){
                Class.forName(JDBC_DRIVER);
                connection=DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
                return connection;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static ResultSet query(String sql,Object... args){
        try {
            PreparedStatement pstm=getConnection().prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                pstm.setObject(i+1,args[i]);
            }
            return pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    public static void closeConn(){
        try{
            if(connection!=null && !connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
