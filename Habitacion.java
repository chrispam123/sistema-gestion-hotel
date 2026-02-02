
public class Habitacion {
    int numero;
    String tipo;
    double precioPorNoche;
    boolean estaOcupada;
    String nombreHuesped;

    public Habitacion(int numero, String tipo, double precioPorNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.estaOcupada = false; // Inicialmente, la habitación está libre
        this.nombreHuesped = null; // No hay huésped inicialmente

    }

    // Getters
    public void reservar(String nombreDelCliente) {
        this.estaOcupada = true;// Cambiamos el estado a ocupada
        this.nombreHuesped = nombreDelCliente;// Asignamos el nombre del huésped
        System.out.println("✅ Reserva exitosa.");// Mensaje de confirmación
    }

    // Setters
    public void liberar() {
        this.estaOcupada = false; // Cambiamos el estado a libre
        this.nombreHuesped = null;// Eliminamos el nombre del huésped
    }

// Método para calcular el total a pagar por una estancia
    
    public double calcularTotal(int noches) {// Recibe el número de noches como parámetro
        return this.precioPorNoche * noches;// Retorna el total a pagar

    }
    
    // Usaremos este método manual en lugar de toString()
    
    // Método para mostrar los detalles de la habitación

    public void mostrarDetalles() {// Muestra los detalles de la habitación
        String estado = estaOcupada ? "Ocupada por " + nombreHuesped : "Disponible";// Determina el estado de la habitación
        System.out.println("Habitación " + numero + " | Tipo: " + tipo + " | Precio: $" + precioPorNoche + " | Estado: " + estado);// Muestra los detalles
        
    }
}// Fin de la clase Habitacion
