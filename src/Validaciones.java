import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Validaciones {

    public static String pedirDato(Scanner scanner, String texto, String regex, String mensajeError) {
        while (true) {
            System.out.print(texto);
            String input = scanner.nextLine();
            if (input.matches(regex)) {
                return input;
            }
            System.out.println(mensajeError);
        }
    }

    public static String validarUsername(Scanner scanner, String texto, String regex, String mensajeError) throws SQLException {
        while (true) {
            String username = pedirDato(
                    scanner,
                    texto,
                    regex,
                    mensajeError
            );

            // Verifica si el username ya existe en la base de datos
            String sql = "SELECT COUNT(*) FROM persona WHERE username = ?";
            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println("Error: El username '" + username + "' ya existe en la base de datos. Intente con otro.");
                    } else {
                        return username;
                    }
                }
            }
        }
    }
}