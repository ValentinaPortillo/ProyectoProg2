import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio {
    private int id;
    private String nombre;
    private int caloriasQuemadas;
    private String tipo;

    public Ejercicio(int id, String nombre, int caloriasQuemadas, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.caloriasQuemadas = caloriasQuemadas;
        this.tipo = tipo;
    }

    // Getters para acceder a los atributos de Ejercicio
    public String getNombre() {
        return nombre;
    }

    public int getCaloriasQuemadas() {
        return caloriasQuemadas;
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

                String createTableSQL = "CREATE TABLE IF NOT EXISTS Ejercicios ("
                        + "id_ejercicio INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "calorias_quemadas INT NOT NULL, "
                        + "tipo VARCHAR(50)"
                        + ")";

                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Ejercicio' creada correctamente");

                InsertarEjercicios();

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

        public void InsertarEjercicios() throws SQLException {
            try (Connection conn = conexion.Obtenerconexion()) {

                String checkSql = "SELECT COUNT(*) FROM ejercicios";

                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                     ResultSet rs = checkStmt.executeQuery()) {

                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println("La tabla 'Ejercicios' ya contiene datos. No se realizaron inserciones.");
                        return;
                    }
                }

                String insertSql = "INSERT INTO Ejercicios (nombre, calorias_quemadas, tipo) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    pstmt.setString(1, "Correr");
                    pstmt.setInt(2, 300);
                    pstmt.setString(3, "Cardio");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Sentadillas");
                    pstmt.setInt(2, 150);
                    pstmt.setString(3, "Fuerza");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Zumba");
                    pstmt.setInt(2, 200);
                    pstmt.setString(3, "Aeróbico");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Natación");
                    pstmt.setInt(2, 400);
                    pstmt.setString(3, "Cardio");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Plancha");
                    pstmt.setInt(2, 100);
                    pstmt.setString(3, "Fuerza");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Bicicleta");
                    pstmt.setInt(2, 250);
                    pstmt.setString(3, "Cardio");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Pesas");
                    pstmt.setInt(2, 200);
                    pstmt.setString(3, "Fuerza");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Aeróbicos");
                    pstmt.setInt(2, 180);
                    pstmt.setString(3, "Aeróbico");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Yoga");
                    pstmt.setInt(2, 70);
                    pstmt.setString(3, "Flexibilidad");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Burpees");
                    pstmt.setInt(2, 300);
                    pstmt.setString(3, "Cardio");
                    pstmt.executeUpdate();

                    System.out.println("Inserciones realizadas correctamente en la tabla 'Ejercicios'.");
                }

            } catch (SQLException e) {
                System.out.println("Error en insertarEjercicios: " + e.getMessage());
                throw e; // Repropaga la excepción si es necesario
            }
        }


    }

    public static List<Ejercicio> obtenerEjercicios() {
        List<Ejercicio> listaEjercicios = new ArrayList<>();
        String sql = "SELECT * FROM Ejercicios";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_ejercicio");
                String nombre = rs.getString("nombre");
                int caloriasQuemadas = rs.getInt("calorias_quemadas");
                String tipo = rs.getString("tipo");

                Ejercicio ejercicio = new Ejercicio(id, nombre, caloriasQuemadas, tipo);
                listaEjercicios.add(ejercicio);
            }

        } catch (SQLException e) {
            System.out.println("Error en obtenerEjercicios: " + e.getMessage());
        }

        return listaEjercicios;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + caloriasQuemadas + " - " + tipo;
    }
}
