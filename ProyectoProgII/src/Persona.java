import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Persona {
    private int id;
    private String username;
    private String nombre;
    private String apellido;
    private int peso;
    private int altura;
    private java.sql.Date fechaPeso;

    //Constructor
    public Persona(int id, String username, String nombre, String apellido, int peso, int altura, java.sql.Date fechaPeso) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.peso = peso;
        this.altura = altura;
        this.fechaPeso = fechaPeso;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getPeso() { return peso; }
    public int getAltura() { return altura; }
    public java.sql.Date getFechaPeso() { return fechaPeso; }


    public static class TPersona {
        public void crearTabla() throws SQLException{
            try (Connection conn = conexion.Obtenerconexion();
                 Statement stnt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Persona ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "nombre VARCHAR(50) NOT NULL, "
                        + "apellido VARCHAR(50) NOT NULL, "
                        + "username VARCHAR(50) NOT NULL UNIQUE, "
                        + "peso SMALLINT, "
                        + "altura SMALLINT, "
                        + "fecha_peso DATE" // Agregar columna de fecha
                        + ")";
                stnt.executeUpdate(createTableSQL);
                System.out.println("Tabla 'Persona' creada correctamente");


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void crearObjeto(Scanner scanner) throws SQLException{
            //Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su nombre: ");
            String a = scanner.nextLine();
            System.out.print("Ingrese su apellido: ");
            String b = scanner.nextLine();
            System.out.print("Ingrese su username: ");
            String usr = scanner.nextLine();
            System.out.print("Ingrese su peso: ");
            String c = String.valueOf(scanner.nextInt());
            System.out.print("Ingrese su altura (en cm): ");
            String d = String.valueOf(scanner.nextInt());

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


                String sql = "INSERT INTO Persona (nombre, apellido, username, peso, altura, fecha_peso) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, a);
                pstmt.setString(2, b);
                pstmt.setString(3, usr);
                pstmt.setInt(4, parseInt(c));
                pstmt.setInt(5, parseInt(d));
                pstmt.setDate(6, fechaSQL);

                int rowCount = pstmt.executeUpdate();
                System.out.println("Filas afectadas: " + rowCount);
                System.out.println("objeto creado correctamente");


            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }




    }

    public double calcularIMC(double peso, double alturaCm) {
        // Convertir altura de centímetros a metros
        double alturaM = alturaCm / 100.0;

        // Calcular IMC
        return peso / (alturaM * alturaM);
    }

    public static Persona buscarPorUsername(String username) {
        String sql = "SELECT * FROM Persona WHERE username = ?";

        try (Connection conn = conexion.Obtenerconexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer el parámetro de búsqueda
            pstmt.setString(1, username);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Procesar los resultados y crear un objeto Persona
            if (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int peso = rs.getInt("peso");
                int altura = rs.getInt("altura");
                java.sql.Date fechaPeso = rs.getDate("fecha_peso");

                // Crear y retornar un objeto Persona con los datos obtenidos
                return new Persona(id, user, nombre, apellido, peso, altura, fechaPeso);
            } else {
                System.out.println("No se encontró ningún usuario con el username: " + username);
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
        }

        return null; // Retorna null si no se encontró la persona o ocurrió un error
    }
}