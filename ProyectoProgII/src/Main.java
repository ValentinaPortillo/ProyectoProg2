import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


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

//            persona.crearObjeto(); //creo el objeto desde el main
//            comida.crearObjetoComida();
//            ejercicio.crearObjeto();

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
                        System.out.println("=== Cargar comida ===");

                        break;
                    case 4:
                        System.out.println("Seleccionaste 'Calcular calorías quemadas'");
                        // Lógica para calcular calorías quemadas
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
