/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package poo.tareauno;

import java.util.Scanner;

/**
 *
 * @author dandi
 */
public class TareaUno {

    static void agregarAvion(){
    }
    
    static void modificarCapacidadAsientos(){
        
    }
    
    static void excluirAvion(){
        
    }
    
    static void asignarPasajeros(){
        
    }
    
    static void vaciarAsiento(){
        
    }
    
    static void vaciarAvion(){
    }
    
    static void consultarAvion(){
        
    }
    
    static void buscarPasajero(){
        
    }
    
    static void consultarAsientosDisponibles(){
        
    }
    
    public static void main(String[] args) {
        final short NUM_AVIONES = 20;
        final short FILAS = 25;
        
        String[][][] aviones = new String [NUM_AVIONES][FILAS][]; // matriz para almacenar todos los aviones
        for (int avion = 0; avion < aviones.length; avion++){ // se recorre la matriz para asignarle la longitud correcta a cada fila.
            for (int fila = 0; fila < aviones[avion].length; fila++){
                if (fila < 4){ // si el índice de la fila es menor que 4, la fila tiene solo 4 asientos
                    aviones[avion][fila] = new String[4];
                } else { // si es mayor o igual que 4, tiene 6 asientos.
                    aviones[avion][fila] = new String[6];
                }
            }
        }
        
        String[] avionesId = new String[NUM_AVIONES]; // Matriz donde se almacenan los identificadores de los aviones.
        // Tiene una relación 1 a 1 con los índices de la primera dimensión de la matriz aviones.
        
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
                    agregarAvion();
                    break;
                case 2:
                    modificarCapacidadAsientos();
                    break;
                case 3:
                    excluirAvion();
                    break;
                case 4:
                    asignarPasajeros();
                    break;
                case 5:
                    vaciarAsiento();
                    break;
                case 6:
                    vaciarAvion();
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
                default:
                    System.out.println("¡Hasta pronto!");
            } 
        } while (opcion != 0);
        leerDato.close();
    }
}
