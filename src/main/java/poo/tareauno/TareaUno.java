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
        
        if(avion == pavionesId.length){ // Valida si ya todos los aviones están registrados.
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
        
        for (int fila = 0; fila < paviones[index].length; fila++){
            if (!seguirFila) {
                break;
            }
            if (paviones[index][fila][0].substring(2,3).equals(asiento.substring(2))){
                for (int seat = 0; seat < paviones[index][fila].length; seat++){
                    if (paviones[index][fila][seat].substring(0,2).equals(asiento.substring(0,2))){
                        if (paviones[index][fila][seat].length() > 5){
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
                    }
                }
            }
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
    
    static void asignarPasajeros(){
        
    }
    
    static void vaciarAsiento(String[] pavionesId,String[][][] paviones,String[][] pPasajeros,String[] pPasajerosId){
        Scanner leerEntrada = new Scanner(System.in);
        String idAvion; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión a excluir (String de 5 caracteres exactos)");
            idAvion = leerEntrada.nextLine(); // Lee la entrada
            if (idAvion.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (idAvion.length() != 5);
        
        int avion = encontrarIndice(pavionesId, idAvion); // devuelve el indice respectivo de la identificacion
        if (avion == pavionesId.length){ // Si el indice es la longitud maxima, no esta registrado.
            System.out.println("El avión con la identificación ingresada no existe.");
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
        
        String idPas; // String para la identificación del avión
        do{
            System.out.println("Ingrese la identificación del pasajero (String de 1 a 12 caracteres)");
            idPas = leerEntrada.nextLine(); // Lee la entrada
            if (idPas.length() > 12 || idPas.length() == 0){ 
            //  Si la entrada no está entre el rango deseado, envía un error.
                System.out.println("ERROR: El string ingresado debe tener de 1 a 12 caracteres.");
            }
        } while (idPas.length() > 12 || idPas.length() == 0);
        int indexPas = encontrarIndice(pPasajerosId, idPas);
        if (indexPas == pPasajerosId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("Pasajero no está registrado. No se puede eliminar del asiento.");
            return;                  
        }
        
        int fila = (Integer.parseInt(asiento.substring(0,2)) - 1);
        int columna;
        if (fila < 4 && asiento.charAt(2) > 'B'){
            columna = (int)asiento.charAt(2) - 67;
        } else{
            columna = (int)asiento.charAt(2) - 65;
        }
        System.out.println(fila + " " + columna);
        
        if ((paviones[avion][fila][columna].substring(6, paviones[avion][fila][columna].length())).equals(idPas)){
            paviones[avion][fila][columna] = paviones[avion][fila][columna].substring(0,6);
            pPasajeros[indexPas] = new String[] {pPasajeros[indexPas][0], pPasajeros[indexPas][1], "", ""};
        } else {
            System.out.println("ERROR: La identificación del pasajero dada no coincide con la del asiento.");
            return;
        }
    }
    
    static void vaciarAvion(){
    }
    
    static void consultarAvion(String[][][] paviones, String[] pavionesId, String[][] pPasajeros, String[] pPasajerosId){
        Scanner leerEntrada = new Scanner(System.in);
        String idAvion; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión a excluir (String de 5 caracteres exactos)");
            idAvion = leerEntrada.nextLine(); // Lee la entrada
            if (idAvion.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (idAvion.length() != 5);
        
        int avion = encontrarIndice(pavionesId, idAvion); // devuelve el indice respectivo de la identificacion
        if (avion == pavionesId.length){ // Si el indice es la longitud maxima, no esta registrado.
            System.out.println("El avión con la identificación ingresada no existe.");
            return;       
        }
            for (int fila = 0; fila < paviones[avion].length; fila++){
                for (int asiento = 0; asiento < paviones[avion][fila].length; asiento++){
                    if (asiento == 2 && fila < 4){
                        System.out.printf("%-50s       	", " ");
                    }
                    String nombre;
                    try{
                        String pasajeroId = paviones[avion][fila][asiento].substring(6);
                        int indexPas = encontrarIndice(pPasajerosId, pasajeroId);
                        nombre = pPasajeros[indexPas][0] + " " + pPasajeros[indexPas][1];
                    }
                    catch (Exception e){
                        nombre = "";
                    }
                    System.out.printf("%s-%s %-20s\t", paviones[avion][fila][asiento].substring(0,2),
                    paviones[avion][fila][asiento].charAt(2), nombre);
                }
                System.out.println("\n");
            }
    }
    
    static void buscarPasajero(){
        
    }
    
    static void consultarAsientosDisponibles(){

    }
    
    static void registrarPasajeros(String[][] pPasajeros, String[] pPasajerosId){
        System.out.println("Registrar pasajeros"); 
        Scanner leerEntrada = new Scanner(System.in);
        String id; // String para la identificación del avión
        do{
            System.out.println("Ingrese la identificación del pasajero (String de 1 a 12 caracteres)");
            id = leerEntrada.nextLine(); // Lee la entrada
            if (id.length() > 12 || id.length() == 0){ 
            //  Si la entrada no está entre el rango deseado, envía un error.
                System.out.println("ERROR: El string ingresado debe tener de 1 a 12 caracteres.");
            }
        } while (id.length() > 12 || id.length() == 0);
        int index = encontrarIndice(pPasajerosId, id);
        if (index != pPasajerosId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("Pasajero ya está registrado. No se puede agregar.");
            return;                  
        }
        
        int pasajero = 0; // Se inicializa el índice del pasajero que se va a agregar.
        for (; pasajero < pPasajerosId.length; pasajero++){ // Se recorre el arreglo de los IDs de los pasajeros para encontrar el primero que esté vacío.
            if("".equals(pPasajerosId[pasajero])){
                pPasajerosId[pasajero] = id; // Se asigna la identificación y se sale.
                break;
            }
        }
        
        if(pasajero == pPasajerosId.length){ // Valida si ya se registraron la capacidad máxima de pasajeros.
            System.out.println("Ya hay la cantidad de máxima de pasajeros registrados.");
            return;
        }
        String nombre;
        do{
            System.out.println("Ingrese el nombre del pasajero (String de 1 a 15 caracteres)");
            nombre = leerEntrada.nextLine(); // Lee la entrada
            if (nombre.length() > 15 || nombre.length() == 0){
                //  Si la entrada no está entre el rango deseado, envía un error.
                System.out.println("ERROR: El string ingresado debe tener de 1 a 15 caracteres.");
            }
        } while (nombre.length() > 15 || nombre.length() == 0);
        String apellido;
        do{
            System.out.println("Ingrese el apellido del pasajero (String de 1 a 15 caracteres)");
            apellido = leerEntrada.nextLine(); // Lee la entrada
            if (apellido.length() > 15 || apellido.length() == 0){
                //  Si la entrada no está entre el rango deseado, envía un error.
                System.out.println("ERROR: El string ingresado debe tener de 1 a 15 caracteres.");
            }
        } while (apellido.length() > 15 || apellido.length() == 0);
        
        // se crea la información del pasajero.
        pPasajeros[pasajero] = new String[] {nombre, apellido, "", ""}; // avion y asiento
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
        // se llenan los arreglos con strings vacios.
        for (int pasajero = 0; pasajero < pasajerosId.length; pasajero++){
            pasajerosId[pasajero] = "";
            for (int i = 0; i < 4; i++){
                pasajeros[pasajero][i] = "";
            }
        }
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
            System.out.println("10: Registrar pasajeros");
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
                    asignarPasajeros();
                    break;
                case 5:
                    vaciarAsiento(avionesId, aviones, pasajeros, pasajerosId);
                    break;
                case 6:
                    vaciarAvion();
                    break;
                case 7:
                    consultarAvion(aviones, avionesId, pasajeros, pasajerosId);
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
