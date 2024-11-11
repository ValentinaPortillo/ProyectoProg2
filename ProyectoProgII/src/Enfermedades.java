import java.sql.*;


public class Enfermedades {
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
                System.out.println("Tabla enfermedades creada correctamente");

                String sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Diabetes");
                pstmt.setBoolean(2,false);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Covid-19");
                pstmt.setBoolean(2,true);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "EDA");
                pstmt.setBoolean(2, false);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Denge");
                pstmt.setBoolean(2,true);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "VIH");
                pstmt.setBoolean(2, false);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "SOPQ");
                pstmt.setBoolean(2, false);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Cancer");
                pstmt.setBoolean(2,true);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Asma");
                pstmt.setBoolean(2,false);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Herpes");
                pstmt.setBoolean(2,false);
                pstmt.executeUpdate();

                sql = "INSERT INTO Enfermedades (nombre_enfermedad, tiene_cura) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Clamidia");
                pstmt.setBoolean(2, true);
                pstmt.executeUpdate();


                int rowCount = pstmt.executeUpdate();
                System.out.println("Filas afectadas: " + rowCount);
                System.out.println("objetos Enferm creado correctamente");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
