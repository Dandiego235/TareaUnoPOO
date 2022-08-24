/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package poo.tareauno;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author dandi
 */
public class TareaUno {

    static int encontrarIndice(String[] pavionesId, String id){
        // Funcion que encuentra el indice de un elemento en un arreglo.
        int index = 0;
        for (;index < pavionesId.length; index++){
            if (id.equals(pavionesId[index])){
                break;
            }
        }
        return index;
    }
    
    static void agregarAvion(String[][][] paviones, String[] pavionesId){
        System.out.println("Agregar avión"); 
        Scanner leerEntrada = new Scanner(System.in);
        String id; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión (String de 5 caracteres exactos)");
            id = leerEntrada.nextLine(); // Lee la entrada
            if (id.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (id.length() != 5);
        int index = encontrarIndice(pavionesId, id);
        if (index != pavionesId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("Avión ya está registrado. No se puede agregar.");
            return;                  
        }
        
        int avion = 0; // Se inicializa el índice del avión vacío que se va a agregar.
        for (; avion < pavionesId.length; avion++){ // Se recorre el arreglo de los IDs de los aviones para encontrar el primero que esté vacío.
            if("".equals(pavionesId[avion])){
                pavionesId[avion] = id; // Se asigna la identificación y se sale.
                break;
            }
        }
        
        if(avion == 20){ // Valida si ya todos los aviones están registrados.
            System.out.println("No hay aviones disponibles para registrar.");
            return;
        }
        
        String zeroPad; // Variables para construir el identificador de cada asiento.
        char clase;
        char letra;
        for(int fila = 0; fila < paviones[avion].length; fila++){
            for(int asiento = 0; asiento < paviones[avion][fila].length; asiento++){ // Se recorren las filas y asientos.
                zeroPad = (fila + 1 < 10) ? "0" : ""; // Si la fila es del 1 al 9, se rellena con 0 adelante.
                clase = (fila < 4) ? 'J' : 'E'; // Si la fila es de las primeras 4, la clase es J, si no es E.
                letra = (char) asiento; // Se inicializa la letra como el asiento para luego convertirla a su caracter.
                if (fila < 4 && asiento > 1){
                    letra += 67; // Si la fila es de las primeras 4 y el asiento es a partir del 2, se suma 67
                    // para convertirlos en E y F.
                } else {
                    letra += 65; // A los demás se le suma 65 para convertirlos en A, B, C, D, E y F.
                }
                paviones[avion][fila][asiento] = zeroPad + (fila + 1) + letra + clase + "A";
                // Se construye el identificador.
            }
        }
    }
    
    static void modificarCapacidadAsientos(String[][][] paviones, String[] pavionesId){
        System.out.println("Modificar capacidad de asientos"); 
        Scanner leerEntrada = new Scanner(System.in);
        String id; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión (String de 5 caracteres exactos)");
            id = leerEntrada.nextLine(); // Lee la entrada
            if (id.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (id.length() != 5);
        
        int index = encontrarIndice(pavionesId, id);
        if (index == pavionesId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("Avión no está registrado. No se puede modificar.");
            return;                  
        }
        
        String asiento;
        do{
            System.out.println("Ingrese el número y letra del asiento. Ej: 01A");
            asiento = leerEntrada.nextLine(); // Lee la entrada
            if (asiento.length() != 3){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 3 caracteres.");
            }
        } while (asiento.length() != 3);
        
        boolean seguirFila = true;
        int fila = 0;
        for (; fila < paviones[index].length; fila++){
            if (!seguirFila) {
                break;
            }
            if (paviones[index][fila][0].substring(2,3).equals(asiento.substring(2))){
                for (int seat = 0; seat < paviones[index][fila].length; seat++){
                    if (paviones[index][fila][seat].substring(0,2).equals(asiento.substring(0,2))){
                        if (paviones[index][fila][seat].length() <= 5){
                            if ("A".equals(paviones[index][fila][seat].substring(5))){
                                paviones[index][fila][seat] = paviones[index][fila][seat].substring(0,5) + "I";
                            }
                            else{
                                paviones[index][fila][seat] = paviones[index][fila][seat].substring(0,5) + "A";
                            }
                            System.out.println(paviones[index][fila][seat]);
                            seguirFila = false;
                            break;  
                        }
                        else {
                        System.out.println("ERROR: Ese asiento tiene pasajeros.");
                        return;
                        }  
                    }
                }
            }
        }
        if (fila == paviones[index].length) {
            System.out.println("ERROR: Ese asiento no existe.");
        }
    }
    
    static void excluirAvion(String[] pavionesId, String[][][] paviones){
        Scanner leerEntrada = new Scanner(System.in);
        String id; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión a excluir (String de 5 caracteres exactos)");
            id = leerEntrada.nextLine(); // Lee la entrada
            if (id.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (id.length() != 5);
        
        int avion = encontrarIndice(pavionesId, id); // devuelve el indice respectivo de la identificacion
        if (avion == pavionesId.length){ // Si el indice es la longitud maxima, no esta registrado.
            System.out.println("El avión con la identificación ingresada no existe.");
            return;       
        }
        
        for (int fila = 0; fila < paviones[avion].length; fila++){ // validación de que el avión está vacío.
            for (int asiento = 0; asiento < paviones[avion][fila].length; asiento++){
                if (paviones[avion][fila][asiento].length() != 5){
                    // si en algun asiento, el string no tiene 5 caracteres, es que tiene un pasajero, entonces el avion no está vacío.
                    System.out.println("El avión no está vacío. No se puede excluir.");
                    return;
                }
            }
        }
        
        for (int fila = 0; fila < paviones[avion].length; fila++){ // Vacía el avión.
            for (int asiento = 0; asiento < paviones[avion][fila].length; asiento++){
                paviones[avion][fila][asiento] = "";
            } 
        }
        pavionesId[avion] = ""; // Borra la identificación.
    }
    
    static void asignarPasajeros(String[][][] paviones, String[] pavionesId, String[] parPasajerosId, String[][] parPasajeros){
        System.out.println("Asignar pasajeros a asientos"); 
        Scanner leerEntrada = new Scanner(System.in);
        String id; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión (String de 5 caracteres exactos)");
            id = leerEntrada.nextLine(); // Lee la entrada
            if (id.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (id.length() != 5);
        
        int index = encontrarIndice(pavionesId, id);
        if (index == pavionesId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("Avión no está registrado. No se puede modificar.");
            return;                  
        }
        
        String asiento;
        do{
            System.out.println("Ingrese el número y letra del asiento. Ej: 01A");
            asiento = leerEntrada.nextLine(); // Lee la entrada
            if (asiento.length() != 3){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 3 caracteres.");
            }
        } while (asiento.length() != 3);
        
        boolean seguirFila = true;
        int fila = 0;
        for (; fila < paviones[index].length; fila++){
            if (!seguirFila) {
                break;
            }
            if (paviones[index][fila][0].substring(2,3).equals(asiento.substring(2))){
                for (int seat = 0; seat < paviones[index][fila].length; seat++){
                    if (paviones[index][fila][seat].substring(0,2).equals(asiento.substring(0,2))){
                        if (paviones[index][fila][seat].length() <= 5 && "A".equals(paviones[index][fila][seat].substring(5))){
                            String pasajero;
                            do{
                                System.out.println("Ingrese la identificación del pasajero");
                                pasajero = leerEntrada.nextLine(); // Lee la entrada
                                if (pasajero.length() > 12 && pasajero.length() <= 0){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                                    System.out.println("ERROR: El string ingresado debe ser entre 1 a 12 caracteres de largo");
                                }
                            } while (pasajero.length() > 12 && pasajero.length() <= 0);
                            
                            int pasaIndex = encontrarIndice(parPasajerosId, pasajero);
                            if (pasaIndex == parPasajerosId.length){
                                System.out.println("ERROR: Ese pasajero no existe");
                                return;
                            }                            
                            paviones[index][fila][seat] += pasajero;
                            parPasajeros[pasaIndex][2] = id;
                            parPasajeros[pasaIndex][3] = asiento;
                            System.out.println(Arrays.deepToString(parPasajeros));
                            System.out.println(Arrays.deepToString(paviones));
                        }
                        else {
                            System.out.println("ERROR: Ese asiento tiene pasajeros o está inactivo.");
                            return;
                        }
                    }
                }
            }  
        }
        if (fila == paviones[index].length) {
            System.out.println("ERROR: Ese asiento no existe.");
        }
    }
    
    
    static void vaciarAsiento(){
        
    }
    
    static void vaciarAvion(String[][][] paviones, String[] pavionesId, String[] parPasajerosId, String[][] parPasajeros){
        System.out.println("Vaciar avión"); 
        Scanner leerEntrada = new Scanner(System.in);
        String id; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión (String de 5 caracteres exactos)");
            id = leerEntrada.nextLine(); // Lee la entrada
            if (id.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (id.length() != 5);
        
        int index = encontrarIndice(pavionesId, id);
        if (index == pavionesId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("Avión no está registrado. No se puede modificar.");
            return;                  
        }
        
        String confirmacion;
        System.out.println("Desea continuar con vaciar el avión?: S/N");
        confirmacion = leerEntrada.nextLine();
        if ("N".equals(confirmacion)){
            return;
        }
        
        int fila = 0;
        for (; fila < paviones[index].length; fila++){
            for (int pasajero = 0; pasajero < paviones.length; pasajero++){
                int pasaIndex = encontrarIndice(parPasajerosId, paviones[index][fila][pasajero].substring(6));
            }
            
            
              
        }
        if (fila == paviones[index].length) {
            System.out.println("ERROR: Ese asiento no existe.");
        }
    }
    
    static void consultarAvion(){
        
    }
    
    static void buscarPasajero(){
        
    }
    
    static void consultarAsientosDisponibles(){

    }
    
    static void registrarPasajeros(String[][] pPasajeros, String[] pPasajerosId){
        
    }
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        final short NUM_AVIONES = 20;
        final short FILAS = 25;
        final short NUM_PASAJEROS = 5000;
        
        String[][][] aviones = new String [NUM_AVIONES][FILAS][]; // matriz para almacenar todos los aviones
        for (int avion = 0; avion < aviones.length; avion++){ // se recorre la matriz para asignarle la longitud correcta a cada fila.
            for (int fila = 0; fila < aviones[avion].length; fila++){
                if (fila < 4){ // si el índice de la fila es menor que 4, la fila tiene solo 4 asientos
                    aviones[avion][fila] = new String[] {"", "", "", ""};
                } else { // si es mayor o igual que 4, tiene 6 asientos.
                    aviones[avion][fila] = new String[] {"", "", "", "", "", ""};
                }
            }
        }
        
        String[] pasajerosId = new String[NUM_PASAJEROS];
        String[][] pasajeros = new String[NUM_PASAJEROS][4];
        
        String[] avionesId = new String[NUM_AVIONES]; // Matriz donde se almacenan los identificadores de los aviones.        
        // Tiene una relación 1 a 1 con los índices de la primera dimensión de la matriz aviones.
        for (int index = 0; index < avionesId.length; index++){ // se llena el arreglo de strings vacios
            avionesId[index] = "";
        }
        
        Scanner leerDato = new Scanner(System.in);
        
        int opcion;
        do {
            System.out.println("Asignación de asientos");
            System.out.println("Ingrese su opcion entre las siguientes");
            System.out.println("1: Agregar avión");
            System.out.println("2: Modificar capacidad de asientos");
            System.out.println("3: Excluir avión");
            System.out.println("4: Asignar pasajeros a asientos");
            System.out.println("5: Vaciar asiento");
            System.out.println("6: Vaciar avión");
            System.out.println("7: Consultar avión");
            System.out.println("8: Buscar pasajero");
            System.out.println("9: Consultar asientos disponibles");
            System.out.println("0: Salir");
            System.out.print("Opción: ");
            opcion = leerDato.nextInt();
            switch (opcion){
                case 1:
                    agregarAvion(aviones, avionesId);
                    break;
                case 2:
                    modificarCapacidadAsientos(aviones, avionesId);
                    break;
                case 3:
                    excluirAvion(avionesId, aviones);
                    break;
                case 4:
                    asignarPasajeros(aviones, avionesId, pasajerosId, pasajeros);
                    break;
                case 5:
                    vaciarAsiento();
                    break;
                case 6:
                    vaciarAvion(aviones, avionesId, pasajerosId, pasajeros);
                    break;
                case 7:
                    consultarAvion();
                    break;
                case 8:
                    buscarPasajero();
                    break;
                case 9:
                    consultarAsientosDisponibles();
                    break;
                case 10:
                    registrarPasajeros(pasajeros, pasajerosId);
                    break;
                default:
                    System.out.println("¡Hasta pronto!");
            } 
        } while (opcion != 0);
        leerDato.close();
    }
}
