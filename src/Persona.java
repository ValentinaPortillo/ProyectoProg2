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
            String a = Validaciones.pedirDato(scanner, "Ingrese su nombre: ",
                    "^[a-zA-Z]{3,50}$", "Error: El nombre debe contener solo letras y entre 3 y 50 caracteres.");
            String b = Validaciones.pedirDato(scanner, "Ingrese su apellido: ",
                    "^[a-zA-Z]{3,50}$", "Error: El apellido debe contener solo letras y entre 3 y 50 caracteres.");
            String usr = Validaciones.validarUsername(scanner, "Ingrese su username: ",
                    "^[a-zA-Z0-9_]{3,50}$", "Error: El username debe contener solo letras, números, guiones bajos y entre 3 y 50 caracteres.");
            String c = Validaciones.pedirDato(scanner, "Ingrese su peso: ",
                    "^([1-9]|[1-9][0-9]|[1-4][0-9]{2}|500)$", "Error: El peso debe ser un número positivo entre 1 y 500.");
            String d = Validaciones.pedirDato(scanner, "Ingrese su altura (en cm): ",
                    "^(2[0-9]|[3-9][0-9]|1[0-9]{2}|2[0-9]{2}|300)$", "Error: La altura debe ser un número positivo entre 20 y 300.");

            String fechaInput = Validaciones.pedirDato(scanner, "Ingrese la fecha en formato dd/mm/yyyy: ",
                    "^((0[1-9]|[12][0-9]|3[01])\\/(0[13578]|1[02])\\/((19|20)\\d{2}))|((0[1-9]|[12][0-9]|30)\\/(0[13-9]|1[0-2])\\/((19|20)\\d{2}))|((0[1-9]|1[0-9]|2[0-8])\\/02\\/((19|20)\\d{2}))|((29)\\/02\\/((19|20)(([02468][048])|([13579][26]))))$", "Error: La fecha debe estar en el formato dd/mm/yyyy y ser valida.");

            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaSQL = null;

            try {
                java.util.Date fecha = formatoEntrada.parse(fechaInput);
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