public class Comidas {
    private int id;
    private String nombre;
    private int calorias;
    private double proteinas;
    private double carbohidratos;
    private double grasas;
   

    public Comidas(int id, String nombre, int calorias, double proteinas, double carbohidratos, double grasas) {
        this.id = id;
        this.nombre = nombre;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.carbohidratos = carbohidratos;
        this.grasas = grasas;
       
    }

    // Getters y toString() para mostrar la comida
    public int getCalorias() { return calorias; }

    @Override
    public String toString() {
        return "Comida [\n" +
               "  ID: " + id + "\n" +
               "  Nombre: " + nombre + "\n" +
               "  Calorías: " + calorias + "\n" +
               "  Proteínas: " + proteinas + "\n" +
               "  Carbohidratos: " + carbohidratos + "\n" +
               "  Grasas: " + grasas + "\n" +
               "]";
    }
}


