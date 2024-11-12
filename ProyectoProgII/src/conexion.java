import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
        private static String url = "jdbc:mysql://localhost:3306/proyectogym" ;
        private static String user = "root" ;
        private static String password = "khalilkan" ;

        public static Connection Obtenerconexion() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }

        // Método para borrar todas las tablas
        public static void borrarTodasLasTablas() throws SQLException {
            try (Connection conn = Obtenerconexion()) {
                BorrarTablas.eliminarTodasLasTablas(conn);  // Llama al método de la clase BorrarTablas
                //System.out.println("Todas las tablas fueron eliminadas.");
            } catch (SQLException e) {
                System.out.println("Error al borrar las tablas: " + e.getMessage());
                throw e; // Vuelve a lanzar la excepción para manejarla si es necesario
            }
        }

}
