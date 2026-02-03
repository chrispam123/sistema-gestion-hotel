
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    //clase con GUI

    private Hotel miHotel;//referencia al hotel osea la logica
    
    //constructor 
    public VentanaPrincipal(Hotel hotel) {
        this.miHotel = hotel;//el constructor recibe el hotel


        //configuracion de la ventana
        setTitle("Sistema de Gestión Hotelera");    
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1,10,10));
        //creacion de botones

        JButton btnMostrarInventario = new JButton("Mostrar Inventario de Habitaciones");

        btnMostrarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar el inventario
                //mostrar inventario en un JOptionPane
                StringBuilder inventario = new StringBuilder();
                for (Habitacion h : miHotel.listaHabitaciones) {
                    String estado = h.estaOcupada ? "Ocupada por " + h.nombreHuesped : "Disponible";

                    inventario.append("Habitación ").append(h.numero).append(" | Tipo: ").append(h.tipo)
                            .append(" | Precio: $").append(h.precioPorNoche).append(" | Estado: ").append(estado).append("\n    ");         
            }

             JOptionPane.showMessageDialog(null, inventario.toString(), "Inventario de Habitaciones", JOptionPane.INFORMATION_MESSAGE);
            }

         });
            
    




        //nuevo boton reservar habitacion
        JButton btnReservarHabitacion = new JButton("Reservar una Habitación");

        btnReservarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para reservar una habitación
              try {
                    String numeroStr = JOptionPane.showInputDialog("Ingrese el número de la habitación a reservar:");
                    int numero = Integer.parseInt(numeroStr);
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del huésped:");

                    boolean exito = miHotel.reservarHabitacion(numero, nombre);
                    if (exito) {
                        JOptionPane.showMessageDialog(null, "✅ Habitación reservada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ No se pudo reservar la habitación. Puede que esté ocupada o no exista.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Entrada inválida. Por favor, ingrese un número válido para la habitación.");
                }
            }       
        });





        JButton btnHacerCheckout = new JButton("Hacer Checkout de una Habitación");
        btnHacerCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para hacer checkout de una habitación
                try {
                    String numeroStr = JOptionPane.showInputDialog("Ingrese el número de la habitación para hacer checkout:");
                    int numero = Integer.parseInt(numeroStr);
                    //pedir el numero de noches 
                    String nochesStr = JOptionPane.showInputDialog("Ingrese el número de noches:");
                    //pedor el porcentaje de descuento
                    int noches = Integer.parseInt(nochesStr);
                    String descuentoStr = JOptionPane.showInputDialog("Ingrese el porcentaje de descuento:");
                    double descuento = Double.parseDouble(descuentoStr);
                    double total = miHotel.checkoutHabitacion(numero, noches, descuento);
                    if (total == -1) {
                        JOptionPane.showMessageDialog(null, "❌ La habitación ya está libre.");
                    } else if (total == -2) {
                        JOptionPane.showMessageDialog(null, "⚠️ No existe esa habitación.");
                    } else {
                        JOptionPane.showMessageDialog(null, "✅ Checkout exitoso. Total a pagar con descuento: $" + total);
                    }
                    //  


                    
                   // JOptionPane.showMessageDialog(null, "✅ Checkout procesado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Entrada inválida. Por favor, ingrese un número válido para la habitación.");
                }
            }
        });




        JButton btnGuardarInventario = new JButton("Guardar Inventario en Archivo");
        btnGuardarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para guardar el inventario en un archivo
                miHotel.guardarInventarioEnArchivo("inventario_hotel.txt");
                JOptionPane.showMessageDialog(null, "✅ Inventario guardado en archivo.");
            }
        });
        


        //agregar botones a la ventana
        add(btnMostrarInventario);
        add(btnReservarHabitacion);
        add(btnHacerCheckout);
        add(btnGuardarInventario);
        //acciones de los botones


    }
}
