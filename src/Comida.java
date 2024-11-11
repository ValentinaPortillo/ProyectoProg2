import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Comida {
    public static class TComida {
        
        // Método para crear la tabla Comida
        public void crearTablaComida() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                 
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Comida ("
                        + "id_comida INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "calorias INT NOT NULL, "
                        + "proteinas FLOAT, "
                        + "carbohidratos FLOAT, "
                        + "grasas FLOAT, "
                        + "fecha_consumo DATE DEFAULT CURDATE()"
                        + ")";
                        
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Comida' creada correctamente");
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        // Método para insertar un registro en la tabla Comida
        public void crearObjetoComida() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre de la comida: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese las calorías: ");
            int calorias = scanner.nextInt();
            System.out.print("Ingrese las proteínas: ");
            float proteinas = scanner.nextFloat();
            System.out.print("Ingrese los carbohidratos: ");
            float carbohidratos = scanner.nextFloat();
            System.out.print("Ingrese las grasas: ");
            float grasas = scanner.nextFloat();

            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Comida (nombre, calorias, proteinas, carbohidratos, grasas) VALUES (?, ?, ?, ?, ?)")) {
                 
                pstmt.setString(1, nombre);
                pstmt.setInt(2, calorias);
                pstmt.setFloat(3, proteinas);
                pstmt.setFloat(4, carbohidratos);
                pstmt.setFloat(5, grasas);

                int rowCount = pstmt.executeUpdate();
                System.out.println("Filas afectadas: " + rowCount);
                System.out.println("Registro de comida creado correctamente");
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
