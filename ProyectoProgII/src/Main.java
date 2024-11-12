import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;


public class Main{
    public static void main(String[] args){

        Connection connection = null;
        try {
            conexion.borrarTodasLasTablas(); // Borra todas las tablas al inicio para evitar duplicidad de datos al ejecutar varias veces
            connection = conexion.Obtenerconexion();
            System.out.println("Conexion realizada");

            //laburen
            Persona.TPersona persona = new Persona.TPersona(); //objeto utilizado para accedeer a los metodos/registros de la clase
            Comida.TComida comida = new Comida.TComida();
            Ejercicio.TEjercicio ejercicio = new Ejercicio.TEjercicio();
            Enfermedades.TEnfermedad enfermedad = new Enfermedades.TEnfermedad();
            EnfermedadesxPersona.TEnfxPer personaEnferma = new EnfermedadesxPersona.TEnfxPer();

            //Creo las tablas
            persona.crearTabla();
            comida.crearTablaComida();
            ejercicio.crearTabla();
            enfermedad.crearTablaEnfermedades(); //Crea la tabla enfermedades y agrega datos estáticos
            personaEnferma.crearTablaEnfefxPer();

            Scanner scanner = new Scanner(System.in);
            int opcionSeleccionada;
            do {
                opcionSeleccionada = Menu.mostrarMenu(scanner);

                switch (opcionSeleccionada) {
                    case 1:
                        System.out.println("=== Crear un nuevo usuario ===");
                        persona.crearObjeto(scanner);
                        break;
                    case 2:
                        System.out.println("=== IMC ===");
                        System.out.println("Ingrese su username: ");
                        String username = scanner.nextLine();
                        Persona persona1 = Persona.buscarPorUsername(username);
                        System.out.println("El IMC de " + username + " es: " + persona1.calcularIMC(persona1.getPeso(), persona1.getAltura()));

                        break;
                    case 3:
                        System.out.println("=== Cargar enfermedad para el usuario ===");
                        System.out.println("Ingrese su username: ");
                        String username2 = scanner.nextLine();
                        Persona persona2 = Persona.buscarPorUsername(username2);
                        List<Enfermedades> enfermedades = Enfermedades.obtenerTodasLasEnfermedades();
                        System.out.println("Selecciones su enfermedad: ");
                        for (Enfermedades enfermedad1 : enfermedades) {
                            System.out.println(enfermedad1.toString());
                        }
                        int enfermedad_seleccionada = scanner.nextInt();
                        EnfermedadesxPersona.insertarEnfermedadPersona(enfermedad_seleccionada, persona2.getId());
                        System.out.println("Enfermedad cargada correctamente");

                        break;
                    case 4:
                        System.out.println("=== Tus enfermedades ===");
                        System.out.println("Ingrese su username: ");
                        String username3 = scanner.nextLine();
                        Persona persona3 = Persona.buscarPorUsername(username3);

                        List<Enfermedades> enfermedades3 = EnfermedadesxPersona.obtenerEnfermedadesPorPersona(persona3.getId());

                        // Imprimir todas las enfermedades de la persona
                        for (Enfermedades enfermedad3 : enfermedades3) {
                            System.out.println(enfermedad3.toString());
                        }
                        break;
                    case 5:
                        System.out.println("Seleccionaste 'Calcular calorías consumidas'");
                        // Lógica para calcular calorías consumidas
                        break;
                    case 6:
                        System.out.println("Seleccionaste 'Ver enfermedades de persona'");
                        // Lógica para ver enfermedades
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

                System.out.println();
            } while (opcionSeleccionada != 0);  // Continuar hasta que el usuario elija salir



    

            // persona1.FuncionImc(id1)


        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        finally {
            try {
                if (connection != null )  {connection.close();
                    System.out.println("la conexion se finalizo");}
            } catch (SQLException e){
                System.out.println("Error:" + e.getMessage());
            }
        }
    }
}
