import java.sql.*;
import java.util.Scanner;

public class Ejercicio {
    private String nombre;
    private int caloriasQuemadas;
    private String duracion;
    private String tipo;

    public Ejercicio(String nombre, int caloriasQuemadas, String duracion, String tipo) {
        this.nombre = nombre;
        this.caloriasQuemadas = caloriasQuemadas;
        this.duracion = duracion;
        this.tipo = tipo;
    }

    // Getters para acceder a los atributos de Ejercicio
    public String getNombre() {
        return nombre;
    }

    public int getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getTipo() {
        return tipo;
    }

    // Clase interna para crear la tabla y manejar la inserción de ejercicios
    public static class TEjercicio {

        // Método para crear la tabla Ejercicio
        public void crearTabla() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {

                String createTableSQL = "CREATE TABLE IF NOT EXISTS Ejercicio ("
                        + "id_ejercicio INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "calorias_quemadas INT NOT NULL, "
                        + "tipo VARCHAR(50)"
                        + ")";

                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Ejercicio' creada correctamente");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Método para insertar un ejercicio
//        public void crearObjeto() throws SQLException {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Ingrese el nombre del ejercicio: ");
//            String nombre = scanner.nextLine();
//            System.out.print("Ingrese las calorías quemadas: ");
//            int caloriasQuemadas = scanner.nextInt();
//            System.out.print("Ingrese la duración en minutos: ");
//            int duracion = scanner.nextInt();
//            scanner.nextLine(); // Consumir el salto de línea
//            System.out.print("Ingrese el tipo de ejercicio (ej: cardio, fuerza): ");
//            String tipo = scanner.nextLine();
//
//            // Crear objeto Ejercicio
//            Ejercicio ejercicio = new Ejercicio(nombre, caloriasQuemadas, String.valueOf(duracion), tipo);
//
//            // Insertar ejercicio en la base de datos
//            try (Connection conn = conexion.Obtenerconexion();
//                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Ejercicio (nombre, calorias_quemadas, duracion, tipo) VALUES (?, ?, ?, ?)")) {
//
//                pstmt.setString(1, ejercicio.getNombre());
//                pstmt.setInt(2, ejercicio.getCaloriasQuemadas());
//                pstmt.setInt(3, Integer.parseInt(ejercicio.getDuracion()));
//                pstmt.setString(4, ejercicio.getTipo());
//
//                int rowCount = pstmt.executeUpdate();
//                System.out.println("Filas afectadas: " + rowCount);
//                System.out.println("Registro de ejercicio creado correctamente");
//
//            } catch (SQLException e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//        }
    }
}
