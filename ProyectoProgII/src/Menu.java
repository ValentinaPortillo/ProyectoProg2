import java.util.Scanner;

public class Menu {
    public static int mostrarMenu(Scanner scanner) {
        int opcion = -1;

        System.out.println("=== Menú de Opciones ===");
        System.out.println("1. Crear nuevo usuario");
        System.out.println("2. Calcular IMC");
        System.out.println("3. Cargar enfermedades para el usuario");
        System.out.println("4. Ver enfermedades de persona");
        System.out.println("5. Ingresar comida y sus atributos");
        System.out.println("6. Calcular calorías consumidas");
        System.out.println("7. Ejercicios");
        System.out.println("0. Salir");

        while (true) {
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());

                if (opcion >= 0 && opcion <= 7) {
                    break;
                } else {
                    System.out.println("Por favor, ingrese una opción válida (0-6).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }

        //scanner.close();
        return opcion;
    }
}
