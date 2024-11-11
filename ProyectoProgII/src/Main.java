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
            persona.crearTablaPersona();
            persona.crearObjeto(); //creo el objeto desde el main

            // persona1.FuncionImc(id1)

            Enfermedades.TEnfermedad enfermedad = new Enfermedades.TEnfermedad();
            enfermedad.crearTablaEnfermedades();


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
