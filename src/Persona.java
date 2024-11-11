import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Persona {
    public static class TPersona {
        public void crearTabla() throws SQLException{
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Persona ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "apellido VARCHAR(50) NOT NULL, "
                        + "peso SMALLINT, "
                        + "fecha_peso DATE" // Agregar columna de fecha
                        + ")";
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla creada correctamente");


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void crearObjeto() throws SQLException{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su nombre: ");
            String a = scanner.nextLine();
            System.out.print("Ingrese su apellido: ");
            String b = scanner.nextLine();
            System.out.print("Ingrese su peso: ");
            String c = String.valueOf(scanner.nextInt());
            

            System.out.print("Ingrese la fecha\n");
            System.out.print("Día: ");
            String dia = String.valueOf(scanner.nextInt());
            System.out.print("Mes: ");
            String mes = String.valueOf(scanner.nextInt());
            System.out.print("Año: ");
            String anio = String.valueOf(scanner.nextInt());


            // Convierto a fecha en formato yyyy-mm-dd
            String fechaString = dia + " " + mes + " " + anio;
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd MM yy");
            java.sql.Date fechaSQL = null;

            try {
                java.util.Date fecha = formatoEntrada.parse(fechaString);
                fechaSQL = new java.sql.Date(fecha.getTime());
            } catch (ParseException e) {
                System.out.println("Error en el formato de la fecha: " + e.getMessage());
                return;
            }


            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {


                String sql = "INSERT INTO Persona (nombre, apellido, peso, fecha_peso) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, a);
                pstmt.setString(2, b);
                pstmt.setInt(3, parseInt(c));
                // pstmt.setDate(4, fechaSQL);




                int rowCount = pstmt.executeUpdate();
                System.out.println("Filas afectadas: " + rowCount);
                System.out.println("objeto creado correctamente");


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }


    }
}