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
                        + "id_ejercicio INT NOT NULL, "
                        + "persona_id INT NOT NULL, "
                        + "duracion INT NOT NULL, "
                        + "PRIMARY KEY (id_ejercicio, persona_id), "
                        + "FOREIGN KEY (id_ejercicio) REFERENCES Ejercicios(id_ejercicio), "
                        + "FOREIGN KEY (persona_id) REFERENCES Persona(id)"
                        + ")";

                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'EjercicioxPersona' creada correctamente");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    // Método para insertar un ejercicio realizado por una persona
    public static void insertarEjercicioPersona(int ejercicioId, int personaId, int duracion) {
        String sql = "INSERT INTO EjercicioxPersona (id_ejercicio, persona_id, duracion) VALUES (?, ?, ?)";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ejercicioId);
            pstmt.setInt(2, personaId);
            pstmt.setInt(3, duracion);

            int rowCount = pstmt.executeUpdate();
            System.out.println("Filas insertadas: " + rowCount);

        } catch (SQLException e) {
            System.out.println("Error al insertar en EjercicioxPersona: " + e.getMessage());
        }
    }

    // Método para obtener los ejercicios realizados por una persona
    public static List<Ejercicio> obtenerEjercicioPorPersona(int personaId) {
        List<Ejercicio> ejercicios = new ArrayList<>();

        String sql = "SELECT j.id_ejercicio, j.nombre, j.calorias_quemadas, j.tipo, jp.duracion "
                + "FROM EjercicioxPersona jp "
                + "JOIN Ejercicios j ON jp.id_ejercicio = j.id_ejercicio "
                + "WHERE jp.persona_id = ?";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_ejercicio");
                String nombre = rs.getString("nombre");
                int caloriasQuemadas = rs.getInt("calorias_quemadas");
                String tipo = rs.getString("tipo");
                int duracion = rs.getInt("duracion");

                Ejercicio ejercicio = new Ejercicio(id, nombre, caloriasQuemadas, tipo, duracion);
                ejercicios.add(ejercicio);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los ejercicios: " + e.getMessage());
        }

        return ejercicios;
    }

    // Método para calcular el total de calorías quemadas por una persona
    public static int calcularCaloriasTotales(int personaId) {
        int totalCalorias = 0;
        List<Ejercicio> ejercicios = obtenerEjercicioPorPersona(personaId);

        for (Ejercicio ejercicio : ejercicios) {
            totalCalorias += ejercicio.getCaloriasQuemadas() * ejercicio.getDuracion();
        }

        return totalCalorias;
    }
}
