import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnfermedadesxPersona {
    public static class TEnfxPer {
        public void crearTablaEnfefxPer() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS EnfermedadesxPersona ("
                        + "PRIMARY KEY (enfermedades_id, persona_id), "
                        + "enfermedades_id INT, "
                        + "persona_id INT, "
                        + "FOREIGN KEY (enfermedades_id) REFERENCES Enfermedades(id), "
                        + "FOREIGN KEY (persona_id) REFERENCES Persona(id) "
                        + ")";
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'EnfermedadesxPersona' creada correctamente");


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void insertarEnfermedadPersona(int enfermedadesId, int personaId) {
        String sql = "INSERT INTO EnfermedadesxPersona (enfermedades_id, persona_id) VALUES (?, ?)";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Configurar los parámetros de la consulta
            pstmt.setInt(1, enfermedadesId);
            pstmt.setInt(2, personaId);

            // Ejecutar el INSERT
            int rowCount = pstmt.executeUpdate();
            System.out.println("Filas insertadas: " + rowCount);

        } catch (SQLException e) {
            System.out.println("Error al insertar en EnfermedadesxPersona: " + e.getMessage());
        }
    }

    public static List<Enfermedades> obtenerEnfermedadesPorPersona(int personaId) {
        List<Enfermedades> enfermedades = new ArrayList<>();

        // Consulta SQL con JOIN entre las tablas
        String sql = "SELECT e.id, e.nombre_enfermedad, e.tiene_cura " +
                "FROM EnfermedadesxPersona ep " +
                "JOIN Enfermedades e ON ep.enfermedades_id = e.id " +
                "JOIN Persona p ON ep.persona_id = p.id " +
                "WHERE p.id = ?";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Configurar el parámetro de la consulta
            pstmt.setInt(1, personaId);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Procesar los resultados y agregar a la lista
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreEnfermedad = rs.getString("nombre_enfermedad");
                boolean tieneCura = rs.getBoolean("tiene_cura");

                // Crear objeto Enfermedad y agregarlo a la lista
                Enfermedades enfermedad = new Enfermedades(id, nombreEnfermedad, tieneCura);
                enfermedades.add(enfermedad);
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
        }

        return enfermedades;
    }
}