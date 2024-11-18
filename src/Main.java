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
            EjercicioxPersona.TEjercicioxPer ejercicioxpersona = new EjercicioxPersona.TEjercicioxPer();
            Enfermedades.TEnfermedad enfermedad = new Enfermedades.TEnfermedad();
            EnfermedadesxPersona.TEnfxPer personaEnferma = new EnfermedadesxPersona.TEnfxPer();
            ComidaxPersona.TComxPer comidaxPersona = new ComidaxPersona.TComxPer();

            // Creo las tablas
            persona.crearTabla();
            comida.crearTablaComida();
            ejercicio.crearTabla();
            ejercicioxpersona.crearTablaEjercxPer();
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

                         boolean ejercicioTrue = true;
                         while (ejercicioTrue) {
                             System.out.println("\n=== Menú Ejercicios ===");
                             System.out.println("1. Registrar un nuevo ejercicio realizado");
                             System.out.println("2. Ver ejercicios registrados por usuario");
                             System.out.println("3. Calcular total de calorías quemadas");
                             System.out.println("0. Salir del menú de ejercicios");

                             String respuestaEjercicio = Validaciones.pedirDato(scanner, "Seleccione una opción: ",
                                     "^[0-3]$", "Error: Las opciones validas solo son del 0 al 3 inclusive.");
                             switch (respuestaEjercicio) {
                                 case "1":
                                     System.out.println("\n=== Registrar nuevo ejericio realizado ===");
                                     String username6 = Validaciones.pedirDato(scanner, "\nIngrese su username: ",
                                             "^[a-zA-Z0-9_]{3,50}$", "Error: El username debe contener solo letras, números, guiones bajos y entre 3 y 50 caracteres.");

                                     Persona persona6 = Persona.buscarPorUsername(username6);
                                     if(persona6 == null){break;}
                                     System.out.println("\nSeleccione un ID de ejercicio");
                                     List<Ejercicio> ejercicios = Ejercicio.obtenerEjercicios();
                                     for (Ejercicio ejercicio1 : ejercicios) {
                                         System.out.println(ejercicio1.toString());
                                     }
                                     int ejercicio_seleccionado = Validaciones.validarID(scanner, "");

                                     String duracion = Validaciones.pedirDato(scanner, "Ingrese la duración del ejericio: ",
                                             "^(?:[1-9]|[1-9][0-9]|[1-2][0-9]{2}|300)$", "Error: La duración debe ser un numero entre 1 y 300 minutos");

                                     EjercicioxPersona.insertarEjercicioPersona(ejercicio_seleccionado, persona6.getId(), Integer.parseInt(duracion));
                                     System.out.println("Ejercicio cargado correctamente\n");

                                     break;
                                 case "2":
                                     String username7 = Validaciones.pedirDato(scanner, "\nIngrese su username: ",
                                             "^[a-zA-Z0-9_]{3,50}$", "Error: El username debe contener solo letras, números, guiones bajos y entre 3 y 50 caracteres.");
                                     Persona persona7 = Persona.buscarPorUsername(username7);
                                     if(persona7 == null){break;}
                                     List<Ejercicio> ejerciciosxpersona = EjercicioxPersona
                                             .obtenerEjercicioPorPersona(persona7.getId());
                                     if (ejerciciosxpersona.isEmpty()) {
                                         System.out.println("El usuario " + persona7.getUsername() + " no tiene ejercicios registrados");
                                         break;
                                     }
                                     System.out.println("\nTus ejercicios registrados son: ");
                                     for (Ejercicio ejerxpers : ejerciciosxpersona) {
                                         System.out.println(ejerxpers.getNombre() + " - " + ejerxpers.getDuracion() + " minutos");
                                     }
                                     System.out.println();
                                     break;

                                 case "3":
                                     String username8 = Validaciones.pedirDato(scanner, "\nIngrese su username: ",
                                             "^[a-zA-Z0-9_]{3,50}$", "Error: El username debe contener solo letras, números, guiones bajos y entre 3 y 50 caracteres.");
                                     Persona persona8 = Persona.buscarPorUsername(username8);
                                     if(persona8 == null){break;}
                                     int calorias_quemadas = EjercicioxPersona.calcularCaloriasTotales(persona8.getId());
                                     System.out.println("\nLas calorias quemadas con tus ejercicios realizados son: " + calorias_quemadas + " kcal\n");
                                     break;
                                 case "0":
                                     ejercicioTrue = false;

                             }
                         }

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
