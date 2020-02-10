package net.severo.taller.vistas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VistaVehiculo {

    public static boolean esEntero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static Date obtenerFecha(String cadena) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNac = null;
        try {
            fechaNac = sdf.parse(cadena);
            return fechaNac;
        } catch (ParseException ex) {
            // si no es fecha devolvemos un null
            return null;
        }
    }

    /*public String pedirMatricula() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Introduzca una matricula para el vehiculo:");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                //Salimos
                return null;
            }
            if (respuesta.length() >8) {
                System.err.println("La matricula del vehiculo ha de tener 4 números y 3 letras");
            }
            //Se establece que el codigo del vuelo ha de tener más de 3 carácter y menos de 8
        } while (respuesta.length() != 7);
        return respuesta;
    }*/   //------------------------------> x aki modificando el segundo if

    public static boolean esDecimal(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public int menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        String menu = "  1. Nuevo Vehiculo. \n 2. Ver Vehiculos \n 3. Modificar Vehiculo \n 4. Eliminar Vehiculo. \n 0. Salir \n ¿Que quiere hacer?";

        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println(menu);
            String entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(entrada);
            }
            if (opcion > 5 || opcion < 0) {
                System.out.println("Opción no valida, elija una opción del 1-4 o 0 para Salir");
                opcion = -1;
            }
        }
        return opcion;
    }

    public Integer pedirIdVuelo() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        Integer id;
        do {
            System.out.println("Introduzca el código identificativo del vehículo:");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                return null;
            }
            if (!esDecimal(respuesta)) {
                System.err.println("El id ha de ser un número decimal.");
            } else {
                //No se permiten vuelos gratuitos
                id = Integer.parseInt(respuesta);
                if (id < 0) {
                    System.err.println("El id ha de ser un numero positivo");
                } else {
                    return id;
                }
            }
        } while (true);
    }

    public Date pedirFechaMatriculacion() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        Date fecha;
        do {
            System.out.println("Introduzca la fecha de matriculación del vehiculo (dd/MM/yyyy)");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                //salimos
                return null;
            }
            fecha = obtenerFecha(respuesta);
            if (fecha == null) {
                System.err.println("El formato de fecha no es valido");
            }
        } while (fecha == null);
        return fecha;
    }

    public Boolean pedirEsElectrico() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("¿El vehiculo es electrico? (S/N)");
            String respuesta = sc.nextLine();
            if ("0".equals(respuesta)) {
                return null;
            }
            if (("S".equals(respuesta.toUpperCase()))) {
                return true;
            }
            if (("N".equals(respuesta.toUpperCase()))) {
                return false;
            }
            System.out.println("Por favor,introduzca S para una respuesta afirmativa y N para una negativa");
        } while (true);

    }
}
