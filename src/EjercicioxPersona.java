import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EjercicioxPersona {

    public static class TEjercicioxPer {

        // Método para crear la tabla EjercicioxPersona
        public void crearTablaEjercxPer() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {

                String createTableSQL = "CREATE TABLE IF NOT EXISTS EjercicioxPersona ("
                        + "PRIMARY KEY (ejercicio_id, persona_id), "
                        + "ejercicio_id INT, "
                        + "persona_id INT, "
                        + "FOREIGN KEY (ejercicio_id) REFERENCES Ejercicio(id), "
                        + "FOREIGN KEY (persona_id) REFERENCES Persona(id) "
                        + ")";

                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'EjercicioxPersona' creada correctamente");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Método para insertar un ejercicio realizado por una persona
        public void insertarEjercicioPersona(int ejercicioId, int personaId) {
            String sql = "INSERT INTO EjercicioxPersona (ejercicio_id, persona_id) VALUES (?, ?)";

            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, ejercicioId);
                pstmt.setInt(2, personaId);

                int rowCount = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + rowCount);

            } catch (SQLException e) {
                System.out.println("Error al insertar en EjercicioxPersona: " + e.getMessage());
            }
        }

        // Método para obtener los ejercicios realizados por una persona
        public List<Ejercicio> obtenerEjercicioPorPersona(int personaId) {
            List<Ejercicio> ejercicios = new ArrayList<>();

            String sql = "SELECT j.id_ejercicio, j.nombre, j.calorias_quemadas, j.duracion, j.tipo "
                       + "FROM EjercicioxPersona jp "
                       + "JOIN Ejercicio j ON jp.ejercicio_id = j.id_ejercicio "
                       + "WHERE jp.persona_id = ?";

            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, personaId);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    int caloriasQuemadas = rs.getInt("calorias_quemadas");
                    String duracion = rs.getString("duracion");
                    String tipo = rs.getString("tipo");

                    Ejercicio ejercicio = new Ejercicio(nombre, caloriasQuemadas, duracion, tipo);
                    ejercicios.add(ejercicio);
                }

            } catch (SQLException e) {
                System.out.println("Error al obtener los ejercicios: " + e.getMessage());
            }

            return ejercicios;
        }

        // Método para calcular el total de calorías quemadas por una persona
        public int calcularCaloriasTotales(int personaId) {
            int totalCalorias = 0;
            List<Ejercicio> ejercicios = obtenerEjercicioPorPersona(personaId);

            for (Ejercicio ejercicio : ejercicios) {
                totalCalorias += ejercicio.getCaloriasQuemadas();
            }

            return totalCalorias;
        }
    }
}
