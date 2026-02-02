
import java.util.Scanner;// Importamos la clase Scanner para entrada de datos

public class Main {//  Definimos la clase Main
    public static void main(String[] args) {// Método principal
        Hotel miHotel = new Hotel();// Creamos una instancia de Hotel

        // Cargamos el inventario desde un archivo (si existe)
        miHotel.cargarInventarioDesdeArchivo("inventario_hotel.txt");
        //si el archivo no existe por primera vez añadimos habitaciones manualmente
        if (miHotel.listaHabitaciones.isEmpty()) {
            System.out.println("⚠️ No se encontró inventario. Añadiendo habitaciones por defecto.");
            Habitacion hab1 = new Habitacion(101, "Individual", 50.0);
            Habitacion hab2 = new Habitacion(102, "Doble", 80.0);
            miHotel.agregarHabitacion(hab1);
            miHotel.agregarHabitacion(hab2);
        
        }

        System.out.println("Iniciando respaldo...");// Mensaje de guardado
        miHotel.guardarInventarioEnArchivo("inventario_hotel.txt");// Llamamos al método para guardar datos

        // Agregamos algunas habitaciones al hotel
        //Habitacion hab1 = new Habitacion(101, "Individual", 50.0);
        //Habitacion hab2 = new Habitacion(102, "Doble", 80.0);
        
        //miHotel.agregarHabitacion(hab1);// Agregamos la habitación 101
        //miHotel.agregarHabitacion(hab2);// Agregamos la habitación 102

        javax.swing.SwingUtilities.invokeLater(() -> {
            
                VentanaPrincipal ventana = new VentanaPrincipal(miHotel);
                ventana.setVisible(true);
            
        });


    }// Fin del método main
}   // Fin de la clase Main


