
## 1. Arquitectura del Sistema

El sistema es una aplicación de gestión hotelera desarrollada en Java, que combina una interfaz gráfica de usuario (GUI) basada en Swing con lógica de negocio orientada a objetos y persistencia de datos en un archivo de texto plano (CSV). La arquitectura sigue un patrón de separación de responsabilidades:

- **Modelo:** Clases `Hotel` y `Habitacion` encapsulan la lógica y los datos del dominio hotelero.
- **Persistencia:** El archivo `inventario_hotel.txt` almacena el estado de las habitaciones en formato CSV.
- **Vista/Controlador:** La clase `VentanaPrincipal` implementa la GUI y actúa como controlador de eventos, interactuando con el modelo.
- **Arranque:** La clase `Main` inicializa el sistema, carga los datos y lanza la GUI.

## 2. Clases, Atributos y Métodos

### Habitacion

**Atributos:**
- `int numero`
- `String tipo`
- `double precioPorNoche`
- `boolean estaOcupada`
- `String nombreHuesped`

**Métodos:**
- `public Habitacion(int numero, String tipo, double precioPorNoche)`
- `public void reservar(String nombreDelCliente)`
- `public void liberar()`
- `public double calcularTotal(int noches)`
- `public void mostrarDetalles()`
- `public double calcularTotalConDescuento(int noches, double porcentajeDescuento)`


---

### Hotel

**Atributos:**
- `ArrayList<Habitacion> listaHabitaciones`

**Métodos:**
- `public Hotel()`
- `public void agregarHabitacion(Habitacion nuevaHabitacion)`
- `public void mostrarInventario()`
- `public boolean reservarHabitacion(int numeroDeseado, String nombreCliente)`
- `public void checkoutHabitacion(int numeroHabitacion)`
- `public void guardarInventarioEnArchivo(String nombreArchivo)`
- `public void cargarInventarioDesdeArchivo(String nombreArchivo)`

---

### VentanaPrincipal (extiende JFrame)

**Atributos:**
- `private Hotel miHotel`

**Métodos:**
- `public VentanaPrincipal(Hotel hotel)`

---

### Main

**Métodos:**
- `public static void main(String[] args)`

---

## 3. Formato del CSV y Reglas de Persistencia

### Formato del Archivo `inventario_hotel.txt`

Cada línea representa una habitación y sigue el formato:
# AI_CONTEXT.md

## 1. Arquitectura del Sistema

El sistema es una aplicación de gestión hotelera desarrollada en Java, que combina una interfaz gráfica de usuario (GUI) basada en Swing con lógica de negocio orientada a objetos y persistencia de datos en un archivo de texto plano (CSV). La arquitectura sigue un patrón de separación de responsabilidades:

- **Modelo:** Clases `Hotel` y `Habitacion` encapsulan la lógica y los datos del dominio hotelero.
- **Persistencia:** El archivo `inventario_hotel.txt` almacena el estado de las habitaciones en formato CSV.
- **Vista/Controlador:** La clase `VentanaPrincipal` implementa la GUI y actúa como controlador de eventos, interactuando con el modelo.
- **Arranque:** La clase `Main` inicializa el sistema, carga los datos y lanza la GUI.

## 2. Clases, Atributos y Métodos

### Habitacion

**Atributos:**
- `int numero`
- `String tipo`
- `double precioPorNoche`
- `boolean estaOcupada`
- `String nombreHuesped`

**Métodos:**
- `public Habitacion(int numero, String tipo, double precioPorNoche)`
- `public void reservar(String nombreDelCliente)`
- `public void liberar()`
- `public double calcularTotal(int noches)`
- `public void mostrarDetalles()`

---

### Hotel

**Atributos:**
- `ArrayList<Habitacion> listaHabitaciones`

**Métodos:**
- `public Hotel()`
- `public void agregarHabitacion(Habitacion nuevaHabitacion)`
- `public void mostrarInventario()`
- `public boolean reservarHabitacion(int numeroDeseado, String nombreCliente)`
- `public void checkoutHabitacion(int numeroHabitacion)`
- `public void guardarInventarioEnArchivo(String nombreArchivo)`
- `public void cargarInventarioDesdeArchivo(String nombreArchivo)`

---

### VentanaPrincipal (extiende JFrame)

**Atributos:**
- `private Hotel miHotel`

**Métodos:**
- `public VentanaPrincipal(Hotel hotel)`

---

### Main

**Métodos:**
- `public static void main(String[] args)`

---

## 3. Formato del CSV y Reglas de Persistencia

### Formato del Archivo `inventario_hotel.txt`

Cada línea representa una habitación y sigue el formato:
numero,tipo,precioPorNoche,estaOcupada,nombreHuesped

**Ejemplo:**
101,Individual,50.0,true,Luis Miguel
102,Doble,80.0,false,null


- `numero`: Número de la habitación (int)
- `tipo`: Tipo de habitación (String)
- `precioPorNoche`: Precio por noche (double)
- `estaOcupada`: Estado de ocupación (boolean: `true` o `false`)
- `nombreHuesped`: Nombre del huésped si está ocupada, o `null` si está libre

### Reglas de Persistencia

- **Guardar:**  
  El método `guardarInventarioEnArchivo(String nombreArchivo)` de [`Hotel`](Hotel.java) recorre todas las habitaciones y escribe sus atributos en el archivo, una habitación por línea, usando el formato CSV descrito.
- **Cargar:**  
  El método `cargarInventarioDesdeArchivo(String nombreArchivo)` de [`Hotel`](Hotel.java) lee cada línea del archivo, parsea los campos y crea instancias de [`Habitacion`](Habitacion.java). Si el campo `nombreHuesped` es `"null"`, se asigna `null` en Java.
- **Sincronización:**  
  Al iniciar el sistema, se intenta cargar el inventario. Si el archivo no existe o está vacío, se crean habitaciones por defecto.  
  Al guardar, se sobrescribe el archivo con el estado actual de las habitaciones.

---

## 4. Explicación Técnica de la GUI

La interfaz gráfica está implementada en la clase [`VentanaPrincipal`](VentanaPrincipal.java), que extiende `JFrame` y utiliza un `GridLayout` para organizar cuatro botones principales:

1. **Mostrar Inventario de Habitaciones:**  
   Muestra en un `JOptionPane` el listado de todas las habitaciones, con su estado y detalles.

2. **Reservar una Habitación:**  
   Solicita al usuario el número de habitación y el nombre del huésped mediante cuadros de diálogo. Llama a `reservarHabitacion` en el modelo y muestra el resultado.

3. **Hacer Checkout de una Habitación:**  
   Solicita el número de habitación, realiza el checkout y muestra un mensaje de confirmación.

4. **Guardar Inventario en Archivo:**  
   Llama a `guardarInventarioEnArchivo` y notifica al usuario que el inventario fue guardado.

**Interacción:**  
La GUI mantiene una referencia al objeto `Hotel` y actúa como intermediario entre el usuario y la lógica de negocio. Todos los cambios realizados a través de la GUI se reflejan en el modelo y pueden persistirse en el archivo CSV.

---

**Archivos relevantes:**
- [Habitacion.java](Habitacion.java)
- [Hotel.java](Hotel.java)
- [VentanaPrincipal.java](VentanaPrincipal.java)
- [Main.java](Main.java)
- [inventario_hotel.txt](inventario_hotel.txt)

  


