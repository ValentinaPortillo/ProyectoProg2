import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrarTablas {

    public static void eliminarTodasLasTablas(Connection conn) throws SQLException {
        Statement stmt = null;
        PreparedStatement dropStmt = null;

        try {
            // Desactivar las claves foráneas
            stmt = conn.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

            // Obtener todos los nombres de tablas en la base de datos actual
            String getTablesSQL = "SELECT table_name FROM information_schema.tables WHERE table_schema = (SELECT DATABASE())";
            ResultSet rs = stmt.executeQuery(getTablesSQL);
            StringBuilder tables = new StringBuilder();

            while (rs.next()) {
                tables.append("`").append(rs.getString(1)).append("`, ");
            }

            // Verificar que existan tablas para eliminar
            if (tables.length() > 0) {
                // Remover la última coma y espacio
                tables.setLength(tables.length() - 2);

                // Construir y ejecutar la sentencia DROP TABLE
                String dropTablesSQL = "DROP TABLE IF EXISTS " + tables.toString();
                dropStmt = conn.prepareStatement(dropTablesSQL);
                dropStmt.executeUpdate();
            } else {
                System.out.println("No hay tablas para eliminar.");
            }

        } finally {
            // Habilitar las claves foráneas nuevamente
            if (stmt != null) stmt.execute("SET FOREIGN_KEY_CHECKS = 1");

            // Cerrar los recursos
            if (stmt != null) stmt.close();
            if (dropStmt != null) dropStmt.close();
        }
    }
}