import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;


public class Main{
    public static void main(String[] args){

        Connection connection = null;
        try {
            connection = conexion.Obtenerconexion();
            System.out.println("Conexion realizada");

            //laburen
            Persona.TPersona persona = new Persona.TPersona(); //objeto utilizado para accedeer a los metodos/registros de la clase
            persona.crearTabla();
            persona.crearObjeto(); //creo el objeto desde el main

            Comida.TComida comida = new Comida.TComida();
            comida.crearTablaComida(); 
            comida.crearObjetoComida(); 
            
               // Crear tabla e insertar datos en Ejercicio
            Ejercicio.TEjercicio ejercicio = new Ejercicio.TEjercicio();
            ejercicio.crearTabla();
            ejercicio.crearObjeto();

             // Crear la tabla Enfermedades y agregar datos
            Enfermedades.TEnfermedad enfermedad = new Enfermedades.TEnfermedad();
            enfermedad.crearTablaEnfermedades();
    

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
