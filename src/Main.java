import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        try {
            connection = conexion.Obtenerconexion();
            System.out.println("Conexion realizada");

            // laburen
            Persona.TPersona persona = new Persona.TPersona(); // objeto utilizado para accedeer a los metodos/registros
                                                               // de la clase
            Comida.TComida comida = new Comida.TComida();
            Ejercicio.TEjercicio ejercicio = new Ejercicio.TEjercicio();
            Enfermedades.TEnfermedad enfermedad = new Enfermedades.TEnfermedad();
            EnfermedadesxPersona.TEnfxPer personaEnferma = new EnfermedadesxPersona.TEnfxPer();
            ComidaxPersona.TComxPer comidaxPersona = new ComidaxPersona.TComxPer();

            // Creo las tablas
            persona.crearTabla();
            comida.crearTablaComida();
            ejercicio.crearTabla();
            enfermedad.crearTablaEnfermedades(); // Crea la tabla enfermedades y agrega datos estáticos
            personaEnferma.crearTablaEnfefxPer();
            comidaxPersona.crearTablaComxPer(); // Usa el nombre correcto del método aquí

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
                        System.out.println("El IMC de " + username + " es: "
                                + persona1.calcularIMC(persona1.getPeso(), persona1.getAltura()));

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

                        List<Enfermedades> enfermedades3 = EnfermedadesxPersona
                                .obtenerEnfermedadesPorPersona(persona3.getId());

                        // Imprimir todas las enfermedades de la persona
                        for (Enfermedades enfermedad3 : enfermedades3) {
                            System.out.println(enfermedad3.toString());
                        }
                        break;
                    case 5:
                        System.out.println("=== Comida ===");
                        boolean comidaTrue = true;
                        while (comidaTrue) {
                            System.out.println();
                            System.out.println("Seleccione");
                            System.out.println();
                            System.out.println("1. Insertar nueva comida");
                            System.out.println("2. Ver todas las comidas del usuario");
                            System.out.println("3. Calcular total de calorías consumidas");
                            System.out.println("0. Salir");
                            System.out.print("Seleccione una opción: ");

                            String respuestaComida = scanner.nextLine();

                            switch (respuestaComida) {
                                case "1":
                                    comidaxPersona.registrarComidaDesdeConsola();
                                    break;
                                case "2":
                                    System.out.print("Ingrese su username: ");
                                    String username8 = scanner.nextLine();
                                    Persona persona8 = Persona.buscarPorUsername(username8);
                                    if (persona8 != null) {
                                        List<Comidas> comidasUsuario = ComidaxPersona
                                                .obtenerComidaPorPersona(persona8.getId());
                                        if (comidasUsuario.isEmpty()) {
                                            System.out.println();
                                            System.out.println("No hay comidas registradas para este usuario.");
                                        } else {
                                            System.out.println();
                                            System.out
                                                    .println("Comidas registradas para el usuario " + username8 + ":");
                                            System.out.println();
                                            for (Comidas comidaa : comidasUsuario) {
                                                System.out.println(comidaa.toString());
                                            }
                                        }
                                    } else {
                                        System.out.println("Usuario no encontrado.");
                                    }
                                    break;
                                case "3":
                                    System.out.print("Ingrese su username por favor: ");
                                    String username9 = scanner.nextLine();
                                    Persona persona9 = Persona.buscarPorUsername(username9);
                                    if (persona9 != null) {
                                        int totalCalorias = comidaxPersona
                                                .calcularTotalCaloriasConsumidasPorUsuario(persona9.getId());
                                        System.out.println();
                                        System.out.println("Total de calorías consumidas: " + totalCalorias
                                                + ", recuerda entrenar (°w°)/* animos!!");
                                    } else {
                                        System.out.println("Usuario no encontrado.");
                                    }
                                    break;
                                case "0":
                                    comidaTrue = false;
                                    break;
                                default:
                                    System.out.println();
                                    System.out.println(
                                            "Opción no válida!!! (°^°)/  Por favor intente de nuevo selecionando números. (=_=)/ ");
                            }
                        }
                        break;

                    case 6:
                        System.out.println("Seleccionaste 'Ejercicios'");
                        // boolean ejercicioTrue = true;
                        // while (ejercicioTrue) {
                        // System.out.println("=== Menú Ejercicios ===");
                        // System.out.println("1. Registrar un nuevo ejercicio realizado");
                        // System.out.println("2. Ver ejercicios registrados por usuario");
                        // System.out.println("3. Calcular total de calorías quemadas");
                        // System.out.println("0. Salir del menú de ejercicios");
                        // System.out.print("Seleccione una opción: ");

                        // String respuestaEjercicio = scanner.nextLine();

                        // switch (respuestaEjercicio) {
                        // case "1":
                        // // Registrar un nuevo ejercicio
                        // System.out.println("Ingrese el ID del ejercicio realizado:");
                        // int ejercicioId = scanner.nextInt();
                        // System.out.println("Ingrese el ID del usuario que realizó el ejercicio:");
                        // int usuarioId = scanner.nextInt();
                        // scanner.nextLine(); // Limpiar el buffer
                        // EjercicioxPersona.insertarEjercicioPersona(ejercicioId, usuarioId); // Método
                        // para
                        // // registrar
                        // // ejercicio
                        // System.out.println("Ejercicio registrado correctamente.");
                        // break;

                        // case "2":
                        // // Ver ejercicios de un usuario
                        // System.out.print("Ingrese su username para ver los ejercicios: ");
                        // String usernameEjercicio = scanner.nextLine();
                        // Persona personaEjercicio = Persona.buscarPorUsername(usernameEjercicio);
                        // if (personaEjercicio != null) {
                        // List<Ejercicio> ejerciciosUsuario = EjercicioxPersona
                        // .obtenerEjercicioPorPersona(personaEjercicio.getId());
                        // if (ejerciciosUsuario.isEmpty()) {
                        // System.out.println("No hay ejercicios registrados para este usuario.");
                        // } else {
                        // System.out.println("Ejercicios registrados para el usuario "
                        // + usernameEjercicio + ":");
                        // for (Ejercicio ejercicio1 : ejerciciosUsuario) {
                        // System.out.println(ejercicio1.toString());
                        // }
                        // }
                        // } else {
                        // System.out.println("Usuario no encontrado.");
                        // }
                        // break;

                        // case "3":
                        // // Calcular calorías quemadas por un usuario
                        // System.out.print("Ingrese su username para calcular calorías quemadas: ");
                        // String usernameCalorias = scanner.nextLine();
                        // Persona personaCalorias = Persona.buscarPorUsername(usernameCalorias);
                        // if (personaCalorias != null) {
                        // int caloriasTotales = EjercicioxPersona
                        // .calcularCaloriasTotales(personaCalorias.getId());
                        // System.out.println("Total de calorías quemadas: " + caloriasTotales);
                        // } else {
                        // System.out.println("Usuario no encontrado.");
                        // }
                        // break;

                        // case "0":
                        // // Salir del submenú de ejercicios
                        // ejercicioTrue = false;
                        // break;

                        // default:
                        // System.out.println("Opción no válida. Por favor intente nuevamente.");
                        // }
                        // }
                        break;

                    case 0:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

                System.out.println();
            } while (opcionSeleccionada != 0); // Continuar hasta que el usuario elija salir

            // persona1.FuncionImc(id1)

        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("la conexion se finalizo");
                }
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
    }
}
