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

    public static int validarID(Scanner scanner, String texto) throws SQLException {
        while (true) {
            System.out.print(texto);
            String input = scanner.nextLine();

            // Verifica si el input es un número válido
            if (!input.matches("\\d+")) { // Acepta solo números positivos
                System.out.println("Error: Ingrese un ID válido (solo números positivos).");
                continue;
            }

            int id = Integer.parseInt(input);

            // Consulta a la base de datos para verificar si el ID existe
            String sql = "SELECT COUNT(*) FROM ejercicios WHERE id_ejercicio = ?";
            try (Connection conn = conexion.Obtenerconexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return id; // ID válido encontrado, lo retornamos
                    } else {
                        System.out.println("Error: El ID " + id + " no existe en la tabla 'Ejercicios'. Intente de nuevo.");
                    }
                }
            }
        }
    }
}