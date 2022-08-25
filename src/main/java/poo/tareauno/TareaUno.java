/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package poo.tareauno;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Tarea Uno: Asignación de asientos
 * Coautores: Daniel Granados Retana, carné 2022104692
 *            Diego Granados Retana, carné 2022158363
 *
 * Programación Orientada a Objetos
 * Profesor: William Mata
 * Instituto Tecnológico de Costa Rica
 * Escuela de Computación
 * 
 * Fecha: 25/07/2022.
 * 
 * En las instrucciones, no se incluye una opción 10. Sin embargo, es necesaria
 * esta opción para registrar los pasajeros para luego agregarlos a cada avión.
 * Por lo tanto, la opción 10 es de registro de pasajeros.
 */
public class TareaUno {
    
    // Función que encuentra el índice de un elemento en un arreglo de una dimensión.
    // Entradas: Arreglo de una dimensión y String con el elemento a buscar
    // Salidas: índice del elemento en el arreglo.
    static int encontrarIndice(String[] pavionesId, String id){
        // Funcion que encuentra el indice de un elemento en un arreglo.
        int index = 0;
        // Ciclo que itera por el arreglo hasta encontrar el índice.
        for (;index < pavionesId.length; index++){
            if (id.equals(pavionesId[index])){
                break; // Si el string es igual a lo que contiene el arreglo en el índice, se sale.
            }
        }
        return index; // Si el index es igual a la longitud del arreglo, no encontró el elemento.
    }
    
    // Función que inicializa aviones y agrega su identificación.
    // Entradas: Identificación, arreglo con todos los aviones, arreglo con las identificaciones de los aviones.
    // Salidas: No hay
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
        // index es el índice de la identificación en el arreglo de identificaciones.
        if (index != pavionesId.length){ // Si el índice es la longitud del arreglo, es que no encontró el elemento.
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("ERROR: Avión ya está registrado. No se puede agregar.");
            return;
        }
        
        int avion = 0; // Se inicializa el índice del avión vacío que se va a agregar.
        for (; avion < pavionesId.length; avion++){ // Se recorre el arreglo de los IDs de los aviones para encontrar el primero que esté vacío.
            if("".equals(pavionesId[avion])){ // Cuando haya uno vacío,
                pavionesId[avion] = id; // Se asigna la identificación y se sale. avión queda con el índice que correponde a la identificación.
                break;
            }
        }
        
        if(avion == pavionesId.length){ // Valida si ya todos los aviones están registrados.
            System.out.println("ERROR: No hay aviones disponibles para registrar.");
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
                // Se construye el identificador y se asigna al asiento.
            }
        }
    }
    
    // Función que modifica la capacidad de los asientos. Cambia el estado de los asientos, de activo a inactivo
    // Entradas: identificación del avión, asiento, arreglo de aviones y arreglo de identificaciones de aviones.
    // Salidas: No hay
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
        // index es el índice de la identificación en el arreglo de identificaciones.
        if (index == pavionesId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("ERROR: Avión no está registrado. No se puede modificar.");
            return;                  
        }
        
        String asiento; // Se va a usar para almacenar el asiento
        do{ // Mientras el usuario no haya ingresado un asiento válido
            System.out.println("Ingrese el número y letra del asiento. Ej: 01A");
            asiento = leerEntrada.nextLine(); // Lee la entrada
            if (asiento.length() != 3){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 3 caracteres.");
            }
        } while (asiento.length() != 3);
        
        boolean seguirFila = true;  // Se va a usar para terminar el for loop
        int fila = 0;  // Se va a usar para recorrer cada fila.
        for (; fila < paviones[index].length; fila++){
            if (!seguirFila) {  // Si esa variable es falsa, significa que no se puede seguir y tiene que terminar
                break;
            }
            
             // Si la fila es correcta, o sea, es igual
            int seat = 0;
            if (paviones[index][fila][0].substring(0,2).equals(asiento.substring(0,2))){
                for (seat = 0; seat < paviones[index][fila].length; seat++){  // empieza a recorrerla
                    if (paviones[index][fila][seat].substring(2,3).equals(asiento.substring(2))){  // Si la letra del asiento es igual a la que se busca
                        if (paviones[index][fila][seat].length() <= 5){  // Si el asiento tiene 5 o menos caracteres, está vacío
                            if ("A".equals(paviones[index][fila][seat].substring(4))){  // Si ese asiento está activo, se convierte en inactivo
                                paviones[index][fila][seat] = paviones[index][fila][seat].substring(0,4) + "I";
                            }
                            else{  // Si está inactivo, se convierte en activo.
                                paviones[index][fila][seat] = paviones[index][fila][seat].substring(0,4) + "A";
                            }
                            seguirFila = false;
                            break;  
                        }
                        else {  // Si el asiento tiene más de 5 caracteres, tiene pasajeros y no se puede modificar.
                        System.out.println("ERROR: Ese asiento tiene pasajeros.");
                        return;
                        }  
                    }
                }
            }
        }
        if (fila == paviones[index].length) {  // Si la longitud del arreglo de filas es igual al índice que se usaba para recorrerla, el asiento no puede existir.
            System.out.println("ERROR: Ese asiento no existe.");
        }
    }
    
    // Función que excluye aviones, los borra de los arreglos.
    // Entradas: Identificación el avión a excluir
    // Salidas: No hay
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
            System.out.println("ERROR: El avión con la identificación ingresada no existe.");
            return;       
        }
        
        for (int fila = 0; fila < paviones[avion].length; fila++){ // validación de que el avión está vacío.
            for (int asiento = 0; asiento < paviones[avion][fila].length; asiento++){
                if (paviones[avion][fila][asiento].length() != 5){
                    // si en algun asiento, el string no tiene 5 caracteres, es que tiene un pasajero, entonces el avion no está vacío.
                    System.out.println("ERROR: El avión no está vacío. No se puede excluir.");
                    return;
                }
            }
        }
        
        for (int fila = 0; fila < paviones[avion].length; fila++){ // Vacía el avión.
            for (int asiento = 0; asiento < paviones[avion][fila].length; asiento++){
                paviones[avion][fila][asiento] = ""; // Se vuelve a poner como estaba al inicio, con strings vacíos.
            } 
        }
        pavionesId[avion] = ""; // Borra la identificación.
    }
    
    // Función que asigna pasajeros a asientos
    // Entradas: el avión, el asiento y el pasajero
    // Salidas: No hay
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
        
         // Igual a Modificar Capacidad de Asientos
        int index = encontrarIndice(pavionesId, id);
        if (index == pavionesId.length){
            // Si encuentra una identificación igual, envía un mensaje de error.
            System.out.println("ERROR: Avión no está registrado. No se puede modificar.");
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
        for (; fila < paviones[index].length; ++fila){
            
            if (!seguirFila) {
                break;
            }
            
            int seat = 0;
            
            if (paviones[index][fila][0].substring(0,2).equals(asiento.substring(0,2))){
                for (seat = 0; seat < paviones[index][fila].length; seat++){
                    if (paviones[index][fila][seat].substring(2,3).equals(asiento.substring(2))){
                         // Si el asiento tiene menos de 6 caracteres de longitud y tiene una A en el lugar que indica el estado, está disponible.
                        if (paviones[index][fila][seat].length() <= 5 && "A".equals(paviones[index][fila][seat].substring(4))){
                            String pasajero;  // Se usa para almacenar el pasajero por agregar
                            boolean continueIdent = false;  // se usa para ver si se tiene que seguir pidiendo la identificación ya que se ha dado incorrectamente
                            do{
                                System.out.println("Ingrese la identificación del pasajero");
                                pasajero = leerEntrada.nextLine(); // Lee la entrada
                                if (pasajero.length() > 12 && pasajero.length() <= 0){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                                    System.out.println("ERROR: El string ingresado debe ser entre 1 a 12 caracteres de largo");
                                }
                                else{
                                    continueIdent = true;  // Detiene el ciclo.
                                    break;
                                }
                            } while (pasajero.length() > 12 && pasajero.length() <= 0 && continueIdent == false);
                            
                            int pasaIndex = encontrarIndice(parPasajerosId, pasajero); // Se usa para recorrer la lista de pasajeros
                            if (pasaIndex == parPasajerosId.length){
                                System.out.println("ERROR: Ese pasajero no existe");
                                return;
                            }                            
                            paviones[index][fila][seat] += pasajero; // Se añade al asiento el pasajero
                            parPasajeros[pasaIndex][2] = id; // Se añade el avión a la información del pasajero
                            parPasajeros[pasaIndex][3] = asiento; // Se añade el asiento a la información del pasajero
                            return;
                        }
                        else {  // Si no está disponible, da mensaje de error
                            System.out.println("ERROR: Ese asiento tiene pasajeros o está inactivo.");
                            return;
                        }
                    }
                }
            }
        }
        if (fila == paviones[index].length) {  // Si el asiento no existe, da mensaje de error.
            System.out.println("ERROR: Ese asiento no existe.");
        }
    }
    
    // Función que vacía un asiento
    // Entradas: Identificación del avión, identificación del asiento e identificación del pasajero.
    // Salidas: No hay
    static void vaciarAsiento(String[] pavionesId,String[][][] paviones,String[][] pPasajeros,String[] pPasajerosId){
        Scanner leerEntrada = new Scanner(System.in);
        String idAvion; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión (String de 5 caracteres exactos)");
            idAvion = leerEntrada.nextLine(); // Lee la entrada
            if (idAvion.length() != 5){ //  Si la entrada no es exactamente de 5 caracteres, envía error y la vuelve a pedir.
                System.out.println("ERROR: El string ingresado debe ser de exactamente 5 caracteres.");
            }
        } while (idAvion.length() != 5);
        
        int avion = encontrarIndice(pavionesId, idAvion); // devuelve el indice respectivo de la identificacion
        if (avion == pavionesId.length){ // Si el indice es la longitud maxima, no esta registrado.
            System.out.println("ERROR: El avión con la identificación ingresada no existe.");
            return;       
        }
        
        String asiento; // String para la identificación del asiento
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
            // Si no encuentra una identificación igual, envía un mensaje de error.
            System.out.println("ERROR: Pasajero no está registrado. No se puede eliminar del asiento.");
            return;                  
        }
        int fila;
        try{
            fila = (Integer.parseInt(asiento.substring(0,2)) - 1); // Se extrae la fila a partir del string del asiento.
            if (fila == -1 || fila >= paviones[avion].length){
                // Si la fila no está entre 0 y la cantidad de filas -1, envía un error.
                System.out.println("ERROR: Asiento ingresado no es válido.");
                return;
            }
        }
        catch(Exception e){
            System.out.println("ERROR: Asiento ingresado no es válido.");
            // Si se ingresa un asiento tal que no se pudo extraer un número, 
            return;
        }
        
        int columna; // Variable para almacenar como la "columna" de los asientos
        if (fila < 4 && asiento.charAt(2) > 'B'){
            // Si estamos en las primeras 4 filas y tenemos una E o F, le restamos 67 para pasarlas de su valor 
            // ASCII a su valor numérico.
            columna = (int)asiento.charAt(2) - 67;
            
        } else{
            // Restamos 65 para pasar del valor ASCII al valor numérico.
            columna = (int)asiento.charAt(2) - 65;
        }
        
        if ((columna > 5 && fila > 3) || (columna > 3 && fila < 4)) { // Si tenemos una columna mayor a la posible.
            System.out.println("ERROR: Asiento ingresado no es válido.");
            return;
        }
        
        if ((paviones[avion][fila][columna].substring(5, paviones[avion][fila][columna].length())).equals(idPas)){
            // Si la identificación de pasajeros al final del asiento es igual a la identificación dada por el usuario,
            paviones[avion][fila][columna] = paviones[avion][fila][columna].substring(0,5);
            // Borramos el final del string con la identificación para dejar solo el asiento
            pPasajeros[indexPas] = new String[] {pPasajeros[indexPas][0], pPasajeros[indexPas][1], "", ""};
            // Editamos el arreglo de pasajeros para borrar el avión y el asiento de la información de los pasajeros.
        } else {
            System.out.println("ERROR: La identificación del pasajero dada no coincide con la del asiento.");
            return;
        }
    }
    
    // Función que vacía el avión
    // Entradas: avión, confirmación del vaciar el avión
    // Salidas: No hay
    static void vaciarAvion(String[][][] paviones, String[] pavionesId, String[] pPasajerosId, String[][] pPasajeros){
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
            System.out.println("ERROR: Avión no está registrado. No se puede modificar.");
            return;                  
        }
        
        String confirmacion; // Almacena la confirmación de vaciar el avión
        System.out.println("Desea continuar con vaciar el avión?: S/N");
        confirmacion = leerEntrada.nextLine();
        if ("N".equals(confirmacion)){
            return;
        }
        
        int fila = 0;
        for (; fila < paviones[index].length; fila++){
            for (int pasajero = 0; pasajero < paviones[index][fila].length; pasajero++){
                 // Si el asiento está ocupado
                if (paviones[index][fila][pasajero].length() > 5){
                } else {  // Si no, busca otro que sí esté ocupado
                    continue;
                }
                int indexPas = encontrarIndice(pPasajerosId, paviones[index][fila][pasajero].substring(5)); // Almacena el índice del pasajero
                paviones[index][fila][pasajero] = paviones[index][fila][pasajero].substring(0,5);  // Elimina la información del pasajero en el asiento
                pPasajeros[indexPas] = new String[] {pPasajeros[indexPas][0], pPasajeros[indexPas][1], "", ""};  // Elimina el avión y asiento del pasajero
            }
        }
    }
    
    // Función que imprime el desglose de los asientos de un avión.
    // Entradas: Identificación del avión, arreglo de aviones, de identificaciones de aviones, de pasajeros y de identificaciones de pasajeros.
    // Salidas: No hay
    static void consultarAvion(String[][][] paviones, String[] pavionesId, String[][] pPasajeros, String[] pPasajerosId){
        Scanner leerEntrada = new Scanner(System.in);
        String idAvion; // String para la identificación del avión
        do{
            System.out.println("Ingrese el identificador del avión a consultar (String de 5 caracteres exactos)");
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
        
        System.out.println("CLASE EJECUTIVA");
            for (int fila = 0; fila < paviones[avion].length; fila++){
                // Se recorren las filas imprimiendo los asientos y pasajeros
                if (fila == 4){
                    System.out.println("CLASE ECONÓMICA");
                    // Al inicio de la fila 3, se imprime este mensaje.
                }
                for (int asiento = 0; asiento < paviones[avion][fila].length; asiento++){
                    if (asiento == 2 && fila < 4){
                        System.out.printf("%-50s       	", " ");
                        // en las filas menores a la 4, se dejan un espacio en blanco en el medio porque estas no tienen 2 asientos.
                    }
                    String nombre;
                    try{
                        String pasajeroId = paviones[avion][fila][asiento].substring(5);
                        // Intenta obtener la identificación de pasajeros.
                        int indexPas = encontrarIndice(pPasajerosId, pasajeroId);
                        // Encuentra el índice que corresponde a la identificación del pasajero
                        nombre = pPasajeros[indexPas][0] + " " + pPasajeros[indexPas][1];
                        // Extrae el nombre y el apellido.
                    }
                    catch (Exception e){
                        // Si no hay identificación, el nombre quda vació
                        nombre = "";
                    }
                    if (paviones[avion][fila][asiento].charAt(4) == 'I'){
                        nombre = "INACTIVO"; // Si el asiento está inactivo, el nombre queda como inactivo.
                    }
                    System.out.printf("%s-%s %-20s\t", paviones[avion][fila][asiento].substring(0,2),
                    paviones[avion][fila][asiento].charAt(2), nombre);
                    // Se usa un string con formatos para que todos los nombres ocupen el mismo espacio.
                }
                System.out.println("\n");
            }
    }
    
    // Función que busca un pasajero a partir de una identificación. Imprime el nombre, el avión y el asiento.
    // Entradas: Identificación del pasajero, y los arreglos con los pasajeros y sus identificaciones.
    static void buscarPasajero(String[][] pPasajeros,String[] pPasajerosId){
        String idPas; // String para la identificación del avión
        Scanner leerEntrada = new Scanner(System.in);
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
            System.out.println("Pasajero no está registrado. No se puede buscar");
            return;                  
        }
        // Se imprimen los datos del pasajero. Si no está asignado a un avión, se imprimen los espacios en blanco.
        System.out.println("Nombre: " + pPasajeros[indexPas][0] + " " + pPasajeros[indexPas][1]);
        System.out.println("Avión: " + pPasajeros[indexPas][2]);
        System.out.println("Asiento: " + pPasajeros[indexPas][3]);
    }
    
    // Función que despliega los asientos disponibles de cada clase y su cantidad en todos los aviones.
    // Entradas: los arreglos de aviones y pasajeros.
    // Salidas: Cantidad de asientos disponibles.
    static void consultarAsientosDisponibles(String[][][] paviones, String[] pavionesId, String[][] pPasajeros, String[] pPasajerosId){
        System.out.println("Consultar asientos disponibles");
        int contadorEcon;  // Almacena la cantidad de asientos disponibles en la clase económica
        int contadorBus;  // Almacena la cantidad de asientos disponibles en la clase ejecutiva.
        for (int avion = 0; avion < pavionesId.length; avion++){  // Recorre cada avión
            if (pavionesId[avion].length() == 0){  // Si el avión está vacío, se lo salta
                continue;
            }
            System.out.println("Avión: " + pavionesId[avion]);
            contadorEcon = 0; // Establece los contadores en 0
            contadorBus = 0; 
            for (int fila = 0; fila < paviones[avion].length; fila++){  // Recorre cada fila
                System.out.println("Fila: " + paviones[avion][fila][0].substring(0,2));
                for (int seat = 0; seat < paviones[avion][fila].length; seat++){ // Recorre cada asiento
                    if (paviones[avion][fila][seat].length() < 6 && paviones[avion][fila][seat].charAt(4) == 'A'){    // Si el asiento no tiene pasajero y está activo
                        System.out.println("Asiento: " + paviones[avion][fila][seat].substring(0,3));
                        if (paviones[avion][fila][seat].charAt(3) == 'J'){  // Si la clase es J, lo suma al contador de efecutivo
                            ++contadorBus;
                        } else { // Si no, al de economía.
                            ++contadorEcon;
                        }
                    }
                }
            }
            System.out.println("Cantidad de asientos disponibles en la clase Ejecutiva: " + contadorBus);
            System.out.println("Cantidad de asientos disponibles en la clase Económica: " + contadorEcon);
            System.out.println("Cantidad de asientos disponibles totales en el avión: " + (contadorBus + contadorEcon));
        }
    }
    
    // Función que registra pasajeros.
    // Entradas: Identificación de pasajero, nombre, apellido
    // Salidas: No hay
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
        // Es una matriz de tres dimensiones, donde la primera dimensión representa la cantidad de aviones en total,
        // la segunda es la cantidad de filas por avión, y la tercera dimensión representa los asientos de cada fila.
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
        do { // Se imprime el menú y se solicita la entrada al usuario.
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
                    asignarPasajeros(aviones, avionesId, pasajerosId, pasajeros);
                    break;
                case 5:
                    vaciarAsiento(avionesId, aviones, pasajeros, pasajerosId);
                    break;
                case 6:
                    vaciarAvion(aviones, avionesId, pasajerosId, pasajeros);
                    break;
                case 7:
                    consultarAvion(aviones, avionesId, pasajeros, pasajerosId);
                    break;
                case 8:
                    buscarPasajero(pasajeros, pasajerosId);
                    break;
                case 9:
                    consultarAsientosDisponibles(aviones, avionesId, pasajeros, pasajerosId);
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
