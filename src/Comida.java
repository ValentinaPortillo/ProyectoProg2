import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Comida {
    public Comida(int id, String nombre, int calorias, Double proteinas, Double carbohidratos, Double grasas) {
        // Inicialización de atributos aquí, si es necesario
    }

    public static class TComida {
        // Método para crear la tabla Comida (general)
        public void crearTablaComida() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                 
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Comida ("
                        + "id_comida INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "calorias INT NOT NULL, "
                        + "proteinas FLOAT, "
                        + "carbohidratos FLOAT, "
                        + "grasas FLOAT"
                        + ")";
                        
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Comida' creada correctamente");
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
    }

    
}