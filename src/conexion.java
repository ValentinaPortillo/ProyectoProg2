import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
        private static String url = "jdbc:mysql://localhost:3306/proyectogym" ;
        private static String user = "root" ;
        private static String password = "12345678" ;

        public static Connection Obtenerconexion() throws SQLException {
            return DriverManager.getConnection(url, user, password);

        }

}
