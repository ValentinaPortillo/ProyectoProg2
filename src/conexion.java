import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class conexion {

        private static String url;
        private static String user;
        private static String password;

        static {
            // Cargar las credenciales desde el archivo
            cargarCredenciales();
        }

        private static void cargarCredenciales() {
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\anon\\Desktop\\taty4\\ProyectoProgII\\src\\conexionconf.txt"))) {
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
}
