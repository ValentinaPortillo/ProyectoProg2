import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Enfermedades {
    private int id;
    private String nombreEnfermedad;
    private boolean tieneCura;

    // Constructor
    public Enfermedades(int id, String nombreEnfermedad, boolean tieneCura) {
        this.id = id;
        this.nombreEnfermedad = nombreEnfermedad;
        this.tieneCura = tieneCura;
    }

    // Getters
    public int getId() { return id; }
    public String getNombreEnfermedad() { return nombreEnfermedad; }
    public boolean isTieneCura() { return tieneCura; }

    public static class TEnfermedad {
        public void crearTablaEnfermedades() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Enfermedades ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre_enfermedad VARCHAR(50) NOT NULL, "
                        + "tiene_cura BOOLEAN"
                        + ")";
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Enfermedades' creada correctamente");

                InsertarEnfermedades();

            } catch (SQLException e) {
                System.out.println("Error en crearTablaEnfermedades: " + e.getMessage());
            }
        }

        public void InsertarEnfermedades() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion()) {

                String checkSql = "SELECT COUNT(*) FROM enfermedades";

                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                     ResultSet rs = checkStmt.executeQuery()) {

                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println("La tabla 'Enfermedades' ya contiene datos. No se realizaron inserciones.");
                        return;
                    }
                }

                String insertSql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    pstmt.setString(1, "Diabetes");
                    pstmt.setBoolean(2, false);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Covid-19");
                    pstmt.setBoolean(2, true);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "EDA");
                    pstmt.setBoolean(2, false);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Dengue");
                    pstmt.setBoolean(2, true);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "VIH");
                    pstmt.setBoolean(2, false);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "SOPQ");
                    pstmt.setBoolean(2, false);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Cancer");
                    pstmt.setBoolean(2, true);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Asma");
                    pstmt.setBoolean(2, false);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Herpes");
                    pstmt.setBoolean(2, false);
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Clamidia");
                    pstmt.setBoolean(2, true);
                    pstmt.executeUpdate();

                    System.out.println("Inserciones realizadas correctamente.");
                }
            } catch (SQLException e) {
                System.out.println("Error en InsertarEnfermedades: " + e.getMessage());
                throw e; // Repropaga la excepción si es necesario
            }
        }
    }

    public static List<Enfermedades> obtenerTodasLasEnfermedades() {
        List<Enfermedades> listaEnfermedades = new ArrayList<>();
        String sql = "SELECT * FROM Enfermedades";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreEnfermedad = rs.getString("nombre_enfermedad");
                boolean tieneCura = rs.getBoolean("tiene_cura");

                Enfermedades enfermedad = new Enfermedades(id, nombreEnfermedad, tieneCura);
                listaEnfermedades.add(enfermedad);
            }

        } catch (SQLException e) {
            System.out.println("Error en obtenerTodasLasEnfermedades: " + e.getMessage());
        }

        return listaEnfermedades;
    }

    @Override
    public String toString() {
        return id + " - " + nombreEnfermedad + " - " + (tieneCura ? "Tiene cura" : "No tiene cura");
    }
}