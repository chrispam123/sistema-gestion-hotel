

// Importamos las herramientas necesarias para escribir archivos
import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;// Importamos la clase ArrayList

public class Hotel {// Definimos la clase Hotel
    ArrayList<Habitacion> listaHabitaciones;// Lista para almacenar las habitaciones


    public Hotel() {// Constructor de la clase Hotel
        this.listaHabitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion nuevaHabitacion) {// Método para agregar una nueva habitación
        listaHabitaciones.add(nuevaHabitacion);
    }

    public void mostrarInventario() {// Método para mostrar el inventario de habitaciones
        System.out.println("\n--- 📋 LISTADO DE HABITACIONES ---");   
        for (Habitacion h : listaHabitaciones) {// Recorremos la lista de habitaciones
            // EXPLICACIÓN: Como no hay toString, si hiciéramos System.out.println(h) 
            // veríamos códigos raros de memoria. Por eso llamamos al método manual:
            h.mostrarDetalles(); // Mostramos los detalles de cada habitación
        }
        System.out.println("----------------------------------");// Línea de cierre
    }

    public boolean reservarHabitacion(int numeroDeseado, String nombreCliente) {// Método para reservar una habitación
        for (Habitacion h : listaHabitaciones) {// Recorremos la lista de habitaciones
            if (h.numero == numeroDeseado) {// Si encontramos la habitación deseada
                if (h.estaOcupada) {// Si ya está ocupada
                    System.out.println("❌ Error: Ya está ocupada.");// Mensaje de error
                    return false;
                } else {// Si está libre
                    h.reservar(nombreCliente);// Reservamos la habitación
                    return true;
                }// Fin del else
            }// Fin del if
        }// Fin del for
        System.out.println("⚠️ No existe esa habitación.");// Mensaje si no se encontró la habitación
        return false;
    }// Fin del método reservarHabitacion



    //Metodo checkout y descuento
    public double procesarCheckoutCompleto(int numeroHabitacion, int noches, double descuento) {// Método para hacer checkout de una habitación
        boolean encontrada = false;// Bandera para verificar si se encontró la habitación
        for (Habitacion h : listaHabitaciones) {// Recorremos la lista de habitaciones  
            if (h.numero == numeroHabitacion) {// Si encontramos la habitación deseada  
                encontrada = true;// Marcamos que la encontramos  
                if (h.estaOcupada) {// Si está ocupada  
                    h.liberar();// Liberamos la habitación  
                    //System.out.println("✅ Checkout exitoso.");// Mensaje de confirmación  
                    double total = h.calcularTotalConDescuento(noches, descuento);
                    this.guardarInventarioEnArchivo("inventario_hotel.txt");// Guardamos el inventario actualizado
                    System.out.println("Persitencia de datos actualizada.");// Mensaje de confirmación
                    //System.out.println("Total a pagar con descuento: $" + total);   
                    return total;// Retornamos el total a pagar
                } else {// Si ya está libre 
                    return -1;// Indicamos que la habitación ya está libre
                   //System.out.println("❌ Error: La habitación ya está libre.");// Mensaje de error  
                }// Fin del else  
               // break;// Salimos del bucle una vez encontrada  
            }// Fin del if  
        }// Fin del for  
        if (!encontrada) System.out.println("⚠️ No existe esa habitación.");// Mensaje si no se encontró la habitación  
        return -2;// Indicamos que no se encontró la habitación
    }   



    // Método para guardar el inventario en un archivo
    public void guardarInventarioEnArchivo(String nombreArchivo) {// Método para guardar el inventario en un archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {// Creamos un BufferedWriter
            for (Habitacion h : listaHabitaciones) {// Recorremos la lista de habitaciones
                //NUEVA MODIFICACIÓN 
                // Formateamos la línea pura
                //Formato numero,tipo,precio,estado,nombreHuesped
                String linea = h.numero + "," + h.tipo + "," + h.precioPorNoche + "," + h.estaOcupada + "," + h.nombreHuesped;


                writer.write(linea);// Escribimos la línea en el archivo
                writer.newLine();// Nueva línea
            }
            System.out.println("✅ Inventario guardado en " + nombreArchivo);// Mensaje de confirmación
        } catch (IOException e) {// Capturamos posibles errores de IO
            System.out.println("❌ Error al guardar el inventario: " + e.getMessage());// Mensaje de error
        }
    }
    

    //metodo para leer inventario desde archivo
    // pendiente en main llamarlo
    public void cargarInventarioDesdeArchivo(String nombreArchivo) {
        this.listaHabitaciones.clear(); // Limpiamos la lista actual
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(","); // Suponiendo que los datos están separados por comas
                int numero = Integer.parseInt(partes[0]);
                String tipo = partes[1];
                double precio = Double.parseDouble(partes[2]);
                boolean estaOcupada = Boolean.parseBoolean(partes[3]);
                String nombreHuesped = partes[4];
            if (nombreHuesped.equals("null")) {
                nombreHuesped = null; // Convertimos "null" a null real
            }

              //usamos el constructor de Habitacion
                Habitacion habitacion = new Habitacion(numero, tipo, precio);
                habitacion.estaOcupada = estaOcupada;
                habitacion.nombreHuesped = nombreHuesped;

                this.listaHabitaciones.add(habitacion); // Agregamos la habitación a la lista 

            }// Fin del while
            System.out.println("✅ Inventario cargado desde " + nombreArchivo);// Mensaje de confirmación

        } catch (IOException e) {// Capturamos posibles errores de IO
            System.out.println("❌ Error al cargar el inventario: " + e.getMessage());// Mensaje de error
            
             
        }
    }
          
}// Fin de la clase Hotel

