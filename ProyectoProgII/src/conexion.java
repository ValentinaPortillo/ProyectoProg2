import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class conexion {
        //private static String url = "jdbc:mysql://localhost:3306/proyectogym" ;
       // private static String user = "root" ;
        //private static String password = "khalilkan" ;

        private static String url;
        private static String user;
        private static String password;

        static {
            // Cargar las credenciales desde el archivo
            cargarCredenciales();
        }

        private static void cargarCredenciales() {
            try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/ValentinaP/Desktop/ProgII/ProyectoProgII/src/conexionconf.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("url=")) {
                        url = linea.substring(4);
                    } else if (linea.startsWith("user=")) {
                        user = linea.substring(5);
                    } else if (linea.startsWith("password=")) {
                        password = linea.substring(9);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Connection Obtenerconexion() throws SQLException {
            if (url == null || user == null || password == null) {
                throw new SQLException("No se han cargado las credenciales correctamente.");
            }
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
