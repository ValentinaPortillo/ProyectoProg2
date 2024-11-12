import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio {
    public static class TEjercicio {
        
        // Método para crear la tabla Ejercicio
        public void crearTabla() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                 
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Ejercicio ("
                        + "id_ejercicio INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "calorias_quemadas INT NOT NULL, "
                        + "duracion INT, " // duración en minutos
                        + "tipo VARCHAR(50)"
                        + ")";
                        
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Ejercicio' creada correctamente");
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        // Método para insertar un registro en la tabla Ejercicio
        public void crearObjeto() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre del ejercicio: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese las calorías quemadas: ");
            int caloriasQuemadas = scanner.nextInt();
            System.out.print("Ingrese la duración en minutos: ");
            int duracion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Ingrese el tipo de ejercicio (ej: cardio, fuerza): ");
            String tipo = scanner.nextLine();

            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Ejercicio (nombre, calorias_quemadas, duracion, tipo) VALUES (?, ?, ?, ?)")) {
                 
                pstmt.setString(1, nombre);
                pstmt.setInt(2, caloriasQuemadas);
                pstmt.setInt(3, duracion);
                pstmt.setString(4, tipo);

                int rowCount = pstmt.executeUpdate();
                System.out.println("Filas afectadas: " + rowCount);
                System.out.println("Registro de ejercicio creado correctamente");
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
