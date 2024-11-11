import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
                    System.out.println("Tabla EnfermedadesxPersona creada correctamente");


                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
}
