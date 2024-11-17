import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ComidaxPersona {

    public static class TComxPer {
        public void crearTablaComxPer() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS ComidaxPersona ("
                        + "comida_id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "persona_id INT, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "calorias INT NOT NULL, "
                        + "proteinas FLOAT, "
                        + "carbohidratos FLOAT, "
                        + "grasas FLOAT, "
                        + "fecha_consumo DATE DEFAULT (CURDATE()), "
                        + "FOREIGN KEY (persona_id) REFERENCES Persona(id)"
                        + ")";
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'ComidaxPersona' creada correctamente");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

       
        // Método para que un usuario registre una comida específica
        public void insertarComidaPersona(int personaId, String nombre, int calorias, float proteinas, float carbohidratos, float grasas) {
            String sql = "INSERT INTO ComidaxPersona (persona_id, nombre, calorias, proteinas, carbohidratos, grasas) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, personaId);
                pstmt.setString(2, nombre);
                pstmt.setInt(3, calorias);
                pstmt.setFloat(4, proteinas);
                pstmt.setFloat(5, carbohidratos);
                pstmt.setFloat(6, grasas);

                int rowCount = pstmt.executeUpdate();
                System.out.println("Comida registrada para el usuario " + personaId + ". Filas insertadas: " + rowCount);

            } catch (SQLException e) {
                System.out.println("Error al insertar comida para usuario: " + e.getMessage());
            }
        }

        // Nuevo método para solicitar datos al usuario y registrar la comida
        public void registrarComidaDesdeConsola() {
            Scanner scanner = new Scanner(System.in);
        
            // Solicitar el username
            System.out.print("Ingrese el username del usuario: ");
            String username = scanner.nextLine();
        
            // Buscar al usuario por username
            Persona persona = Persona.buscarPorUsername(username);
        
            if (persona != null) {
                // El usuario fue encontrado, ahora podemos pedir la comida
                System.out.print("Ingrese el nombre de la comida: ");
                String nombre = scanner.nextLine();
        
                System.out.print("Ingrese las calorías: ");
                int calorias = scanner.nextInt();
        
                System.out.print("Ingrese la cantidad de proteínas: ");
                float proteinas = scanner.nextFloat();
        
                System.out.print("Ingrese la cantidad de carbohidratos: ");
                float carbohidratos = scanner.nextFloat();
        
                System.out.print("Ingrese la cantidad de grasas: ");
                float grasas = scanner.nextFloat();
        
                // Llamar al método que inserta los datos en la base
                insertarComidaPersona(persona.getId(), nombre, calorias, proteinas, carbohidratos, grasas);
            } else {
                System.out.println("No se encontró un usuario con el username: " + username);
            }
        }
        


         // Método para calcular el total de calorías consumidas por un usuario específico
     public int calcularTotalCaloriasConsumidasPorUsuario(int personaId) {
        int totalCalorias = 0;
        String sql = "SELECT SUM(calorias) AS total_calorias FROM ComidaxPersona WHERE persona_id = ?";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCalorias = rs.getInt("total_calorias");
            }

        } catch (SQLException e) {
            System.out.println("Error al calcular el total de calorías: " + e.getMessage());
        }

        return totalCalorias;
      }
    }

    public static List<Comidas> obtenerComidaPorPersona(int personaId) {
        List<Comidas> comidas = new ArrayList<>();
        String sql = "SELECT comida_id, nombre, calorias, proteinas, carbohidratos, grasas, fecha_consumo FROM ComidaxPersona WHERE persona_id = ?";
        
        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("comida_id");
                String nombre = rs.getString("nombre");
                int calorias = rs.getInt("calorias");
                double proteinas = rs.getDouble("proteinas");
                double carbohidratos = rs.getDouble("carbohidratos");
                double grasas = rs.getDouble("grasas");
                

                // Crear una instancia de Comida.Comidas y añadirla a la lista
                Comidas comida = new Comidas(id, nombre, calorias, proteinas, carbohidratos, grasas);
                comidas.add(comida);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las comidas del usuario: " + e.getMessage());
        }

        return comidas;
    }

    


   
}

        
     
