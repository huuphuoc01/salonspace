//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
//import com.microsoft.sqlserver.jdbc.SQLServerException;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class test {
//    public static void main(String[] arg) {
//        SQLServerDataSource ds = new SQLServerDataSource();
//        ds.setUser("sa");
//        ds.setPassword("123456");
//        ds.setServerName("localhost");
//        ds.setPortNumber(1433);
//        ds.setDatabaseName("AB");
//        ds.setEncrypt("false");
//        try (Connection conn = ds.getConnection()) {
//            System.out.println("success");
//            System.out.println(conn.getMetaData());
//        } catch (SQLServerException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
